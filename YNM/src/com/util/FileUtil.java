package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
/**
 * @版权：
 * @作者：丁强龙
 * @创建时间:2012年8月21日
 * @模块功能：
 * @修改记录： 1. 2.
 */
public class FileUtil
{
  public static boolean copy(String srcfile, String destfile)
  {
    try
    {
      File file_in = new File(srcfile);
      File file_out = new File(destfile);
      FileInputStream in1 = new FileInputStream(file_in);
      FileOutputStream out1 = new FileOutputStream(file_out);
      byte[] bytes = new byte[1024];
      int c;
      while ((c = in1.read(bytes)) != -1) {
        out1.write(bytes, 0, c);
      }
      in1.close();
      out1.close();
      return true;
    } catch (Exception e) {
      System.out.println("文件拷贝失败!");
    }return false;
  }

  public static boolean copy(File srcfile, File destfile)
  {
    try
    {
      File file_in = srcfile;
      File file_out = destfile;
      FileInputStream in1 = new FileInputStream(file_in);
      FileOutputStream out1 = new FileOutputStream(file_out);
      byte[] bytes = new byte[1024];
      int c;
      while ((c = in1.read(bytes)) != -1) {
        out1.write(bytes, 0, c);
      }
      in1.close();
      out1.close();
      return true;
    } catch (Exception e) {
      System.out.println("文件拷贝失败!");
    }return false;
  }

  public static File[] getAllFiles(String filepath, String filter)
  {
    File f = new File(filepath);
    if (f.isFile()) {
      File[] files = { f };
      return files;
    }
    return f.listFiles();
  }

  public static boolean createTextFile(String filename, String txt)
  {
    try {
      File f = new File(filename);
      if (f.exists()) {
        f.delete();
      }
      PrintWriter out = new PrintWriter(new FileWriter(f));
      out.print(txt);
      out.close();
      return true;
    } catch (Exception ix) {
      System.out.println("文件拷贝失败");
    }return false;
  }

  public static boolean createTextFile(String filename, StringBuffer txt)
  {
    try {
      File f = new File(filename);
      if (f.exists()) {
        f.delete();
      }
      PrintWriter out = new PrintWriter(new FileWriter(f));
      out.print(txt.toString());
      out.close();
      return true;
    } catch (Exception ix) {
      System.out.println("文件拷贝失败");
    }return false;
  }

  public static boolean appendTextFile(String filename, String txt)
  {
    try
    {
      FileWriter f = new FileWriter(filename, true);
      PrintWriter out = new PrintWriter(f);
      out.print(txt);
      out.close();
      f.close();
      return true;
    } catch (IOException e) {
    }
    return false;
  }

  public static boolean appendTextFile(String filename, StringBuffer txt)
  {
    return appendTextFile(filename, txt.toString());
  }

  public static StringBuffer getFileContent(String filename) {
    try {
      FileInputStream file = new FileInputStream(filename);
      BufferedReader bReader = new BufferedReader(new InputStreamReader(file));

      StringBuffer sb = new StringBuffer();
      String message;
      while ((message = bReader.readLine()) != null)
      {
        sb.append(message);
      }
      bReader.close();
      file.close();
      return sb;
    } catch (IOException e) {
      System.out.println("文件" + filename + "读取失败。" + e.getMessage());
    }return null;
  }

  public static StringBuffer getFileContent(String filename, String breakString)
  {
    try
    {
      FileInputStream file = new FileInputStream(filename);
      BufferedReader bReader = new BufferedReader(new InputStreamReader(file));

      StringBuffer sb = new StringBuffer();
      String message;
      while ((message = bReader.readLine()) != null)
      {
        if (message.indexOf(breakString) > -1) {
          break;
        }
        sb.append(message);
      }
      bReader.close();
      file.close();
      return sb;
    } catch (IOException e) {
      System.out.println("文件" + filename + "读取失败。" + e.getMessage());
    }return null;
  }

  public static void getDirs(List<String> filelist, String strPath)
  {
    File dir = new File(strPath);
    File[] files = dir.listFiles();

    if (files == null) {
      return;
    }
    for (int i = 0; i < files.length; i++)
      if (files[i].isDirectory()) {
        getDirs(filelist, files[i].getAbsolutePath());
      } else {
        String strFileName = files[i].getAbsolutePath().toLowerCase();
        System.out.println("---" + strFileName);
        filelist.add(files[i].getAbsolutePath());
      }
  }

  public static void getCurrentDirs(List<String> filelist, String strPath)
  {
    File dir = new File(strPath);
    File[] files = dir.listFiles();

    if (files == null) {
      return;
    }
    for (int i = 0; i < files.length; i++)
      if (files[i].isDirectory())
        filelist.add(files[i].getAbsolutePath());
  }

  public static void main(String[] args)
  {
    System.out.println(getFileContent("C:\\oc4j1032\\j2ee\\home\\applications\\ProjApplication\\ProjArchive\\documents\\workspaces\\default\\admin\\通讯录\\合纵.data", "</config>"));
  }
}