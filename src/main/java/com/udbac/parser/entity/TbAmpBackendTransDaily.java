package com.udbac.parser.entity;

public class TbAmpBackendTransDaily {
	
	private String createDate;
	   private String mic;
	   private String behaviorVV;
	   private String transactionVV;
	   private String phonBuyVV;
	   private String setMealVV;
	   private String partsVV;
	public String getMic() {
		return mic;
	}
	public void setMic(String mic) {
		this.mic = mic;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getBehaviorVV() {
		return behaviorVV;
	}
	public void setBehaviorVV(String behaviorVV) {
		this.behaviorVV = behaviorVV;
	}
	public String getTransactionVV() {
		return transactionVV;
	}
	public void setTransactionVV(String transactionVV) {
		this.transactionVV = transactionVV;
	}
	public String getPhonBuyVV() {
		return phonBuyVV;
	}
	public void setPhonBuyVV(String phonBuyVV) {
		this.phonBuyVV = phonBuyVV;
	}
	public String getSetMealVV() {
		return setMealVV;
	}
	public void setSetMealVV(String setMealVV) {
		this.setMealVV = setMealVV;
	}
	public String getPartsVV() {
		return partsVV;
	}
	public void setPartsVV(String partsVV) {
		this.partsVV = partsVV;
	}
	
	public String toString(){
		return "'" + createDate + '\'' +
                ", '" + mic + '\'' +
                ", " + behaviorVV +
                ", " + transactionVV +
                "," + phonBuyVV + 
                ", " + setMealVV + 
                ", " + partsVV ;		
	}

}
