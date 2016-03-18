package com.example.user.myapplication.domain;

public class User {

	public int no;
	public String email;
	public String password;
	public int point;

	public User(){
		System.out.println(this.getClass());
	}

	public User(int no, String email, String password, int point){
		this.no = no;
		this.email = email;
		this.password = password;
		this.point = point;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "User [no=" + no + ", email=" + email + ", password=" + password + ", point=" + point + "]";
	}
	
	
}
