package com.shuanghe.excel;

import java.util.List;

/**
 * Description:
 * <p>
 * Date: 2018/10/17
 * Time: 23:30
 *
 * @author yushuanghe
 */
public class ExcelReaderUtil {
    //excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    //excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";

    /**
     * 每获取一条记录，即打印
     * 在flume里每获取一条记录即发送，而不必缓存起来，可以大大减少内存的消耗，这里主要是针对flume读取大数据量excel来说的
     *
     * @param sheetName
     * @param sheetIndex
     * @param curRow
     * @param cellList
     */
    public static void sendRows(String filePath, String sheetName, int sheetIndex, int curRow, List<String> cellList) {
        StringBuffer oneLineSb = new StringBuffer();
        oneLineSb.append(filePath);
        oneLineSb.append("--");
        oneLineSb.append("sheet").append(sheetIndex);

        //加上sheet名
        oneLineSb.append("::").append(sheetName);
        oneLineSb.append("--");
        oneLineSb.append("row").append(curRow);
        oneLineSb.append("::");
        for (String cell : cellList) {
            oneLineSb.append(cell.trim());
            oneLineSb.append("|");
        }
        String oneLine = oneLineSb.toString();
        if (oneLine.endsWith("|")) {
            oneLine = oneLine.substring(0, oneLine.lastIndexOf("|"));
        }// 去除最后一个分隔符

        System.out.println(oneLine);
    }

    public static void readExcel(String fileName) throws Exception {
        int totalRows = 0;

        if (fileName.endsWith(EXCEL03_EXTENSION)) {
            //处理excel2003文件
            ExcelXlsReader excelXls = new ExcelXlsReader();
            totalRows = excelXls.process(fileName);

        } else if (fileName.endsWith(EXCEL07_EXTENSION)) {
            //处理excel2007文件
            ExcelXlsxReader excelXlsxReader = new ExcelXlsxReader();
            totalRows = excelXlsxReader.process(fileName);
        } else {
            throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
        }
        System.out.println("发送的总行数：" + totalRows);
    }

    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\yushuanghe\\Documents\\happyelements\\乐元素_1009.xlsx";
        ExcelReaderUtil.readExcel(path);
    }
}