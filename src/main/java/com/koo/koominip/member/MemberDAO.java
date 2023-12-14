package com.koo.koominip.member;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Service
public class MemberDAO {
	@Autowired
	private SqlSession ss;

	private String addrCon(MultipartRequest mr) {
		String addr1 = mr.getParameter("addr1");
		String addr2 = mr.getParameter("addr2");
		String addr3 = mr.getParameter("addr3");
		String m_addr = addr1 + "@" + addr2 + "@" + addr3;
		return m_addr;
	}

	private String[] addrDiv(String m_addr) {
		String[] addr = m_addr.split("@");
		return addr;
	}
	
	public List<Member> idChk(Member m) {
		List<Member> Members = ss.getMapper(MemberMapper.class).checkId(m);
		return Members;
	}
	
	public void info(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("user");
		String[] addr = addrDiv(m.getM_addr());
		String[] phone = phoneDiv(m.getM_phone());
		req.setAttribute("addr", addr);
		req.setAttribute("phone", phone);
	}
	
	public void join(HttpServletRequest req, Member m) {
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 20 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "가입실패(파일용량초과)");
			return;
		}

		try {
			m.setM_id(mr.getParameter("m_id"));
			m.setM_pw(mr.getParameter("m_pw"));
			m.setM_name(mr.getParameter("m_name"));

			m.setM_phone(phoneCon(mr));

			m.setM_addr(addrCon(mr));

			String m_pic = URLEncoder.encode(mr.getFilesystemName("m_pic"), "UTF-8").replace("+", " ");
			m.setM_pic(m_pic);

			m.setM_role(mr.getParameter("m_role"));

			if (ss.getMapper(MemberMapper.class).join(m) == 1) {
				req.setAttribute("result", "가입성공");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "가입실패");
		}
	}

	public void login(HttpServletRequest req, HttpServletResponse res, Member loginM) {
		try {
			// 1. id가 있는지 먼저 확인
			List<Member> mlst = ss.getMapper(MemberMapper.class).checkId(loginM);
			// m에는 넘어온 모든 데이터 (id, pw)가 들어있음
			// checkId 쿼리는 id로만 쿼리를 수행

			// 2. id가 있다면
			if (mlst.size() != 0) {
				Member m = mlst.get(0); // 리스트의 첫번째(0번째)에 있는 내용을 member에 담음
				// 3. 위 Member의 pw와 같다면
				if (m.getM_pw().equals(loginM.getM_pw())) {
					// 4. 세션 > user 속성으로 db에서 가져온 member를 담음
					req.getSession().setAttribute("user", m);
					req.getSession().setMaxInactiveInterval(60 * 10);
					req.setAttribute("result", "로그인 성공");
					setLastIdCookie(req, res);
				} else {
					req.setAttribute("result", "비밀번호 오류");
				}
			} else {
				req.setAttribute("result", "가입되지 않은 아이디입니다");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "로그인 실패");
		}
	}

	public boolean loginCheck(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("user");
		if (m != null) {
			req.setAttribute("login", "member/user.jsp");
			return true;
		} else {
			req.setAttribute("login", "member/login.jsp");
			return false;
		}
	}

	public void logout(HttpServletRequest req) {
		req.getSession().setAttribute("user", null);
	}

	private String phoneCon(MultipartRequest mr) {
		String phone1 = mr.getParameter("phone1");
		String phone2 = mr.getParameter("phone2");
		String phone3 = mr.getParameter("phone3");
		String m_phone = phone1 + "-" + phone2 + "-" + phone3;
		return m_phone;
	}
	
	private String[] phoneDiv(String m_phone) {
		String[] phone = m_phone.split("-");
		return phone;
	}
	
	public void setLastIdCookie(HttpServletRequest req, HttpServletResponse res) {
		Cookie c = new Cookie("lastLoginId", ((Member) req.getSession().getAttribute("user")).getM_id());
		c.setMaxAge(60 * 60 * 24 * 3);
		res.addCookie(c);
	}
	
	public void update(HttpServletRequest req, Member m) {
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		System.out.println(path);
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 20 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정실패(파일용량초과)");
			return;
		}
		
		Member user = (Member) req.getSession().getAttribute("user");
		
		String oldPic = user.getM_pic();
		String m_pic = null;
		
		try {
			m.setM_id(mr.getParameter("m_id"));
			m.setM_pw(mr.getParameter("m_pw"));
			m.setM_name(mr.getParameter("m_name"));

			m.setM_phone(phoneCon(mr));

			m.setM_addr(addrCon(mr));
			
			m_pic = mr.getFilesystemName("m_pic");
			if (m_pic == null) {
				m_pic = oldPic;
			} else if (m_pic.equals(oldPic)) {
				oldPic = URLDecoder.decode(oldPic, "UTF-8");
				new File(path + "/" + oldPic).delete();
				m_pic = URLEncoder.encode(m_pic, "UTF-8").replace("+", " ");
			} else {
				m_pic = URLEncoder.encode(m_pic, "UTF-8").replace("+", " ");
			}

			m.setM_pic(m_pic);
			
			m.setM_role(mr.getParameter("m_role"));

			if (ss.getMapper(MemberMapper.class).updateInfo(m) == 1) {
				if (!oldPic.equals(m_pic)) {
					oldPic = URLDecoder.decode(oldPic, "UTF-8");
					new File(path + "/" + oldPic).delete();
				}
				
				req.getSession().setAttribute("user", m);
				req.setAttribute("result", "수정성공");
			} else {
				req.setAttribute("result", "수정실패(서버오류)");
				if (!oldPic.equals(m_pic)) {
					m_pic = URLDecoder.decode(m_pic, "UTF-8");
					new File(path + "/" + m_pic).delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "수정실패(연결실패)");
			
			if (!oldPic.equals(m_pic)) {
				try {
					m_pic = URLDecoder.decode(m_pic, "UTF-8");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				new File(path + "/" + m_pic).delete();
			}
		}
	}

	public void withdraw(HttpServletRequest req) {
		Member m = (Member) req.getSession().getAttribute("user");
		String path = req.getSession().getServletContext().getRealPath("resources/img");
		try {
			String m_pic = URLDecoder.decode(m.getM_pic(), "UTF-8");
			
			if (ss.getMapper(MemberMapper.class).withdraw(m) == 1) {
				new File(path + "/" + m_pic).delete();
				req.setAttribute("result", "탈퇴완료");
			} else {
				req.setAttribute("result", "탈퇴실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "탈퇴실패(서버문제)");
		}
	}
}
