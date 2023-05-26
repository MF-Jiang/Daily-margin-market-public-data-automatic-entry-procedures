package Pachong1;//取出数据


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
    public static String[] fdata = new String[20];
    /**
     *
     * @param filePath  需要读取的文件路径
     * @param column  指定需要获取的列数，例如第一列 1
     * @param startRow 指定从第几行开始读取数据
     * @param  endRow 指定结束行
     * @return 返回读取列数据的set
     */
    public static HashSet<String> getColumnSet(String filePath, int column, int startRow , int endRow){
        Workbook wb = readExcel(filePath); //文件
        Sheet sheet = wb.getSheetAt(0); //sheet
        int rownum = sheet.getPhysicalNumberOfRows(); //行数
        Row row = null;
        HashSet<String> result = new HashSet<>();
        String cellData = null;

        if(wb != null){
            for (int i=startRow-1; i<=endRow; i++){
                //System.out.println(i);
                row = sheet.getRow(i);
                if(row !=null){
                    cellData = (String) getCellFormatValue(row.getCell(column-1));
                    result.add(cellData.replaceAll(" ", ""));
                }else{
                    break;
                }
                //System.out.println(cellData);
                fdata[i]=cellData;
                //System.out.println(fdata[i]);
                //System.out.println(cellData);
            }
        }
        return  result;
    }

    /**
     *
     * @param filePath 需要读取的文件路径
     * @param column 指定需要获取的列数，例如第一列 1
     * @param startRow 指定从第几行开始读取数据
     * @return  返回读取列数据的set
     */
    public static HashSet<String> getColumnSet(String filePath, int column, int startRow){
        Workbook wb = readExcel(filePath); //文件
        Sheet sheet = wb.getSheetAt(0); //sheet
        int rownum = sheet.getPhysicalNumberOfRows(); //行数
        //System.out.println("sumrows " + rownum);

        return  getColumnSet(filePath, column, startRow , rownum-1);
    }



    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    cell.setCellType(CellType.STRING);  //将数值型cell设置为string型
                    cellValue = cell.getStringCellValue();
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }

    public static String[] takeoutfromnew(){
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        //System.out.println(w);

        if (w!=1){
            calendar.add(Calendar.DAY_OF_MONTH, -1);  // 在当前日基础上-1
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
            //System.out.println(format.format(calendar.getTime()));

            String getdatapath = "融资融券市场每日数据统计"+format.format((calendar.getTime()))+".xls";
            HashSet<String> columnSet = ExcelUtil.getColumnSet(getdatapath, 4, 2);
        }else {
            calendar.add(Calendar.DAY_OF_MONTH, -3);  // 周一在当前日基础上-3
            SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
            //System.out.println(format.format(calendar.getTime()));

            String getdatapath = "融资融券市场每日数据统计"+format.format((calendar.getTime()))+".xls";
            HashSet<String> columnSet = ExcelUtil.getColumnSet(getdatapath, 4, 2);
        }




        //读取第4列的从第2行开始往后的数据 到set
        //System.out.println(Arrays.toString(fdata));
        return fdata;

    }

    //@org.testng.annotations.Test
    public static void main(String[] args) {
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);  // 在当前日基础上-1
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
        //System.out.println(format.format(calendar.getTime()));

        String getdatapath = "融资融券市场每日数据统计"+format.format((calendar.getTime()))+".xls";

        HashSet<String> columnSet = ExcelUtil.getColumnSet(getdatapath, 4, 2);
        //读取第4列的从第2行开始往后的数据 到set
        //System.out.println(fdata);
        System.out.println(Arrays.toString(fdata));
    }

}
