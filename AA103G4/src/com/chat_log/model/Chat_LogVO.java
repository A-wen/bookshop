package com.chat_log.model;

import java.sql.Date;

public class Chat_LogVO {
	private Integer C_NO;
	private Integer G_NO;
	private Integer M_NO;
	private String C_CONTENT;
	private Date C_TIME;
	public Integer getC_NO() {
		return C_NO;
	}
	public void setC_NO(Integer c_NO) {
		C_NO = c_NO;
	}
	public Integer getG_NO() {
		return G_NO;
	}
	public void setG_NO(Integer g_NO) {
		G_NO = g_NO;
	}
	public Integer getM_NO() {
		return M_NO;
	}
	public void setM_NO(Integer m_NO) {
		M_NO = m_NO;
	}
	public String getC_CONTENT() {
		return C_CONTENT;
	}
	public void setC_CONTENT(String c_CONTENT) {
		C_CONTENT = c_CONTENT;
	}
	public Date getC_TIME() {
		return C_TIME;
	}
	public void setC_TIME(Date c_TIME) {
		C_TIME = c_TIME;
	}
	@Override
	public String toString() {
		return "Chat_LogVO [C_NO=" + C_NO + ", G_NO=" + G_NO + ", M_NO=" + M_NO + ", C_CONTENT=" + C_CONTENT
				+ ", C_TIME=" + C_TIME + "]";
	}
	
	
}
