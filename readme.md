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
## 테스트 적용 범위
- Junit5를 통한 단위테스트 적용
- MyBatisMapper, Service에 테스트 코드 적용
- Servlet은 테스트 대상 제외 (추후 스프링 프레임워크 적용 후 MockMvc테스트 예정)
- Jacoco같은 커버리지 툴도 스프링 프레임워크 이후 적용

## 🖼️ 갤러리형 게시판

- 카테고리 유형에 따른 갤러리 UI 적용
- 대표 썸네일 1장만 attachs[0]에 매핑
- 이미지 미존재 시 기본 플레이스홀더(`noimg.jpg`) 출력
- 목록 쿼리/뷰 분기 구조 준비 완료

---
## 📆 개발 히스토리 요약
#### 2025-06-16 내용
  - DB 설계 tbl_member, tbl_category, tbl_board, tbl_reply, tbl_attach 
  - HikariCP, Mybatis 적용(xml + java)
  - 해당 테이블에 일치하는 domain 클래스 설계 (Dto, Vo는 분류하지 않음. 스프링 프레임워크 수업시 분리 예정)
  - Mybatis 기본 골격 샘플 활용
  - tbl_member을 활용한 세션 기반 인증. 회원가입, 로그인 페이지 및 구현
  - Cors, CharsetEncode filter적용
  - ContextPath > listener를 통해 짧은 이름으로 적용
#### 2025-06-17 내용
  - member 활용을 이용한 session scope적용으로 dashboard 화면 전환
  - 회원의 민감정보(password)를 brcrypt encode 방식으로 적용
  - dashboard > board > member 관련 jsp 구현 (bootstrap5 기반)
  - tbl_board기반의 crud Mybatis Mapper 설정
  - boardMapper 정의 및 기본 CRUD 적용
  - board관련 서비스 설계 및 mapper 호출.
  - Servlet과의 매핑및 목록, 단일 조회 적용 후 테스트
  - js의 alert에 메세지를 응답하는 AlertUtil 적용
#### 2025-06-18 내용
  - 글 작성, 글 수정, 글 삭제 구현
  - 비 로그인시 href 파라미터를 이용한 로그인 페이지 리디렉션후 본 페이지 돌아오기 적용
  - 간이 배포 테스트 war export후 aws ubuntu 환경에 tomcat9설치
  - sub domain을 기반으로 한 reverse proxy + tomcat server.xml 세팅
  - pbl.domain.com 접속시 80 포트로 tomcat 연동 확인
  - 페이지네이션 처리 추가 시작
  - limit, offset 활용한 페이지 적용 
  - Criteria class를 활용한 page, amount 필드로 페이징 일관성 유지
#### 2025-06-19 내용
  - PageDto class 설계 criteria를 필드로 활용하고 start, end, left, right, doubleLeft, doubleRight적용
  - 생성자 내부 내용을 통해 필드 초기화 
  - 게시글 전체갯수 및 목록상에 bootstrap 5 pagination 적용
  - 검색 기능 구현 설계
  - criteria에 category, type, keyword추가
  - mybatis 태그 중 where, trim, if, foreach 활용
#### 2025-06-20 내용
  - 로깅 적용법 이해 slf4j + logback활용.
  - logback.xml내에 로깅 포매터 및 color highlight지정.
  - rolling appender를 이용한 파일 추가 및 일별 로깅 파일 분리
  - Junit5 적용 이해, scope, main/test 소스 분리
  - @Test @Before @Display등 어노테이션을 통한 테스트 클래스 / 메서드 이해
  - Assertion을 활용한 given, when, then, expect, assert의 단계 적용
  - 댓글 (Reply) 구현을 위한 화면 구성
#### 2025-06-23 내용
  - ReplyController 제작, GET, POST, PUT, DELETE의 HttpMethod를 활용한 Single controller적용
  - get으로 호출시 /reply/{rno}, /reply/list/{bno}, /reply/list/{bno}/{lastRno} 구분 후 더보기식 페이지네이션 고려 URI 설계
  - jQuery ajax를 활용 + bootstrap5 modal을 활용한 댓글 비동기 구현
  - 비동기 호출 후 적용되는 jquery ajax메서드 모듈화 및 공통 작업
  - rest api 호출 테스트(Postman 활용)
  - 작성후 글 즉시 반영, 더보기 시 목록 추가, 수정 및 삭제시 즉시 반영 UI 적용
