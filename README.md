# 도서관리 애플리케이션의 요구사항

### 사용자

- 도서관의 사용자를 등록할 수 있다. (이름 필수, 나이 선택)
- 도서관 사용자의 목록을 볼 수 있다.
- 도서관 사용자 이름을 업데이트 할 수 있다.
- 도서관 사용자를 삭제할 수 있다.

### 책

- 도서관에 책을 등록 및 삭제할 수 있다.
- 사용자가 책을 빌릴 수 있다.
    - 다른 사람이 그 책을 진작 빌렸다면 빌릴 수 없다.
- 사용자가 책을 반납할 수 있다.

---

## 첫번째 API 개발 (유저 생성 API 개발)

### 사용자를 등록할 수 있다.(이름 필수, 나이 선택)

#### API 명세

- HTTP Method : POST
- HTTP Path : /user
- HTTP Body : JSON

```java
{
    "name" : String (null 불가능),
    "age"" : Integer   
        }
```

- 결과는 반환하지 않는다. (HTTP 상태 200 OK)

---

## 두번째 API 개발 (유저 조회)

#### API 명세

- HTTP Method : GET
- HTTP Path: /user
- 쿼리 : 없음
- 결과 반환 : 객체를 반환하면 json 형식으로 반환된다. getter 가 있다는 존재하에!

```json
  [{
    "id" : Long,
    "name" : String (null 불가능),
    "age" : Integer
}]

```

### 현재 까지의 문제점 등록 조회 기능은 된다. 하지만!!!

- 데이터베이스에 저장하고 있지 않기 때문에 서버를 재시작하면 날라간다.
- 데이터베이스와 연결을 해서 저장해야한다!

---

## 세번째 API 개발(유저 업데이트)

#### API 명세

- HTTP Method: PUT
- HTTP Path: /user
- HTTP Body (Json)

```json
{
  "id" : Long,
  "name" : String // 변경되어야 하는 이름이 들어오는 곳
}
```

- 결과 반환 X (HTTP 상태 200 OK)

---

## 네번째 API 개발(유저 삭제)

#### API 명세

- HTTP Method: DELETE
- HTTP Path: /user
- 쿼리 사용
  - 문자열 name (삭제되어야 하는 사용자 이름)
- 결과 반환 X(HTTP 상태 200 OK)

## 현재 개발한 업데이트 삭제는 치명적인 오류가 있다.

- 존재하지 않는 유저에 대한 수정과 삭제등에 200 OK 가 떨어진다.
- 예외를 처리 해주어야 한다.


---

### 예외처리

-  업데이트와 삭제를 각각의 기준에 맞게 select 를 통해
객체를 받아내고 그 후에 그 객체가 비었다면 IllegalArgumentException 을 반환해준다.


--- 

## 또 하나의 문제점 

> 우리는 지금 Controller 에서 모든 기능을 담당하고 있다.
> 그렇기 때문에 Service 와  Repository 로 나누어 주겠다.
