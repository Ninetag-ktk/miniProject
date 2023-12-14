package com.koo.koominip.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.koo.koominip.TokenMaker;
import com.koo.koominip.member.MemberDAO;

@Controller
public class BoardController {
	
	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private BoardDAO bDAO;
	
	private boolean isFirstReq;
	
	public BoardController() {
		isFirstReq = true;
	}
	
	@RequestMapping(value = "/board.go", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		if (isFirstReq) {
			bDAO.countAllPost();
			isFirstReq = false;
		}
		mDAO.loginCheck(req);
		bDAO.searchClear(req);
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/board.page", method = RequestMethod.GET)
	public String changePage(HttpServletRequest req) {
		mDAO.loginCheck(req);
		bDAO.getPost(req, Integer.parseInt(req.getParameter("pageNum")));
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/comment.delete", method = RequestMethod.GET)
	public String deleteComment(HttpServletRequest req, BoardComment comment) {
		if (mDAO.loginCheck(req)) {
			bDAO.deleteComment(req, comment);
		}
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/post.delete", method = RequestMethod.GET)
	public String deletePost(HttpServletRequest req, BoardPost post) {
		if (mDAO.loginCheck(req)) {
			bDAO.deletePost(req, post);
		}
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/post.edit", method = RequestMethod.POST)
	public String editPost(HttpServletRequest req, BoardPost post) {
		if (mDAO.loginCheck(req)) {
			bDAO.editPost(req, post);
		}
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/post.search", method = RequestMethod.POST)
	public String searchPost(HttpServletRequest req) {
		mDAO.loginCheck(req);
		bDAO.searchSet(req);
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/comment.write", method = RequestMethod.POST)
	public String writeComment(HttpServletRequest req, BoardComment comment) {
		if (mDAO.loginCheck(req)) {
			bDAO.writeComment(req, comment);
		}
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/post.write", method = RequestMethod.POST)
	public String writePost(HttpServletRequest req, BoardPost post) {
		if (mDAO.loginCheck(req)) {
			bDAO.writePost(req, post);
		}
		bDAO.getPost(req, 1);
		TokenMaker.makeToken(req);
		req.setAttribute("content", "board/board.jsp");
		return "index";
	}
	
}
