package testBookManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import data.CustomRow;
import data.TestBook;
import data.TestObj;
import utils.ImportCSV;

public class TestBookManager {
	ImportCSV importCSV = new ImportCSV();
	private static final Logger logger = LogManager.getLogger(TestBookManager.class);
	
	public TestBook generateTestBook(String nomeExcelTest) throws EncryptedDocumentException, IOException {
		
		TestBook testBook = new TestBook();
		TestObj testObj;
		List<CustomRow> rowDataTest;
		List<TestObj> testObjList = new ArrayList<TestObj>();
		List<String> stepList;
		
		rowDataTest = importCSV.getTestBook("TestBook",nomeExcelTest);
		
		for(CustomRow cr : rowDataTest ) {
			testObj = new TestObj();
			stepList = new ArrayList<String>();
			int dimensioneMappa = cr.getCustomRowHM().size();
			logger.info("dimensione mappa : "+dimensioneMappa);
			testObj.setIdTest(cr.getCustomRowHM().get("0").getValore());
			logger.info("valore letto da customRow [idTest] : "+cr.getCustomRowHM().get("0").getValore());
			testObj.setTestTitle(cr.getCustomRowHM().get("1").getValore());
			testObj.setTestDescription(cr.getCustomRowHM().get("2").getValore());
			testObj.setDaEseguire(cr.getCustomRowHM().get("3").getValore());
			testObj.setPrecondizione(cr.getCustomRowHM().get("4").getValore());
			
			for(int iTest =5; iTest < dimensioneMappa; iTest++ ) {
				String iStr = Integer.toString(iTest);
				logger.info("step da inserire in coda : " +cr.getCustomRowHM().get(iStr).getValore());
				stepList.add(cr.getCustomRowHM().get(iStr).getValore());	
			}
			testObj	.setStepList(stepList);
			testObjList.add(testObj);
			testBook.setTestBookList(testObjList);
		}
		
		
		
		return testBook;
	}

}
