2025.08.09 whit lucas 

ORM(Object Relational Mapping) > JPA(자바진영) > Hibernate(구현체) > Spring Data JPA(라이브러리) -> 얘도 항상 구현체가 필요한데 Hibernate   

JPA = Java Persistence API == ORM 표준
-> JPA 자바 객체를 DB 에 저장하는 기술 == Interface   

!!Spring Data JPA!! -> JPA라는 인터페이스를 구현한 구현체 라이브러리   
JPA 의 구현체가 필요하다? 구현체 -> Hibernate   
JPA != Hibernate

Spring Data JPA 안에는 Hibernate 가 있다.
> ORM : 객체(Object)와 관계형 데이터베이스를 매핑하는 기술에 대한 개념   
> JPA : ORM 기술을 자바 진영에서 사용할 수 있도록 만든 표준 명세서. 인터페이스의 모음.   
> Hibernate : JPA 규약을 따르는 대표적인 ORM 프레임워크이자 구현체. JPA를 사용하기 위해서는 이같은 구현체가 반드시 필요함.   
> Spring Data JPA : 스프링 프레임워크에서 JPA를 편리하게 사용할 수 있도록 만든 모듈

BIGINT || Long   
NUMBER(19, 0) == BIGINT == LONG
> ID 컬럼 기본키의 경우 실제 서비스에서 int 로는 부족하기 때문에 BIGINT || NUMBER 를 사용한다.
Spring Data JPA 는 기본적으로 모든 설정을 해준다 !   
다만 커스텀 설정할 기능도 많고, 할수 있는것도 많음 ! -> 복잡함.

N + 1, Stack Over Flow
> 쿼리 1번 실행 시 그와 연관관계를 맺고있는 데이터를 조회하기 위해 N번의 쿼리가 조회되는 문제.   
> 이런 경우 DB 서버에 부하가 생겨 서버 다운 또는 병목 현상으로 인한 Stack over flow 같은 문제가 생길 수 있다.

interface -> 여기서 상속받은 JPARepository 가 있는 repository 하나 -> interface 에서 기능 추가한 impl 클래스 하나
> 기본 CRUD가 정의 되어 있는 JpaRepository 인터페이스와 사용자 쿼리가 정의되어 있는 인터페이스를 각각 만들고 
> 이를 구현하는 Impl 클래스를 만드는 방법도 있음



------------------

### 연관 관계 매핑

테이블의 외래키를 반영하는 작업. 엔티티 객체 간의 참조(매핑)를 의미

1. 방향   
   (데이터 모델링에서는 자동으로 양방향 관계를 맺고 서로 참조하지만 JPA 에서는 지정이 필요함.)

- 단방향 관계 : 두 엔티티가 관계를 맺을 때, 한쪽의 엔티티만 참조하는 관계. A -> B
- 양방향 관계 : 두 엔티티가 서로 참조하는 관계. A <-> B   

> 무조건 양방향 관계를 맺으면 좋을 것 같지만, 객체 입장에서는 불필요한 관계들로 인해 복잡해질 수 있음.   
> ex. User 객체가 무수히 많은 다른 연관 객체들과 양방향 관계를 맺게 되는 것....
> 
> 기본적으로 단방향 관계를 사용하고 필요한 경우에만 양방향 관계를 맺도록 작성.  
> ex. group.getQuestion() : Group -> Question 단방향 관계

2. 다중성
   (관계를 맺는 두 엔티티의 관계...)

- @ManyToOne : 다대일 (N:1)
- @OneToMany : 일대다 (1:N)
- @OneToOne : 일대일 (1:1)
- @ManyToMany : 다대다 (N:N)

> Groups - Questions -> @OneToMany (1:N) - @ManyToOne (N:1)

3. 연관 관계의 주인   
- 양방향 관계를 맺는 경우 객체가 서로 외래키를 가질 수 있는데, 하나의 객체가 외래 키를 관리해야 하며 이를 연관 관계의 주인이라 함.
- 주인만 외래 키를 등록,수정,삭제 등 관리 할 수 있으며, 주인이 아닌 객체는 조회만 가능함.

```java
// Questions <-> Answers 양방향 관계의 예시
@Entity
public class Questions {
    //...필드 생략
    
    @OneToMany(mappedBy = "answer")
    private List<Answer> answers = new ArrayList<>();
}

@Entity
public class Answer {
    //...필드 생략

    // 외래키를 갖는 Many 의 객체가 연관관계의 주인이 된다.
    @ManyToOne
    @JoinCilumn(name = "QUESTION_ID")
    private Question question;
}
```

