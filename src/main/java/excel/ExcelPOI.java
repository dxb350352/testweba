package excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


//

public class ExcelPOI {


    /**
     * @param args
     * @throws IOException java xuzhe.ExcelPOI
     */

    @SuppressWarnings("deprecation")

    public static void main(String[] args) throws IOException {

        // TODO Auto-generatedmethod stub

//        ExcelPOI.POICreateExcel();

        ExcelPOI.POIReadExcel();


    }


    public static void POICreateExcel() throws IOException {

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("new sheet");

        //0行

        HSSFRow row = sheet.createRow((short) 0);

        //1列

        row.createCell((short) 1).setCellValue("HelloWorld");


        FileOutputStream fileOut = new FileOutputStream("c:\\workbook.xls");

        wb.write(fileOut);

        fileOut.close();

    }


    private static HSSFWorkbook readFile(String filename) throws IOException {

        return new HSSFWorkbook(new FileInputStream(filename));

    }


    public static void POIReadExcel() throws IOException {

        String fileName = "E:\\idea_java_workspace\\testweba\\src\\main\\java\\excel\\话单.xls";

        HSSFWorkbook wb = ExcelPOI.readFile(fileName);


        System.out.println("Datadump:\n");


        for (int k = 0; k < wb.getNumberOfSheets(); k++) {

            HSSFSheet sheet = wb.getSheetAt(k);

            int rows = sheet.getPhysicalNumberOfRows();

            System.out.println("Sheet" + k + " \"" + wb.getSheetName(k) + "\" has" + rows

                    + "row(s).");

            for (int r = 0; r < rows; r++) {

                HSSFRow row = sheet.getRow(r);

                if (row == null) {

                    continue;

                }


                int cells = row.getPhysicalNumberOfCells();

                System.out.println("\nROW" + row.getRowNum() + " has " + cells

                        + "cell(s).");

                for (int c = 0; c < cells; c++) {

                    HSSFCell cell = row.getCell(c);

                    String value = null;


                    switch (cell.getCellType()) {


                        case HSSFCell.CELL_TYPE_FORMULA:

                            value = "FORMULA value=" + cell.getCellFormula();

                            break;


                        case HSSFCell.CELL_TYPE_NUMERIC:

                            value = "NUMERIC value=" + cell.getNumericCellValue();

                            break;


                        case HSSFCell.CELL_TYPE_STRING:

                            value = "STRING value=" + cell.getStringCellValue();

                            break;


                        default:

                    }

                    System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="

                            + value);

                }

            }

        }

    }

}

