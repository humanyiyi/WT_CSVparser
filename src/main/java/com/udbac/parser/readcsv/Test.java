package com.udbac.parser.readcsv;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import com.udbac.parser.database.BeyondbConnection;
import com.udbac.parser.entity.TbAmpFlowNatureDaily;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;


public class Test {
	private static  Connection con = null;
	
    public static List<TbAmpFlowNatureDaily> getTbAmpFlowNatureDaily(String dir){
    	
    	File mcidPortalFile = new File("D:\\shop_refer+entrypage（排除mcid）0806-0828.csv");
    	List<String[]> mcidPortalRows = parseCSV2Rows(mcidPortalFile);
    	
    	
    	List<TbAmpFlowNatureDaily> tbAmpFlowNatureDailyList = new ArrayList<TbAmpFlowNatureDaily>();
    	TbAmpFlowNatureDaily tbAmpFlowNatureDaily = null;
    	
    	for(String[] row1: mcidPortalRows){
    		
    		tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
    		if(row1.length != 10 || null == row1[0]){
				continue;
			}
    		
    		if(row1[4] != null && row1[4].equals("合计")){
				continue;
			}
    		tbAmpFlowNatureDaily.setUrl(row1[2]);
    		tbAmpFlowNatureDaily.setEntryPage(row1[4]);
    		tbAmpFlowNatureDaily.setVisits(row1[7]);
    		tbAmpFlowNatureDaily.setPv(row1[9]);
    		
    		
    		tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
    	}
    	  	
   

		return tbAmpFlowNatureDailyList;
    	
		
    	
    }
    
    public static List<String[]> parseCSV2Rows(File filename) {
        CsvParserSettings visitsSetting = new CsvParserSettings();
        visitsSetting.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(visitsSetting);
        List<String[]> allRows = parser.parseAll(filename, "GB2312");
        return allRows;
    }
 

//	  public static void main(String[] args) {
//
//			File transactionFile = new File( "D:\\shop_refer+entrypage（排除mcid）0806-0828.csv");
//			List<String[]> transactionRows = BaseTableReader.parseCSV2Rows(transactionFile);
//			for(String[] row2 : transactionRows){
//				if(row2.length == 10 && null != row2[0]){
//					if(row2[4] != null && row2[4].equals("合计"))
//					{continue;}
//					System.out.println(row2[4]);	
//				}
//								System.out.println(row2[4]);
////				System.out.println(row2[2].equals("合计"));
//				//System.out.println(row2[1]);
//			}
//			
//
//}

    
    public static  void insertIntoTbAmpFlowNatureDaily() throws SQLException{
		String tableName = "tb_amp_flow_nature_daily";
//		BaseTableReader bt= new BaseTableReader();
		if(con ==null){
	        con = BeyondbConnection.getConnection();
	   }
		List<TbAmpFlowNatureDaily> list = BaseTableReader.getTbAmpFlowNatureDaily("d:\\csvs");
		String sql = "INSERT INTO " + tableName + " VALUES(null,null,?,?,?,? )";
		for (TbAmpFlowNatureDaily natureTable : list) {			
			
		
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, natureTable.getUrl());
			pstmt.setString(2, natureTable.getEntryPage());
			pstmt.setString(3,natureTable.getVisits());
			pstmt.setString(4, natureTable.getPv());
			pstmt.executeUpdate();
			}
		/**
		 * 关闭相关对象
		 */
		//con.close();  	
	}
	
    
    public static void main(String[] args) throws SQLException {
//        List<TbAmpFlowNatureDaily> list = getTbAmpFlowNatureDaily("d:\\csvs");
//        for (TbAmpFlowNatureDaily baseTable : list) {
//            System.out.println(baseTable.toString());
//        }
//        System.out.println(list.size());
    	insertIntoTbAmpFlowNatureDaily();
    	
    }
}
