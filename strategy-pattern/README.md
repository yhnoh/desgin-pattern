
### 1.전략 패턴 (Stratey Pattern)
---


- 전략 패턴이란 ***행위를 정의하고 캡슐화해서 행위를 사용하는 대상이 각각의 알고리즘을 수정해서 쓸 수 있게 해주는 패턴***이다.
  > **캡슐화**는 객체가 내부적으로 기능을 어떻게 구현하는지를 감추는 것입니다. 이를 통해 내부의 기능 구현이 변경되더라도 그 기능을 사용하는 코드는 영향을 받지 않도록 만들어 줍니다.
  ```java
  
  ```
- 대상에 대한 행위를 직접 정의하는 것이 아닌 ***행위를 정의하고 그 행위를 할 대상에 구성***하게한다.
  - 프로그램 실행중에도 코드를 수정하지 않고 대상에 대한 행위를 변경할 수 있다는 장점이 있다.


### 2. 대상에 대한 행위를 직접 정의할 경우

- 여러 종류의 오리들을 구현해야한다는 요청사항이 들어왔다 가정해보자.
    - 나무 오리, 고무 오리, 진짜 오리 등등....
- 오리는 여러 행동들이 가능하다.
  - 날기, 수영하기, 울기 등등...

#### 2.1. 상속을 통한 대상에 대한 행위를 직접 정의
- `Duck`이라는 추상 클래스를 만든 이후 여러 종류의 오리를 구현해보자.
- `Duck`에는 미리 오리의 행위를 구현하고, 하위 클래스가 필요한 행위들을 재정의한다.  
    ```java
    /**
    * 오리의 추상 클래스
    */
    public abstract class Duck {

        public void quack() {
            System.out.println("울부짖다.");
        }

        public void fly() {
            System.out.println("난다.");
        }

        public void swim() {
            System.out.println("수영한다.");
        }

    }
    ```
- 오리 구현 클래스
    ```java
    //진짜 오리 울기(O), 날기(O), 수영(O)
    public class RealDuck extends Duck{
    }

    //고무오리 울기(X), 날기(X), 수영(O)
    public class RubberDuck extends Duck{
        @Override
        public void quack() {
            System.out.println("울부짖지 못한다.");
        }

        @Override
        public void fly() {
            System.out.println("날지 못한다.");
        }

    }

    //나무 오리 울기(X), 날기(X), 수영(X)
    public class WoodDuck extends Duck{
        @Override
        public void quack() {
            System.out.println("울부짖지 못한다.");
        }

        @Override
        public void fly() {
            System.out.println("날지 못한다.");
        }

        @Override
        public void swim() {
            System.out.println("수영을 못한다.");
        }
    }
    ```
- 실제 코드를 작성해보니 생각 보다 쉽게 여러 종류의 오리를 구현할 수 있어다.

#### 2.2  상속을 통한 대상에 대한 행위를 직접 정의할때 발생하는 문제점
- 
