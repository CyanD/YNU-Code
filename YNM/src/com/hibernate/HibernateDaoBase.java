/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hibernate;

import com.util.StringUtil;
import java.io.*;
import java.sql.*;
import java.util.List;
import org.hibernate.*;

public class HibernateDaoBase {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(HibernateFactory.class);

    public Session getSession() {
        return HibernateFactory.getSession();
    }

    public void delete(Object obj) {
        if (obj == null) {
            return;
        }
        Session session = null;
        Transaction trans = null;
        try {
            session = getSession();
            trans = session.beginTransaction();
            session.delete(obj);
            session.flush();
            session.clear();
            trans.commit();
        } catch (HibernateException he) {
            trans.rollback();
            if (he instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) he).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(he);
            }
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 更新对象
     * @param obj Object
     */
    public void update(Object obj) {
        if (obj == null) {
            return;
        }

        Session session = null;
        Transaction trans = null;
        try {
            session = getSession();
            trans = session.beginTransaction();
            obj = merge(obj);
            session.update(obj);
            session.flush();
            session.clear();
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            //如果是 SQL 错误 取出SQL 错误信息
            if (ex instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) ex).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(ex);
            }
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 保存或者更新对象
     * @param obj Object
     */
    public void saveNew(Object obj) {
        if (obj == null) {
            return;
        }
        Session session = null;
        Transaction trans = null;
        try {
            session = getSession();
            trans = session.beginTransaction();
            session.saveOrUpdate(obj);
            session.flush();
            session.clear();
            trans.commit();
        } catch (Exception ex) {
            if (ex instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) ex).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(ex);
            }
            trans.rollback();
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Object merge(Object obj) throws Exception {
        Session session = null;
        try {
            session = getSession();
            Object result = (Object) session.merge(obj);
            return result;
        } catch (Exception re) {
            log.error("merge failed", re);
            throw new Exception("merge object err!");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <T> T getByKey(Class<T> entityClass, Serializable key) {
        T obj = null;
        Session session = null;
        try {
            session = getSession();
            obj = (T) session.get(entityClass, key);

        } catch (Exception ex) {
            if (ex instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) ex).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(ex);
            }
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return obj;
    }

    public <T> T max(String poName, String propertyName, String whereClause) {
        T max = null;
        Session session = null;
        try {
            session = getSession();
            String sql = "select max(" + propertyName + ") from " + poName + " as t ";
            if (whereClause != null && !"".equals(whereClause)) {
                sql = sql + whereClause;
            }
            Query query = session.createQuery(sql);
            Object o1 = query.uniqueResult();
            if (o1 != null) {
                max = (T) o1;
            }
        } catch (HibernateException he) {
            if (he instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) he).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(he);
            }
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return max;
    }
    //执行 hsql 语法的 语句

    public int ExecQuery(String hsql) {
        int aCOunt = 0;
        Session session = null;
        try {
            session = getSession();
            Query query = session.createQuery(hsql);
            aCOunt = query.executeUpdate();
        } catch (Exception ex) {
            if (ex instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) ex).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(ex);
            }
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aCOunt;

    }

    public int ExecQueryList(List<String> hsqls) {
        int aCOunt = 0;
        Session session = null;
        try {
            session = getSession();
            for (int i = 0; i < hsqls.size(); i++) {
                String hsql = hsqls.get(i);
                Query query = session.createQuery(hsql);
                aCOunt = aCOunt + query.executeUpdate();
            }
        } catch (HibernateException he) {
            if (he instanceof JDBCException) {
                SQLException sqlEx = ((JDBCException) he).getSQLException();
                log.error(sqlEx);
            } else {
                log.error(he);
            }
        } catch (Exception ex) {
            log.error(ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return aCOunt;

    }

    protected long getNextId(String objName, String fieldName) {
        return getNextId(objName, fieldName, 1);
    }

    protected long getNextIdWidthTrans(String objName, String fieldName, int count) {
        long tid = 0;
        Session session = null;
        try {
            session = getSession();
            tid = getNextId(objName, fieldName, count);
        } catch (Exception ex) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tid;
    }

    protected long getNextId(String objName, String fieldName, int count) {
        return getNextId(objName, fieldName, "", count);
    }

    protected long getNextId(String objName, String fieldName, String clause, int count) {
        if (objName == null || "".equals(objName) || fieldName == null || "".equals(fieldName)) {
            return -1;
        }
        long newID = 0;
        Session session = null;

        try {
            session = getSession();
            String sql = "select max(m." + fieldName + ") from " + objName + " as m";
            if (clause != null && !"".equals(clause)) {
                sql = sql + clause;
            }
            Query query = session.createQuery(sql);
            List lists = query.list();

            if (lists != null && lists.size() > 0 && lists.get(0) != null) {
                newID = (Long) lists.get(0);
            }
        } catch (Exception ex) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return ++newID;
    }

    /**
     * 
     * @param objName 表名
     * @return
     */
    protected int getObjectsCount(String objName) {
        return getObjectsCount(objName, "");
    }

    /**
     * 
    /**
     * 
     * @param objName 表名	
     * @param clause 条件从句
     * @return
     */
    protected int getObjectsCount(String objName, String clause) {
        return getObjectsCount(objName, clause, "");
    }

    public int getObjectsCount(String objName, String clause, String insName) {
        int _count = 0;
        Session session = null;

        try {
            session = getSession();
            String _sql = "select count(*) from " + objName;
            if (insName != null && !"".equals(insName)) {
                _sql = _sql + " " + insName;
            }
            if (clause != null && !("".equals(clause))) {
                _sql = _sql + clause;
            }
            Query query = session.createQuery(_sql);
            Object obj = query.iterate().next();
            _count = StringUtil.toInt(obj);
        } catch (Exception ex) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return _count;
    }

    /**
     * 通过条件从句删除数据,自身不带事务
     * @param objName 
     * @param clause
     */
    public void deleteByClause(String objName, String clause) {
        Session session = null;

        try {
            session = getSession();
            StringBuilder sb = new StringBuilder();
            sb.append("delete from ");
            sb.append(objName);
            if (clause != null && !("".equals(clause))) {
                sb.append(clause);
            }

            Query query = session.createQuery(sb.toString());
            query.executeUpdate();
//			_trans.commit();

        } catch (Exception ex) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * 通过条件从句删除数据,自身带事务
     * @param objName 
     * @param clause
     */
    public void deleteByClauseWithTrans(String objName, String clause) {
        Session session = null;
        Transaction _trans = null;
        try {
            session = getSession();
            _trans = session.beginTransaction();

            StringBuilder sb = new StringBuilder();
            sb.append("delete from ");
            sb.append(objName);
            if (clause != null && !("".equals(clause))) {
                sb.append(clause);
            }

            Query query = session.createQuery(sb.toString());
            query.executeUpdate();
            _trans.commit();

        } catch (RuntimeException re) {
            if (_trans != null) {
                _trans.rollback();
            }
            throw re;
        }
    }
}
