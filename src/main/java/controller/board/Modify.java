package controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import domain.Attach;
import domain.Board;
import domain.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import service.BoardService;
import util.AlertUtil;

@WebServlet("/board/modify")
@Slf4j
public class Modify extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = Criteria.init(req);
		
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근입니다", "/board/list", req, resp);
			return;
		}
		Long bno = Long.valueOf(req.getParameter("bno"));
		
		if(req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인 후 글 작성해주세요", "/member/login?bno=" + bno + "&" + cri.getQs2(), req, resp, true);
			return;
		}

		
		BoardService service = new BoardService();
		Board board = service.findBy(bno);
		req.setAttribute("cri", cri);
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 세션 체크
		Criteria cri = Criteria.init(req);
		if(req.getSession().getAttribute("member") == null) {
			AlertUtil.alert("로그인 후 글 작성해주세요", "/member/login?" + cri.getQs2(), req, resp, true);
			return;
		}
//		
		// 파라미터 수집
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = req.getParameter("id");
		Integer cno = cri.getCno();
		Long bno = Long.valueOf(req.getParameter("bno"));
		String encodedStr = req.getParameter("encodedStr");
		Type type = new TypeToken<List<Attach>>() {}.getType();
		List<Attach> list = new Gson().fromJson(encodedStr, type);
//		// board 인스턴스 생성
		
		log.info("{}", list);
		Board board = Board.builder().attachs(list).title(title).content(content).cno(cno).bno(bno).build();
		log.info("{}", board);
//		
//		// 서비스 호출
		new BoardService().modify(board);
		log.info("{}", cri);
//		// 리디렉션 (/board/list)
		AlertUtil.alert("글이 수정되었습니다", "/board/view?bno=" + bno + "&" + cri.getQs2(), req, resp);
	}
	
}
