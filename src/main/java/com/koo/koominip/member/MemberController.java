package com.koo.koominip.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

	@Autowired
	private MemberDAO mDAO;
	
	@RequestMapping(value = "/member.id.check", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Member> idChk(Member m) {
		List<Member> Members = mDAO.idChk(m);
		return Members;
	}
	
	@RequestMapping(value = "/member.info", method = RequestMethod.GET)
	public String memberInfo(HttpServletRequest req) {
		if (mDAO.loginCheck(req)) {
			mDAO.info(req);
			req.setAttribute("content", "member/info.jsp");
		} else {
			req.setAttribute("content", "home.jsp");			
		}
		return "index";
	}

	@RequestMapping(value = "/member.join", method = RequestMethod.POST)
	public String memberJoin(HttpServletRequest req, Member m) {
		mDAO.join(req, m);
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.login", method = RequestMethod.POST)
	public String memberLogin(HttpServletRequest req, HttpServletResponse res, Member m) {
		mDAO.login(req, res, m);
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.logout", method = RequestMethod.GET)
	public String memberLogout(HttpServletRequest req) {
		mDAO.logout(req);
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.signup", method = RequestMethod.GET)
	public String memberSignup(HttpServletRequest req) {
		mDAO.loginCheck(req);
		req.setAttribute("content", "member/join.jsp");
		return "index";
	}
	
	@RequestMapping(value = "/member.update", method = RequestMethod.POST)
	public String memberUpdate(HttpServletRequest req, Member m) {
		mDAO.update(req, m);
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");
		return "index";
	}

	@RequestMapping(value = "/member.withdraw", method = RequestMethod.GET)
	public String memberWithdraw(HttpServletRequest req) {
		if (mDAO.loginCheck(req)) {
			mDAO.withdraw(req);
			mDAO.logout(req);
		}
		mDAO.loginCheck(req);
		req.setAttribute("content", "home.jsp");			
		return "index";
	}
}
