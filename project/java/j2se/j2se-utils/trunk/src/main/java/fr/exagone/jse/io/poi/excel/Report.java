package fr.exagone.jse.io.poi.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class Report {

	private static String FILE_PATH = "d:/devs/tp-exagone-jse/io/poi/excel/";
	private static String SHEET_NAME = "Report";
	
	private static String COL_MAIN_TITLE_CENTER  = "TABLEAU DE BORD MAINTENANCE DES APPLIATIONS METIERS";
	private static String COL_MAIN_TITLE_LEFT  = "DSI/APPLICATION";
	private static String COL_MAIN_TITLE_RIGHT = "Mensuel [août2009]";
	private static String COL_APPLICATION = "Application";
	private static String COL_DIR_CLIENTE  = "DIRECTION Cliente";
	private static String COL_CRITICITE = "Criticité (1)";
	private static String COL_TYPE_APPLICATION = "Type appli (2)";
	private static String COL_NB_USER = "NB Utilisateurs (3)";
	private static String COL_REPONSABLE = "Responsable";
	private static String COL_CONTRAT = "Contrat (4)";
	private static String COL_INCIDENTS = "Incidents";
	private static String COL_CHG_JOURS = "Charge Jours";
	private static String COL_CHG_INTRA = "intra";
	private static String COL_CHG_EXTRA = "extra";
	
	private static String COL_MC = "Maintenance corrective";
	private static String COL_NB_BUGS = "nb cas logués";
	private static String COL_ME = "Maintenance évolutive (5)";
	private static String COL_NB_DEMANDE = "nb demandes";
	private static String COL_DE = "Développement d'états";
	private static String COL_NB_ETATS = "nb états";
	private static String COL_REC = "Recette";
	private static String COL_ASS_UTI = "Assistance utilisateurs";
	private static String COL_CHG_DEP = "Charge DEP";
	private static String COL_CHG_DEPSR = "Charge DEPSR";
	private static String COL_CHG_TMA = "Charge TMA";
	private static String COL_ETAT = "Etat";
	private static String COL_COMMENTS = "Commentaires";
	
	
	public static void main(String[] args) {
		
		//création du classeur.
		Workbook wb = new HSSFWorkbook();
		
		//création de la feuille associé.
		Sheet sheet = wb.createSheet(SHEET_NAME);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);
        //sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        sheet.setAutobreaks(true);//pour printer sur une seule page
        
        //récupération des styles
        Map<String, CellStyle> styles = createStyles(wb);
	
        //
        //première ligne
        //
        Row titleRow = sheet.createRow(0);
        titleRow.setHeightInPoints(35);
        
        Cell titleCellLeft = titleRow.createCell(0);
        titleCellLeft.setCellValue(COL_MAIN_TITLE_LEFT);
        titleCellLeft.setCellStyle(styles.get("mainTitle"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$D$1"));
        
        Cell titleCellCenter = titleRow.createCell(4);
        titleCellCenter.setCellValue(COL_MAIN_TITLE_CENTER);
        titleCellCenter.setCellStyle(styles.get("mainTitle"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$E$1:$V$1"));
        
        Cell titleCellRight = titleRow.createCell(22, Cell.CELL_TYPE_STRING);
        titleCellRight.setCellValue(COL_MAIN_TITLE_RIGHT);
        titleCellRight.setCellStyle(styles.get("mainTitle"));
        sheet.addMergedRegion(CellRangeAddress.valueOf("$W$1:$Z$1"));
        
        //
		//création de la deuxième ligne
        //
		Row headerReportRow1 = sheet.createRow(1);
		headerReportRow1.setHeightInPoints(25);
		Row headerReportRow2 = sheet.createRow(2);
		headerReportRow2.setHeightInPoints(25);
		Row headerReportRow3 = sheet.createRow(3);
		headerReportRow3.setHeightInPoints(25);
		Row headerReportRow4 = sheet.createRow(4);
		headerReportRow4.setHeightInPoints(25);		
		//Paramétrage par défaut des bordures d'en-tête.
        for (int i = 0; i < 26; i++) {
            Cell cell = headerReportRow1.createCell(i);
            cell.setCellStyle(createBorderedStyle(wb));
        }
        for (int i = 0; i < 26; i++) {
            Cell cell = headerReportRow2.createCell(i);
            cell.setCellStyle(createBorderedStyle(wb));
        }                
        for (int i = 0; i < 26; i++) {
            Cell cell = headerReportRow3.createCell(i);
            cell.setCellStyle(createBorderedStyle(wb));
        }  
		
		
		//colonneA: Application
		Cell headerReportCellA = headerReportRow1.createCell(0, Cell.CELL_TYPE_STRING);
		headerReportCellA.setCellValue(COL_APPLICATION);
		headerReportCellA.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$A$4"));	
		
		//colonneB
		Cell headerReportCellB = headerReportRow1.createCell(1, Cell.CELL_TYPE_STRING);
		headerReportCellB.setCellValue(COL_DIR_CLIENTE);
		headerReportCellB.setCellStyle(styles.get("headerVertical"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$B$2:$B$4"));
		
		//colonneC
		Cell headerReportCellC = headerReportRow1.createCell(2,Cell.CELL_TYPE_STRING);
		headerReportCellC.setCellValue(COL_CRITICITE);
		headerReportCellC.setCellStyle(styles.get("headerVertical"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$C$2:$C$4"));
		
		//colonneD
		Cell headerReportCellD = headerReportRow1.createCell(3,Cell.CELL_TYPE_STRING);
		headerReportCellD.setCellValue(COL_TYPE_APPLICATION);
		headerReportCellD.setCellStyle(styles.get("headerVertical"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$D$2:$D$4"));		

		//colonneE
		Cell headerReportCellE = headerReportRow1.createCell(4,Cell.CELL_TYPE_STRING);
		headerReportCellE.setCellValue(COL_NB_USER);
		headerReportCellE.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$E$2:$E$4"));
		
		//colonneF
		Cell headerReportCellF = headerReportRow1.createCell(5, Cell.CELL_TYPE_STRING);
		headerReportCellF.setCellValue(COL_REPONSABLE);
		headerReportCellF.setCellStyle(styles.get("headerVertical"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$F$2:$F$4"));
		
		//colonneG
		Cell headerReportCellG = headerReportRow1.createCell(6, Cell.CELL_TYPE_STRING);
		headerReportCellG.setCellValue(COL_CONTRAT);
		headerReportCellG.setCellStyle(styles.get("headerVertical"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$G$2:$G$4"));
		
		//colonneH
		Cell headerReportCellH = headerReportRow1.createCell(7, Cell.CELL_TYPE_STRING);
		headerReportCellH.setCellValue(COL_INCIDENTS);
		headerReportCellH.setCellStyle(styles.get("headerVertical"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$H$2:$H$4"));	
		
		//ColonneI: Maintenance corrective
		Cell headerReportCellI2 = headerReportRow1.createCell(8, Cell.CELL_TYPE_STRING);
		headerReportCellI2.setCellValue(COL_MC);
		headerReportCellI2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$I$2:$K$2"));
		
		Cell headerReportCellI3 = headerReportRow2.createCell(8, Cell.CELL_TYPE_STRING);
		headerReportCellI3.setCellValue(COL_NB_BUGS);
		headerReportCellI3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$I$3:$I$4"));

		Cell headerReportCellJ3 = headerReportRow2.createCell(9, Cell.CELL_TYPE_STRING);
		headerReportCellJ3.setCellValue(COL_CHG_JOURS);
		headerReportCellJ3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$J$3:$K$3"));
		
		Cell headerReportCellJ4 = headerReportRow3.createCell(9, Cell.CELL_TYPE_STRING);
		headerReportCellJ4.setCellValue(COL_CHG_INTRA);
		headerReportCellJ4.setCellStyle(styles.get("headerHorizontal"));	
		
		Cell headerReportCellK4 = headerReportRow3.createCell(10, Cell.CELL_TYPE_STRING);
		headerReportCellK4.setCellValue(COL_CHG_EXTRA);
		headerReportCellK4.setCellStyle(styles.get("headerHorizontal"));		
		
		
		//ColonneL: ME
		Cell headerReportCellL2 = headerReportRow1.createCell(11, Cell.CELL_TYPE_STRING);
		headerReportCellL2.setCellValue(COL_ME);
		headerReportCellL2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$L$2:$N$2"));		
		
		Cell headerReportCellL3 = headerReportRow2.createCell(11, Cell.CELL_TYPE_STRING);
		headerReportCellL3.setCellValue(COL_NB_DEMANDE);
		headerReportCellL3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$L$3:$L$4"));		
		
		Cell headerReportCellM3 = headerReportRow2.createCell(12, Cell.CELL_TYPE_STRING);
		headerReportCellM3.setCellValue(COL_CHG_JOURS);
		headerReportCellM3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$M$3:$N$3"));	
		
		Cell headerReportCellM4 = headerReportRow3.createCell(12, Cell.CELL_TYPE_STRING);
		headerReportCellM4.setCellValue(COL_CHG_INTRA);
		headerReportCellM4.setCellStyle(styles.get("headerHorizontal"));	
		
		Cell headerReportCellN4 = headerReportRow3.createCell(13, Cell.CELL_TYPE_STRING);
		headerReportCellN4.setCellValue(COL_CHG_EXTRA);
		headerReportCellN4.setCellStyle(styles.get("headerHorizontal"));		
		
		//ColonneO: devéloppement état
		Cell headerReportCellO2 = headerReportRow1.createCell(14, Cell.CELL_TYPE_STRING);
		headerReportCellO2.setCellValue(COL_DE);
		headerReportCellO2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$O$2:$P$2"));
		
		Cell headerReportCellO3 = headerReportRow2.createCell(14, Cell.CELL_TYPE_STRING);
		headerReportCellO3.setCellValue(COL_NB_ETATS);
		headerReportCellO3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$O$3:$O$4"));		
		
		Cell headerReportCellP3 = headerReportRow2.createCell(15, Cell.CELL_TYPE_STRING);
		headerReportCellP3.setCellValue(COL_CHG_JOURS);
		headerReportCellP3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$P$3:$P$4"));			
		
		//ColonneQ: Recette
		Cell headerReportCellQ2 = headerReportRow1.createCell(16, Cell.CELL_TYPE_STRING);
		headerReportCellQ2.setCellValue(COL_REC);
		headerReportCellQ2.setCellStyle(styles.get("headerHorizontal"));	
		
		Cell headerReportCellQ3 = headerReportRow2.createCell(16, Cell.CELL_TYPE_STRING);
		headerReportCellQ3.setCellValue(COL_CHG_JOURS);
		headerReportCellQ3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$Q$3:$Q$4"));			
		
		//ColonneR: assistance utilisateur
		Cell headerReportCellR2 = headerReportRow1.createCell(17, Cell.CELL_TYPE_STRING);
		headerReportCellR2.setCellValue(COL_ASS_UTI);
		headerReportCellR2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$R$2:$S$2"));
		
		Cell headerReportCellR3 = headerReportRow2.createCell(17, Cell.CELL_TYPE_STRING);
		headerReportCellR3.setCellValue(COL_CHG_INTRA);
		headerReportCellR3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$R$3:$R$4"));		
		
		Cell headerReportCellS3 = headerReportRow2.createCell(18, Cell.CELL_TYPE_STRING);
		headerReportCellS3.setCellValue(COL_CHG_EXTRA);
		headerReportCellS3.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$S$3:$S$4"));
		
		//ColonneT: charge dep
		Cell headerReportCellT2 = headerReportRow1.createCell(19, Cell.CELL_TYPE_STRING);
		headerReportCellT2.setCellValue(COL_CHG_DEP);
		headerReportCellT2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$T$2:$T$4"));

		//ColonneU: charge depsr
		Cell headerReportCellU2 = headerReportRow1.createCell(20, Cell.CELL_TYPE_STRING);
		headerReportCellU2.setCellValue(COL_CHG_DEPSR);
		headerReportCellU2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$U$2:$U$4"));
		
		//ColonneV: charge tma
		Cell headerReportCellV2 = headerReportRow1.createCell(21, Cell.CELL_TYPE_STRING);
		headerReportCellV2.setCellValue(COL_CHG_TMA);
		headerReportCellV2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$V$2:$V$4"));
		
		//etat: colonne avec indicateur couleur
		Cell headerReportCellW2 = headerReportRow1.createCell(22, Cell.CELL_TYPE_STRING);
		headerReportCellW2.setCellValue(COL_ETAT);
		headerReportCellW2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$W$2:$W$4"));		
		
		//ColonneW: charge commentaires
		Cell headerReportCellX2 = headerReportRow1.createCell(23, Cell.CELL_TYPE_STRING);
		headerReportCellX2.setCellValue(COL_COMMENTS);
		headerReportCellX2.setCellStyle(styles.get("headerHorizontal"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$X$2:$Z$2"));
		sheet.addMergedRegion(CellRangeAddress.valueOf("$X$2:$X$4"));
		
		//
		// Définition de la taille des cellules
		//
		sheet.setColumnWidth(0, 20*256);
		sheet.setColumnWidth(2, 5*256);
		sheet.setColumnWidth(3, 5*256);
		sheet.setColumnWidth(4, 8*256);//E
		sheet.setColumnWidth(6, 5*256);//G
		sheet.setColumnWidth(7, 5*256);//H
		sheet.setColumnWidth(8, 8*256);//I
		sheet.setColumnWidth(9, 6*256);//J		
		sheet.setColumnWidth(10, 6*256);//K
		sheet.setColumnWidth(11, 10*256);//L
		sheet.setColumnWidth(12, 6*256);//M
		sheet.setColumnWidth(13, 6*256);//N
		sheet.setColumnWidth(17, 6*256);//R
		sheet.setColumnWidth(18, 6*256);//S		
		sheet.setColumnWidth(22, 5*256);//W
		
        // Write the output to a file
        String file = FILE_PATH + "report-test.xls";
        FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			wb.write(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if (out!=null) {
					out.close();	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        System.out.println("Document report-test généré!");
	}
	
    /**
     * Create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        CellStyle style;
        
        //mise en forme du style de la ligne titre-centre.
        Font titleFontCenter = wb.createFont();
        titleFontCenter.setFontHeightInPoints((short)10);
        titleFontCenter.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setFont(titleFontCenter);
        styles.put("mainTitle", style);
        
        //Font HeaderReport
        Font fontHeader = wb.createFont();
        fontHeader.setFontHeightInPoints((short)9);
        
        //Cell Header - texte-horizontal
        style = wb.createCellStyle();
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);//renvoyer à la ligne automatiquement
        style.setFont(fontHeader);
        //couleur du header
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("headerHorizontal", style);
        
        //Cell Header - texte-vertical
        style = wb.createCellStyle();
        style.setFont(fontHeader);
        style = createBorderedStyle(wb);
        style.setRotation((short)90);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setWrapText(true);//renvoyer à la ligne automatiquement
        //couleur du header
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("headerVertical", style);
        
        return styles;
    }	
    
    /**
     * style des bordures.
     */
    private static CellStyle createBorderedStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }    
}
