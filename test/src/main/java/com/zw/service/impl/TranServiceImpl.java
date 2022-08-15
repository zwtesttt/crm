package com.zw.service.impl;

import com.zw.dao.CustomerMapper;
import com.zw.dao.TranMapper;
import com.zw.domain.Customer;
import com.zw.domain.Tran;
import com.zw.domain.user;
import com.zw.gongong.changliang.ReturnObject;
import com.zw.gongong.tools.DateFormat;
import com.zw.gongong.tools.UuidTools;
import com.zw.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("tranService")
public class TranServiceImpl implements TranService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TranMapper tranMapper;

    @Override
    public void saveTran(Map<String, Object> map) {
        user u1=(user)map.get(ReturnObject.SESSION_USER);
        String customerName=(String)map.get("customerName");
        Customer customer=customerMapper.selectCustomer(customerName);

        if (customer==null){
            customer=new Customer();
            customer.setOwner(u1.getId());
            customer.setName(customerName);
            customer.setId(UuidTools.returnUuid());
            customer.setCreate_time(DateFormat.datefor(new Date()));
            customer.setCreate_by(u1.getId());
            customerMapper.saveCustmer(customer);
        }
        Tran tran= new Tran();
        tran.setStage((String)map.get("stage"));
        tran.setOwner((String)map.get("owner"));
        tran.setNext_contact_time((String)map.get("next_contact_time"));
        tran.setName((String)map.get("name"));
        tran.setMoney((String)map.get("money"));
        tran.setId(UuidTools.returnUuid());
        tran.setExpected_date((String)map.get("expected_date"));
        tran.setCustomer_id(customer.getId());
        tran.setCreate_by(u1.getId());
        tran.setCreate_time(DateFormat.datefor(new Date()));
        tran.setContact_summary((String)map.get("contact_summary"));
        tran.setContacts_id((String)map.get("contacts_id"));
        tran.setActivity_id((String)map.get("activity_id"));
        tran.setDescription((String)map.get("description"));
        tran.setSource((String)map.get("source"));
        tran.setType((String)map.get("type"));
        tranMapper.insertTran(tran);
    }

    @Override
    public List<Tran> queryAllTran() {
        return tranMapper.queryAllTran();
    }
}
