/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.paging;

import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Range<T> {

    /**
     * true，表示此次操作成功;false,表示失败
     */
    boolean success;
    /**
     * 返回消息，成功或者失败的详细信息
     */
    String message;
    /**
     * 结果总数
     */
    long totalSize;
    /**
     * 返回数据对象
     */
    T obj;
    
    private Map<String,String> fieldValMsgs;
    
    private String actionValMsgs;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

	public Map<String, String> getFieldValMsgs() {
		return fieldValMsgs;
	}

	public void setFieldValMsgs(Map<String, String> fieldValMsgs) {
		this.fieldValMsgs = fieldValMsgs;
	}

	public String getActionValMsgs() {
		return actionValMsgs;
	}

	public void setActionValMsgs(String actionValMsgs) {
		this.actionValMsgs = actionValMsgs;
	}
    
}
