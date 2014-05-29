/**
@模块编号：
@模块名称：简单数据表项
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

import java.util.HashMap;
import java.util.Map;

public class SimpleTableItem {
	private Map<String, Object> itemdatas;

	public SimpleTableItem() {
		itemdatas = new HashMap<String, Object>();
	}

	public Map<String, Object> getItemdatas() {
		return itemdatas;
	}

	public void setItemdatas(Map<String, Object> itemdatas) {
		this.itemdatas = itemdatas;
	}

	public void addCell(String colname,Object value)
	{
		//if(!itemdatas.containsKey(colname))
			itemdatas.put(colname, value);
	}
}
