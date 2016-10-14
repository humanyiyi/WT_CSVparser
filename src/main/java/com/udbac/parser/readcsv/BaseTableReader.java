package com.udbac.parser.readcsv;

import com.udbac.parser.entity.*;
import com.udbac.parser.util.TimeUtil;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 43890 on 2016/9/19.
 */
public class BaseTableReader {
    private static Logger logger = Logger.getLogger(BaseTableReader.class);

    /**
     * 获取监测日数据基础表数据
     *
     * @param dir
     * @return
     */
    public static List<TbAmpBackendBaseDaily> getTbAmpBackendBaseDaily(String dir) {
        List<TbAmpBackendBaseDaily> baseTableList = new ArrayList<TbAmpBackendBaseDaily>();
        TbAmpBackendBaseDaily baseTable;
        File visitsFile = new File(dir + "\\营销活动(访次).csv");

        File avisitFile = new File(dir + "\\营销活动(访客).csv");

        File activityFile = new File(dir + "\\营销活动(clicks).csv");

        File jumpFile = new File(dir + "\\营销活动(跳出).csv");

        if (!visitsFile.exists()) {
            logger.error(visitsFile + "文件不存在。");
//            System.out.println(visitsFile + "文件不存在。");
        } else if (!avisitFile.exists()) {
            logger.error(avisitFile + "文件不存在。");
//            System.out.println(avisitFile + "文件不存在。");
        } else if (!activityFile.exists()) {
            logger.error(activityFile + "文件不存在。");
//            System.out.println(activityFile + "文件不存在。");
        } else if (!jumpFile.exists()) {
            logger.error(jumpFile + "文件不存在。");
//            System.out.println(jumpFile + "文件不存在。");
        } else {
            List<String[]> visitRows = parseCSV2Rows(visitsFile);
            List<String[]> avisitRows = parseCSV2Rows(avisitFile);
            List<String[]> activityRows = parseCSV2Rows(activityFile);
            List<String[]> jumpRows = parseCSV2Rows(jumpFile);
            if (!getTime(visitRows).equals(TimeUtil.getYesterday())) {
                logger.error(visitsFile + "中日期不对。");
//                System.out.println(visitsFile + "中日期不对。");
            } else if (!getTime(avisitRows).equals(TimeUtil.getYesterday())) {
                logger.error(avisitFile + "中日期不对。");
//                System.out.println(avisitFile + "中日期不对。");
            } else if (!getTime(activityRows).equals(TimeUtil.getYesterday())) {
                logger.error(activityFile + "中日期不对。");
//                System.out.println(activityFile + "中日期不对。");
            } else if (!getTime(jumpRows).equals(TimeUtil.getYesterday())) {
                logger.error(jumpFile + "中日期不对。");
//                System.out.println(jumpFile + "中日期不对。");
            } else if (visitRows.size() < 2000) {
                logger.error(visitsFile + "文件中数据质量存在问题。");
//                System.out.println(visitsFile + "文件中数据质量存在问题。");
            } else if (avisitRows.size() < 2000) {
                logger.error(avisitFile + "文件中数据质量存在问题。");
//                System.out.println(avisitFile + "文件中数据质量存在问题。");
            } else if (activityRows.size() < 2000) {
                logger.error(activityFile + "文件中数据质量存在问题。");
//                System.out.println(activityFile + "文件中数据质量存在问题。");
            } else if (jumpRows.size() < 1600) {
                logger.error(jumpFile + "文件中数据质量存在问题。");
//                System.out.println(jumpFile + "文件中数据质量存在问题。");
            } else {
                for (String[] row1 : visitRows) {
                    baseTable = new TbAmpBackendBaseDaily();
                    if (row1.length != 6 || null == row1[0]) {
                        continue;
                    }
                    baseTable.setCreateDate(getTime(visitRows));
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
            }
        }
        return baseTableList;
    }

    /**
     * 获取后端监测日转化数据表数据
     *
     * @param dir
     * @return
     */
    public static List<TbAmpBackendTransDaily> getTbAmpBackendTransDaily(String dir) {

        List<TbAmpBackendTransDaily> tbAmpBackendTransDailyList = new ArrayList<TbAmpBackendTransDaily>();
        TbAmpBackendTransDaily tbAmpBackendTransDaily = null;

        File macketFile = new File(dir + "\\营销活动(落地页元素点击)去掉nv.csv");

        File transactionFile = new File(dir + "\\营销活动_办业务转化.csv");

        File phonBuyFile = new File(dir + "\\营销活动_买手机转化.csv");

        File setMealFile = new File(dir + "\\营销活动_办套餐转化.csv");

        File partsFile = new File(dir + "\\营销活动_挑配件转化.csv");
        if (!macketFile.exists()) {
            logger.error(macketFile + "文件不存在。");
//            System.out.println(macketFile + "文件不存在。");
        } else if (!transactionFile.exists()) {
            logger.error(transactionFile + "文件不存在。");
//            System.out.println(transactionFile + "文件不存在。");
        } else if (!phonBuyFile.exists()) {
            logger.error(phonBuyFile + "文件不存在。");
//            System.out.println(phonBuyFile + "文件不存在。");
        } else if (!setMealFile.exists()) {
            logger.error(setMealFile + "文件不存在。");
//            System.out.println(setMealFile + "文件不存在。");
        } else if (!partsFile.exists()) {
            logger.error(partsFile + "文件不存在。");
//            System.out.println(partsFile + "文件不存在。");
        } else {
            List<String[]> macketRows = parseCSV1Rows(macketFile);
            List<String[]> transactionRows = parseCSV2Rows(transactionFile);
            List<String[]> phonBuyRows = parseCSV2Rows(phonBuyFile);
            List<String[]> setMealRows = parseCSV2Rows(setMealFile);
            List<String[]> partsRows = parseCSV2Rows(partsFile);
            if (!getTime(macketRows).equals(TimeUtil.getYesterday())) {
                logger.error(macketFile + "中日期不对。");
//                System.out.println(macketFile + "中日期不对。");
            } else if (!getTime(transactionRows).equals(TimeUtil.getYesterday())) {
                logger.error(transactionFile + "中日期不对。");
//                System.out.println(transactionFile + "中日期不对。");
            } else if (!getTime(phonBuyRows).equals(TimeUtil.getYesterday())) {
                logger.error(phonBuyFile + "中日期不对。");
//                System.out.println(phonBuyFile + "中日期不对。");
            } else if (!getTime(setMealRows).equals(TimeUtil.getYesterday())) {
                logger.error(setMealFile + "中日期不对。");
//                System.out.println(setMealFile + "中日期不对。");
            } else if (!getTime(partsRows).equals(TimeUtil.getYesterday())) {
                logger.error(partsFile + "中日期不对。");
//                System.out.println(partsFile + "中日期不对。");
            } else if (macketRows.size() < 1000) {
                logger.error(macketFile + "文件中数据质量存在问题。");
//                System.out.println(macketFile + "文件中数据质量存在问题。");
            } else if (transactionRows.size() < 1000) {
                logger.error(transactionFile + "文件中数据质量存在问题。");
//                System.out.println(transactionFile + "文件中数据质量存在问题。");
            } else if (phonBuyRows.size() < 1000) {
                logger.error(phonBuyFile + "文件中数据质量存在问题。");
//                System.out.println(phonBuyFile + "文件中数据质量存在问题。");
            } else if (setMealRows.size() < 1000) {
                logger.error(setMealFile + "文件中数据质量存在问题。");
//                System.out.println(setMealFile + "文件中数据质量存在问题。");
            } else if (partsRows.size() < 1000) {
                logger.error(partsFile + "文件中数据质量存在问题。");
//                System.out.println(partsFile + "文件中数据质量存在问题。");
            } else {
                for (String[] row1 : macketRows) {
                    tbAmpBackendTransDaily = new TbAmpBackendTransDaily();
                    if (row1.length != 6 || null == row1[0]) {
                        continue;
                    } else {
                        if (row1[2].equals("合计")) {
                            tbAmpBackendTransDaily.setCreateDate(getTime(macketRows));
                            tbAmpBackendTransDaily.setMic(row1[1]);
                            tbAmpBackendTransDaily.setBehaviorVV(row1[5]);
                            for (String[] row2 : transactionRows) {
                                if (row2.length == 6 && row2[1].equals(tbAmpBackendTransDaily.getMic())) {
                                    if (row2[2].equals("办理成功"))
                                        tbAmpBackendTransDaily.setTransactionVV(row2[3]);
                                }
                            }

                            for (String[] row3 : phonBuyRows) {
                                if (row3.length == 6 && row3[1].equals(tbAmpBackendTransDaily.getMic())) {
                                    if (row3[2].equals("提交订单按钮"))
                                        tbAmpBackendTransDaily.setPhonBuyVV(row3[3]);
                                }
                            }

                            for (String[] row4 : setMealRows) {
                                if (row4.length == 6 && row4[1].equals(tbAmpBackendTransDaily.getMic())) {
                                    if (row4[2].equals("办理成功"))
                                        tbAmpBackendTransDaily.setSetMealVV(row4[3]);
                                }
                            }

                            for (String[] row5 : partsRows) {
                                if (row5.length == 6 && row5[1].equals(tbAmpBackendTransDaily.getMic())) {
                                    if (row5[2].equals("提交订单按钮"))
                                        tbAmpBackendTransDaily.setPartsVV(row5[3]);
                                }
                            }

                            tbAmpBackendTransDailyList.add(tbAmpBackendTransDaily);
                        }
                    }
                }
            }
        }

        return tbAmpBackendTransDailyList;

    }

    /**
     * 获取营销流量数据表数据
     *
     * @param dir
     * @return
     */
    public static List<TbAmpFlowMarketingDaily> getTbAmpFlowMarketingDaily(String dir) {

        File ampFlowMarketFile = new File(dir + "\\营销流量allhits_WT.es-new.csv");
//        File ampFlowMarketFile = new File(dir + "\\book1.csv");
        List<String[]> ampFlowMarketRows;
//        CsvUtil ampFlowMarketUtil ;

        List<TbAmpFlowMarketingDaily> tbAmpFlowMarketingDailyList = new ArrayList<TbAmpFlowMarketingDaily>();
        TbAmpFlowMarketingDaily tbAmpFlowMarketingDaily = null;
        if (!ampFlowMarketFile.exists()) {
            logger.error(ampFlowMarketFile + "文件不存在。");
//            System.err.println(ampFlowMarketFile + "文件不存在。");
        } else {
            ampFlowMarketRows = parseCSV2Rows(ampFlowMarketFile);
            if (!getTime(ampFlowMarketRows).equals(TimeUtil.getYesterday())) {
                logger.error(ampFlowMarketFile + "中日期不对。");
//                System.out.println(ampFlowMarketFile + "中日期不对。");
            } else if (ampFlowMarketRows.size() < 1000) {
                logger.error(ampFlowMarketFile + "文件中数据质量存在问题。");
//                System.out.println(ampFlowMarketFile + "文件中数据质量存在问题。");
            } else {
                for (String[] row1 : ampFlowMarketRows) {
                    tbAmpFlowMarketingDaily = new TbAmpFlowMarketingDaily();
                    if (row1.length != 6 || null == row1[0]) {
                        continue;
                    }
                    if (row1[2].equals("合计")) {
                        continue;
                    }
                    tbAmpFlowMarketingDaily.setCreateDate(getTime(ampFlowMarketRows));
                    tbAmpFlowMarketingDaily.setMic(row1[1]);
                    tbAmpFlowMarketingDaily.setUrl(row1[2]);
                    tbAmpFlowMarketingDaily.setVisits(row1[3]);
                    tbAmpFlowMarketingDaily.setPv(row1[5]);

                    tbAmpFlowMarketingDailyList.add(tbAmpFlowMarketingDaily);

                }

            }
        }
        return tbAmpFlowMarketingDailyList;
    }

    /**
     * 获取自然流量数据表数据
     *
     * @param dir
     * @return
     */

    public static List<TbAmpFlowNatureDaily> getTbAmpFlowNatureDaily(String dir) {

        List<TbAmpFlowNatureDaily> tbAmpFlowNatureDailyList = new ArrayList<TbAmpFlowNatureDaily>();
        TbAmpFlowNatureDaily tbAmpFlowNatureDaily = null;

        File mcidPortalFile = new File(dir + "\\访前网站_入站页(排除mcid)门户pc.csv");

        File mcidPortalTouchFile = new File(dir + "\\访前网站_入站页(排除mcid)门户mobile.csv");

        File mcidShopFile = new File(dir + "\\访前网站_入站页(排除mcid)shop.csv");

        File mcidTouchFile = new File(dir + "\\访前网站_入站页(排除mcid)touch.csv");
        if (!mcidPortalFile.exists()) {
            logger.error(mcidPortalFile + "文件不存在。");
//            System.out.println(mcidPortalFile + "文件不存在。");
        } else if (!mcidPortalTouchFile.exists()) {
            logger.error(mcidPortalTouchFile + "文件不存在。");
        } else if (!mcidShopFile.exists()) {
            logger.error(mcidShopFile + "文件不存在。");
//            System.out.println(mcidShopFile + "文件不存在。");
        } else if (!mcidTouchFile.exists()) {
            logger.error(mcidTouchFile + "文件不存在。");
//            System.out.println(mcidTouchFile + "文件不存在。");
        } else {
            List<String[]> mcidPortalRows = parseCSV2Rows(mcidPortalFile);
            List<String[]> mcidPortalTouchRows = parseCSV2Rows(mcidPortalTouchFile);
            List<String[]> mcidShopRows = parseCSV2Rows(mcidShopFile);
            List<String[]> mcidTouchRows = parseCSV2Rows(mcidTouchFile);
            if (!getTime(mcidPortalRows).equals(TimeUtil.getYesterday())) {
                logger.error(mcidPortalFile + "中日期不对。");
//                System.out.println(mcidPortalFile + "中日期不对。");
            } else if (!getTime(mcidPortalTouchRows).equals(TimeUtil.getYesterday())) {
                logger.error(mcidPortalTouchFile + "中日期不对。");
            } else if (!getTime(mcidShopRows).equals(TimeUtil.getYesterday())) {
                logger.error(mcidShopFile + "中日期不对。");
//                System.out.println(mcidShopFile + "中日期不对。");
            } else if (!getTime(mcidTouchRows).equals(TimeUtil.getYesterday())) {
                logger.error(mcidTouchFile + "中日期不对。");
//                System.out.println(mcidTouchFile + "中日期不对。");
            } else if (mcidPortalRows.size() < 4560) {
                logger.error(mcidPortalFile + "文件中数据质量存在问题。");
//                System.out.println(mcidPortalFile + "文件中数据质量存在问题。");
            } else if (mcidPortalTouchRows.size() < 1000) {
                logger.error(mcidPortalTouchFile + "文件中数据质量存在问题。");
            } else if (mcidShopRows.size() < 10665) {
                logger.error(mcidShopFile + "文件中数据质量存在问题。");
//                System.out.println(mcidShopFile + "文件中数据质量存在问题。");
            } else if (mcidTouchRows.size() < 7730) {
                logger.error(mcidTouchFile + "文件中数据质量存在问题。");
//                System.out.println(mcidTouchFile + "文件中数据质量存在问题。");
            } else {
                for (String[] row1 : mcidPortalRows) {

                    tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
                    if (row1.length != 10 || null == row1[0]) {
                        continue;
                    }
                    if (row1[4] != null && row1[4].equals("合计")) {
                        continue;
                    }
                    tbAmpFlowNatureDaily.setCreateDate(getTime(mcidPortalRows));
                    tbAmpFlowNatureDaily.setClassfy("PORTAL_PC");
                    tbAmpFlowNatureDaily.setUrl(row1[2]);
                    tbAmpFlowNatureDaily.setEntryPage(row1[4]);
                    tbAmpFlowNatureDaily.setVisits(row1[7]);
                    tbAmpFlowNatureDaily.setPv(row1[9]);

                    tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
                }

                for (String[] row4 : mcidPortalRows) {

                    tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
                    if (row4.length != 10 || null == row4[0]) {
                        continue;
                    }
                    if (row4[4] != null && row4[4].equals("合计")) {
                        continue;
                    }
                    tbAmpFlowNatureDaily.setCreateDate(getTime(mcidPortalRows));
                    tbAmpFlowNatureDaily.setClassfy("PORTAL_MOBILE");
                    tbAmpFlowNatureDaily.setUrl(row4[2]);
                    tbAmpFlowNatureDaily.setEntryPage(row4[4]);
                    tbAmpFlowNatureDaily.setVisits(row4[7]);
                    tbAmpFlowNatureDaily.setPv(row4[9]);

                    tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
                }

                for (String[] row2 : mcidShopRows) {

                    tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
                    if (row2.length != 10 || null == row2[0]) {
                        continue;
                    }
                    if (row2[4] != null && row2[4].equals("合计")) {
                        continue;
                    }
                    tbAmpFlowNatureDaily.setCreateDate(getTime(mcidShopRows));
                    tbAmpFlowNatureDaily.setClassfy("SHOP");
                    tbAmpFlowNatureDaily.setUrl(row2[2]);
                    tbAmpFlowNatureDaily.setEntryPage(row2[4]);
                    tbAmpFlowNatureDaily.setVisits(row2[7]);
                    tbAmpFlowNatureDaily.setPv(row2[9]);

                    tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
                }

                for (String[] row3 : mcidTouchRows) {

                    tbAmpFlowNatureDaily = new TbAmpFlowNatureDaily();
                    if (row3.length != 10 || null == row3[0]) {
                        continue;
                    }
                    if (row3[4] != null && row3[4].equals("合计")) {
                        continue;
                    }
                    tbAmpFlowNatureDaily.setCreateDate(getTime(mcidTouchRows));
                    tbAmpFlowNatureDaily.setClassfy("TOUCH");
                    tbAmpFlowNatureDaily.setUrl(row3[2]);
                    tbAmpFlowNatureDaily.setEntryPage(row3[4]);
                    tbAmpFlowNatureDaily.setVisits(row3[7]);
                    tbAmpFlowNatureDaily.setPv(row3[9]);

                    tbAmpFlowNatureDailyList.add(tbAmpFlowNatureDaily);
                }
            }
        }
        return tbAmpFlowNatureDailyList;

    }

    /**
     * 获取聚合页流量数据表数据
     *
     * @param dir
     * @return
     */
    public static List<TbAmpFlowTotalDaily> getTbAmpFlowTotalDaily(String dir) {

        List<TbAmpFlowTotalDaily> tbAmpFlowTotalDailyList = new ArrayList<TbAmpFlowTotalDaily>();
        TbAmpFlowTotalDaily tbAmpFlowTotalDaily = null;

        File portalFile = new File(dir + "\\门户_页.csv");

        File shopFile = new File(dir + "\\shop_页.csv");

        File touchFile = new File(dir + "\\touch_页.csv");

        if (!portalFile.exists()) {
            logger.error(portalFile + "文件不存在。");
//            System.out.println(portalFile + "文件不存在。");
        } else if (!shopFile.exists()) {
            logger.error(shopFile + "文件不存在。");
//            System.out.println(shopFile + "文件不存在。");
        } else if (!touchFile.exists()) {
            logger.error(touchFile + "文件不存在。");
//            System.out.println(touchFile + "文件不存在。");
        } else {

            List<String[]> portalRows = parseCSV2Rows(portalFile);
            List<String[]> shopRows = parseCSV2Rows(shopFile);
            List<String[]> touchRows = parseCSV2Rows(touchFile);

            if (!getTime(portalRows).equals(TimeUtil.getYesterday())) {
                logger.error(portalFile + "中日期不对。");
//                System.out.println(portalFile + "中日期不对。");
            } else if (!getTime(shopRows).equals(TimeUtil.getYesterday())) {
                logger.error(shopFile + "中日期不对。");
//                System.out.println(shopFile + "中日期不对。");
            } else if (!getTime(touchRows).equals(TimeUtil.getYesterday())) {
                logger.error(touchFile + "中日期不对。");
//                System.out.println(touchFile + "中日期不对。");
            } else if (portalRows.size() < 5200) {
                logger.error(portalFile + "文件中数据质量存在问题。");
//                System.out.println(portalFile + "文件中数据质量存在问题。");
            } else if (shopRows.size() < 62320) {
                logger.error(shopFile + "文件中数据质量存在问题。");
//                System.out.println(shopFile + "文件中数据质量存在问题。");
            } else if (touchRows.size() < 21200) {
                logger.error(touchFile + "文件中数据质量存在问题。");
//                System.out.println(touchFile + "文件中数据质量存在问题。");
            } else {
                for (String[] row1 : portalRows) {
                    tbAmpFlowTotalDaily = new TbAmpFlowTotalDaily();
                    if (row1.length != 8 || null == row1[0]) {
                        continue;
                    }
                    tbAmpFlowTotalDaily.setCreateDate(getTime(portalRows));
                    tbAmpFlowTotalDaily.setClassfy("PORTAL");
                    tbAmpFlowTotalDaily.setUrl(row1[2]);
                    tbAmpFlowTotalDaily.setVisits(row1[5]);
                    tbAmpFlowTotalDaily.setPv(row1[6]);
                    tbAmpFlowTotalDaily.setViewTime(row1[7]);

                    tbAmpFlowTotalDailyList.add(tbAmpFlowTotalDaily);
                }

                for (String[] row2 : shopRows) {

                    tbAmpFlowTotalDaily = new TbAmpFlowTotalDaily();
                    if (row2.length != 8 || null == row2[0]) {
                        continue;
                    }
                    tbAmpFlowTotalDaily.setCreateDate(getTime(shopRows));
                    tbAmpFlowTotalDaily.setClassfy("SHOP");
                    tbAmpFlowTotalDaily.setUrl(row2[2]);
                    tbAmpFlowTotalDaily.setVisits(row2[5]);
                    tbAmpFlowTotalDaily.setPv(row2[6]);
                    tbAmpFlowTotalDaily.setViewTime(row2[7]);

                    tbAmpFlowTotalDailyList.add(tbAmpFlowTotalDaily);
                }

                for (String[] row3 : touchRows) {

                    tbAmpFlowTotalDaily = new TbAmpFlowTotalDaily();
                    if (row3.length != 8 || null == row3[0]) {
                        continue;
                    }
                    tbAmpFlowTotalDaily.setCreateDate(getTime(touchRows));
                    tbAmpFlowTotalDaily.setClassfy("TOUCH");
                    tbAmpFlowTotalDaily.setUrl(row3[2]);
                    tbAmpFlowTotalDaily.setVisits(row3[5]);
                    tbAmpFlowTotalDaily.setPv(row3[6]);
                    tbAmpFlowTotalDaily.setViewTime(row3[7]);

                    tbAmpFlowTotalDailyList.add(tbAmpFlowTotalDaily);
                }
            }
        }

        return tbAmpFlowTotalDailyList;

    }

    /**
     * 获取csv文件的日期
     *
     * @param ampRows
     * @return
     */
    public static String getTime(List<String[]> ampRows) {
        String date = null;
        for (int i = 0; i <= 50; i++) {
            String[] row1 = ampRows.get(i);
            if (row1.length == 2) {
                date = row1[0].substring(0, 10);
            }
        }
        return date;
    }

    public static List<TbAmpWEbDaily> getTbAmpWEbDaily(String dir) {

        File ampWebFile = new File(dir + "\\校园营销-物联网落地页元素点击0806-0828.csv");
        List<String[]> ampWebRows = parseCSV2Rows(ampWebFile);

        List<TbAmpWEbDaily> tbAmpWebDailyList = new ArrayList<TbAmpWEbDaily>();
        TbAmpWEbDaily tbAmpWebDaily = null;
        for (String[] row1 : ampWebRows) {
            tbAmpWebDaily = new TbAmpWEbDaily();
            if (row1.length != 7 || null == row1[0]) {
//				System.out.println(row1.length);
                continue;
            }
            tbAmpWebDaily.setMcid(row1[1]);
            tbAmpWebDaily.setEvent(row1[2]);
            tbAmpWebDaily.setVisits(row1[3]);
            tbAmpWebDaily.setVisitRate(row1[4]);
            tbAmpWebDaily.setClick(row1[5]);
            tbAmpWebDaily.setPageview(row1[6]);
            tbAmpWebDailyList.add(tbAmpWebDaily);
        }
        return tbAmpWebDailyList;
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
//        List<TbAmpBackendBaseDaily> list = getBaseTable("d:\\csvs");
//        for (TbAmpBackendBaseDaily baseTable : list) {
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
