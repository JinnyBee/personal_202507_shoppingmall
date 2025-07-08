# 여성 의류 쇼핑몰 LittleStar 프로젝트

Spring Boot 기반의 여성 의류 쇼핑몰 웹 애플리케이션입니다.  
쇼핑몰 기능 외에도 **자유게시판(커뮤니티)** 기능을 포함하고 있습니다.  
Bootstrap 5를 활용해 반응형 UI를 구현하였으며, PostgreSQL을 데이터베이스로 사용했습니다.

---

## 주요 기능

### 쇼핑몰 기능 (구현 예정)
- 상품 목록 / 상세 페이지
- 장바구니
- 주문 및 결제 (기본 로직)
- 사용자 회원가입 / 로그인 / 마이페이지
- 카테고리별 상품 필터링

### 자유게시판
- 게시글 목록 / 상세보기
- 게시글 작성 / 수정 / 삭제 (로그인 사용자)
- 댓글 기능
- 좋아요

---

## 사용 기술

| 항목          | 기술 스택 |
|---------------|-----------|
| **Backend**   | Spring Boot 3.5.0 |
| **Frontend**  | Thymeleaf + Bootstrap 5 |
| **Database**  | PostgreSQL |
| **ORM**       | Spring Data JPA |
| **Security**  | Spring Security (로그인 인증 처리) |
| **Build Tool**| Gradle |
| **배포**       |  |

---

## 프로젝트 실행 방법

1. **프로젝트 클론**
   ```bash
   git clone https://github.com/JinnyBee/personal_202507_shoppingmall.git
   cd your-repo-name

2. **DB 설정**
`application.properties` 파일 중 PostgreSQL 연결 관련 설정 정보

   ```properties
   # DataSource
   spring.datasource.driver-class-name=org.postgresql.Driver
   spring.datasource.url=jdbc:postgresql://localhost:5432/shoppingmall
   spring.datasource.username=hjjeon
   spring.datasource.password=1234

   # JPA
   spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.show_sql=true
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.open-in-view=false


3. **의존성 설치 및 서버 실행**
   ```bash
   ./gradlew build
   ./gradlew bootRun

4. **웹 접속**
   ```chrome
   http://localhost:8080

5. **디렉토리 구조**
   ```bash
   src/
    └─ main/
        ├─ java/com/example/shoppingmall
        │   ├─ Config/                # Spring Security 등 설정
        │   ├─ Constant/              # 프로젝트 전역 상수 정의
        │   ├─ Controller/            # 웹 컨트롤러
        │   ├─ DTO/                   # 웹 컨트롤러
        │   ├─ Entity/                # 엔티티 클래스
        │   ├─ Repository/            # JPA Repository
        │   ├─ Service/               # 비즈니스 로직
        │   └─ config/                # Spring Security 등 설정
        └─ resources/
            ├─ static/                # CSS, JS, 이미지 등 정적 자원
            ├─ templates/             # Thymeleaf 템플릿
            └─ application.properties # 환경 설정

6. **개발자**
   - **이름**: 전현진 (JinnyBee)  
   - **이메일**: hjjeon81@gmail.com  
   - **GitHub**: [@JinnyBee](https://github.com/JinnyBee)
