package com.udbac.parser.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.udbac.parser.database.BeyondbConnection;
import com.udbac.parser.entity.BaseTable;
import com.udbac.parser.entity.TbAmpBackendTransDaily;
import com.udbac.parser.entity.TbAmpFlowMarketingDaily;
import com.udbac.parser.entity.TbAmpFlowNatureDaily;
import com.udbac.parser.entity.TbAmpFlowTotalDaily;
import com.udbac.parser.readcsv.BaseTableReader;

public class InsertIntoDababase {
	private Logger logger = Logger.getLogger(InsertIntoDababase.class);
	 private  Connection con = null;
	 
	 /**
	 * 输入后端监测日数据到tb_amp_backend_base _daily表中
	 */	 
	public void insertIntoTbAmpBackendBaseDaily() throws SQLException{
		String tableName = "csvtest";
//		BaseTableReader bt= new BaseTableReader();
		if(con ==null){
	        con = BeyondbConnection.getConnection();
	   }
		List<BaseTable> list = BaseTableReader.getBaseTable("d:\\csvs");
		PreparedStatement pstmt;
		for (BaseTable baseTable : list) {
			String sql = "INSERT INTO " + tableName + " VALUES(" + baseTable.toString() + ")";
			try{
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(sql);
			}
			}
		con.close();
		System.out.println("插入数据到tb_amp_backend_base _daily完成");

//		String sql = "insert into csvtest values(null,?,?,?,?,?,?)";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		for (BaseTable baseTable : list) {
//		pstmt.setString(1, baseTable.getMic());
//		pstmt.setString(2, baseTable.getVisits());
//		pstmt.setString(3,baseTable.getVisitor());
//		pstmt.setString(4, baseTable.getPv());
//		pstmt.setString(5, baseTable.getClick());
//		pstmt.setString(6, baseTable.getViewTime());
//		if(baseTable.getMic().length() > 255){
//			continue;
//		}
//		pstmt.executeUpdate();
//		}	
	}
	
	/**
	 * 输入活动网站监测业务转换数据到tb_amp_backend_trans_daily表中
	 */
	public  void insertIntoTbAmpBackendTransDaily() throws SQLException{
		String tableName = "tb_amp_backend_trans_daily";
//		BaseTableReader bt= new BaseTableReader();
		if(con ==null){
	        con = BeyondbConnection.getConnection();
	   }
		List<TbAmpBackendTransDaily> list = BaseTableReader.getTbAmpBackendTransDaily("d:\\csvs");
		PreparedStatement pstmt;
		for (TbAmpBackendTransDaily transTable : list) {			
			String sql = "INSERT INTO " + tableName + " VALUES(" + transTable.toString() + ")";	
			try{
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(sql);
			}
			}
		/**
		 * 关闭相关对象
		 */
		con.close();  	
	}
	
	/**
	 * 输入数据到tb_amp_flow_marketing_daily表中
	 */
	public  void insertIntoTbAmpFlowMarketingDaily() throws SQLException{
		String tableName = "tb_amp_flow_marketing_daily";
//		BaseTableReader bt= new BaseTableReader();
		if(con ==null){
	        con = BeyondbConnection.getConnection();
	   }
		List<TbAmpFlowMarketingDaily> list = BaseTableReader.getTbAmpFlowMarketingDaily("d:\\csvs");
		PreparedStatement pstmt;
		for (TbAmpFlowMarketingDaily maketTable : list) {			
			String sql = "INSERT INTO " + tableName + " VALUES(" + maketTable.toString() + ")";
			try{
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(sql);
			}
			}
		/**
		 * 关闭相关对象
		 */
		con.close();  	
	}
	
	/**
	 * 输入数据到tb_amp_flow_nature_daily表中
	 * @throws SQLException 
	 */
	public  void insertIntoTbAmpFlowNatureDaily() throws SQLException {
		
		PropertyConfigurator.configure("log4j.properties");
		String tableName = "tb_amp_flow_nature_daily";
		if(con ==null){
	        con = BeyondbConnection.getConnection();
	   }
		List<TbAmpFlowNatureDaily> list = BaseTableReader.getTbAmpFlowNatureDaily("d:\\csvs");
		PreparedStatement pstmt;
		for (TbAmpFlowNatureDaily natureTable : list) {			
			String sql = "INSERT INTO " + tableName + " VALUES(" + natureTable.toString() + ")";
			
			try{
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(Exception e){
//			logger.sqlError(sql);
				logger.error(sql);
//				e.printStackTrace();
//				System.out.println(sql);
			}
			}
		/**
		 * 关闭相关对象
		 */
			con.close();
		 	
	}
	
	/**
	 * 输入数据到tb_amp_flow_total_daily表中
	 */
	public  void insertIntoTbAmpFlowTotalDaily() throws SQLException{
		String tableName = "tb_amp_flow_total_daily";
//		BaseTableReader bt= new BaseTableReader();
		if(con ==null){
	        con = BeyondbConnection.getConnection();
	   }
		List<TbAmpFlowTotalDaily> list = BaseTableReader.getTbAmpFlowTotalDaily("d:\\csvs");
		PreparedStatement pstmt;
		for (TbAmpFlowTotalDaily natureTable : list) {			
			String sql = "INSERT INTO " + tableName + " VALUES(" + natureTable.toString() + ")";
			try{
				pstmt = con.prepareStatement(sql);
				pstmt.executeUpdate();
			}catch(Exception e){
				System.out.println(sql);
			}
			}
		/**
		 * 关闭相关对象
		 */
		con.close();	
	}

}
