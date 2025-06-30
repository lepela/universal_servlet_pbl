package util;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonRespUtil {
	private static final Gson GSON = new Gson();
	
	
	public static <T> T readJson(HttpServletRequest req, Class<T> clazz) {
		try(BufferedReader br = req.getReader()) {
			return GSON.fromJson(br, clazz);
		} catch (Exception e) {
			throw new RuntimeException("json 파싱 실패", e);
		}
	}
	// writeJson overload
	public static void writeJson(HttpServletResponse resp, Object data) {
		try {
			writeJson(resp, data, 200);
		} catch (Exception e) {
			writeJson(resp, Map.of("msg", "서버 오류"), 500);
		}
	}
	
	// object data에 올수있는건. 일반 domain, map
	public static void writeJson(HttpServletResponse resp, Object data, int status) {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json");
		resp.setStatus(status);
		
		try(PrintWriter pw = resp.getWriter()) {
			pw.print(GSON.toJson(data));
		}
		catch (Exception e) {
			throw new RuntimeException("json 응답 실패", e);
		}
	}
}
