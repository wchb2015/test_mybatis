import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wangchongbei on 16-1-6.
 */
public class TestPOI {
    @org.junit.Test
    public void fileTest() {

        File dir = new File("/home/wangchongbei/下载/test.xlsx");
        File dir2 = new File("/home/wangchongbei/下载");
        if (dir2.isDirectory()) {
            File[] files = dir2.listFiles();
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    //写EXCEL
    @org.junit.Test
    public void writeExcel() {
        String[] title = {"id", "name", "sex"};

        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel工作薄
        HSSFSheet sheet = workbook.createSheet();//创建一个工作表Sheet
        HSSFRow row = sheet.createRow(0);//创建第一行

        //写入标题
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加数据
        for (int i = 1; i <= 10; i++) {
            HSSFRow nextRow = sheet.createRow(i);
            HSSFCell cell2 = nextRow.createCell(0);
            cell2.setCellValue("a" + i);
            cell2 = nextRow.createCell(1);
            cell2.setCellValue("user" + i);
            cell2 = nextRow.createCell(2);
            cell2.setCellValue("男" + i);
        }

        //创建Excel文件,并将Excel内容存入文件
        File file = new File("e:/poi_test.xls");
        try {
            file.createNewFile();
            FileOutputStream fos = FileUtils.openOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readExcel() {
        File file = new File("/home/wangchongbei/下载/test.xlsx");
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));

            XSSFSheet sheet = workbook.getSheetAt(0);//读取第一Sheet
            int firstRowNum = 0;
            int lastRowNum = sheet.getLastRowNum();
            //循环读取每一行
            for (int i = 0; i <= lastRowNum; i++) {
                XSSFRow row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    XSSFCell cell = row.getCell(j);
//                  String value = cell.getStringCellValue();cell.getCellType();
                    String value = cell.getRawValue();
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
