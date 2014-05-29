/**
@模块编号：
@模块名称：简单数据表
@备注 ： 
@模块功能：
@版权：
@作者：丁强龙
@创建时间:2010年8月5日
@修改记录：
1.
2.
 */
package com.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleTable {
	
	private List<String> columnNames;// 列名
	private List<SimpleTableItem> datas;// 数据表

	public SimpleTable() {
		columnNames = new ArrayList<String>();  
		datas = new ArrayList<SimpleTableItem>();
	}

	public List<String> getColumnNames() {
		return columnNames;
	}

	public List<SimpleTableItem> getDatas() {
		return datas;
	}
	
	public void addColumnName(String colname)
	{
		if(!columnNames.contains(colname))
			columnNames.add(colname);
	}
	
	public void addItemData(SimpleTableItem item)
	{
		datas.add(item);
	}
}
