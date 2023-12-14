package com.koo.koominip.board;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koo.koominip.member.Member;

@Service
public class BoardDAO {
	private int allPostCount;
	private String[] hexValue = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	private Random r = new Random();

	@Autowired
	private SqlSession ss;

	public void countAllPost() {
		allPostCount = ss.getMapper(BoardMapper.class).countAllPost();
	}
	
	public void deleteComment(HttpServletRequest req, BoardComment comment) {
		try {
			if (ss.getMapper(BoardMapper.class).deleteComment(comment) == 1) {
				req.setAttribute("result", "댓글 삭제 완료");
			} else {
				req.setAttribute("result", "댓글 삭제 실패");
			}
		} catch (Exception e) {
			req.setAttribute("result", "댓글 삭제 실패");
		}
	}
	
	private String colorGenerate() {
		int h = 0;
		String color = "#";
		for (int i = 0; i < 6; i++) {
			h = r.nextInt(16);
			color = color.concat(hexValue[h]);
		}
		return color;
	}
	
	public void deletePost(HttpServletRequest req, BoardPost post) {
		try {
			if (ss.getMapper(BoardMapper.class).deletePost(post) == 1) {
				req.setAttribute("result", "글삭제 완료");
				allPostCount --;
			} else {
				req.setAttribute("result", "글삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "글삭제 실패");
		}
	}
	
	public void editPost(HttpServletRequest req, BoardPost post) {
		try {
			if (ss.getMapper(BoardMapper.class).updatePost(post) == 1) {
				req.setAttribute("result", "수정 성공");
			} else {
				req.setAttribute("result", "수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정 실패");
		}
	}
	
	public void getPost(HttpServletRequest req, int page) {
		req.setAttribute("pageNum", page);
		int postPerPage = (req.getParameter("p") != null) ? 
				Integer.parseInt((String) req.getParameter("p")) : 3;
		req.setAttribute("p", postPerPage);
		String search = (String) req.getSession().getAttribute("search");
		BoardSelector selector = new BoardSelector();

		int postCount = 0;
		if (search == null) {
			postCount = allPostCount;
			search = "";
			selector.setSearch(search);
		} else {
			selector.setSearch(search);
			postCount = ss.getMapper(BoardMapper.class).countPost(selector);
		}
		int allPageCount = (int) Math.ceil((double) postCount / postPerPage);
		req.setAttribute("allPageCount", allPageCount);
		// rownum의 번호 지정
		int start = (page - 1) * postPerPage + 1;
		int end = (page == allPageCount) ? postCount : start + postPerPage - 1;
		
		selector.setStart(start);
		selector.setEnd(end);
		
		List<BoardPost> posts = ss.getMapper(BoardMapper.class).getPost(selector);
		
		for (BoardPost boardPost : posts) {
			boardPost.setP_comments(ss.getMapper(BoardMapper.class).getComment(boardPost));
		}
		req.setAttribute("posts", posts);
	}
	
	public void searchClear(HttpServletRequest req) {
		req.getSession().setAttribute("search", null);
	}
	
	public void searchSet(HttpServletRequest req) {
		req.getSession().setAttribute("search", req.getParameter("search"));
	}
	
	
	private boolean tokenChk(HttpServletRequest req, String token) {
		String tokenChk = (String) req.getSession().getAttribute("token");
		if (tokenChk != null && tokenChk.equals(token)) {
			return false;
		}
		return true;
	}
	
	public void writeComment(HttpServletRequest req, BoardComment comment) {
		String token = req.getParameter("token");
		if (tokenChk(req, token)) {
			try {
				
				Member m = (Member) req.getSession().getAttribute("user");
				comment.setC_writer_id(m.getM_id());
				
				if (ss.getMapper(BoardMapper.class).writeComment(comment) == 1) {
					req.getSession().setAttribute("token", token);
					req.setAttribute("result", "댓글쓰기 성공");
				} else {
					req.setAttribute("result", "댓글쓰기 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("result", "댓글쓰기 실패");
			}
		} else {
//			req.setAttribute("result", "");
			return;
		}
	}

	public void writePost(HttpServletRequest req, BoardPost post) {
		String token = req.getParameter("token");
		if (tokenChk(req, token)) {
			try {
				Member m = (Member) req.getSession().getAttribute("user");
				post.setP_writer_id(m.getM_id());
				post.setP_color(colorGenerate());
				String p_txt = post.getP_txt().replace("\r\n", "<br>");
				post.setP_txt(p_txt);
				
				if (ss.getMapper(BoardMapper.class).writePost(post) == 1) {
					req.getSession().setAttribute("token", token);
					allPostCount ++;
					req.setAttribute("result", "글쓰기 성공");
				} else {
					req.setAttribute("result", "글쓰기 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("result", "글쓰기 실패");			
			}
		} else {
//			req.setAttribute("result", "");
			return;
		}
	}
}
