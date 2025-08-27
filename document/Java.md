### for() || stream()
- for(), if() / 명령적(Imperative), 어떻게(How)
  - 장점 : 직관적인 코드, 일치하는 code 찾으면 바로 return
  - 단점 : 조건문 많아지면 가독성 저하

- stream(), filter() / 선언적(Declarative), 무엇(What)
  - 장점 : 간결함, 선언적 코드로 무엇을 하려는지 한눈에 파악 가능, 재활용성 (중간 연산 추가 가능)
  - 단점 : 스트림 객체 생성, 람다식 처리 과정 추가로 인해 작은 규모에서는 오버헤드 발생

### findAny() && findFirst()
- findAny() : Stream에서 가장 먼저 탐색되는 요소 return
- findFirst() : 일치하는 요소들 중 첫번째 요소 return
