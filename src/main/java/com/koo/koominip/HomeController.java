package com.koo.koominip;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koo.koominip.board.BoardDAO;
import com.koo.koominip.member.Member;
import com.koo.koominip.member.MemberDAO;

@Controller
public class HomeController {

	@Autowired
	private MemberDAO mDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest req) {
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");
		return "index";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home1(HttpServletRequest req) {
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");
		return "index";
	}
}
