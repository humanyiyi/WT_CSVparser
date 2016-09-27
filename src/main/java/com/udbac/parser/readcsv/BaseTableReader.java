package com.udbac.parser.readcsv;

import com.udbac.parser.entity.BaseTable;
import com.udbac.parser.entity.TbAmpBackendTransDaily;
import com.udbac.parser.entity.TbAmpFlowMarketingDaily;
import com.udbac.parser.entity.TbAmpFlowNatureDaily;
import com.udbac.parser.entity.TbAmpFlowTotalDaily;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 43890 on 2016/9/19.
 */
public class BaseTableReader {

	/**
	 * 获取监测日数据基础表数据
	 * @param dir
	 * @return
	 */
    public static  List<BaseTable> getBaseTable(String dir) {
        File visitsFile = new File(dir + "\\营销活动(访次).csv");
        List<String[]> visitRows = parseCSV2Rows(visitsFile);

        File avisitFile = new File(dir + "\\营销活动(访客).csv");
        List<String[]> avisitRows = parseCSV2Rows(avisitFile);

        File activity = new File(dir + "\\营销活动(clicks).csv");
        List<String[]> activityRows = parseCSV2Rows(activity);

        File jump = new File(dir + "\\营销活动(跳出).csv");
        List<String[]> jumpRows = parseCSV2Rows(jump);

        List<BaseTable> baseTableList = new ArrayList<BaseTable>();
        BaseTable baseTable = null;

        for (String[] row1 : visitRows) {
            baseTable = new BaseTable();
            if (row1.length != 6 || null == row1[0]) {
                continue;
            }
            baseTable.setMic(row1[1]);
            baseTable.setVisits(row1[2]);
            baseTable.setPv(row1[4]);
            baseTable.setViewTime(row1[5]);
            for (String[] row2 : avisitRows) {
                if (row2.length == 4 && row2[1].equals(baseTable.getMic())) {
                    baseTable.setVisitor(row2[2]);
                }
            }
            for (String[] row3 : activityRows) {
                if (row3.length == 4 && row3[1].equals(baseTable.getMic())) {
                    baseTable.setClick(row3[2]);
                }
            }
            for (String[] row4 : jumpRows) {
                if (row4.length == 4 && row4[1].equals(baseTable.getMic())) {
                    baseTable.setBounceVisit(row4[2]);
                }
            }
            baseTableList.add(baseTable);
        }
        return baseTableList;
    }
    
    /**
     * 获取后端监测日转化数据表数据
     * @param dir
     * @return
     */
    public static List<TbAmpBackendTransDaily> getTbAmpBackendTransDaily(String dir){
		File macketFile = new File(dir + "\\营销活动(落地页元素点击)去掉nv.csv");
		List<String[]> macketRows = parseCSV1Rows(macketFile);
		
		File transactionFile = new File(dir + "\\营销活动_办业务转化.csv");
		List<String[]> transactionRows = parseCSV2Rows(transactionFile);
		
		File phonBuyFile = new File(dir + "\\营销活动_买手机转化.csv");
		List<String[]> phonBuyRows = parseCSV2Rows(phonBuyFile);
		
		File setMealFile = new File(dir + "\\营销活动_办套餐转化.csv");
		List<String[]> setMealRows = parseCSV2Rows(setMealFile);
		
		File partsFile = new File(dir + "\\营销活动_挑配件转化.csv");
		List<String[]> partsRows = parseCSV2Rows(partsFile);
		
		List<TbAmpBackendTransDaily> tbAmpBackendTransDailyList = new ArrayList<TbAmpBackendTransDaily>();
		TbAmpBackendTransDaily tbAmpBackendTransDaily = null;
		
		for(String[] row1:macketRows){
			tbAmpBackendTransDaily = new TbAmpBackendTransDaily();
			if(row1.length != 6 || null == row1[0]){
				continue;
			}
			else{
				if(row1[2].equals("合计")){
			tbAmpBackendTransDaily.setMic(row1[1]);
			tbAmpBackendTransDaily.setBehaviorVV(row1[5]);
			for(String[] row2 : transactionRows){
				if(row2.length == 6 && row2[1].equals(tbAmpBackendTransDaily.getMic()))
				{
					if(row2[2].equals("办理成功"))
					tbAmpBackendTransDaily.setTransactionVV(row2[3]);
				}	
			}
			
			for(String[] row3 : phonBuyRows){
				if(row3.length == 6  && row3[1].equals(tbAmpBackendTransDaily.getMic()))
				{
					if(row3[2].equals("提交订单按钮"))
					tbAmpBackendTransDaily.setPhonBuyVV(row3[3]);
				}	
			}
			
			for(String[] row4 : setMealRows){
				if(row4.length == 6 && row4[1].equals(tbAmpBackendTransDaily.getMic()))
				{
					if(row4[2].equals("办理成功"))
					tbAmpBackendTransDaily.setSetMealVV(row4[3]);
				}	
			}
			
			for(String[] row5 : partsRows){
				if(row5.length == 6  && row5[1].equals(tbAmpBackendTransDaily.getMic()))
				{
					if(row5[2].equals("提交订单按钮"))
					tbAmpBackendTransDaily.setPartsVV(row5[3]);
				}	
			}
			
			tbAmpBackendTransDailyList.add(tbAmpBackendTransDaily);
		}
			}
		}
		return tbAmpBackendTransDailyList;
		
	}
    
