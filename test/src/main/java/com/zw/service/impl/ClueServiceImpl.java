package com.zw.service.impl;

import com.zw.dao.*;
import com.zw.domain.*;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.tools.DateFormat;
import com.zw.gongong.tools.UuidTools;
import com.zw.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("clueservice")
public class ClueServiceImpl implements ClueService {

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Autowired
    private ContactsMapper contactsMapper;

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ClueMapper cluemapper;
    @Override
    public int insertClue(Clue clue) {
        return cluemapper.insertClue(clue);
    }

    @Override
    public List<Clue> selectClue(Map<String,Object> map) {
        return cluemapper.selectClue(map);
    }

    @Override
    public int selectCon(Map<String, Object> map) {
        return cluemapper.selectCon(map);
    }

    @Override
    public Clue selectClueDetail(String id) {
        return cluemapper.selectClueDetail(id);
    }

    @Override
    public void saveConvert(Map<String,Object> map) {
        String clueid=(String) map.get("clueId");
        Clue clue=cluemapper.selectClueById(clueid);
        Customer customer=new Customer();
        customer.setAddress(clue.getAddress());
        customer.setContact_summary(clue.getContact_summary());
        user user=(user)map.get(ReturnObject.SESSION_USER);
        customer.setCreate_by(user.getId());
        customer.setCreate_time(DateFormat.datefor(new Date()));
        customer.setDescription(clue.getDescription());
        customer.setId(UuidTools.returnUuid());
        customer.setName(clue.getCompany());
        customer.setNext_contact_time(clue.getNext_contact_time());
        customer.setOwner(user.getId());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
//        将线索中的客户信息转到客户表
        int custstu=customerMapper.saveCustmer(customer);

        Contacts contacts=new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContact_summary(clue.getContact_summary());
        contacts.setCreate_by(user.getId());
        contacts.setCreate_time(DateFormat.datefor(new Date()));
        contacts.setCustomer_id(customer.getId());
        contacts.setDescription(clue.getDescription());
        contacts.setEmail(clue.getEmail());
        contacts.setFullname(clue.getFullname());
        contacts.setId(UuidTools.returnUuid());
        contacts.setJob(clue.getJob());
        contacts.setMphone(clue.getMphone());
        contacts.setNext_contact_time(clue.getNext_contact_time());
        contacts.setOwner(user.getId());
        contacts.setSource(clue.getSource());
//        将线索中的联系人信息保存到联系人表中
        int contas=contactsMapper.saveClueContacts(contacts);

//        根据线索id查找线索备注
        List<ClueRemark> cluereList=clueRemarkMapper.selectClueRemarkByClueId(clueid);

//        判断备注列表是否为空,如果不为空将线索备注分别保存到用户备注表和联系人备注表中
        if (cluereList!=null&&cluereList.size()>0){
            CustomerRemark cstremark=null;
            ContactsRemark conremark=null;
            List<CustomerRemark> custrrlist=new ArrayList<>();
            List<ContactsRemark> conrelist=new ArrayList<>();
            for (ClueRemark cre:cluereList){
                cstremark=new CustomerRemark();
                cstremark.setCreate_by(cre.getCreate_by());
                cstremark.setCreate_time(cre.getCreate_time());
                cstremark.setId(UuidTools.returnUuid());
                cstremark.setCustomer_id(customer.getId());
                cstremark.setEdit_by(cre.getEdit_by());
                cstremark.setEdit_time(cre.getEdit_time());
                cstremark.setEdit_flag(cre.getEdit_flag());
                cstremark.setNote_content(cre.getNote_content());
                custrrlist.add(cstremark);

                conremark=new ContactsRemark();
                conremark.setCreate_by(cre.getCreate_by());
                conremark.setCreate_time(cre.getCreate_time());
                conremark.setId(UuidTools.returnUuid());
                conremark.setEdit_by(cre.getEdit_by());
                conremark.setEdit_flag(cre.getEdit_flag());
                conremark.setEdit_time(cre.getEdit_time());
                conremark.setContacts_id(contacts.getId());
                conremark.setNote_content(cre.getNote_content());
                conrelist.add(conremark);
            }
//            将转换的备注添加到客户备注表中
            int customerRemarkStu=customerRemarkMapper.insertCustomerRemark(custrrlist);
//            将线索中的备注添加到联系人备注表中
            int contatsRemarkStu=contactsRemarkMapper.insertContactsRemarkByList(conrelist);
        }

//        查找出所有跟线索有关联关系的市场活动
        List<ClueActivityRelation> clueActivityRelations=clueActivityRelationMapper.selectClueActivityRelationByClueId(clueid);
//        判断list是否为空，如果不为空，将市场活动关联关系关联到联系人关联市场活动表中
        if (clueActivityRelations!=null&&clueActivityRelations.size()>0){
            ContactsActivityRelation conar=null;
            List<ContactsActivityRelation> conarlist=new ArrayList<>();
            for (ClueActivityRelation re:clueActivityRelations
                 ) {
                conar=new ContactsActivityRelation();
                conar.setId(UuidTools.returnUuid());
                conar.setContacts_id(contacts.getId());
                conar.setActivity_id(re.getActivity_id());
                conarlist.add(conar);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByList(conarlist);
        }

//        如果需要创建交易，添加一条交易记录
        String isConvert=(String) map.get("isConvert");
        if ("true".equals(isConvert)){
            Tran tran=new Tran();
            String activityId=(String) map.get("activityId");
            tran.setActivity_id(activityId);
            tran.setContacts_id(contacts.getId());
            tran.setCreate_by(user.getId());
            tran.setCreate_time(DateFormat.datefor(new Date()));
            tran.setCustomer_id(customer.getId());
            tran.setExpected_date((String)map.get("expectedDate"));
            tran.setId(UuidTools.returnUuid());
            tran.setMoney((String)map.get("money"));
            tran.setName((String)map.get("name"));
            tran.setOwner(user.getId());
            tran.setStage((String)map.get("stage"));
            tranMapper.insertTran(tran);

            if (cluereList!=null&&cluereList.size()>0){
                TranRemark tranRemark=null;
                List<TranRemark> tranremarkList=new ArrayList<>();
                for (ClueRemark cr:cluereList
                     ) {
                    tranRemark=new TranRemark();
                    tranRemark.setCreate_by(cr.getCreate_by());
                    tranRemark.setCreate_time(cr.getCreate_time());
                    tranRemark.setEdit_by(cr.getEdit_by());
                    tranRemark.setEdit_time(cr.getEdit_time());
                    tranRemark.setEdit_flag(cr.getEdit_flag());
                    tranRemark.setId(UuidTools.returnUuid());
                    tranRemark.setNote_content(cr.getNote_content());
                    tranRemark.setTran_id(tran.getId());
                    tranremarkList.add(tranRemark);
                }
                tranRemarkMapper.insertTranRemarkByList(tranremarkList);
            }
        }
//        删除线索备注
        clueRemarkMapper.deleteClueRemarkByClueId(clueid);
//删除线索和市场活动关联关系
        clueActivityRelationMapper.deleteClueActivityByClueId(clueid);
//删除线索
        cluemapper.deleteClueById(clueid);
    }
}
