/**
 * @模块编号：
 * @模块名称：
 * @模块功能：利用反射机制设置对象值
 * @author dql
 * 
 */
package com.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class ReflectCommandUtil {

    /**
     * 设置对象的字段值
     * 
     * @param requestMap
     *            结构[字段名,字段值]
     * @param classFullName
     *            类全名
     * @return 返回完成属性值设置后的对象
     * @throws Exception
     * @注意：
     */
    @SuppressWarnings("unchecked")
    public static Object getCommand(Map requestMap, String classFullName)
            throws Exception {
        Class c = Class.forName(classFullName);
        Object o = c.newInstance();
        return updateCommand(requestMap, o);
    }

    /**
     * 设置对象的字段值
     * 
     * @param requestMap
     *            结构[字段名,字段值]
     * @param obj
     *            为所创建的对象
     * @return 返回设置完成属性值后的对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Object updateCommand(Map requestMap, Object obj)
            throws Exception {
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (!Modifier.isPrivate(methods[i].getModifiers())
                    && !Modifier.isProtected(methods[i].getModifiers())
                    && methods[i].getName().startsWith("set")) {
                String name = methods[i].getName().substring(3).toLowerCase();
                if (requestMap.containsKey(name)) {
                    Object param = requestMap.get(name);
                    Object[] values = findOutParamValues(param, methods[i]);
                    methods[i].invoke(obj, values);// 通过反射机制调用方法

                }
            }
        }
        return obj;
    }

    /**
     * 
     * @param paramValue
     *            参数值
     * @param method
     *            方法名
     * @return 返回含参数值的方法
     */
    public static Object[] findOutParamValues(Object paramValue, Method method) {

        return findOutParamValues(paramValue, method, "", ",");
    }

    @SuppressWarnings("unchecked")
    public static Object[] findOutParamValues(Object paramValue, Method method,
            String format, String doublesplitchar) {
        Class[] params = method.getParameterTypes();// 该方法的所有参数
        Object[] objs = new Object[params.length];

        for (int i = 0; i < params.length; i++) {// 该方法所有参数值都设为paramValue
            String pvString = DVConvert.toString(paramValue);

            if (params[i] == String.class) {
                if (paramValue == null) {
                    paramValue = null;
                } else {
                    objs[i] = DVConvert.toString(paramValue);
                }
            } else if (params[i] == Short.TYPE
                    || (params[i].getName() == null ? Short.class.getName() == null : params[i].getName().equals(Short.class.getName())))// short ||
            // Short，支持Short的包装类型和Short类型
            {
                pvString = pvString.replace(doublesplitchar, "");//去除千位分隔符

                short number = DVConvert.toShort(pvString);

                if (null == paramValue || "".equals(paramValue)) {
                    if (params[i].getName().equals(short.class.getName())) {
                        objs[i] = 0;
                    } else {
                        objs[i] = null;
                    }
                } else {
                    objs[i] = new Integer(number);
                }

            } else if (params[i].equals(Integer.TYPE)
                    || params[i].getName().equals(Integer.class.getName())) {

                pvString = pvString.replace(doublesplitchar, "");//去除千位分隔符

                int number = DVConvert.toInt(pvString);

                if (null == paramValue || "".equals(paramValue)) {
                    if (params[i].getName().equals(int.class.getName())) {
                        objs[i] = 0;
                    } else {
                        objs[i] = null;
                    }
                } else {
                    objs[i] = new Integer(number);
                }
            } else if (params[i] == Long.TYPE
                    || (params[i].getName() == null ? Long.class.getName() == null : params[i].getName().equals(Long.class.getName()))) {

                pvString = pvString.replace(doublesplitchar, "");//去除千位分隔符

                long number = DVConvert.tolong(pvString);
                if (null == paramValue || "".equals(paramValue)) {
                    if (params[i].getName().equals(long.class.getName())) {
                        objs[i] = 0l;
                    } else {
                        objs[i] = null;
                    }
                } else {
                    objs[i] = new Long(number);
                }
            } else if (params[i] == Float.TYPE
                    || (params[i].getName() == null ? Float.class.getName() == null : params[i].getName().equals(Float.class.getName()))) {

                pvString = pvString.replace(doublesplitchar, "");//去除千位分隔符

                float number = DVConvert.toFloat(pvString);

                if (null == paramValue || "".equals(paramValue)) {
                    if (params[i].getName().equals(float.class.getName())) {
                        objs[i] = 0.00f;
                    } else {
                        objs[i] = null;
                    }
                } else {
                    objs[i] = new Float(number);
                }
            } else if (params[i] == Double.TYPE
                    || (params[i].getName() == null ? Double.class.getName() == null : params[i].getName().equals(Double.class.getName()))) {

                pvString = pvString.replace(doublesplitchar, "");//去除千位分隔符

                double number = DVConvert.toDouble(pvString);


                if (null == paramValue || "".equals(paramValue)) {
                    if (params[i].getName().equals(double.class.getName())) {
                        objs[i] = 0d;
                    } else {
                        objs[i] = null;
                    }
                } else {
                    objs[i] = new Double(number);
                }
            } else if (params[i] == Boolean.TYPE
                    || (params[i].getName() == null ? boolean.class.getName() == null : params[i].getName().equals(boolean.class.getName()))) {
                boolean number = SConvert.toBoolean(paramValue);
                if (null == paramValue || "".equals(paramValue)) {
                    if (params[i].getName().equals(Double.class.getName())) {
                        objs[i] = false;
                    } else {
                        objs[i] = null;
                    }
                } else {
                    objs[i] = number;
                }
            } else if (params[i].equals(Date.class)) {// 处理当为Date类型情况
                if (format != null && !"".equals(format)) {
                    objs[i] = DateUtil.toDate(String.valueOf(paramValue),
                            format);
                } else {
                    objs[i] = (Date) paramValue;
                }
            } else if (params[i].equals(Timestamp.class)) {// 处理当为Timestamp类型情况
                objs[i] = (Timestamp) paramValue;
            } else {
                objs[i] = paramValue;
            }
        }
        return objs;
    }
    // public static Object[] findOutParamValues(Object paramValue, Method
    // method,String format)
    // {
    // Class[] params=method.getParameterTypes();//该方法的所有参数
    // Object[] objs=new Object[params.length];
    //	  
    // for(int i=0;i<params.length;i++){//该方法所有参数值都设为paramValue
    // //String parmClassName = params[i].getName();
    // if(params[i]==String.class){
    // objs[i]=DVConvert.toString(paramValue);
    // }
    // else if(params[i]==Short.TYPE ||params[i].getName() ==
    // Short.class.getName())//short || Short，支持Short的包装类型和Short类型
    // {
    // short number=DVConvert.toShort(paramValue);
    // objs[i]=new Short(number);
    // }
    // else if(params[i]==Integer.TYPE ||params[i].getName() ==
    // Integer.class.getName()){
    // int number=DVConvert.toInt(paramValue);
    // if(null == paramValue ||"".equals(paramValue))objs[i]=null;
    // else objs[i]=new Integer(number);
    // }
    // else if(params[i]==Long.TYPE ||params[i].getName() ==
    // Long.class.getName()){
    // long number=DVConvert.tolong(paramValue);
    // if(null == paramValue ||"".equals(paramValue))objs[i]=null;
    // else objs[i]=new Long(number);
    // }
    // else if(params[i]==Float.TYPE ||params[i].getName() ==
    // Float.class.getName()){
    // float number=DVConvert.toFloat(paramValue);
    // objs[i]=new Float(number);
    // }
    // else if(params[i]==Double.TYPE ||params[i].getName() ==
    // Double.class.getName()){
    // double number=DVConvert.toDouble(paramValue);
    // if(null == paramValue ||"".equals(paramValue))objs[i]=null;
    // else objs[i]=new Double(number);
    // }
    // else if(params[i]==Boolean.TYPE ||params[i].getName() ==
    // Boolean.class.getName()){
    // boolean number=SConvert.toBoolean(paramValue);
    // objs[i]=new Boolean(number);
    // }
    // else if(params[i].equals(Date.class))
    // {//处理当为Date类型情况
    // if(format != null && !"".equals(format))
    // {
    // objs[i] = DateUtil.toDate(String.valueOf(paramValue), format);
    // }
    // else
    // {
    // objs[i] = (Date)paramValue;
    // }
    // }
    // else if(params[i].equals(Timestamp.class)){//处理当为Timestamp类型情况
    // objs[i]= (Timestamp)paramValue;
    // //Date date = new Date();
    // //date = (Date)paramValue;
    // //objs[i] = date;
    //	    	
    // /*Calendar cal = Calendar.getInstance();
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // String mDateTime=formatter.format(cal.getTime());
    // objs[i] = mDateTime;*/
    // }
    // else
    // objs[i]=paramValue;
    // }
    // return objs;
    // }
}
