package controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.dto.Criteria;
import service.BoardService;
import util.AlertUtil;

@WebServlet("/board/remove")
public class Remove extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getParameter("bno") == null) {
			AlertUtil.alert("잘못된 접근입니다", "/board/list", req, resp);
			return;
		}
		
		BoardService service = new BoardService();
		service.remove(Long.valueOf(req.getParameter("bno")));
		Criteria cri = Criteria.init(req);
		AlertUtil.alert("글이 삭제되었습니다", "/board/list?" + cri.getQs2(), req, resp);
	}
}
