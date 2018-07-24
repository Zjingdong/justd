package org.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class File {
    public static void main(String[] args) throws IOException {
        java.io.File f=new java.io.File("d:/1.txt");
        InputStream fis = new FileInputStream(f);
        byte[] bbuf = new byte[100];
        int hasRead = 0;
        while ((hasRead = fis.read(bbuf)) > 0 )
        {
            System.out.print(new String(bbuf , 0 , hasRead,"GB2312" ));
            System.out.println("我");
        }
        fis.close();


        String str="aaaaaa";
        byte[] bs=str.getBytes();
        System.out.println(new String(bs));
    }
}
