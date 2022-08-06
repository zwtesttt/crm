import com.zw.gongong.tools.GetCellValue;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream("C:\\Users\\86147\\Desktop\\java\\activity.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int k = 0; k < row.getLastCellNum(); k++) {//row.getLastCellNum()是总列数+1，所以不需要最后一列
                cell = row.getCell(k);
                System.out.print(GetCellValue.getCellValue(cell)+" ");
            }
            System.out.println("");
        }

    }

}