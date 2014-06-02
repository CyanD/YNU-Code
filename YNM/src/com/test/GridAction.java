package com.test;

import java.util.ArrayList;
import java.util.List;


public class GridAction {
	private List<User> list = new ArrayList();
	
	public List<User> getList(){
		return list;
	}
	
	public String execute() throws Exception {
		for(int i=0;i<10;i++){
			User user = new User("user"+i,"password"+i);
			list.add(user);
		}
		return "success";
	}

	public static void main(String[] args) {
		
	}
	
}
