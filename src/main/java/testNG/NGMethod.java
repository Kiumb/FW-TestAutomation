package testNG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import data.TestBook;
import data.TestObj;
import driverManager.DController;
import utils.ImportCSV;
import utils.Utils;

public class NGMethod {
	
	final static String PROJECT_PATH = System.getProperty("user.dir");
	private static final Logger logger = LogManager.getLogger(ImportCSV.class);
	String idTest="";
	DController dController = new DController();
//	  //DRIVER PER LOGIN NUOVO NUMERO *************
//		//driver = dController.startDriverClearCache(idTest);
	
//	public void runNGTest(TestBook testBook) {
//		
//		
//		logger.info("directoryName .:"+PROJECT_PATH+"/resultNG");
//		
////		TestNG testSuite = new TestNG();
////		testSuite.setTestClasses(new Class[] { NewTest.class });
////		testSuite.addListener(new NGListenerTest1());
////		testSuite.setDefaultSuiteName("My Test Suite");
////		testSuite.setDefaultTestName("My Test");
////		testSuite.setOutputDirectory(PROJECT_PATH+"/resultNG");		
////		testSuite.run();
//		
//		
//		//PROVA 2
//		
//		XmlSuite xmlSuite = new XmlSuite();
//        xmlSuite.setName("Sample_Suite");
//        ParamContainer paramC = new ParamContainer(idTest);
//        Map<String, String> fieldValues = paramC.getValues();
//        xmlSuite.setParameters(fieldValues);
//        XmlTest xmlTest = new XmlTest(xmlSuite);
//        xmlTest.setName("Sample_test");
//        xmlTest.setXmlClasses(Collections.singletonList(new XmlClass(NewTest.class)));
//        TestNG tng = new TestNG();
//        tng.addListener(new NGListenerTest1());
//        tng.setDefaultSuiteName("Sample_Suite_X");
//        tng.setOutputDirectory(PROJECT_PATH+"/resultNG");
//        tng.setDefaultSuiteName("My Test Suite");
//        tng.setXmlSuites(Collections.singletonList(xmlSuite));
//        tng.run();
//
//	}
	
//	   private void testRunner(Map<String, String> testngParams , TestBook testBook) {
 public void testRunner(TestBook testBook) {
		   
		   List<TestObj> testObjList = testBook.getTestBookList();
		   Map<String, String> testngParams;
		   

	        // Running TestNG programmatically
	        // http://testng.org/doc/documentation-main.html#running-testng-programmatically
		   
		   TestNG testNG = new TestNG();
		   List<XmlSuite> suites = new ArrayList<XmlSuite>();
		    
		  for (TestObj testObj :testObjList) {
	        //Create an instance on TestNG
	        
			if(testObj.getDaEseguire().compareTo("True")==0) {
			        testNG.setOutputDirectory(PROJECT_PATH+"/resultNG/test"+Utils.getTimeForName());	
			        testngParams = new HashMap<String, String>();
			        testngParams.put("idTest", testObj.getIdTest());
			        System.out.println("param idtest = " + testngParams.get("idTest"));
			        
		
			        //Create an instance of XML Suite and assign a name for it.
			        XmlSuite suite = getXmlSuite(testObj.getTestTitle());
			        suite.setPreserveOrder(true);
		
			        //Create an instance of XmlTest and assign a name for it.
			        XmlTest test = getXmlTest(suite);
		
			        //Add any parameters that you want to set to the Test.
			        test.setParameters(testngParams);
		
			        //Create a list which can contain the classes that you want to run.
			        List<XmlClass> classes = getXmlClasses(testObj);
		
			        //Assign that to the XmlTest Object created earlier.
			        test.setXmlClasses(classes);
			       
			        
			        //check classi caricate
			        
			        for(XmlClass xmlC :test.getXmlClasses()) {
			        	logger.info("Classi da testare : " +xmlC.getName());
			        }
		
			        //Create a list of XmlTests and add the Xmltest you created earlier to it.
			        List<XmlTest> tests = new ArrayList<XmlTest>();
			        tests.add(test);
			        
			        suite.setListeners(Arrays.asList(NGListenerTest1.class.getName()));
		
			        //add the list of tests to your Suite.
			        suite.setTests(tests);
		
			        //Add the suite to the list of suites.
			        //List<XmlSuite> suites = new ArrayList<XmlSuite>();
			        suites.add(suite);
				}

	        
	   }
		//Set the list of Suites to the testNG object you created earlier.
	        testNG.setXmlSuites(suites);

	        //invoke run() - this will run your class.
	        testNG.run();

	 }

	   
	   
