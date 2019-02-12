package com.app.hibernate;

public class Customer {
	private int cid;
	private String cname;
	private String email;
	private int balance;
	private University university;
	
	public Customer() {
		
	}
	
	public Customer(String name,String em,int bal,University univ) {
		this.cname=name;
		this.email=em;
		this.balance=bal;
		this.university=univ;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
}
