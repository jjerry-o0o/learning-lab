## 스프링 프레임워크, Spring Framework
> 자바 엔터프라이즈 개발을 편하게 해주는 오픈소스 경량급 애플리케이션 프레임워크
  - __애플리케이션 프레임워크__   
    - 스프링의 핵심 기술(IoC/DI,AOP)에 담긴 프로그래밍 모델을 일관되게 적용하여   
       엔터프라이즈 애플리케이션 전 계층 및 영역에 전략과 기능을 제공해줌으로써 편리한 개발 가능
  - __경량급__
    - 스프링이 있기 이전에 EJB+고급 WAS를 사용해야 했던 시절과 비교하여   
      단순&가벼운 코드+톰캣/제티 등에서도 동작 가능한 깔끔한 기술력 (생산성,품질면에서 유리)
- 핵심 가치 : 애플리케이션 개발에 필요한 기반을 제공해서 개발자가 비즈니스 로직 구현에만 집중 할 수 있도록 하는 것
  - 기타 복잡한 설정 및 요소들을 스프링 프레임워크에 위임하고 비즈니스 로직 개발만 집중 할 수 있도록 도와줌   
    (POJO, IoC/제어의 역전, DI/의존성 주입, AOP/관점 지향 프로그래밍)

### POJO, Plain Old Java Object
- 이름 그대로 기본 자바 오브젝트를 의미함
- POJO의 조건
  - 특정 규약(Contract)에 종속되지 않음
    - ex. 상속을 통한 규약 없이 자유로운 적용이 가능한 오브젝트
  - 특정 환경에 종속되지 않음
    - ex. 웹 환경에서 사용되는 비즈니스 로직을 담은 POJO 클래스라 하더라도 웹 기술을 담은 클래스나 인터페이스를 사용하지 않는 것
- POJO의 장점
  - POJO의 조건이 곧 장점. 자유로운 적용이 가능하며 복잡한 엔터프라이즈 개발에 적합

### POJO를 이용한 엔터프라이즈 애플리케이션 프레임워크 스프링
- 스프링은 기술 영역에서는 관여하지만, 비즈니스 로직 부분에서는 지원만
- 코드가 자연스레 객체지향적인 설계 원리를 따라가도록 이끌어줌

### IoC, Inversion of Control (제어의 역전)
- 사용할 겍체를 직접 생성하지 않고, 객체의 생명주기 관리를 외부(스프링 컨테이너=IoC 컨테이너)에 위임하는 것   
- 제어의 역전을 통해 DI/의존성 주입과 AOP/관점 지향 프로그래밍이 가능해진다.
```java
// ex. 외부에 위임하지 않고, 직접 객체를 생성하여 사용하는 경우
@RestController
public class MemberController {
    
    private MemberService service = new MemberService();
    
    // Controller codes...
}
```

### DI, Dependency Injection (의존성 주입)
- 의존성 주입은 제어 역전의 방법 중 하나로, 사용할 객체를 직접 생성하지 않고 외부 컨테이너가 생성한 객체를 주입받아 사용하는 방식

> 의존성 주입 방법 3가지   
>
> 1. 생성자를 통한 의존성 주입
> ```java
> @RestController
> public class MemberController {
> 
>     private final MemberService service;
> 
>     @Autowired
>     public MemberController(MemberService service) {
>         this.service = service;
>     }
>     
>     // Controller codes...
> }
> ```
> 
> 2. 필드 객체 선언을 통한 의존성 주입
> ```java
> @RestController
> public class MemberController {
> 
>     @Autowired
>     private MemberService service;
>     
>     // Controller codes...
> }
> ```
> 
> 3. setter를 통한 의존성 주입
> ```java
> @RestController
> public class MemberController {
> 
>     MemberService service;
> 
>     @Autowired
>     public void setMemberService(MemberService service) {
>         this.service = service;
>     }
>     
>     // Controller codes...
> }
> ```
> 
> - @Autowired 사용 (스프링 4.3 이후부터는 Lombok 라이브러리 이용하여 생략 가능)
> ```java
> @RestController
> @RequiredArgsConstuctor
> public class MemberController {
> 
>     private final MemberService service;
> 
>     // Controller codes...
> }
> ```
> 
> - 스프링 공식 문서에서 권장하는 의존성 주입 방법은 생성자를 통한 방법   
>   - 순수 자바 코드로도 문제 없이 동작
>     -> @Autowired 를 추가하여 스프링 컨테이너가 객체를 관리하도록 함
>   - 불변성 확보. 객체 초기화 할 때 필수로 의존성 주입이 되어있어야지만 객체 생성이 가능하기 때문   
>     -> Controller 객체 생성시 final 로 선언된 Service 도 의존성 주입된 상태인 경우에만 Controller 객체 생성 가능

### AOP, Aspect-Oriented Programming (관점 지향 프로그래밍)


### @Transactional 에 대해
- 선언적 트랜잭션으로 트랜잭션을 @Transactional 어노테이션만 선언하면   
PlatformTransactionManager 와 TransactionTemplate를 사용하여 제어하는 별도의 작업 없이 트랜잭션 적용 가능
- 