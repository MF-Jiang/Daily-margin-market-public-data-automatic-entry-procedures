package Pachong1;

import java.io.File;
import java.io.IOException;

public class RenameFile {
    public static void  Rename(String oldname, String newname) throws IOException {
        // 旧的文件或目录
        File oldName = new File(oldname);
        // 新的文件或目录
        File newName = new File(newname);
        if (newName.exists()) {  //  确保新的文件名不存在
            throw new java.io.IOException("file exists");
        }
        if(oldName.renameTo(newName)) {
            System.out.println("Successfully rename");
        } else {
            System.out.println("Error");
        }
    }

}