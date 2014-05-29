package com.core.paging;

/**
 * @模块编号：
 * @模块名称：
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@模块功能：分页构造
@修改记录：
1.
2.
 */
import java.util.ArrayList;
import java.util.List;

public class Page implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6263869982242094543L;
    /**
     * 分页常量
     */
    public static final String FIRST = "FIRST";
    public static final String LAST = "LAST";
    public static final String PREV = "PREV";
    public static final String NEXT = "NEXT";
    public static final String TOPAGE = "TOPAGE";
    public static final int PAGE_SIZE = 20;
    public static final int DEFAULT_START = 0;
    public static final int CURRENT_PAGE = 1;
    private int allRows;//结果集行数	
    private int allPages;//结果集页数	
    private int pageSize;
    private int currentPage;
    private int begin;
    private int end;
    private boolean isPrevEnable = false;
    private boolean isNextEnable = false;
    private boolean isFirstEnable = false;
    private boolean isLastEnable = false;
    private List<Integer> pageNOs;

    /**	 
     * @param _allRow 构造分页需要参数
     * @param _currentPage 当前页数
     * @param _pageSize 每页行记录数
     */
    public Page(int _allRow, int _currentPage, int _pageSize) {
        this.allRows = _allRow;
        this.pageSize = (_pageSize <= 0) ? PAGE_SIZE : _pageSize;
        this.currentPage = (_currentPage <= 0) ? CURRENT_PAGE : _currentPage;
        init();
    }

    public Page() {
        //this.allRows = PAGE_SIZE;
        //this.allPages = 1;
        //this.pageSize = PAGE_SIZE;
        //this.currentPage = 1;
    }

    public void init(int _allRow, int _currentPage, int _pageSize) {
        this.allRows = _allRow;
        this.pageSize = (_pageSize <= 0) ? PAGE_SIZE : _pageSize;
        this.currentPage = (_currentPage <= 0) ? CURRENT_PAGE : _currentPage;
        init();
    }

    private void init() {

        if (allRows <= this.pageSize) {
            allPages = 1;
            currentPage = 1;
            this.begin = 0;//索引从零开始
            this.end = allRows;
        } else {
            if ((allRows) % pageSize == 0)//能整除
            {
                allPages = (allRows) / pageSize;// 总页数
            } else// 有余数
            {
                allPages = (allRows) / pageSize + 1;
            }
            currentPage = (allPages <= currentPage) ? allPages : currentPage;// 当前页数			
            calCurrentRowsIndex();
        }
        SetNavEnable();
    }

    private void SetNavEnable() {
        this.isPrevEnable = (this.currentPage != 1 && this.allPages > 1);
        this.isNextEnable = (this.currentPage != this.allPages && this.allPages > 1);
        this.isFirstEnable = (this.currentPage != 1 && this.allPages > 1);
        this.isLastEnable = (this.currentPage != this.allPages && this.allPages > 1);
    }

    /**	 
     */
    private void calCurrentRowsIndex() {
        if (currentPage == 1) {
            this.begin = 0;
            end = this.pageSize;
        } else {
            this.begin = (currentPage - 1) * pageSize;
            int localEnd = currentPage * pageSize;
            end = (localEnd > this.allRows) ? allRows : localEnd;
        }

    }

    /**
     * 
     * @param page 当前页
     * @param param 控制翻页的参数
     * @return
     */
    public Page createPageByPrama(Page page, String param) {
        int curPage = page.getCurrentPage();
        if (param != null && !("".equals(param))) {
            if (Page.FIRST.equals(param.toUpperCase())) {
                curPage = 1;
            } else if (Page.LAST.equals(param.toUpperCase())) {
                curPage = page.getAllPages();
            } else if (Page.PREV.endsWith(param.toUpperCase())) {
                curPage--;
            } else if (Page.NEXT.equals(param.toUpperCase())) {
                curPage++;
            }
        }
        page.setCurrentPage(curPage);
        return page;
    }

    /**     
     * @return
     */
    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getAllRows() {
        return allRows;
    }

    public void setAllRows(int allRows) {
        this.allRows = allRows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        calCurrentRowsIndex();
        SetNavEnable();
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public boolean getIsPrevEnable() {
        return isPrevEnable;
    }

    public void setPrevEnable(boolean isPrevEnable) {
        this.isPrevEnable = isPrevEnable;
    }

    public boolean getIsNextEnable() {
        return isNextEnable;
    }

    public void setNextEnable(boolean isNextEnable) {
        this.isNextEnable = isNextEnable;
    }

    public boolean getIsFirstEnable() {
        return isFirstEnable;
    }

    public void setFirstEnable(boolean isFirstEnable) {
        this.isFirstEnable = isFirstEnable;
    }

    public boolean getIsLastEnable() {
        return isLastEnable;
    }

    public void setLastEnable(boolean isLastEnable) {
        this.isLastEnable = isLastEnable;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Integer> getPageNOs() {
        if (pageNOs == null) {
            pageNOs = new ArrayList<Integer>();
        }
        pageNOs.clear();
        for (int i = 1; i <= allPages; i++) {
            pageNOs.add(i);
        }
        return pageNOs;
    }
}
