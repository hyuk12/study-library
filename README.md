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