    /**
     * 获取营销流量数据表数据
     * @param dir
     * @return
     */
    public static List<TbAmpFlowMarketingDaily> getTbAmpFlowMarketingDaily(String dir){
    	
    	File ampFlowMarketFile = new File(dir + "\\营销流量allhits_WT.es-new.csv");
    	List<String[]> ampFlowMarketRows = parseCSV2Rows(ampFlowMarketFile);
    	
    	List<TbAmpFlowMarketingDaily> tbAmpFlowMarketingDailyList = new ArrayList<TbAmpFlowMarketingDaily>();
    	TbAmpFlowMarketingDaily tbAmpFlowMarketingDaily = null;
		for(String[] row1:ampFlowMarketRows){
			
			tbAmpFlowMarketingDaily = new TbAmpFlowMarketingDaily();
			if(row1.length != 6 || null == row1[0]){
				continue;
			}
			if(row1[2].equals("合计")){
				continue;
			}
			tbAmpFlowMarketingDaily.setMic(row1[1]);
			tbAmpFlowMarketingDaily.setUrl(row1[2]);
			tbAmpFlowMarketingDaily.setVisits(row1[3]);
			tbAmpFlowMarketingDaily.setPv(row1[5]);
			
			tbAmpFlowMarketingDailyList.add(tbAmpFlowMarketingDaily);
			
		}
		return tbAmpFlowMarketingDailyList;
    	
    }
    
    /**
     * 获取自然流量数据表数据
     * @param dir
     * @return
     */
    
    //乱码乱码会导致空指针
    public static List<TbAmpFlowNatureDaily> getTbAmpFlowNatureDaily(String dir){
    	
    	File mcidPortalFile = new File(dir + "\\访前网站_入站页(排除mcid)门户pc.csv");
    	List<String[]> mcidPortalRows = parseCSV2Rows(mcidPortalFile);
    	
    	File mcidShopFile = new File(dir + "\\访前网站_入站页(排除mcid)shop.csv");
    	List<String[]> mcidShopRows = parseCSV2Rows(mcidShopFile);
    	
    	File mcidTouchFile = new File(dir + "\\访前网站_入站页(排除mcid)touch.csv");
    	List<String[]> mcidTouchRows = parseCSV2Rows(mcidTouchFile);
    	
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
    	  	
    	for(String[] row2: mcidShopRows){
    		
    		tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
    		if(row2.length != 10 || null == row2[0]){
				continue;
			}
    		
    		if(row2[4] != null && row2[4].equals("合计") ){
				continue;
			}
    		
    		tbAmpFlowNatureDaily.setUrl(row2[2]);
    		tbAmpFlowNatureDaily.setEntryPage(row2[4]);
    		tbAmpFlowNatureDaily.setVisits(row2[7]);
    		tbAmpFlowNatureDaily.setPv(row2[9]);
    		
    		
    		tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
    	}

    	for(String[] row3: mcidTouchRows){
    		
    		tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
    		if(row3.length != 10 || null == row3[0]){
				continue;
			}
    		
    		if(row3[4] != null && row3[4].equals("合计")  ){
				continue;
			}
    		
    		tbAmpFlowNatureDaily.setUrl(row3[2]);
    		tbAmpFlowNatureDaily.setEntryPage(row3[4]);
    		tbAmpFlowNatureDaily.setVisits(row3[7]);
    		tbAmpFlowNatureDaily.setPv(row3[9]);
    		
    		
    		tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
    	}

		return tbAmpFlowNatureDailyList;
    	
		
    	
    }
    
