package fr.exagone.files.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fr.exagone.files.xls.bean.Employee;

public class XlsParsingExample {

	
	private static final String FILE_PATH = "C:/devs/workspaces/indigo/utils/utils-project/datas/xls/";
	private static final String FIlE_NAME = "employee.xlsx";
	private static final String FILE_DEST = "filedest.xls";
	
	public static void main(String[] args) {

	  List<Employee> empList = new ArrayList<Employee>();
      try {
         
    	  String strFile = FILE_PATH + FIlE_NAME;
          File file = new File(strFile);
       
          FileInputStream fileStream = new FileInputStream(file);
          //Get the workbook instance for XLS file
          XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
              //Get first sheet from the workbook
          XSSFSheet sheet = workbook.getSheetAt(0);    
         
          getAndPrintEmployees(sheet);
         
          // load employee objects from excel file
          // Now empList will be holding all employee objects
          Employee employee = new Employee();
          loadEmployeesToList(sheet,empList,employee);
         
          // now print employee objects from list using Enhanced For loop
          System.out.println(" Id \t Name \t Salary \n");
          for(Employee emp:empList){
              System.out.print(emp.getId()+"\t");
              System.out.print(emp.getName()+"\t");
              System.out.println(emp.getSalary());
          }
 
          // Method to add employee objects to excel sheet
          addEmployeeToExcel(sheet,empList);
         
          // Write newly modified workbook to a file.
          FileOutputStream fileOut = new FileOutputStream(FILE_PATH + FILE_DEST);
          workbook.write(fileOut);
          fileOut.close();
      }
      catch(FileNotFoundException e)
      {
       e.printStackTrace();
      }
      catch(IOException e)
      {
        System.out.println(e);
      } catch (SecurityException e) {
          e.printStackTrace();
      } catch (InstantiationException e) {
          e.printStackTrace();
      } catch (IllegalAccessException e) {
          e.printStackTrace();
      } catch (NoSuchFieldException e) {
          e.printStackTrace();
      } 
		
	}
	
   private static void getAndPrintEmployees(XSSFSheet sheet) {
        System.out.println(" -- Employee Report -------");
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                }
            }
            System.out.println();
        }
    }
   
     
   private static void loadEmployeesToList(XSSFSheet sheet,
           List<Employee> empList, Employee employee) throws InstantiationException,
           IllegalAccessException, SecurityException, NoSuchFieldException {
      
       Row column = sheet.getRow(0);
       String columnNames[] = getColumnNames(column);
      
       Iterator<Row> rowIterator = sheet.iterator();
      
       int rowOne = 0;

       while(rowIterator.hasNext()){
           Row row = rowIterator.next();
           Iterator<Cell> cellIterator = row.cellIterator();
          
           Employee newRecord = employee.getClass().newInstance();
          
           // Skip First row coz that is column names
           if(rowOne >0){          
               int i=0;
               while (cellIterator.hasNext()) {
                   Cell cell = cellIterator.next();
                   String columnName = columnNames[i++];
                   Field f1 = employee.getClass().getDeclaredField(columnName.trim());
                   f1.setAccessible(true);
                   f1.set(newRecord, getTypeValue(f1.getType(),cell));
                      
               }
               empList.add(newRecord);   
           }
           rowOne++;
       }
      
   }  
	
   private static Object getTypeValue(Class<?> type, Cell cell) {
       Object typedValue = null;
       if(type == int.class){
           typedValue = (int) cell.getNumericCellValue();
       } else if(type == double.class){
           typedValue = cell.getNumericCellValue();
       } else if(type == boolean.class){
           typedValue = cell.getBooleanCellValue();
       } else if(type == String.class){
           typedValue = cell.getStringCellValue();
       }
       return typedValue;
   }  
   
   
   private static String[] getColumnNames(Row column) {
       String columns[] = new String[column.getPhysicalNumberOfCells()];
       Iterator<Cell> cellIterator = column.cellIterator();
       int i = 0;
       while (cellIterator.hasNext()) {
           Cell cell = cellIterator.next();
           columns[i++] = cell.getStringCellValue();
       }       
      
       return columns;
   }
   
   private static void addEmployeeToExcel(XSSFSheet sheet,
           List<Employee> empList) {
       int rowCount = sheet.getPhysicalNumberOfRows();
      
       for(Employee emp:empList){
           Row newRow = sheet.createRow(++rowCount);
          
           Cell idCell = newRow.createCell(0);
           idCell.setCellValue(emp.getId());

           Cell nameCell = newRow.createCell(1);
           nameCell.setCellValue(emp.getName());

           Cell salaryCell = newRow.createCell(2);
           salaryCell.setCellValue(emp.getSalary());
       }
   }       
   
   
	  
}
