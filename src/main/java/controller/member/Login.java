package controller.member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Member;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.MemberService;
import util.HikariCPUtil;

@WebServlet("/member/login")
@Slf4j
public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터 수집
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		log.info("{} {}", id, pw);
		// 2. service.login(id, pw)호출
		boolean ret = new MemberService().login(id, pw);
		log.info("{}", ret);
		
		if(ret) { // 로그인 성공
			HttpSession session = req.getSession();
			session.setMaxInactiveInterval(60 * 10);
			session.setAttribute("member", new MemberService().findById(id));
			
			String url = req.getParameter("url");
			if(url == null) {
				resp.sendRedirect(req.getContextPath() + "/index");
			}
			else {
				String decodedUrl = URLDecoder.decode(url, "utf-8");
				Criteria cri = Criteria.init(req);
				resp.sendRedirect(decodedUrl + "?" + cri.getQs2());
			}
			
			// contextPath >> /pbl
		}
		else { // 로그인 실패
			resp.sendRedirect("login?msg=fail");
		}
		// 3. index로 리디렉션
		// resp.sendRedirect("../index");
	}
	
}
