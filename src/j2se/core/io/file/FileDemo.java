package j2se.core.io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        System.out.println(File.separator);

        File file = new File("src/j2se/core/io/file/FileDemo.java");

        if (file.isFile()) {
            System.out.println(file + "是一个文件");
        }

        showFileInformation(file);

        createNewFile("newFile.txt");

        deleteFile("newFile.txt");
    }

    private static void deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.delete())
                System.out.println("文件删除成功！");
            else
                System.out.println("文件删除失败！");
        } else
            System.out.println("文件不存在！");
    }

    private static void createNewFile(String str) throws IOException {
        File file = new File(str);
        if (!file.exists()) {
            if (file.createNewFile())
                System.out.println("文件创建成功！");
            else
                System.out.println("文件创建失败！");
        } else
            System.out.println("文件已存在！");
    }

    private static void showFileInformation(File file) {
        System.out.println("是否可读：" + file.canRead());
        System.out.println("是否可写：" + file.canWrite());
        System.out.println("文件大小：" + file.length());
        System.out.println("最后修改时间：" + new Date(file.lastModified()));

        System.out.println("绝对路径为：" + file.getAbsolutePath());
        System.out.println("文件名为：" + file.getName());
        System.out.println("所在目录为：" + file.getParent());
    }
}
