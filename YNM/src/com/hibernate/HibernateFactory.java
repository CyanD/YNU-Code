/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate;

import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 * hibernate会话工厂
 */
public class HibernateFactory {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(HibernateFactory.class);
    /** 会话工厂 依赖于hibernate.cfg.xml */
    private static SessionFactory sessionFactory2;
    /** 配置信息，及系统信息 */
    private static Properties properties;

    private HibernateFactory() {
    }

    public static void init() {
        if (sessionFactory2 != null) {
            return;
        }
        try {
            sessionFactory2 = new AnnotationConfiguration() //
                    .configure().buildSessionFactory();
            // new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            log.error(e.toString(), e);
            // e.printStackTrace();
            // throw new ExceptionInInitializerError(e);
        }
    }

    // private synchronized static void init()throws HibernateException{
    // // 装载配置，构造SessionFactory对象
    //
    // Configuration configuration;
    // configuration = new Configuration().configure("/csuser.cfg.xml");
    // properties = (Properties)configuration.getProperties().clone();
    // sessionFactory2 = configuration.buildSessionFactory();
    // //Log.log("//----------------sessionFactory init.");
    // }
    /**
     * 打开一个会话 用于数据库操作
     * 
     * @throws HibernateException
     * @return Session
     */
    public synchronized static Session getSession() throws HibernateException {
        if (sessionFactory2 == null) {
            init();
        }
        return sessionFactory2.openSession();
    }

    /**
     * 初始化
     */
    public static void init0() throws HibernateException {
        // 装载配置，构造SessionFactory对象
        if (sessionFactory2 == null) {
            init();
        }

    }
}
