package controller.endpoint;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import lombok.extern.slf4j.Slf4j;

@ServerEndpoint("/echo")
@Slf4j
public class EchoEndPoint {
	// 모든 연결된 세션을 저장할 Set
	private static final Set<Session> SESSIONS = new CopyOnWriteArraySet<Session>();
	
	// 최소 3개의 메서드 정의
	// 연결
	@OnOpen
	public void onOpen(Session session) {
		SESSIONS.add(session);
		log.info("현재 접속자수는 {} 명입니다", SESSIONS.size());
	}
	// 메세지발송
	@OnMessage
	public void onMessage(String msg, Session session) throws IOException{
		log.info("수신된 메세지 : {}", msg);
		for(Session s : SESSIONS) {
			s.getBasicRemote().sendText(session.getId() + " : " + msg);
		}
	}
	// 종료
	@OnClose
	public void onClose(Session session) {
		SESSIONS.remove(session);
		log.info("현재 접속자수는 {} 명입니다", SESSIONS.size());
	}
	
	
}
