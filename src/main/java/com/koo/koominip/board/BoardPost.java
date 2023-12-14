package com.koo.koominip.board;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BoardPost {
	private BigDecimal p_num;
	private String p_writer_id;
	
	private String p_txt;
	private Date p_when;
	private String p_color;
	
	private String m_pic;
	private String m_name;
	private List<BoardComment> p_comments;
	
	public BoardPost() {
		// TODO Auto-generated constructor stub
	}

	public BoardPost(BigDecimal p_num, String p_writer_id, String p_txt, Date p_when, String p_color, String m_pic,
			String m_name, List<BoardComment> p_comments) {
		super();
		this.p_num = p_num;
		this.p_writer_id = p_writer_id;
		this.p_txt = p_txt;
		this.p_when = p_when;
		this.p_color = p_color;
		this.m_pic = m_pic;
		this.m_name = m_name;
		this.p_comments = p_comments;
	}

	public BigDecimal getP_num() {
		return p_num;
	}

	public void setP_num(BigDecimal p_num) {
		this.p_num = p_num;
	}

	public String getP_writer_id() {
		return p_writer_id;
	}

	public void setP_writer_id(String p_writer_id) {
		this.p_writer_id = p_writer_id;
	}

	public String getP_txt() {
		return p_txt;
	}

	public void setP_txt(String p_txt) {
		this.p_txt = p_txt;
	}

	public Date getP_when() {
		return p_when;
	}

	public void setP_when(Date p_when) {
		this.p_when = p_when;
	}

	public String getP_color() {
		return p_color;
	}

	public void setP_color(String p_color) {
		this.p_color = p_color;
	}

	public String getM_pic() {
		return m_pic;
	}

	public void setM_pic(String m_pic) {
		this.m_pic = m_pic;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public List<BoardComment> getP_comments() {
		return p_comments;
	}

	public void setP_comments(List<BoardComment> p_comments) {
		this.p_comments = p_comments;
	}

}
