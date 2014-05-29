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
public class GridPager<T> {

    /**
     * 结果总数
     */
    long total;
    /**
     * 返回数据集合
     */
    List<T> datas;
    long currpage;
    long records;

    public GridPager() {
        this.total = 0;
        this.datas = new ArrayList<T>();
    }

    public long getCurrpage() {
        return currpage;
    }

    public void setCurrpage(long currpage) {
        this.currpage = currpage;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
