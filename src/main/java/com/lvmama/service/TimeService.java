package com.lvmama.service;


import com.lvmama.dao.AttDetailMapper;
import com.lvmama.model.AttDetail;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeService {

    private static final Log LOG = LogFactory.getLog(TimeService.class);
    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private AttDetailMapper attDetailMapper;

    @Before
    public void setUp() throws Exception {

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        sqlSession = sqlSessionFactory.openSession();

        attDetailMapper = sqlSession.getMapper(AttDetailMapper.class);

    }

    @After
    public void after() throws Exception {
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test() {

        long startTime = System.currentTimeMillis();

        List<File> files = getExcelFileList("/home/wangchongbei/下载/work_t");

        List<AttDetail> result = transferToList(files);

        if (result.size() > 0) {
            for (AttDetail detail : result) {
//                insert(detail);
                attDetailMapper.insertAttDetail(detail);
            }
        }

        LOG.info("TimeUsed = " + (System.currentTimeMillis() - startTime));
    }


    private List<AttDetail> transferToList(List<File> fileList) {
        List<AttDetail> result = new ArrayList<AttDetail>();
        Date createTime = new Date();
        Map<String, List<Date>> dateMap = new HashMap<String, List<Date>>();

        if (fileList != null && fileList.size() > 0) {
            for (File file : fileList) {

                try {

                    XSSFWorkbook workbook = new XSSFWorkbook(FileUtils.openInputStream(file));

                    XSSFSheet sheet = workbook.getSheetAt(0);

                    int lastRowNum = sheet.getLastRowNum();

                    for (int i = 1; i <= lastRowNum; i++) {

                        XSSFRow row = sheet.getRow(i);
                        if (row.getCell(1) != null && row.getCell(1).getCellType() == 1 && "王重倍".equals(row.getCell(1).getStringCellValue())) {
                            Date tempDate = row.getCell(5).getDateCellValue();

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String ds = sdf.format(tempDate);

                            if (!dateMap.keySet().contains(ds)) {
                                List<Date> dateList = new ArrayList<Date>();
                                dateList.add(tempDate);
                                dateMap.put(ds, dateList);
                            } else {
                                List tempList = dateMap.get(ds);
                                if (!tempList.contains(tempDate)) {
                                    tempList.add(tempDate);
                                }
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            for (String key : dateMap.keySet()) {

                Collections.sort(dateMap.get(key));

                List<Date> dates = dateMap.get(key);

                AttDetail detail = new AttDetail();
                Date startTime = dates.get(0);
                Date endTime = dateMap.get(key).get(dates.size() - 1);
                detail.setStartTime(startTime);
                detail.setEndTime(endTime);
                detail.setCreateTime(createTime);
                detail.setMinute((endTime.getTime() - startTime.getTime()) / (1000 * 60));
                result.add(detail);
            }

        }
        LOG.info("Record Size:" + result.size() + " and Result:" + result.toString());

        return result;

    }

    private List<File> getExcelFileList(String directoryPath) {
        List<File> files = new ArrayList<File>();

        File fileDirectory = new File(directoryPath);
        if (fileDirectory.isDirectory()) {
            String[] fileList = fileDirectory.list();

            for (int i = 0; i < fileList.length; i++) {
                File file = new File(directoryPath + "//" + fileList[i]);
                if (!file.isDirectory() && file.getName().endsWith("xlsx") && file.getName().contains("副本") == false) {
                    files.add(file);
                    LOG.info("file:" + file.getName());
                }
            }

        }

        return files;
    }


    private static Connection getConnection() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.15.1.156:3306/mysql_test";
        String username = "root";
        String password = "111111";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    private static int insert(AttDetail detail) {
        Connection conn = getConnection();


        int i = 0;
        String sql = "insert into att_detail(name,start_time,end_time,minute,month," +
                "user_no,card_no,door_no,door_name," +
                "create_time,update_time)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stat;
        try {
            stat = (PreparedStatement) conn.prepareStatement(sql);
//            stat.setString(1, "haha");
//            stat.setDate(2, null);
            stat.setString(1, detail.getName());

            stat.setTimestamp(2, new Timestamp(detail.getStartTime().getTime()));
            stat.setTimestamp(3, new Timestamp(detail.getEndTime().getTime()));
            stat.setLong(4, detail.getMinute());
            stat.setInt(5, detail.getMonth());
            stat.setString(6, detail.getUserNo());
            stat.setLong(7, detail.getCardNo());
            stat.setLong(8, detail.getDoorNo());
            stat.setString(9, detail.getDoorName());
            stat.setTimestamp(10, new Timestamp(detail.getCreateTime().getTime()));
            stat.setDate(11, null);
            i = stat.executeUpdate();
            stat.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int batchSaveDetails(List<AttDetail> details, int stepSize) {
        int effectRows = 0;
        int count = 0;
        StringBuilder values = new StringBuilder();
        List<Object> params = new LinkedList<Object>();

        String prefix = "insert into att_detail(name,start_time,end_time,minute,month," +
                "user_no,card_no,door_no,door_name," +
                "create_time,update_time)VALUES";

        for (AttDetail detail : details) {
            if (count != 0) {
                values.append(",");
            }
            values.append("(?,?,?,?,?,?,?,?,?,?,?)");//? size = 24
            params.add(detail.getName());
            params.add(detail.getStartTime());
            params.add(detail.getEndTime());
            params.add(detail.getMinute());
            params.add(detail.getMonth());
            params.add(detail.getUserNo());
            params.add(detail.getCardNo());
            params.add(detail.getDoorNo());
            params.add(detail.getDoorName());
            params.add(detail.getCreateTime());
            params.add(detail.getUpdateTime());

            count++;
            if (count % stepSize == 0) {
//                effectRows += executeSql(prefix + values.toString(), params.toArray());
                count = 0;
                values = new StringBuilder();
                params.clear();
            }
        }

        if (count != 0) {
//            effectRows += executeSql(prefix + values.toString(), params.toArray());
        }

        return effectRows;
    }

}
/*if (row.getCell(1) != null && row.getCell(1).getCellType() == 1 && "王重倍".equals(row.getCell(1).getStringCellValue())) {

                            LOG.info("Date:" + row.getCell(5).getDateCellValue());

                            AttDetail detail = new AttDetail();
                            detail.setName(row.getCell(1).getStringCellValue());
                            detail.setMonth(201512);

                            detail.setCardNo(Long.parseLong(row.getCell(0).getRawValue()));
                            detail.setName(row.getCell(1).getStringCellValue());
                            detail.setDoorNo(Long.parseLong(row.getCell(3).getRawValue()));
                            detail.setDoorName(row.getCell(4).getStringCellValue());
                            detail.setUserNo(row.getCell(6).getStringCellValue());

                            row.getCell(5).getDateCellValue();

                            detail.setTempTime(row.getCell(5).getDateCellValue());

                            detail.setCreateTime(createTime);

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String ds = sdf.format(detail.getTempTime());

                            if (!dateMap.keySet().contains(ds)) {
                                List<Date> dateList = new ArrayList<Date>();
                                dateList.add(detail.getTempTime());
                                dateMap.put(ds, dateList);
                            } else {

                                List tempList = dateMap.get(ds);
                                if (!tempList.contains(detail.getTempTime())) {

                                    tempList.add(detail.getTempTime());
                                    Collections.sort(tempList);
                                    Date startTime = dateMap.get(ds).get(0);
                                    Date endTime = dateMap.get(ds).get(tempList.size() - 1);
                                    detail.setStartTime(startTime);
                                    detail.setEndTime(endTime);
                                    detail.setMinute((endTime.getTime() - startTime.getTime()) / (1000 * 60));
                                    result.add(detail);
                                }

                            }

                        }*/