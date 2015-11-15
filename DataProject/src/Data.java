import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class Data {

    private Data() {

    }

    private static final String SEPARATORS = " ";
    static XSSFRow row;

    private static boolean isSep(char c) {
        return SEPARATORS.indexOf(c) != -1;
    }

    public static String nextWordOrSeparator(String text, int startIndex) {
        boolean firstIsSep = isSep(text.charAt(startIndex));
        int checkIndex = startIndex + 1;
        while (checkIndex < text.length()
                && firstIsSep == isSep(text.charAt(checkIndex))) {
            checkIndex++;
        }
        return text.substring(startIndex, checkIndex);
    }

    public static Student[] processStudentData() throws IOException {
        FileInputStream fis = new FileInputStream(
                new File("TDA Students Test.xlsx")); // read in spreadsheet
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet spreadsheet = workbook.getSheetAt(0);

        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = spreadsheet
                .iterator(); //make iterator

        int i = 0;
        Student[] studenterinos = new Student[100]; //make array
        int j = 0;
        row = (XSSFRow) rowIterator.next();

        //set fields of each student
        while (j < 91) {

            studenterinos[j] = new Student();
            int mnth = 0, yr = 0, lngth = 0, columnIndex = 0;
            row = (XSSFRow) rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            Cell cell = cellIterator.next();
            columnIndex = cell.getColumnIndex();
            studenterinos[j].setMajor(cell.getStringCellValue());
            if (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setMinor("");
                    columnIndex += 1;
                } else {
                    studenterinos[j].setMinor(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }

            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setGradMonth(0);
                    studenterinos[j].setGradYear(0);
                    columnIndex += 1;
                } else {
                    Date yey = cell.getDateCellValue();
                    String yey2 = yey.toString();
                    String month = nextWordOrSeparator(yey2, 5);
                    String year = yey2.substring(yey2.length() - 4,
                            yey2.length());
                    yr = Integer.parseInt(year);
                    if (month.equals("December") || month.equals("Dec")) {
                        mnth = 12;
                    } else {
                        mnth = 5;
                    }
                    lngth = yey2.length();
                    studenterinos[j].setGradMonth(mnth);
                    studenterinos[j].setGradYear(yr);
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }
            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setDegrees("");
                    columnIndex += 1;
                } else {
                    studenterinos[j].setDegrees(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }
            if (cellIterator.hasNext()) {
                for (i = 0; i < 2; i++)

                {
                    String title = new String();
                    String description = new String();
                    int a = 0;
                    if (cellIterator.hasNext()) {
                        a += 1;
                        if (columnIndex + 1 != cell.getColumnIndex()) {
                            studenterinos[j].setJobEntry("", "");
                            if (a <= 2) {
                                columnIndex += 2;
                            }
                        } else {
                            title = cell.getStringCellValue();

                            cell = cellIterator.next();

                            description = cell.getStringCellValue();
                            columnIndex = cell.getColumnIndex();

                            studenterinos[j].setJobEntry(title, description);
                        }
                    }
                }
            }
            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setComputerSkills("");
                    columnIndex += 1;
                } else {
                    studenterinos[j]
                            .setComputerSkills(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }
            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setLanguageSkills("");
                    columnIndex += 1;
                } else {
                    studenterinos[j]
                            .setLanguageSkills(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }
            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setPersonalSkills("");
                    columnIndex += 1;
                } else {
                    studenterinos[j]
                            .setPersonalSkills(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }

            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setCourses("");
                    columnIndex += 1;
                } else {
                    studenterinos[j].setCourses(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }

            if (cellIterator.hasNext()) {
                if (columnIndex + 1 != cell.getColumnIndex()) {
                    studenterinos[j].setProjects("");
                    columnIndex += 1;
                } else {
                    studenterinos[j].setProjects(cell.getStringCellValue());
                    columnIndex = cell.getColumnIndex();
                    cell = cellIterator.next();
                }
            }

            j++;
        }
        fis.close();
        return studenterinos;
    }
}
