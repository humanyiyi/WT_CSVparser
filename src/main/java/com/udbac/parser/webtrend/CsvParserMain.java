package com.udbac.parser.webtrend;

import java.sql.SQLException;

import com.udbac.parser.database.InsertIntoDababase;

public class CsvParserMain {
	
	public static void main(String[] args) throws SQLException {
		InsertIntoDababase in = new InsertIntoDababase();
		/**
		 * 数据写入tb_amp_backend_base_daily表
		 */
//		in.insertIntoTbAmpBackendBaseDaily();
		/**
		 * 数据写入tb_amp_backend_trans_daily表
		 */
//		in.insertIntoTbAmpBackendTransDaily();
		/**
		 * 数据写入tb_amp_flow_marketing_daily表
		 */
//		in.insertIntoTbAmpFlowMarketingDaily();
		/**
		 * 数据写入tb_amp_flow_nature_daily表
		 */
		in.insertIntoTbAmpFlowNatureDaily();
		/**
		 * 数据写入tb_amp_flow_total_daily表
		 */
//		in.insertIntoTbAmpFlowTotalDaily();
	}
}
