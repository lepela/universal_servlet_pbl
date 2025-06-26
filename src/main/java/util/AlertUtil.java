package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlertUtil {
	public static void alert(String msg, String url, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		req.setAttribute("msg", msg);
		req.setAttribute("url", req.getContextPath() + url);
		req.getRequestDispatcher("/WEB-INF/views/common/alert.jsp").forward(req, resp);
	}

	public static void alert(String msg, String url, HttpServletRequest req, HttpServletResponse resp, boolean isUrl) throws IOException, ServletException {
		if(isUrl) {
			url =  url + "&url=" + URLEncoder.encode(req.getRequestURL().toString(), "utf-8"); 
		}
		log.info("{}", url);
		alert(msg, url, req, resp);
	}
}
