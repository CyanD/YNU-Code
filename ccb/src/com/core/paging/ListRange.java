package com.core.paging;

/**
 * @模块编号：
 * @模块名称：
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@模块功能：json构造
@修改记录：
1.
2.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * 满足json数据pojo
 * 使用ajax传送数据的时候按照该pojo统一  
 */
public class ListRange<T> {

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
     * 返回数据集合
     */
    List<T> list;

    public ListRange() {
        this.totalSize = 0;
        this.list = new ArrayList<T>();
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
