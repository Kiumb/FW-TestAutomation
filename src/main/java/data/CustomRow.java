package data;

import java.util.HashMap;

public class CustomRow {
	HashMap<String,CellsMapper> customRowHM;
	int rowNumber;
//	public CustomRow(){
//		//customRowHM = new HashMap<String, CellsMapper>();	
//	}
	public HashMap<String, CellsMapper> getCustomRowHM() {
		return customRowHM;
	}
	public void setCustomRowHM(HashMap<String, CellsMapper> customRowHM) {
//		this.customRowHM =  new HashMap<String,CellsMapper>(customRowHM);
		this.customRowHM =  (HashMap<String, CellsMapper>) customRowHM.clone();
//		hashMap1 = (HashMap) hashMap.clone();
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	
}
