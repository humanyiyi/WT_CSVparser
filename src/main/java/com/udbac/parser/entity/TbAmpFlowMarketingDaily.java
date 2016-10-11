package com.udbac.parser.entity;

public class TbAmpFlowMarketingDaily {
	
	private String createDate;
	private String mic;
	private String url;
	private String visits;
	private String pv;
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getMic() {
		return mic;
	}
	public void setMic(String mic) {
		this.mic = mic;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getVisits() {
		return visits;
	}
	public void setVisits(String visits) {
		this.visits = visits;
	}
	public String getPv() {
		return pv;
	}
	public void setPv(String pv) {
		this.pv = pv;
	}
	
	public String toString(){
		
		return "'" + createDate + '\'' +
                ", '" + mic + '\'' +
                ", '" + url + '\'' +
                ", " + visits +
                "," + pv ;
	}

}
