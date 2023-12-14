package com.koo.koominip.board;

import java.math.BigDecimal;
import java.util.Date;

public class BoardComment {
	private BigDecimal c_num;
	private BigDecimal c_p_num;
	private String c_writer_id;
	private String c_txt;
	private Date c_when;
	
	private String m_name;
	
	public BoardComment() {
		// TODO Auto-generated constructor stub
	}

	public BoardComment(BigDecimal c_num, BigDecimal c_p_num, String c_writer_id, String c_txt, Date c_when,
			String m_name) {
		super();
		this.c_num = c_num;
		this.c_p_num = c_p_num;
		this.c_writer_id = c_writer_id;
		this.c_txt = c_txt;
		this.c_when = c_when;
		this.m_name = m_name;
	}

	public BigDecimal getC_num() {
		return c_num;
	}

	public void setC_num(BigDecimal c_num) {
		this.c_num = c_num;
	}

	public BigDecimal getC_p_num() {
		return c_p_num;
	}

	public void setC_p_num(BigDecimal c_p_num) {
		this.c_p_num = c_p_num;
	}

	public String getC_writer_id() {
		return c_writer_id;
	}

	public void setC_writer_id(String c_writer_id) {
		this.c_writer_id = c_writer_id;
	}

	public String getC_txt() {
		return c_txt;
	}

	public void setC_txt(String c_txt) {
		this.c_txt = c_txt;
	}

	public Date getC_when() {
		return c_when;
	}

	public void setC_when(Date c_when) {
		this.c_when = c_when;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

}
