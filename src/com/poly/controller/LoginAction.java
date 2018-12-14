package com.poly.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entities.Account;
import model.LoginModel;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private String username;
	private String password;
	private String name;
	List<Account> listacc = new ArrayList<>();
	List<Account> listacc2 = new ArrayList<>();
	Account account2;
	public Account getAccount2() {
		return account2;
	}

	public void setAccount2(Account account2) {
		this.account2 = account2;
	}

	public List<Account> getListacc2() {
		return listacc2;
	}

	public void setListacc2(List<Account> listacc2) {
		this.listacc2 = listacc2;
	}

	public List<Account> getListacc() {
		return listacc;
	}

	public void setListacc(List<Account> listacc) {
		this.listacc = listacc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	LoginModel loginModel = new LoginModel();
	Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	
	
	public String processLogin() {
		if (LoginModel.logindb(username, password)) {
			this.listacc = loginModel.searchAccountByName(this.username);
			this.listacc2=loginModel.searchAccountByName2(this.username);
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.put("fullname", this.listacc);
			String  checkrole = String.valueOf(this.listacc2.get(0));
			Boolean b = Boolean.valueOf(true);
			Boolean c = Boolean.valueOf(checkrole);
			if (c == b) {
				return SUCCESS;
			} else {
				return "nhanvien";
			}
		} else {
			this.addActionError("Invalid username and password");
		}

		return INPUT;
	}

	public String processlogout() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("fullname");
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}

}
