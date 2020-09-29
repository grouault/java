package fr.exagone.files.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import fr.exagone.files.xls.bean.EventXlsLine;
import fr.exagone.files.xls.bean.XlsLine;

public class XlsParsingEventExemple {

	
	private static final String FILE_PATH = "C:/devs/workspaces/indigo/utils/utils-project/datas/event/";
	
//	private static final String FIlE_NAME = "import-one-event.xls";
//	private static final String FIlE_NAME = "import-one-event-1.xls";
	private static final String FIlE_NAME = "import-event-no-localisation.xls";
	
	// private static final String FIlE_NAME = "import-user-with-space-2.xls";
	
	// private static final String FIlE_NAME = "import-user.xlsx";
	// private static final String FIlE_NAME = "import-user-colonne-supplementaire.xlsx";
	private static final String FILE_DEST = "filedest.xls";
    private static final String EXPECTED_COLUMNS = "titre#categorie#type#localisation#dateDebut#dateFin#thematique#publicCible#urlLien#description#evtNational#urlIllusatration#credit#tarifs#partenaires#contacts#datePublication";
    private static final String MANDATORY_COLUMNS_EVENT_XLS = "titre#categorie#type#localisation#dateDeb#dateFin#thematique#publicCible#urlLien";
	
    public static void main(String[] args) {
		
  	  List<XlsLine> eventList = new ArrayList<XlsLine>();
      try {
         
    	  String strFile = FILE_PATH + FIlE_NAME;
          File file = new File(strFile);

          FileInputStream fileStream = new FileInputStream(file);
          //Get the workbook instance for XLS file
          HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
          // XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
              //Get first sheet from the workbook
          HSSFSheet sheet = workbook.getSheetAt(0); 
          // XSSFSheet sheet = workbook.getSheetAt(0);    
          getAndPrintEvent(sheet);
          
          // load event objects from excel file
          // Now empList will be holding all employee objects*
          List<String> expectedColumns = Arrays.asList(EXPECTED_COLUMNS.split("#"));
          XlsLine xslLine = new EventXlsLine();
          loadEventToList(sheet, eventList, xslLine, expectedColumns);
          
          // now print employee objects from list using Enhanced For loop
          System.out.println("-------------");
          System.out.println("-- PARSING --");
          System.out.println("-------------");
          for(XlsLine eventXlsLine : eventList){
        	  System.out.println( "Line = " + ((EventXlsLine)eventXlsLine).getLineNumber() );
              System.out.println("Titre =" + ((EventXlsLine)eventXlsLine).getTitre() + "\t");
              System.out.println("Categorie =" +  ((EventXlsLine)eventXlsLine).getCategorie() + "\t");
              System.out.println("Type =" +  ((EventXlsLine)eventXlsLine).getType() + "\t");
              System.out.println("Localisation =" +  ((EventXlsLine)eventXlsLine).getLocalisation() + "\n");
              
              System.out.println("Date debut =" +  ((EventXlsLine)eventXlsLine).getDateDebut() + "\n");
              System.out.println("Date fin =" +  ((EventXlsLine)eventXlsLine).getDateFin() + "\n");
              System.out.println("Thematique =" +  ((EventXlsLine)eventXlsLine).getThematique() + "\n");
              System.out.println("Public cible =" +  ((EventXlsLine)eventXlsLine).getPublicCible() + "\n");
              System.out.println("Url Lien =" +  ((EventXlsLine)eventXlsLine).getUrlLien() + "\n");
              System.out.println("Description =" +  ((EventXlsLine)eventXlsLine).getDescription() + "\n");
              System.out.println("Evnt national =" +  ((EventXlsLine)eventXlsLine).getEvtNational() + "\n");
              System.out.println("Url illustration =" +  ((EventXlsLine)eventXlsLine).getUrlIllusatration() + "\n");
              System.out.println("Credit =" +  ((EventXlsLine)eventXlsLine).getCredit() + "\n");
              System.out.println("Tarif =" +  ((EventXlsLine)eventXlsLine).getTarifs() + "\n");
              System.out.println("Partenaire =" +  ((EventXlsLine)eventXlsLine).getPartenaires() + "\n");
              System.out.println("Date pubication =" +  ((EventXlsLine)eventXlsLine).getDatePublication() + "\n");
             
          }
          
      } catch(FileNotFoundException e) {
    	  e.printStackTrace();
      } catch(IOException e) {
        System.out.println(e);
      } catch (SecurityException e) {
          e.printStackTrace();
      } catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
		
	} 
    
    
    private static void loadEventToList(HSSFSheet sheet,
            List<XlsLine> empList, XlsLine xslLine, List<String> expectedColumns) throws InstantiationException,
            IllegalAccessException, SecurityException, NoSuchFieldException {
       
        Row column = sheet.getRow(0);
        String columnNames[] = getColumnNames(column);
           
        int rowOne = 0;
        int cpt = 0;
        
        for(Row row : sheet) {
        	cpt ++;
	        XlsLine newRecord = xslLine.getClass().newInstance();
	        newRecord.setLineNumber(cpt);
        	if (rowOne > 0) {
    	        int i=0;
        		for(int cn=0; cn<row.getLastCellNum(); cn++) {
	    	       // If the cell is missing from the file, generate a blank one
	    	       // (Works by specifying a MissingCellPolicy)
	    	       Cell cell = row.getCell(cn, Row.CREATE_NULL_AS_BLANK);
	    	       System.out.println("CELL: " + cn + " --> " + cell.toString()); // Print the cell for debugging
                   String columnName = columnNames[i++];
                   if (expectedColumns.contains(columnName)) {
                       Field f1 = xslLine.getClass().getDeclaredField(columnName.trim());
                       f1.setAccessible(true);
                       f1.set(newRecord, getTypeValue(f1.getType(),cell));                    	   
                   }
    	    	}
        		empList.add(newRecord);   
        	}
        	rowOne ++;
       }
        
       /*
        * 
        * Iterator ne prend pas en compte les cellules vides.
        * http://stackoverflow.com/questions/4929646/how-to-get-an-excel-blank-cell-value-in-apache-poi
        * 
        Iterator<Row> rowIterator = sheet.iterator();
        while(rowIterator.hasNext()){
     	   cpt ++;
            Row row = rowIterator.next();
            if (!isRowEmpty(row)) {
 	           Iterator<Cell> cellIterator = row.cellIterator();
 	          
 	           XlsLine newRecord = xslLine.getClass().newInstance();
 	           newRecord.setLineNumber(cpt);
 	           
 	           // Skip First row coz that is column names
 	           if(rowOne >0){          
 	               int i=0;
 	               while (cellIterator.hasNext()) {
 	                   Cell cell = cellIterator.next();
 	                   String columnName = columnNames[i++];
 	                   if (expectedColumns.contains(columnName)) {
 	                       Field f1 = xslLine.getClass().getDeclaredField(columnName.trim());
 	                       f1.setAccessible(true);
 	                       f1.set(newRecord, getTypeValue(f1.getType(),cell));                    	   
 	                   }
 	                  
 	               }
 	               empList.add(newRecord);   
 	           }
 	           rowOne++;
            }
        }
        */
       
    } 
    
    private static boolean isRowEmpty(Row row) {
	    for (int c = row.getFirstCellNum(); c <= row.getLastCellNum(); c++) {
	        Cell cell = row.getCell(c);
	        if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
	            return false;
	    }
	    return true;
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
    
    

    private static void getAndPrintEvent(HSSFSheet sheet) {
        System.out.println(" -- Event Report -------");
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
        
          
}
