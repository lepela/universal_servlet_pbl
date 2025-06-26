# Universal JSP Board 📝

> SSR과 CSR이 공존하는, 실무형 Servlet/JSP 기반 게시판 시스템  
> - 유니버설 렌더링 + SEO 대응  
> - CSR 기반 첨부파일 처리  
> - 확장 가능한 REST & SPA 기반 설계 대비 구조  

---

## 🔧 Tech Stack

- **Backend**: Servlet 3.1, JSP, Tomcat 9, MariaDB
- **Build**: Maven (Dynamic Web Project → Maven 프로젝트 전환)
- **ORM**: MyBatis
- **Connection Pool**: HikariCP
- **Logging**: SLF4J + Logback + Log4JDBC
- **Security**: Bcrypt (비밀번호 해싱)
- **Util**: Gson, Thumbnailator, JSTL 1.2
- **Test**: JUnit5

---

## 🗂️ 주요 테이블 구조

- `tbl_member`: 회원 테이블 (게시글·댓글 작성자 참조)
- `tbl_board`: 게시글 테이블
- `tbl_reply`: 댓글 테이블 (비동기 처리 기반)
- `tbl_attach`: 첨부파일 테이블 (1:N 구조)
- `tbl_category`: 카테고리 구분
- 🔄 모든 테이블은 FK 기반의 관계형 설계 유지

---

## 🖋️ 주요 기능 및 구현 내역

### ✅ 게시글 기능

- 게시글 작성, 수정, 삭제 (권한 체크 포함)
- 카테고리 기반 리스트 + 페이징 + 검색
- Criteria DTO 기반의 동적 SQL 구성
- 댓글 수, 첨부파일 수, 대표 이미지(uuid/path) 서브쿼리 조회 처리

### ✅ 댓글 (Reply) 기능

- AJAX 기반 비동기 처리
- 시간 표현 포맷 변경 (Gson 기반)
- 댓글 페이징
- `selectKey` 기반 rno/regdate 동시 반환
- 화면 수정/삭제 반영 (동기화 처리)

### ✅ 첨부파일

- 비동기 AJAX 업로드
- 확장자 제한: `exe`, `sh`, `jsp`, `java`, `class`, `html`, `js`
- 중복 파일명 → UUID + 원본 이름 저장 방식
- 이미지 여부 판단 + 썸네일 자동 생성
- 첨부 갯수/크기 제한 (max 5개, 개당 10MB, 총합 50MB)
- SSR 수정 화면에서도 CSR 방식 공유 가능 (`data-*` 속성 기반)
- ghost file 처리: Quartz 스케줄러 기반 삭제 처리

---

## 🖼️ 갤러리형 게시판 (진행 중)

- 카테고리 유형에 따른 갤러리 UI 적용
- 대표 썸네일 1장만 attachs[0]에 매핑
- 이미지 미존재 시 기본 플레이스홀더(`noimg.jpg`) 출력
- 목록 쿼리/뷰 분기 구조 준비 완료

---

## 🧰 공통 유틸리티

- `AlertUtils`: 스크립트 기반 알림 처리
- `PasswordEncoder`: BCrypt 비밀번호 해싱
- `ParamUtils` (예정): 요청 파라미터 수집
- `JsonRespUtils` (예정): JSON 기반 응답코드 포맷화

---

## 🌐 배포 및 인프라 구성

### ✅ 완료된 항목

- AWS EC2 수동 배포
- Nginx Reverse Proxy + Subdomain Redirect
- DNS + Cloudflare 연동
- 비즈메일용 MX 레코드 설정
- WebSocket Echo, 1:N Broadcast (Tomcat 기반)

### 🛠️ 예정

- SSL 인증서 발급 및 적용 (Let's Encrypt or Cloudflare SSL)

---

## ⏱️ 학습/수업 기준

- **총 소요 시간**: 일 4시간 × 15일 = 약 60시간 설계 기반
- 스프링 + 리액트 수업 진입 전, SSR + CSR 구조 감각 학습을 위한 교두보
- 첨부파일 처리, CSR 모듈링 등은 Next.js 구조의 사전 체화용 설계

---

## 🚀 실행 환경 (개발용)

```properties
# db.properties (외부 파일 분리, .gitignore 처리)
jdbc.url=jdbc:mariadb://localhost:3306/pbl2
jdbc.username=YOUR_ID
jdbc.password=YOUR_PW
