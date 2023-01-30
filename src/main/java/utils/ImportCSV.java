package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import data.CellsMapper;
import data.CustomRow;

public class ImportCSV {
	final static String PROJECT_PATH = System.getProperty("user.dir");
	private static final Logger logger = LogManager.getLogger(ImportCSV.class);
	
	public HashMap<String,CellsMapper> getCSVRowDataByIdTest(String sheetName , String idTest) throws EncryptedDocumentException, IOException {
		
			HashMap<String,CellsMapper> dataList = new HashMap<String, CellsMapper>();
			//C:\Users\valentino.mancino\appium-eclipseWS\appiumtest\testCaseData
		   	System.out.println("directoryName .:"+PROJECT_PATH+"/testCaseData/TestCase1.xlsx");
		   	
		   	
			logger.info("Import CSV - Path: "+PROJECT_PATH+"/testCaseData/TestCase1.xlsx");
		   	
		   	Workbook workbook = null;
		   	try {
		   	  workbook = WorkbookFactory.create(new File(PROJECT_PATH+"/testCaseData/TestCase1.xlsx"));
		   	}catch (FileNotFoundException e) {
		   		logger.error("TestCase1.xlsx file non trovato o utilizzato da un altra app");
		   		logger.warn("exception message : ", e);
		   		System.exit(1);
			}
		   
		    
		 // Retrieving the number of sheets in the Workbook
		    System.out.print("Workbook has " + workbook.getNumberOfSheets() + " ");
		    
		 // Retrieving Sheets Java 8 forEach with lambda
		    //System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
		    workbook.forEach(sheet -> {
		        System.out.println("Sheet Name => " + sheet.getSheetName());
		    });
    
	        /*
	        ==================================================================
	        (Get Header From File) Iterating over all columns in the rows [0] 
	        ==================================================================
	         */
	        //Sheet sheetOne = workbook.getSheetAt(0);
	        Sheet sheetOne = workbook.getSheet(sheetName);
	        Row header = sheetOne.getRow(0);
	        DataFormatter dFormatter = new DataFormatter();
	        //aggiungere controllo se null
	        header.forEach(cell -> {
	            String cellValue = dFormatter.formatCellValue(cell);
	            String colNumber = Integer.toString( cell.getColumnIndex() );
	            CellsMapper cellsFromFile = new CellsMapper();
	            cellsFromFile.setChiave(cellValue);
	            cellsFromFile.setColNumber(colNumber);
	            
	            dataList.put(colNumber, cellsFromFile);
	            
	            //System.out.print("Col"+colNumber + ":" + cellValue + "\t");

	        });
	        
	        //recupero la riga con l'idTest desiderato
	        logger.info("IdTest per la ricerca riga "+idTest);
	        int rowNumber = getRowByIdTest(sheetOne,idTest);
	        logger.info("Numero di riga da processare: "+rowNumber);
	        Row rowDataOne = sheetOne.getRow(rowNumber);
	        DataFormatter dFormatter2 = new DataFormatter();
	        //aggiungere controllo null
	        rowDataOne.forEach(cell -> {
	            String cellValue = dFormatter2.formatCellValue(cell);
	            String colNumber = Integer.toString( cell.getColumnIndex() );
	            CellsMapper cellsFromFile = new CellsMapper();
	            
	            dataList.get(colNumber).setValore(cellValue);
	            
	            System.out.println("Col: "+ dataList.get(colNumber).getColNumber() + " - cellChiave: " + dataList.get(colNumber).getChiave() + " - cellValue: " + dataList.get(colNumber).getValore());
	            
//	            for(CellsMapper cellmapper : dataList) {
//	                if(cellmapper.getColNumber().equals(colNumber)) {
//	                    cellmapper.setValore(cellValue);
//	                    System.out.println("Col: "+cellmapper.getColNumber() + " - cellChiave: " + cellmapper.getChiave() + " - cellValue: " + cellmapper.getValore());
//	                }
//	            }
	            //cellsFromFile.setChiave(cellValue);
	            //cellsFromFile.setColNumber(colNumber);
	            
	            //dataList.add(cellsFromFile);
	            
	            ;

	        });
    
	         // Closing the workbook
	         workbook.close();
	         System.out.println("woorkbook close");
	         return dataList;
	}
	
