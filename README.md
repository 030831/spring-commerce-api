# spring-commerce-api

Java/Spring 기반 미니 쇼핑몰 백엔드 API 프로젝트입니다.

이 프로젝트의 목적은 완성형 쇼핑몰을 빠르게 만드는 것이 아니라, Spring Boot, JPA, MySQL, REST API, DB 설계, DTO 분리, 요청 검증, 예외 처리 흐름을 단계적으로 익히는 것입니다.

## 기술 스택

- Java 17
- Spring Boot 3.5.14
- Spring Web
- Spring Data JPA
- MySQL
- Gradle
- Lombok
- Bean Validation
- Postman
- Git/GitHub

## 현재 구현 범위

현재는 Product 도메인을 중심으로 기본 CRUD API를 구현했습니다.

- 상품 등록
- 상품 단건 조회
- 상품 전체 조회
- 상품 수정
- 상품 삭제
- DTO 기반 요청/응답 분리
- 요청 값 검증
- 상품 미존재 예외 처리
- 검증 실패 예외 처리

아직 회원, 주문, 장바구니, 결제, 인증/인가, 배포는 구현하지 않았습니다.

## 주요 학습 목표

- EntityManager 기반 Repository 구현
- JPA persist, find, JPQL, remove 동작 이해
- 트랜잭션 안에서 변경 감지로 수정 처리
- Entity를 API 요청/응답에 직접 사용하지 않고 DTO로 분리
- @Valid와 Bean Validation을 이용한 요청 검증
- GlobalExceptionHandler를 이용한 예외 응답 처리

## Product API

### 상품 등록

POST /api/products

Request Body

{
"name": "상품A",
"price": 10000
}

Response Body

1

현재는 생성된 상품 id만 반환합니다.

### 상품 단건 조회

GET /api/products/{productId}

Response Body

{
"id": 1,
"name": "상품A",
"price": 10000,
"createdAt": "2026-05-28T15:00:00"
}

### 상품 전체 조회

GET /api/products

Response Body

[
{
"id": 1,
"name": "상품A",
"price": 10000,
"createdAt": "2026-05-28T15:00:00"
}
]

### 상품 수정

PATCH /api/products/{productId}

Request Body

{
"name": "수정된 상품A",
"price": 15000
}

### 상품 삭제

DELETE /api/products/{productId}

## 요청 검증

상품 생성과 수정 요청에서는 다음 조건을 검증합니다.

- 상품명은 필수입니다.
- 상품 가격은 필수입니다.
- 상품 가격은 0보다 커야 합니다.

검증 실패 시 400 Bad Request와 함께 에러 메시지를 반환합니다.

{
"message": "상품명은 필수입니다."
}

## 예외 처리

존재하지 않는 상품 id로 조회, 수정, 삭제를 요청하면 404 Not Found를 반환합니다.

{
"message": "해당 상품은 존재하지 않습니다."
}

예외 응답은 GlobalExceptionHandler에서 처리합니다.

## 로컬 실행 설정

이 프로젝트는 MySQL을 사용합니다.

공개 저장소에는 DB 비밀번호를 올리지 않기 위해 application-local.properties 파일은 GitHub에 포함하지 않습니다.

로컬에서 다음과 같이 DB를 준비합니다.

CREATE DATABASE shopping_mall;
CREATE USER 'shopping_user'@'localhost' IDENTIFIED BY '비밀번호';
GRANT ALL PRIVILEGES ON shopping_mall.* TO 'shopping_user'@'localhost';
FLUSH PRIVILEGES;

src/main/resources/application-local.properties 파일을 생성하고 로컬 DB 정보를 작성합니다.

spring.datasource.url=jdbc:mysql://localhost:3306/shopping_mall?serverTimezone=Asia/Seoul&characterEncoding=UTF-8
spring.datasource.username=shopping_user
spring.datasource.password=비밀번호

## 현재 개발 단계

현재 Product API 1차 구현이 완료된 상태입니다.

다음 단계 후보는 다음과 같습니다.

- ProductController 테스트 추가
- README와 노션 문서 보완
- Member 도메인 구현
- Order 도메인 구현
- 공통 응답 형식 도입 검토