#### 2025-06-24 내용
  - 파일 업로드 이해 Multipart/form-data enctype 적용, Part인터페이스 이해
  - 동기방식 업로드를 비동기로 전환하면서 발생되는 ajax call header 변경, 
  - file의 metadata 분석, ext 분리로 same file name policy 정의 (UUID 활용)
  - Attach Dto 분석 이해, 파일 업로드 시 경로 구분(연/월/일) uuid, origin 구분, 이미지 여부 기록, 파일 크기 기록
  - 파일 업로드 성공 시 응답 json형태 확인 attach 의 list형태를 gson으로 생성 후 확인
#### 2025-06-25 내용
  - 파일의 업로드 후 json데이터 분석 후 화면에 표시
  - attach 에 odr이라는 업로드 순서 기록 필드 추가
  - 업로드된 모든 파일의 내용은 list로 관리 data-* 활용으로 uuid, path, origin, image, size, odr 전부 기록
  - 이 파일들 중 이미지는 섬네일 생성 구현 (Thumbnailator lib 적용)
  - 파일 목록 상 삭제시 섬네일 같이 삭제, 반대로 섬네일 삭제시 목록에서도 제거 event 적용
  - /display?uuid=path= 를 통한 이미지 표시 서블릿 구현
  - /download?uuid=origin=path= 를 통한 파일 다운로드 서블릿 구현
  - 게시글의 첨부파일로 기록되기 위한 사전작업 board class에 List<Attach> 추가
#### 2025-06-26 내용
  - 게시글과 첨부파일은 1:N관계로 파라미터 수집을 위한 json stringify를 활용 후 게시글 작성시 전송.
  - 동기방식으로 적용된 board와 별개로 gson을 통한 fromjson으로 List<Attach> 수집 후 setAttachs 적용
  - service에서 selectKey를 활용한 작성 후 게시글 번호를 알아와서 attachs에 개별 지정후 insert
  - 동작방식을 transactional 하게 만들기 위해 sqlsession 호출 방식 변경으로 session.commit() session.rollback() 분리 구현
  - 게시글 등록, 수정 시 첨부파일 순서 변경 구현
  - 게시글 삭제 시 댓글 여부, 첨부파일 여부에 따른 child row transaction 처리
  - 게시글 목록에 댓글 갯수, 첨부파일 여부 표시
  - websocket echo, broadcast 샘플 적용 (추후 필요시 notification or toast 적용 목적)
#### 2025-06-27 내용
##### 카테고리 작업
  - enum타입 사용
  - mybatis enum 적용 type handler 처리
  - 카테고리의 유형 view_type (gallery, list, feed)
  - 카테고리의 상태 status (active, disabled)
  - 카테고리의 정보를 application 영역 객체에 바인딩 (성능 최적화)
##### 갤러리형 게시판 
  - UI변경
  - 반응형, 카드형 갤러리 처리
  - no image의 대체이미지 처리
##### 첨부파일 변경시 순서 조정 기능 추가
  - jqueryui - sortable()을 사용
  - odr 재조정으로 화면에 보이게될 순서 재배치

  - 게시글 목록에서 한번에 보여질 amount 드롭다운처리
  - 검색어 유지 
  - 미인증시 로그인 후 이전페이지

  - 미첨부, 첨부 파일 관리 (ghost file) 
#### 2025-06-30 내용
  - Utility 추가 ParamUtil, JsonRespUtil 추가
    - ParamUtil은 get, post 상관없이 특정 domain의 parameter 자동수집, String, int, long, boolean, enum 대응
    - JsonRespUtil은 서버 상태코드 및 응답 메세지 공통 사항 추가
    - 두 클래스 모드 reflection, generic 적극 사용
  - 계층형 게시판 구현
   - grp, seq, depth 세가지 컬럼을 게시판에 추가
   - 답글이 적용된 게시판의 정렬순서 order grp desc, seq asc으로 적용
   - 원글 작성과 답글 작성시의 서로 다른처리를 service에서 적용
#### 2025-07-01 내용
  - 첨부파일 비동기 작업시 발생되는 ghost file (orphan file) 처리
  - Quartz 라이브러리 + cron expression 사용으로 특정 trigger 에 특정 job 처리 하도록 유도
  - 적용된 스케줄러는 리스너로 등록
  
## 🧰 공통 유틸리티

- `AlertUtils`: 스크립트 기반 알림 처리
- `PasswordEncoder`: BCrypt 비밀번호 해싱
- `ParamUtils` : 요청 파라미터 수집
- `JsonRespUtils` : JSON 기반 응답코드 포맷화

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

이슈테스트
```properties
# db.properties (외부 파일 분리, .gitignore 처리)
jdbc.url=jdbc:mariadb://localhost:3306/pbl2
jdbc.username=YOUR_ID
jdbc.password=YOUR_PW
