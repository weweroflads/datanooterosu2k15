import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data {

    private Data() {

    }

    static XSSFRow row;
    static XSSFRow row2;

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(
                new File("TDA Students Test.xlsx")); // read in spreadsheet
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = spreadsheet
                .iterator(); //make iterator
        int i = 0; //make counter for size of student array
        row = (XSSFRow) rowIterator.next(); //skip row 1 (title row)
        while (rowIterator.hasNext()) {
            i++;
            row = (XSSFRow) rowIterator.next();
        } //find size of array
        Student[] studenterinos = new Student[i]; //make array
        int j = 0;
        row2 = (XSSFRow) rowIterator.next(); //skip row 1

        //set fields of each student
        while (rowIterator.hasNext()) {
            int mnth = 0, yr = 0, lngth = 0, k = 0;
            row2 = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            studenterinos[j].setMajor(cell.getStringCellValue());

            cell = cellIterator.next();
            studenterinos[j].setMinor(cell.getStringCellValue());

            cell = cellIterator.next();
            String yey = cell.getStringCellValue();
            lngth = yey.length();
            if (lngth == 6) {
                mnth = Integer.parseInt(yey.substring(0, 1));
                yr = Integer.parseInt(yey.substring(2, 5));
            } else {
                mnth = Integer.parseInt(yey.substring(0, 2));
                yr = Integer.parseInt(yey.substring(3, 6));
            }
            studenterinos[j].setGradMonth(mnth);
            studenterinos[j].setGradYear(yr);

            cell = cellIterator.next();
            studenterinos[j].setDegrees(cell.getStringCellValue());

            cell = cellIterator.next();
            studenterinos[j].setComputerSkills(cell.getStringCellValue());

            cell = cellIterator.next();
            studenterinos[j].setLanguageSkills(cell.getStringCellValue());

            cell = cellIterator.next();
            studenterinos[j].setPersonalSkills(cell.getStringCellValue());

            cell = cellIterator.next();
            studenterinos[j].setCourses(cell.getStringCellValue());

            cell = cellIterator.next();
            studenterinos[j].setProjects(cell.getStringCellValue());

            for (i = 0; i < 3; i++) {
                cell = cellIterator.next();
                String title = new String();
                title = cell.getStringCellValue();

                cell = cellIterator.next();
                String description = new String();
                description = cell.getStringCellValue();

                studenterinos[j].setJobEntry(title, description);
            }

            j++;
        }
        workbook.close();

    }
}
