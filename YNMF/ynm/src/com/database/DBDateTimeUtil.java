package com.database;

import java.io.PrintStream;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import oracle.sql.TIMESTAMP;

/**
 * 
 * @author dql
 */
public class DBDateTimeUtil
{
  public static final String TIMESTAMP_ADDTION = "00";

  public static Date convertObject2Date(Object obj)
  {
    if (obj == null) return null; try
    {
      if ("java.sql.Date".equals(obj.getClass().getName()))
        return (Date)obj;
      if ("java.sql.Timestamp".equals(obj.getClass().getName()))
        return new Date(((Timestamp)obj).getTime());
      if ("oracle.sql.TIMESTAMP".equals(obj.getClass().getName())) {
        return new Date(((TIMESTAMP)obj).dateValue().getTime());
      }
      System.out.println("无效的类型转换。" + obj.getClass().getName() + ">>" + "java.sql.Timestamp");
      return null;
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }return null;
  }

  public static Object convertObject2Timestamp(Object obj)
  {
    if (obj == null) return null;
    try
    {
      if ("java.sql.Date".equals(obj.getClass().getName()))
      {
        return new Timestamp(((Date)obj).getTime());
      }if ("java.sql.Timestamp".equals(obj.getClass().getName()))
        return (Timestamp)obj;
      if ("java.sql.Time".equals(obj.getClass().getName()))
        return new Timestamp(((Time)obj).getTime());
      if ("oracle.sql.TIMESTAMP".equals(obj.getClass().getName())) {
        return ((TIMESTAMP)obj).timestampValue();
      }
      System.out.println("无效的类型转换。" + obj.getClass().getName() + ">>" + "java.sql.Timestamp");
      return null;
    }
    catch (Exception e) {
      e.printStackTrace();
    }return null;
  }

  public static Date convertObject2SQLDate(Object obj)
  {
    if (obj == null) return null; try
    {
      System.out.println(obj.getClass().getName() + ">>" + "java.sql.Date");
      if ("java.sql.Date".equals(obj.getClass().getName()))
      {
        return (Date)obj;
      }if ("java.sql.Timestamp".equals(obj.getClass().getName()))
      {
        return new Date(((Timestamp)obj).getTime());
      }if ("java.sql.Time".equals(obj.getClass().getName()))
        return new Date(((Time)obj).getTime());
      if ("oracle.sql.TIMESTAMP".equals(obj.getClass().getName()))
      {
        return ((TIMESTAMP)obj).dateValue();
      }
      System.out.println("无效的类型转换。" + obj.getClass().getName() + ">>" + "java.sql.Date");
      return null;
    }
    catch (Exception e) {
      e.printStackTrace();
    }return null;
  }
}