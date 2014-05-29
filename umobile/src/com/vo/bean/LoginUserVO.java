package com.vo.bean;
public class LoginUserVO{
	private String password;
	private String account;
	
	private String securityCode;

	public String getAccount() {
		return account;
	}
	public String getPassword() {
		return password;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
}
