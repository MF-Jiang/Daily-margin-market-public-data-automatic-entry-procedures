package Pachong1;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class WriteExcel {

    private String pathname;
    private Workbook workbook;
    private Sheet sheet1;

    /**使用栗子
     * WriteExcel excel = new WriteExcel("D:\\myexcel.xlsx");
     * excel.write(new String[]{"1","2"}, 0);//在第1行第1个单元格写入1,第一行第二个单元格写入2
     */
    public void write(String[] writeStrings, int rowNumber) throws Exception {
        //将内容写入指定的行号中
        Row row = sheet1.getRow(rowNumber);
        Boolean getin = false;
        int i = 2;
        //遍历整行中的列序号
        while (getin==false){
            //根据行指定列坐标j,然后在单元格中写入数据
            Cell initialcell = row.getCell(i);
            if (initialcell!=null){
                i++;
                continue;
            }
            Cell cell = row.createCell(i);
            cell.setCellValue(writeStrings[0]);
            getin = true;



        }
        OutputStream stream = new FileOutputStream(pathname);
        workbook.write(stream);
        stream.close();
    }

    public WriteExcel(String excelPath) throws Exception {
        //在excelPath中需要指定具体的文件名(需要带上.xls或.xlsx的后缀)
        this.pathname = excelPath;
        String fileType = excelPath.substring(excelPath.lastIndexOf(".") + 1, excelPath.length());
        //创建文档对象
        if (fileType.equals("xls")) {
            //如果是.xls,就new HSSFWorkbook()
            workbook = ExcelUtil.readExcel(excelPath);
        } else if (fileType.equals("xlsx")) {
            //如果是.xlsx,就new XSSFWorkbook()
            workbook = ExcelUtil.readExcel(excelPath);
        } else {
            throw new Exception("format wrong!!！");
        }
        // 创建表sheet
        sheet1 = workbook.getSheet("sheet1");
    }

    public static void  Writeinexcel() throws Exception {
        WriteExcel excel = new WriteExcel("融资融券市场每日数据统计汇总.xlsx");
        String[] needin = ExcelUtil.takeoutfromnew();
        //System.out.println(needin[0]);
        for (int i=1;i<needin.length;i++){
            excel.write(new String[]{needin[i]},i);
        }
    }

    public static void main(String[] args) throws Exception {
        WriteExcel excel = new WriteExcel("融资融券市场每日数据统计汇总.xlsx");
        String[] needin = ExcelUtil.takeoutfromnew();
        System.out.println(needin[1]);
        for (int i=1;i<needin.length;i++){
            excel.write(new String[]{needin[i]},i);
        }

    }
}