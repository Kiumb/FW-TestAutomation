package data;

public class CellsMapper {
	String chiave;
	String valore;
	String colNumber;
	String idTest;
	

	public String getIdTest() {
		return idTest;
	}

	public void setIdTest(String idTest) {
		this.idTest = idTest;
	}

	public CellsMapper()
	{
		
	}

	public String getColNumber() {
		return colNumber;
	}

	public void setColNumber(String colNumber) {
		this.colNumber = colNumber;
	}
	
	public CellsMapper(String chiave, String valore) {
        this.chiave = chiave;
        this.valore = valore;

    }

	public String getChiave() {
		return chiave;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}
	

}
