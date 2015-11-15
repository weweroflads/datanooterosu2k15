import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public final class Employer {

    private Employer() {
    }

    public static Job[] employer() throws IOException {
        FileInputStream fis = new FileInputStream("TDA Jobs Data Test.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet spreadsheet = workbook.getSheetAt(0);
        HSSFRow row;

        HSSFCell cell;

        Iterator<Row> rowIterator = spreadsheet.iterator();

        int i = 0;
        row = (HSSFRow) rowIterator.next(); //skip row 1 (title row)

        Job[] job = new Job[300];

        String[] column = new String[5];

        while (i < 262) {
            job[i] = new Job();
            row = (HSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            int j = 0;

            cell = (HSSFCell) cellIterator.next();
            column[j] = cell.getStringCellValue();
            j++;

            cell = (HSSFCell) cellIterator.next();
            column[j] = cell.getStringCellValue();
            j++;

            cell = (HSSFCell) cellIterator.next();
            column[j] = cell.getStringCellValue();
            j++;

            cell = (HSSFCell) cellIterator.next();
            column[j] = cell.getStringCellValue();
            j++;

            cell = (HSSFCell) cellIterator.next();
            column[j] = cell.getStringCellValue();

            job[i].populateMembers(column);
            i++;

        }
        return job;
    }
}
