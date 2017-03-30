package j2se.core.thread.MulitiThreadDemo;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 合并文件：合并由拆分文件拆分的文件
 * 要求将拆分文件放到一个文件中
 * 主要利用随机文件读取和文件输入输出流
 * Created by yobdc on 2016/12/21.
 */
public class FileCombination implements Runnable {

    // 拆分文件存放的目录
    String srcDirectory = null;
    // 结果文件存放目录
    String trueDirectory;
    // 存放所有拆分文件名
    String[] separatedFiles;
    // 存放所有拆分文件名及分件大小
    String[][] separatedFilesAndSize;
    // 确定文件个数
    int fileNum = 0;
    // 据拆分文件名确定现在原文件名
    String fileRealName = "";

    public FileCombination(String localFileAddress, String localFileAddress_tmp) {
        this.srcDirectory = localFileAddress_tmp;
        this.trueDirectory = localFileAddress;
    }

    @Override
    public void run() {
        getFileAttribute(srcDirectory);
        if (combFile()) {
            System.out.println("合并文件成功！");
            deleteTmp();
        } else
            System.out.println("合并文件失败！");
    }

    /**
     * 删除临时文件
     */
    private void deleteTmp() {
        for (int i = 0; i < fileNum; i++) {
            File file = new File(srcDirectory + "\\" + separatedFiles[i]);
            file.delete();
        }

        File src = new File(srcDirectory);
        src.delete();
    }

    /**
     * 合并文件：利用随机文件读写
     */
    private boolean combFile() {
        RandomAccessFile raf = null;
        long alreadyWrite = 0;
        FileInputStream fis = null;
        int len = 0;
        byte[] bt = new byte[1024];

        try {
            String realName = trueDirectory + "\\" + fileRealName;
            raf = new RandomAccessFile(realName, "rw");
            for (int i = 0; i < fileNum; i++) {
                //每个part文件追加写入最终文件
                raf.seek(alreadyWrite);
                System.out.println("alreadyWrite:" + alreadyWrite);
                fis = new FileInputStream(new File(srcDirectory + "\\" + separatedFiles[i]));
                while ((len = fis.read(bt)) > 0) {
                    raf.write(bt, 0, len);
                }
                //每次新建流，都关闭
                fis.close();
                alreadyWrite += Long.parseLong(separatedFilesAndSize[i][1]);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (raf != null)
                    raf.close();
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成一些属性，做初始化
     *
     * @param srcDirectory
     */
    private void getFileAttribute(String srcDirectory) {
        File file = new File(srcDirectory);
        //存放所有拆分文件名
        separatedFiles = new String[file.list().length];
        separatedFiles = file.list();
        // 依文件数目动态生成二维数组，包括文件名和文件大小，第一维装文件名，第二维为该文件的字节大小
        //存放所有拆分文件名及分件大小
        separatedFilesAndSize = new String[separatedFiles.length][2];
        //排序
        Arrays.sort(separatedFiles);
        //当前文件夹下面有多少个文件
        fileNum = separatedFiles.length;
        for (int i = 0; i < fileNum; i++) {
            separatedFilesAndSize[i][0] = separatedFiles[i];
            separatedFilesAndSize[i][1] = String.valueOf(getFileSize(separatedFiles[i]));
        }
        //取得文件分隔前的原文件名
        fileRealName = getRealName(separatedFiles[0]);
    }

    private long getFileSize(String fileName) {
        File file = new File(srcDirectory + "\\" + fileName);
        return file.length();
    }

    private String getRealName(String fileName) {
//        StringTokenizer st = new StringTokenizer(fileName, ".");
//        //x.zip.part1=>x.zip
//        return st.nextToken() + "." + st.nextToken();

        String name = fileName.substring(0, fileName.lastIndexOf("."));
        return name;
    }
}
