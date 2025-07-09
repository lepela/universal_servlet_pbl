package controller;

import java.io.IOException;import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JsonRespUtil;
import util.RedisUtil;

@WebServlet("/redis-checker")
public class RedisChecker extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String key = "user-token";
		if(RedisUtil.exists(key)) {
			JsonRespUtil.writeJson(resp, Map.of("ret", RedisUtil.get(key), "ttl", RedisUtil.ttl(key)));
		}
		else {
			JsonRespUtil.writeJson(resp, null);
		}
	}
	
}
