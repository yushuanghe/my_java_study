package readCsv;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * java读写csv文件demo
 * Created by yushuanghe on 2017/07/18.
 */
public class Java2Csv {
    public static void main(String[] args) {
        try {
            readerCsv("C:\\Users\\yushuanghe\\Documents\\Tencent Files\\260062680\\FileRecv\\jilin_haodf.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取csv
     *
     * @param csvFilePath
     * @throws Exception
     */
    public static void readerCsv(String csvFilePath) throws Exception {

        CsvReader reader = new CsvReader(csvFilePath, ',',
                Charset.forName("utf-8"));// shift_jis日语字体,utf-8
        reader.readHeaders();
        String[] headers = reader.getHeaders();

        List<Object[]> list = new ArrayList<Object[]>();
        while (reader.readRecord()) {
            list.add(reader.getValues());
        }
        Object[][] datas = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            datas[i] = list.get(i);
        }

        /*
         * 以下输出
         */

        for (int i = 0; i < headers.length; i++) {
            System.out.print(headers[i] + "\t");
        }
        System.out.println("");

        for (int i = 0; i < datas.length; i++) {
            Object[] data = datas[i]; // 取出一组数据
            System.out.print("第" + (i + 1) + "行：");
            for (int j = 0; j < data.length; j++) {
                Object cell = data[j];
                System.out.print(cell + "\t");
            }
            System.out.println("");
        }
    }

    /**
     * 写入csv
     *
     * @param csvFilePath 文件名路径 +文件名字
     * @param data        数据项
     */
    public static void writerCsv(String csvFilePath, String[][] data) {

        CsvWriter writer = null;
        try {
            writer = new CsvWriter(csvFilePath, ',', Charset.forName("GBK"));// shift_jis日语字体,utf-8

            for (int i = 0; i < data.length; i++) {
                writer.writeRecord(data[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    /**
     * 读取多个csv文件，并且为文件增加id与city列
     *
     * @param csvFilePaths
     * @return
     */
    private static String[][] readCsv(String[] csvFilePaths, String city) {
        String inputPath = "";

        CsvReader reader = null;// shift_jis日语字体,utf-8
        List<String[]> list = new ArrayList<String[]>();

        try {
            for (String csvFilePath : csvFilePaths) {
                reader = new CsvReader(inputPath + csvFilePath, ',', Charset.forName("utf-8"));
                reader.readHeaders();
                String[] headers = reader.getHeaders();

                while (reader.readRecord()) {
                    list.add(reader.getValues());
                }
            }

            String[][] datas = new String[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                datas[i] = list.get(i);
                datas[i] = Java2Csv.insertElement(datas[i], String.valueOf(i + 1), 0);
                datas[i] = Java2Csv.insertElement(datas[i], city, 1);
            }
            return datas;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    /**
     * 增加数组元素，用于给csv文件增加列
     *
     * @param original
     * @param element
     * @param index
     * @return
     */
    public static String[] insertElement(String original[], String element, int index) {
        int length = original.length;
        String destination[] = new String[length + 1];
        System.arraycopy(original, 0, destination, 0, index);
        destination[index] = element;
        System.arraycopy(original, index, destination, index
                + 1, length - index);
        return destination;
    }
}
