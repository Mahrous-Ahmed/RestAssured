package Utilites;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UtilityClass {

    static FileInputStream FIS = null;

    public static FileInputStream getFileInputStream(){
        String filepath = System.getProperty("user.dir")+"/src/main/resources/Book.xlsx";
        File srcFile = new File(filepath);
        try {
            FIS = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Error ,occured : " + e.getMessage());
        }

        return FIS;
    }

    public static Object[][] getExcelData() {

        try {

            FIS   = getFileInputStream();
            XSSFWorkbook wb = new XSSFWorkbook(FIS);
            XSSFSheet sheet = wb.getSheetAt(0);
            int TotalNumberOfRows = sheet.getLastRowNum();

            Object[][] books = new String[TotalNumberOfRows-1][4];

            for(int i = 1 ; i < TotalNumberOfRows; i++ ){
                XSSFRow row = sheet.getRow(i);
                for(int j = 0 ; j < 4 ; j++)
                {
                    books[i-1][j]  = row.getCell(j).toString().trim();
                }

            }
            wb.close();
            return books;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) throws IOException {
        getExcelData();
    }




}
