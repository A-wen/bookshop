package com.s_book.model;

import java.sql.Date;

public class S_bookVO implements java.io.Serializable{
		private Integer s_book_no;
		private Integer s_gro_no;
		private Date cre_date;
		private Date end_date;
		
		public Integer getS_book_no() {
			return s_book_no;
		}
		public void setS_book_no(Integer s_book_no) {
			this.s_book_no = s_book_no;
		}
		public Integer getS_gro_no() {
			return s_gro_no;
		}
		public void setS_gro_no(Integer s_gro_no) {
			this.s_gro_no = s_gro_no;
		}
		public Date getCre_date() {
			return cre_date;
		}
		public void setCre_date(Date cre_date) {
			this.cre_date = cre_date;
		}
		public Date getEnd_date() {
			return end_date;
		}
		public void setEnd_date(Date end_date) {
			this.end_date = end_date;
		}
}