	    private XmlSuite getXmlSuite(String testNameSuite) {
	        XmlSuite suite = new XmlSuite();
	        suite.setName(testNameSuite);
	        //suite.setSuiteFiles(null);
	        suite.getChildSuites();
	        return suite;
	    }

	    private XmlTest getXmlTest(XmlSuite suite) {
	        XmlTest test = new XmlTest(suite);
	        test.setName("test 2021 semiautomatico"+idTest);
	        return test;
	    }

	    private List<XmlClass> getXmlClasses(TestObj tObj) {
	    	
	    	List<XmlClass> classez = new ArrayList<XmlClass>();

	    	
			idTest = tObj.getIdTest();
			logger.info("idTest  : " + idTest);
			
			List<String> stepList = tObj.getStepList();
			//logger.info("stepList  : " + stepList);
			for(String str:stepList) {
				
				logger.info("stepList  STR : " + str);
				switch (str) {
				  
				  case "CashOut - Primo Bollettino Premarcato":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashOut.bollettino.PrimoBollettinoPremarcato"));
						 break;
					    }
				  case "CashOut - Successivo Bollettino Premarcato":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashOut.bollettino.SuccessiviBollettiniPremarcati"));
						 break;
					    }
				  case "Profile - Cambio PassCode":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.CambioPassCode"));
						 break;
					    }
				  case "CashIn - Richiedi prima ricarica":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashIn.PrimaRichiestaRicarica"));
						 break;
					    }
				  case "CashIn - Richiedi successiva ricarica":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashIn.RichiestaRicaricaSuccessiva"));
						 break;
					    }
				  case "CashOut - Accetta una ricarica":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashIn.VerificaEAccettaRichiestaRicarica"));
						 break;
					    }
				  case "Login - Solo Login":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.login.SoloLogin"));
						 break;
					    }
				  case "Login - Primo Login":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.login.PrimoLogin"));
						 break;
					    }
				  case "CashIn - Ricarica Conto Con Carta di Credito":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashIn.RicaricaContoConCartaDiCred"));
						 break;
					    }
				  case "CashOut - Prima Ricarica Telefonica":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashOut.ricaricaTelefonica.PrimaRicaricaTelefonica"));
						 break;
					    }
				  case "CashOut - Succes Ricarica Telefonica":{
						 logger.info("ID Test  : " + idTest + "Step Trovato : " + str);
						 classez.add(new XmlClass("feature.cashOut.ricaricaTelefonica.SuccessivaRicaricaTelefonica"));
						 break;
					    }
				  case "Login - Mediolanum":{
					  	classez.add(new XmlClass("mediolanumApp.LoginMedioApp"));
						 break;
					    }
				  case "InstantCredit - Mediolanum":{
					  	classez.add(new XmlClass("mediolanumApp.InstantCreditMedioApp"));
						 break;
					    }
				  case "Login - Mediolanum ios remote":{
					  	classez.add(new XmlClass("mediolanumApp.LoginIOSMedioApp"));
						 break;
					    }
				  case " ":{
						 break;
					    }
				  default:
					  	logger.info("ID Test  : " + idTest + "Step NON Trovato : " + str);
					    System.out.println("Step NON Trovato");
				}
				
				
				
				
				
			}

			return classez;
			//System.out.println(utils.getTime());

	    	
	        
	    }
	
	
	/**
     * Simulates a class that will contain all the key/value pairs that are to be used as
     * <code><parameters></code> for the suite.
     */
    public static class ParamContainer {
        private Map<String, String> values = new HashMap<>();

        ParamContainer(String id) {
            values.put("idTestValue", id);
        }

        Map<String, String> getValues() {
            return values;
        }
    }

}



	   	