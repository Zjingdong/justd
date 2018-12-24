package org.file.fileBackup;

import java.io.*;

/**
 * @author: Justd
 * @Date: 2018/12/24 13:42
 * @Description:
 */
public class FileCut {
    public static final Integer FILE_SIZE = 4 * 1024 * 1024;

    public static void splitCut(File f, int partSize) {
        if (f.length() > FILE_SIZE) {
            System.out.println("启动成功");
            //块数
            int count = (int) (Math.ceil(f.length() / partSize)) + 1;
            System.out.println("块数" + count);
            try {
                /**
                 * 创建输入输出流对象*/
                InputStream inf = new FileInputStream(f);
                System.out.println("输入流启动成功");
                OutputStream[] outf = new FileOutputStream[count];
                System.out.println("输出流启动成功：" + outf.length);
                /**创建文件夹，存储各小块文件*/
                int filePrefix = f.getName().lastIndexOf(".");
                String str = f.getParent() + "\\" + f.getName().substring(0, filePrefix);/**目录路径*/
                File dirfile = new File(str);
                if (!dirfile.exists()) {
                    dirfile.mkdirs();
                }
                /**创建各小块文件并命名*/
                File[] dir_f = new File[count];
                System.out.println("数组创建成功：" + dir_f.length);
                /**获取文件类型*/
                String fName = f.getName();
                String fPattern = fName.substring(fName.lastIndexOf("."));
                System.out.println("文件类型获取成功：" + fPattern);
                for (int j = 0; j < count; j++) {
                    //命名文件名称
                    String newPath = str + "\\" + f.getName().substring(0, filePrefix) + "-" + j + fPattern;
                    dir_f[j] = new File(newPath);
                    outf[j] = new FileOutputStream(dir_f[j]);
                }
                /**写入各块内容*/
                int s, m = 0, n = FILE_SIZE;
                byte[] buffer = new byte[n];
                s = inf.read(buffer, 0, n);
                while (s != -1 && m < count) {
                    if (dir_f[m].length() < FILE_SIZE) {
                        outf[m].write(buffer, 0, n);
                        s = inf.read(buffer, 0, n);
                    }
                    if (dir_f[m].length() == FILE_SIZE) {
                        outf[m].close();
                        m = m + 1;
                        int off = (int) (f.length() - m * FILE_SIZE);
                        if (off < FILE_SIZE) {
                            outf[m].write(buffer, 0, off);
                            outf[m].close();
                            break;
                        }
                    }
                }
                inf.close();
                f.delete();
            } catch (IOException ioe) {
                System.out.println("IO 异常");
            } catch (IndexOutOfBoundsException ine) {
                System.out.println("数组越界 异常");
            } catch (Exception e) {
                System.out.println("异常");
            }
        } else {
            System.out.println("启动失败");
        }
    }

    public static void divide(String fPath) {
        File f = new File(fPath);
        /**文件存在*/
        if (f.exists()) {
            /**是单个文件*/
            if (f.isFile() && f.length() > FILE_SIZE) {
                /**调用单文件分割方法*/
                splitCut(f, FILE_SIZE);
            }
            /**是目录*/
            else if (f.isDirectory() && f.length() > FILE_SIZE) {
                /**目录文件数组化*/
                File[] dir = f.listFiles();

                for (int i = 0; i < dir.length; i++) {
                    if (dir[i].exists() && dir[i].length() > FILE_SIZE) {
                        if (dir[i].isFile()) {
                            splitCut(dir[i], FILE_SIZE);
                        } else if (dir[i].isDirectory() && dir[i].length() > FILE_SIZE) {
                            divide(dir[i].getAbsolutePath());
                        }
                    } else {
                        System.out.println(dir[i].getAbsolutePath() + "文件或目录较小，无需处理！");
                    }
                }
            }
        } else {
            System.out.println(fPath + "  不存在，分割失败！");
        }
    }

    /**
     * 小块组装/还原
     *
     * @param fPath 待组装的文件/目录绝对路径
     * @return null
     * 思路：扫描文件/目录，当遇到内部文件以  文件夹名  命名且予以编号时，合并文件夹内所有文件并覆盖此文件夹
     * result：文件/目录属性与分割前相同
     */
    public static void pack(String fPath) {
        File f = new File(fPath);
        boolean flag = false;
        int t, num = 0;
        if (f.exists()) {
            if (f.isDirectory()) {
                File[] dir = f.listFiles();
                for (t = 0; t < dir.length; t++) {
                    if (dir[t].isFile()) {
                        if (dir[t].getName().lastIndexOf("-") != -1) {
                            String cutName = dir[t].getName().substring(0, dir[t].getName().lastIndexOf("-"));
                            if (f.getName().compareTo(cutName) == 0) {
                                flag = true;
                                num += 1;
                            } else {
                                flag = false;
                            }
                        }
                    } else if (dir[t].isDirectory()) {
                        pack(dir[t].getAbsolutePath());
                    }
                }
                /**组装开始*/
                if (flag == true && num == dir.length) {
                    try {
                        /**重新构建路径=文件夹路径+后缀*/
                        String dirName = dir[0].getName();
                        int begin = dirName.lastIndexOf(".");
                        String coverPath = f.getAbsolutePath() + dirName.substring(begin, dirName.length());
                        File coverFile = new File(coverPath);

                        /**构建输入输出流：
                         * 输入流：packIn dir 文件夹中的编号文件
                         * 输出流：packOut  coverFile
                         */
                        OutputStream packOut = new FileOutputStream(coverFile, true);
                        int sizeOfDir = dir.length;
                        for (t = 0; t < sizeOfDir; t++) {
                            for (int k = 0; k < sizeOfDir; k++) {
                                int b = dir[k].getName().lastIndexOf("-");
                                int e = dir[k].getName().lastIndexOf(".");
                                /**找到与 序号匹配的 文件 写入*/
                                if (dir[k].getName().substring(b + 1, e).compareTo(String.valueOf(t)) == 0) {
                                    //System.out.println(t+"   "+ dir[k].getName().substring(b+1, e));
                                    InputStream packIn = new FileInputStream(dir[k]);
                                    int s, n = FILE_SIZE;
                                    byte[] buffer = new byte[n];
                                    s = packIn.read(buffer, 0, n);
                                    if (dir[k].length() == FILE_SIZE) {
                                        packOut.write(buffer, 0, n);
                                        packIn.close();
                                        dir[k].delete();
                                    } else {
                                        int end = (int) dir[k].length();
                                        packOut.write(buffer, 0, end);
                                        packIn.close();
                                        dir[k].delete();
                                    }
                                    break;
                                }
                            }
                        }
                        packOut.close();
                        System.out.println("文件数：" + dir.length);
                        f.delete();
                    } catch (Exception e) {
                        System.out.println("异常");
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
//        String path = "D:\\百度\\2017阿里技术年度精选上.pdf";//要切割的文件/目录路径
//        divide(path);//切割
        pack("D:\\百度\\2017阿里技术年度精选上");//组装（给出路径）
    }
}

