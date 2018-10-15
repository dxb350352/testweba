package excel;

import jxl.Sheet;
import jxl.Workbook;

import java.io.File;


public class ExcelJXL {

    static String sourceFile = "E:\\idea_java_workspace\\testweba\\src\\main\\java\\excel\\话单.xls"; //源文件

    public static void main(String[] args) {

        try {

            Workbook book = Workbook.getWorkbook(new File(sourceFile));


            //0代表第一个工作表对象

            Sheet sheet = book.getSheet(0);

            int rows = sheet.getRows();

            int cols = sheet.getColumns();

            for (int z = 0; z < rows; z++) {
                //0代表列数，z代表行数
                for (int j = 0; j < cols; j++) {
                    System.out.print(sheet.getCell(j, z).getContents().trim() + ",");
                }
                System.out.println();
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