	public int getRowByIdTest (Sheet sheet,String idTest) {
		for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == CellType.STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(idTest)) {
	                    return row.getRowNum();  
	                }
	            }
	        }
	    }               
	    return 0;
		
	}
	
	
	
	
	public List<CustomRow> getTestBook(String sheetName , String nomeFileExcel) throws EncryptedDocumentException, IOException {
		CustomRow cRow;
		List<CustomRow> cRowListX;
		HashMap<String,CellsMapper> dataList = new HashMap<String, CellsMapper>();
		
		
		//C:\Users\valentino.mancino\appium-eclipseWS\appiumtest\testCaseData
	   	System.out.println("directoryName .:"+PROJECT_PATH+"/testCaseData/"+nomeFileExcel);
	   	
	   	
		logger.info("Import CSV - Path: "+PROJECT_PATH+"/testCaseData/"+nomeFileExcel);
	   	
	   	Workbook workbook = null;
	   	try {
	   	  workbook = WorkbookFactory.create(new File(PROJECT_PATH+"/testCaseData/"+nomeFileExcel));
	   	}catch (FileNotFoundException e) {
	   		logger.error("TestCase1.xlsx file non trovato o utilizzato da un altra app");
	   		logger.warn("exception message : ", e);
	   		System.exit(1);
		}
	   
	    
	 // Retrieving the number of sheets in the Workbook
	    System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheet");
	    
	 // Retrieving Sheets Java 8 forEach with lambda
	    //System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
	    workbook.forEach(sheet -> {
	        System.out.println("Sheet Name => " + sheet.getSheetName());
	    });

        /*
        ==================================================================
        (Get Header From File) Iterating over all columns in the rows [0] 
        ==================================================================
         */
        //Sheet sheetOne = workbook.getSheetAt(0);
        Sheet sheetOne = workbook.getSheet(sheetName);
        Row header = sheetOne.getRow(0);
        DataFormatter dFormatter = new DataFormatter();
        
      //recupero la riga con l'idTest desiderato
        int numRowTotal = sheetOne.getLastRowNum();
        cRowListX = new ArrayList<CustomRow>();
        for(int i = 1; i<= numRowTotal; i++ ) {
        
        		//aggiungere controllo se null
		        header.forEach(cell -> {
		            String cellValue = dFormatter.formatCellValue(cell);
		            String colNumber = Integer.toString( cell.getColumnIndex() );
		            CellsMapper cellsFromFile = new CellsMapper();
		            cellsFromFile.setChiave(cellValue);
		            cellsFromFile.setColNumber(colNumber);
		            
		            dataList.put(colNumber, cellsFromFile);         
		            //System.out.print("Col"+colNumber + ":" + cellValue + "\t");
		        	});

		        Row rowDataOne = sheetOne.getRow(i);
		        DataFormatter dFormatter2 = new DataFormatter();
		        //aggiungere controllo null        
		       
		            Iterator<Cell> it1=rowDataOne.cellIterator();
		            while(it1.hasNext()){
		                Cell cell = it1.next();
		                //System.out.println("Row: "+cell.getRowIndex()+" ,Column: "+cell.getColumnIndex());
		                //System.out.println(cell);
		                String cellValue = dFormatter2.formatCellValue(cell);
			            String colNumber = Integer.toString( cell.getColumnIndex() );
			            CellsMapper cellsFromFile = new CellsMapper();
			            
			            if(!cellValue.isEmpty()||!cellValue.isBlank()) {
			            	dataList.get(colNumber).setValore(cellValue);
			            	//System.out.println("Row: "+ rowDataOne.getRowNum() +" Col: "+ dataList.get(colNumber).getColNumber() + " - cellChiave: " + dataList.get(colNumber).getChiave() + " - cellValue: " + dataList.get(colNumber).getValore());
			            	logger.info("Row: "+ rowDataOne.getRowNum() +" Col: "+ dataList.get(colNumber).getColNumber() + " - cellChiave: " + dataList.get(colNumber).getChiave() + " - cellValue: " + dataList.get(colNumber).getValore());
					    } else {
			            	dataList.get(colNumber).setValore("");
			            }
		            }
		            
		        
		        
		        
		        if(!dataList.get("0").getValore().isEmpty()||!dataList.get("0").getValore().isBlank()) {
		        	cRow = new CustomRow();
		        	for(CustomRow cr: cRowListX) {
		        		logger.info("verifico i valori prima = "+ cr.getCustomRowHM().get("0").getValore());	
		        	}
		        	logger.info("Test Object creato con idTesr = "+dataList.get("0").getValore());
		        	cRow.setCustomRowHM(dataList);
		        	logger.info("idTest in cRow = "+ cRow.getCustomRowHM().get("0").getValore());
		        	cRowListX.add(cRow);
		        	logger.info("cRowListX size = "+ cRowListX.size());
//		        	for(CustomRow cr: cRowListX) {
//		        		logger.info("verifico i valori dopo = "+ cr.getCustomRowHM().get("0").getValore());	
//		        	}
		        	
		        	
		        	//logger.info(cRowListX.size()-1+") IdTest : " + cRowListX.get(cRowListX.size()-1).getCustomRowHM().get("0").getValore() +" - Test Title : " + cRowListX.get(cRowListX.size()-1).getCustomRowHM().get("1").getValore() );
		        }

        
        }
        
        
         // Closing the workbook
        logger.info("Dimensione testbook cRowList : " + cRowListX.size() + " test trovati");
        for(int r = 0; r< cRowListX.size(); r++) {
       	 //CustomRow newCustRow = new CustomRow();
       	 //newCustRow = cRowListX.get(r);
       	 //HashMap<String,CellsMapper> copiaRowHM = newCustRow.getCustomRowHM();
       	 //logger.info(r+") IdTest copiaRowHM : " + copiaRowHM.get("0").getValore() +" - Test Title : " + copiaRowHM.get("1").getValore() );
       	 logger.info(r+") IdTest cRowListX : " + cRowListX.get(r).getCustomRowHM().get("0").getValore() +" - Test Title : " + cRowListX.get(r).getCustomRowHM().get("1").getValore() );
        } 
         
         System.out.println("woorkbook close");
 
         workbook.close();
         return cRowListX;
}
	
	
	
	

}