    /**
     * 获取聚合页流量数据表数据
     * @param dir
     * @return
     */
    public static List<TbAmpFlowTotalDaily> getTbAmpFlowTotalDaily(String dir){
       	
    	File portalFile = new File(dir + "\\门户_页.csv");
    	List<String[]> portalRows = parseCSV2Rows(portalFile);
    	
    	File shopFile = new File(dir + "\\shop_页.csv");
    	List<String[]> shopRows = parseCSV2Rows(shopFile);
    	
    	File touchFile = new File(dir + "\\touch_页.csv");
    	List<String[]> touchRows = parseCSV2Rows(touchFile);
    	
    	List<TbAmpFlowTotalDaily> tbAmpFlowTotalDailyList = new ArrayList<TbAmpFlowTotalDaily>();
    	TbAmpFlowTotalDaily tbAmpFlowTotalDaily = null;
    	
    	for(String[] row1: portalRows){
    		
    		tbAmpFlowTotalDaily = new TbAmpFlowTotalDaily();
    		if(row1.length != 8 || null == row1[0]){
				continue;
			}
    		tbAmpFlowTotalDaily.setUrl(row1[2]);
    		tbAmpFlowTotalDaily.setVisits(row1[5]);
    		tbAmpFlowTotalDaily.setPv(row1[6]);
    		tbAmpFlowTotalDaily.setViewTime(row1[7]);
    		
    		tbAmpFlowTotalDailyList.add(tbAmpFlowTotalDaily);
    	}
    	
    	for(String[] row2: shopRows){
    		
    		tbAmpFlowTotalDaily = new TbAmpFlowTotalDaily();
    		if(row2.length != 8 || null == row2[0]){
				continue;
			}
    		tbAmpFlowTotalDaily.setUrl(row2[2]);
    		tbAmpFlowTotalDaily.setVisits(row2[5]);
    		tbAmpFlowTotalDaily.setPv(row2[6]);
    		tbAmpFlowTotalDaily.setViewTime(row2[7]);
    		
    		tbAmpFlowTotalDailyList.add(tbAmpFlowTotalDaily);
    	}
    	
    	for(String[] row3: touchRows){
    		
    		tbAmpFlowTotalDaily = new TbAmpFlowTotalDaily();
    		if(row3.length != 8 || null == row3[0]){
				continue;
			}
    		tbAmpFlowTotalDaily.setUrl(row3[2]);
    		tbAmpFlowTotalDaily.setVisits(row3[5]);
    		tbAmpFlowTotalDaily.setPv(row3[6]);
    		tbAmpFlowTotalDaily.setViewTime(row3[7]);
    		
    		tbAmpFlowTotalDailyList.add(tbAmpFlowTotalDaily);
    	}
    	
    	

		return tbAmpFlowTotalDailyList;
    	
    }

    public static List<String[]> parseCSV2Rows(File filename) {
        CsvParserSettings visitsSetting = new CsvParserSettings();
        visitsSetting.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(visitsSetting);
        List<String[]> allRows = parser.parseAll(filename, "GB2312");
        return allRows;
    }
    public static List<String[]> parseCSV1Rows(File filename) {
        CsvParserSettings visitsSetting = new CsvParserSettings();
        visitsSetting.getFormat().setLineSeparator("\n");
        CsvParser parser = new CsvParser(visitsSetting);
        List<String[]> allRows = parser.parseAll(filename, "UTF-8");
        return allRows;
    }

//    public static void main(String[] args) {
//        List<BaseTable> list = getBaseTable("d:\\csvs");
//        for (BaseTable baseTable : list) {
//            System.out.println(baseTable.toString1());
//        }
//        System.out.println(list.size());
//    }
    
    

  public static void main(String[] args) {
      List<TbAmpFlowNatureDaily> list = getTbAmpFlowNatureDaily("d:\\csvs");
      for (TbAmpFlowNatureDaily baseTable : list) {
          System.out.println(baseTable.toString());
      }
      System.out.println(list.size());
  }

//  List<TbAmpFlowTotalDaily> list = getTbAmpFlowTotalDaily("d:\\csvs");
//  for (TbAmpFlowTotalDaily baseTable : list) {
//      System.out.println(baseTable.toString());
//  }
//  System.out.println(list.size());
//}
}
