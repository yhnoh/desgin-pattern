# 데코레이터 패턴(Decorator Pattern)

### 정의

- 주어진 상황 및 용도에 따라 어떤 객체에 책임을 덧붙이는 패턴
- 객체의 추가적인 기능을 **동적으로 유연하게 확장**할 수 있는 패턴
- **개방-폐쇄 원칙(OCP)**을 지킬 수 있다.
    - 기능을 추가하거나 변경해야 할 때 이미 제대로 동작하고 있던 **원래 코드를 변경하지 않아도, 기존의 코드에 새로운 코드를 추가함으로써 기능의 추가나 변경이 가능**
- 필요이상으로 데코레이터 패턴을 사용시, 코드가 복잡해줄 수 있음

### 구조

- Component : 실제 로직 수행을 위해 추상화 하는 역할
- ConcreateComponent : Component의 구현체이면서 실질적인 로직을 수행하는 역할
- Decorator : Component를 상속 받아 부가 적인 기능을 추상화 하는 역할
- ConcreateDecorator : Decorator를 상속 받아 추가적인 기능을 실제로 구현하는 역할

### 상황

1. 에스프레소를 주문하는 고객이 있다.
2. 아메리카노를 주문하는 고객이 있고, 라떼를 주문하는 고객이 있다.
3. 아메리카노와 라떼를 주문할때는 **에스프레소에 물과 우유를 추가(Decorator Pattern)**하면 된다.

### Component

```java
public interface Component {
    String add();
}
```

- add라는 메소드 정의
- 에스프레소, 물, 우유를 추가 하기 위해 만들어진 인터페이스

### ConcreateComponent

```java
public class ConcreateComponent implements Component{
    @Override
    public String add() {
        return "에스프레소";
    }
}
```

- 실제로 에스프레소를 내려주는 Component의 구현체

### Decorator

```java
public abstract class Decorator implements Component{
    private Component coffeeComponent;

    public Decorator(Component coffeeComponent) {
        this.coffeeComponent = coffeeComponent;
    }

    @Override
    public String add() {
        return coffeeComponent.add();
    }
}
```

- 에스프레소를 받아서 물과 우류를 넣어주기 위한 추상 클래스
- 추상 클래스를 상속 받아서 부가적인 기능을 수행

### ConcreateDecorator

```java
public class MilkDecorator extends Decorator{

    public MilkDecorator(Component coffeeComponent) {
        super(coffeeComponent);
    }

    @Override
    public String add() {
        return super.add() + " + 우유";
    }
}

public class WaterDecorator extends Decorator{

    public WaterDecorator(Component coffeeComponent) {
        super(coffeeComponent);
    }

    @Override
    public String add() {
        return super.add() + " + 물";
    }
}
```

- 물과 우유를 추가할 수 있는 구현체

### 실행

```java
public class Main {

    public static void main(String[] args) {
        Component espresso = new ConcreateComponent();
        System.out.println(espresso.add());

        Component americano = new WaterDecorator(new ConcreateComponent());
        System.out.println(americano.add());

        Component latte = new MilkDecorator(new ConcreateComponent());
        System.out.println(latte.add());

    }
}
```

### 출처

> [https://coding-factory.tistory.com/713](https://coding-factory.tistory.com/713)
> 

> [https://ko.wikipedia.org/wiki/개방-폐쇄_원칙](https://ko.wikipedia.org/wiki/%EA%B0%9C%EB%B0%A9-%ED%8F%90%EC%87%84_%EC%9B%90%EC%B9%99)
>