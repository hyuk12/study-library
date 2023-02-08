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
> 그렇기 때문에 Service 와  Repository 로 나누어 주겠다

여기서 의문점은 왜 컨트롤러에서 왜 모든 기능을 구현하면 안되는가

하나의 함수는 하나의 기능만 담당해야 합니다. <br>
클래스는 작아야합니다. <br>
유지보수가 어렵습니다. <br>

#### 현재 컨트롤러는 

1. API 진입 지점으로써 HTTP Body 를 객체로 변환한다. (이것이 원래 controller 의 역할)
2. 유저가 있는지 없는지 확인하고 예외처리를 해준다. (service 의 역할)
3. SQL 을 사용해 실제 DB와 통신을 담당한다. (repository 의 역할)

---


## UserController 와 스프링 컨테이너

- UserController 의 의아한점

> static 이 아닌 코드는 인스턴스화를 해야한다 <br>
> UserController 는 JdbcTemplate 에 의존하고 있다. <br>
> 
> @RestController 는 UserController 클래스를 **스프링 빈**으로 등록시킨다.
> 
> 
### 스프링 빈이란?

- 서버가 시작되면, 스프링 서버 내부에 거대한 컨테이너를 만들게 된다.
- 컨테이너 안에는 클래스가 들어가게 된다.
- 이때 다양한 정보도 함께 들어가고 인스턴스화까지 이루어진다.
- 이때 스프링 컨테이너 안에 들어간 클래스를 **스프링 빈**이라고 한다.
- 필요한 의존성이 자동으로 설정 된다.

### 스프링 컨테이너는 필요한 클래스를 연결해준다.

---

## 스프링 빈으로 모두 등록했다

1. 스프링컨테이너가 생성된다
2. 유저레포지토리가 등록되고
3. 레포지토리가 필요한 서비스가 생성
4. 서비스가 필요한 컨트롤러가 생성

### 문자열 SQL을 직접 사용하는 것이 너무 어렵다.

- 단점으로 사람이다보니 문자열로 작성하는 SQL이 틀릴 가능성이 크다.
- 바로바로 알 수가 없다.

그래서 나온 것이 JPA 이다.

---

### JPA (Java Persistence API)

자바 진영의 ORM이다.
이 ORM 은 Object - Relational Mapping

 즉 객체 오브젝트와 릴레이셔널 관계를 맺는다 
 - 객체와 관계형 DB의 테이블을 짝지어 데이터를 영구적으로 저장할 수 있도록 정해진 JAVA진영의 규칙

여기서 중요한 것은 JPA는 규칙이다.<br>
규칙과 구현한 코드가 있다. <br>
이때 구현한 코드가 HIBERNATE 이다.<br>

간단하게 표현하자면 인터페이스인 JPA를 구현한 것이 하이버네이트이다


---

### 이제 JPA를 이용하여 유저 테이블에 대응되는 Entity Class를만들어본다.

- @Entity : 스프링이 User 객체와 user 테이블을 같은 것으로 바라본다.

> Entity 는 저장되고, 관리되어야 하는 데이터를 말한다.

빠져있는 아이디만 추가해주면 된다.

- @Id : id 로 생각하겠다.
- GeneratedValue : primary key는 자동 생성되는 값이다.
- strategy = GenerationType.IDENTITY: autoIncrement

Jpa 를 쓰기 위해서는 아무것도 들어있지 않은 기본생성자가 꼭 필요하다.

- @Column : 길이라던지 , Null 이 되는지 안되는지 체크할 때 쓰인다. 기본적으로 테이블과 객체의 변수명이 같으면 name부분이 생략가능하고 컬럼어노테이션 자체를 생략가능하다

이제 추가설정이 필요하다
- application.yml 에서 설정을 추가해준다.

```yaml
  jpa:
    hibernate:
      ddl-auto: none 
      # 스프링을 시작할 때 데이터베이스를 어떻게 할 것인가
      # create : 기존 테이블이 있다면 삭제 후 다시 생성
      # create-drop: 스프링이 종료될 때 테이블을 모두 제거
      # update: 객체와 테이블이 다른 부분만 변경
      # validate: 객체와 테이블이 동일한지 확인
      # none : 별다른 조치를 하지 않는다.
    properties:
      hibernate:
        show_sql: true # jpa를 사용해 db에 sql을 날릴 때 sql을 보여줄 것인가?
        format_sql: true # 보여줄 때 포매팅을 해서 보여줄 것인가?
        dialect: org.hibernate.dialect.MySQL8Dialect
        # 이 옵션으로 DB를 특정하면 조금씩 다른 SQL을 수정해준다.

```

---

### Spring Data JPA 를 이용해서 자동으로 쿼리 날려보기

#### 목표: SQL 을 작성하지 않고 생성 수정 조회를 해보기

- save : 주어지는 객체를 저장하거나 업데이트 시켜준다.
- findAll : 주어지는 객체가 매핑된 테이블의 모든 데이터를 가져온다
- findById: id를 기준으로 특정한 1개의 데이터를 가져온다.

> 이 메소드들을 쓰게 되면 알아서 기능을 처리해주는데
> 그것은 Spring Data JPA 가  복잡한 JPA 코드를 스프링과 함께
> 쉽게 사용할 수 있도록 도와주는 라이브러리이다.



