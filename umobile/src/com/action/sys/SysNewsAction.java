package com.action.sys;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
@Component("sysNewsAction")
@Scope("prototype")
public class SysNewsAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
