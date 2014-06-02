/**
@版权：
@作者：丁强龙
@创建时间:2011年7月20日
@模块功能：对象操作
@修改记录：
@1.
@2.
 */
package com.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class ObjectUtil {

    /**
     * 千位分隔符
     */
    public final static String DoubleSplitChar = ",";

    /**
     * 对象复制，深拷贝
     * 
     * @param obj需要拷贝的对象
     * @return 新的对象
     */
    public static Object cloneObject(Object obj) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return in.readObject();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 调用get方法在给定的对象【obj】中查找字段为【fieldname】的值
     * 
     * @param fieldname
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object findFiledValue(String fieldname, Object obj) {
        if (fieldname == null || "".equals(fieldname)) {
            return null;
        }
        Object result = null;
        try {
            Class clazz = obj.getClass();
            // Field[] fields = obj.getClass().getDeclaredFields();//获得属性
            // if(fields == null || fields.length<1)return null;
            PropertyDescriptor pd = new PropertyDescriptor(fieldname, clazz);
            Method getMethod = pd.getReadMethod();// 获得get方法
            result = getMethod.invoke(obj);// 执行get方法返回一个Object

        } catch (Exception ex) {
        }
        return result;
    }

    /**
     * 注意：该方法浮点数的千位分隔符默认的是","，若使用其他的千位分割符，请使用该方法的另一个重载版本
     * @param fieldname
     *            字段名
     * @param obj
     *            对象
     * @param value
     *            值
     * @param format
     *            如果是时间或是浮点数等需要格式的格式参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean setFiledValue(String fieldname, Object obj,
            Object value, String format) {
        if (obj == null || fieldname == null || "".equals(fieldname)) {
            return true;
        }
        try {
            Class clazz = obj.getClass();
            // Field[] fields = obj.getClass().getDeclaredFields();//获得字段
            // if(fields == null || fields.length<1)return true;
            PropertyDescriptor pd = new PropertyDescriptor(fieldname, clazz);
            if (pd == null) {
                return false;
            }
            Method setMethod = pd.getWriteMethod();
            if (setMethod == null) {
                return false;
            }
            if (Modifier.isPublic(setMethod.getModifiers())) {
                Object[] pvalues = ReflectCommandUtil.findOutParamValues(value,
                        setMethod, format, DoubleSplitChar);
                setMethod.invoke(obj, pvalues);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 
     * @param fieldname
     * @param obj
     * @param value
     * @param format
     * @param String
     *            千位分隔符
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean setFiledValue(String fieldname, Object obj,
            Object value, String format, String doubleSplitChar) {
        if (obj == null || fieldname == null || "".equals(fieldname)) {
            return true;
        }
        try {
            Class clazz = obj.getClass();
            // Field[] fields = obj.getClass().getDeclaredFields();//获得字段
            // if(fields == null || fields.length<1)return true;
            PropertyDescriptor pd = new PropertyDescriptor(fieldname, clazz);
            if (pd == null) {
                return false;
            }
            Method setMethod = pd.getWriteMethod();
            if (setMethod == null) {
                return false;
            }
            if (Modifier.isPublic(setMethod.getModifiers())) {
                Object[] pvalues = ReflectCommandUtil.findOutParamValues(value,
                        setMethod, format, doubleSplitChar);
                setMethod.invoke(obj, pvalues);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    /**
     * 移除列表中的空值
     * @param objs 列表，若列表中的对象为空，将被直接移除
     */
    @SuppressWarnings("rawtypes")
	public static void removeListNullValue(List objs)
    {
    	for(int i=objs.size()-1;i>-1;i--)
    	{
    		if(objs.get(i) == null)
    		{
    			objs.remove(i);
    		}
    	}
    }

    /**
     * 
     * @param objs
     *            需要排序的对象列表
     * @param fields
     *            排序字段集合
     * @param sortType
     *            排序方式 0升序，1降序
     */
    public static void sortObjects(List<Object> objs, List<String> fields,
            int sortType) {
        // 暂时未实行
        // mulds.add(0, null);// 多加一个元素作为哨兵
        // int j = 0;
        // for (int i = 2; i < mulds.size(); ++i) {
        // mulds.set(0, mulds.get(i));// 第一个元素为哨兵
        // if (mulds.get(i).getInputdate().compareTo(
        // mulds.get(i - 1).getInputdate()) < 0) {
        // //
        // t.getInputdate().compareTo(sortedList.get(i).getInputdate());//0表示相等，<0
        // // t.inputdate 在sortedList.get(j).inputdate之前
        // // j=
        // //
        // i-1;while(mulds.get(i).getInputdate().compareTo(mulds.get(j).getInputdate())
        // // < 0){j--;}//插入排序,等价于下边的
        // for (j = i - 1; mulds.get(0).getInputdate().compareTo(
        // mulds.get(j).getInputdate()) < 0; --j) {
        // mulds.set(j + 1, mulds.get(j));// 顺序表，需后移元素
        // }
        // mulds.set(j + 1, mulds.get(0));
        // // mulds.add(j+1, mulds.get(0));
        // }
        // }
        // mulds.remove(0);// 删除哨兵
    }
}
