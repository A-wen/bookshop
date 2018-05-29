package com.person_msg.model;

import java.sql.Timestamp;

public class Person_MsgVO {

	private Integer pm_No;
	private Integer pm_Send;
	private Integer s_Status;
	private Integer pm_Recv;
	private Integer r_Status;
	private String pm_Content;
	private Timestamp pm_Date;
	public Integer getPm_No() {
		return pm_No;
	}
	public void setPm_No(Integer pm_No) {
		this.pm_No = pm_No;
	}
	public Integer getPm_Send() {
		return pm_Send;
	}
	public void setPm_Send(Integer pm_Send) {
		this.pm_Send = pm_Send;
	}
	public Integer getS_Status() {
		return s_Status;
	}
	public void setS_Status(Integer s_Status) {
		this.s_Status = s_Status;
	}
	public Integer getPm_Recv() {
		return pm_Recv;
	}
	public void setPm_Recv(Integer pm_Recv) {
		this.pm_Recv = pm_Recv;
	}
	public Integer getR_Status() {
		return r_Status;
	}
	public void setR_Status(Integer r_Status) {
		this.r_Status = r_Status;
	}
	public String getPm_Content() {
		return pm_Content;
	}
	public void setPm_Content(String pm_Content) {
		this.pm_Content = pm_Content;
	}
	public Timestamp getPm_Date() {
		return pm_Date;
	}
	public void setPm_Date(Timestamp pm_Date) {
		this.pm_Date = pm_Date;
	}
	@Override
	public String toString() {
		return "Person_MsgVO [pm_No=" + pm_No + ", pm_Send=" + pm_Send + ", s_Status=" + s_Status + ", pm_Recv="
				+ pm_Recv + ", r_Status=" + r_Status + ", pm_Content=" + pm_Content + ", pm_Date=" + pm_Date + "]";
	}

	
}
