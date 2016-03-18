package com.example.user.myapplication.domain;

import java.io.Serializable;

public class Product implements Serializable{

	public int no;
	public String url;
	//Thumb & 큰 이미지.
	public String loc1;
	public String loc2;

	public int point;
	public String sdate;
	public String fdate;
	public String title;
	public String descr;
	public String dept;
	public String type;


	public Product() {
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLoc1() {
		return loc1;
	}
	public void setLoc1(String loc1) {
		this.loc1 = loc1;
	}
	public String getLoc2() {
		return loc2;
	}
	public void setLoc2(String loc2) {
		this.loc2 = loc2;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product{" +
				"no=" + no +
				", url='" + url + '\'' +
				", loc1='" + loc1 + '\'' +
				", loc2='" + loc2 + '\'' +
				", point=" + point +
				", sdate='" + sdate + '\'' +
				", fdate='" + fdate + '\'' +
				", title='" + title + '\'' +
				", descr='" + descr + '\'' +
				", dept='" + dept + '\'' +
				", type='" + type + '\'' +
				'}';
	}
}
