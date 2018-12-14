package com.poly.controller;

import com.opensymphony.xwork2.ActionSupport;
import entities.Account;
import model.AccountModel;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	private AccountModel accountModel = new AccountModel();

	private List<Account> listacc = new ArrayList<>();

	private Account account = new Account();
	private String birthday;
	private int accId;
	private String name;
	private String cfmpassword;


	public String getCfmpassword() {
		return cfmpassword;
	}

	public void setCfmpassword(String cfmpassword) {
		this.cfmpassword = cfmpassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public List<Account> getListacc() {
		return listacc;
	}

	public void setListacc(List<Account> listacc) {
		this.listacc = listacc;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@SkipValidation
	@Override
	public String execute() throws Exception {
		this.listacc = accountModel.getAccounts();
		return SUCCESS;
	}

	@SkipValidation
	public String addNew() throws Exception {
		return SUCCESS;
	}

	public String processAddNew() throws Exception {
		boolean isCheck = accountModel.addNew(this.account);
		if (isCheck) {
			this.listacc = accountModel.getAccounts();
			return SUCCESS;
		}
		request.setAttribute("message", "Add new is unsuccessful!");
		return ERROR;
	}

//	@SkipValidation
//	public String delete() throws Exception {
//		this.account = accountModel.getAccountByID(this.accId);
//		if (this.account.getRole() == "Admin") {
//			this.listacc = accountModel.getAccounts();
//			request.setAttribute("message", "Delete is unsuccessful!");
//		} else {
//			boolean isCheck = accountModel.delete(this.account);
//			if (isCheck) {
//				this.listacc = accountModel.getAccounts();
//				return SUCCESS;
//			} else {
//				return INPUT;
//			}
//
//		}
//		return ERROR;
//
//	}

	@SkipValidation
	public String update() throws Exception {
		this.account = accountModel.getAccountByID(this.accId);
		return SUCCESS;
	}

	@SkipValidation
	public String processUpdate() throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date birthDate = format.parse(this.birthday);
		java.sql.Date birthSQL = new java.sql.Date(birthDate.getTime());
		this.account.setBirthday(birthSQL);
		boolean isCheck = accountModel.update(this.account);
		if (isCheck) {
			this.listacc = accountModel.getAccounts();
			return SUCCESS;
		}
		System.out.println(this.listacc);
		request.setAttribute("message", "Update is unsuccessful!");
		return ERROR;
	}

	@SkipValidation
	public String search() throws Exception {
		this.listacc = accountModel.getAccounts();
		return SUCCESS;
	}

	@SkipValidation
	public String processSearch() throws Exception {
		if (this.name.trim().equals("")) {
			this.listacc = accountModel.getAccounts();
		} else {
			this.listacc = accountModel.searchAccountByName(this.name);
		}
		return SUCCESS;
	}
}
