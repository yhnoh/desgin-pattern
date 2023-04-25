
### 1. 팩토리 패턴
---

- 팩토리 패턴은 ***객체를 생성하는 부분만 따로 캡슐화를 하고 추상화한 것***이 바로 팩토리 패턴의 핵심이다.
  - ***객체를 생성하는 코드를 추상화하여 코드를 한곳에서 관리하지 않으면, 변화(생성,수정,삭제)가 발생 했을 때 해당 클라이언트 코드를 전부 수정***해줘야 한다.
  > 인터페이스를 바탕으로 만들어진 코드는 확장 가능한 코드가 된다.

#### 1.1 인터페이스를 바탕으로 만들어진 코드란 무엇인가?
- 예를 들어 상품에 대한 할인 정책이 있다고 생각해보자.
  - 할인가와 할인율을 통하여 상품 가격 할인이 들어가는 포로그램을 기획자가 요청했다고 생각해보자.
    ```java
    public class Product {

        private double originPrice;

        public Product(double originPrice) {
            this.originPrice = originPrice;
        }

        public void orderByDiscountPrice(double discount){
            PriceDiscountPolicy priceDiscountPolicy = new PriceDiscountPolicy(discount);
            Double discountPrice = priceDiscountPolicy.discountPrice(originPrice);

            System.out.println("상품 원가 = " + originPrice);
            System.out.println("상품 할인가 = " + discountPrice);
            System.out.println("상품 주문가 = " + (originPrice - discountPrice));
        }

        public void orderByDiscountRate(double discount){
            RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy(discount);
            Double discountPrice = rateDiscountPolicy.discountPrice(originPrice);

            System.out.println("상품 원가 = " + originPrice);
            System.out.println("상품 할인가 = " + discountPrice);
            System.out.println("상품 주문가 = " + (originPrice - discountPrice));
        }

    }
    ```
  - 만약 할인 정책이 계속 늘어난다면 `Product` 클래스를 계속해서 수정해할 수 밖에 없다.
    - 할인 정책이 계속해서 늘어난다면 `Product` 클래스에 계속해서 코드를 늘릴수 밖에 없다.
    - `Product`클래스에서 해당 할인정책에 대한 객체 정보를 알필요가 있을까?
    - `Product`클래스를 사용하는 개발자 입장에서도 너무 많은 정보가 있으니 사용하기 힘들 수 있다.
  - 어떠한 할인 정책이 추가되어도 `Product` 클래스를 수정하지 않는 방법이 뭐가 있을까?
    - 바로 인터페이스를 바탕으로 `Product`를 구성하는 것이다.
    - `DiscountPolicy`라는 인터페이스를 만들어 한번 코드를 구성해보자
    ```java
    public class Product {

        private double originPrice;

        public Product(double originPrice) {
            this.originPrice = originPrice;
        }

        public void order(DiscountPolicy discountPolicy){
            System.out.println("상품 원가 = " + originPrice);
            Double discountPrice = discountPolicy.discountPrice(originPrice);
            System.out.println("상품 할인가 = " + discountPrice);
            System.out.println("상품 주문가 = " + (originPrice - discountPrice));
        }
    }
    ```
    ![](./img/discount_policy.png)
    - `DiscountPolicy` 인터페이스 덕분에 할인정책이 계속해서 추가되어도 `Product` 클래스는 변경되지 않는다.

<br/>

> ***인터페이스를 바탕으로 만들어진 코드는 확장 가능한 코드가 된다.***<br/>
> ***반대로 구현 클래스를 바탕으로 만들어진 코드는 확장이 어렵다.***


### 2. 간단한 팩토리 (Simple Factory)
---

- 디자인 패턴이라기 보다는 프로그램밍에서 자주 사용하는 관용구에 가깝다.
- 간단한 팩토리는 ***생성하는 부분을 캡슐화하여 생성관련한 것만 모아놓은 구현 클래스***이다.
- 인스턴스를 생성하는 부분에대한 코드를 계속 변경해 나가야할 때 유용하게 활용할 수 있다.
    ```java
    public class PizzaStore {

        public Pizza orderPizza(String pizzaType){
            Pizza pizza = null;

            //피자의 종류는 지속적으로 변경될 수 있다.
            if(pizzaType.equals("cheese")){
                pizza = new ChessePizza();
            }else if(pizzaType.equals("pepperoni")){
                pizza = new Pepperoni();
            }

            //피자를 준비하는 과정은 전부 동일하다.
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    }
    ```
    - ***피자를 생성하는 부분이 계속해서 변경되기 때문에 해당 부분을 캡슐화(분리)*** 한 간단한 피자 팩토리를 만들어 보자.
    ```java
    public class SimplePizzaFactory {
        public Pizza createPizza(String pizzaType) {
            Pizza pizza = null;
            
            if(pizzaType.equals("cheese")){
                pizza = new ChessePizza();
            }else if(pizzaType.equals("pepperoni")){
                pizza = new PepperoniPizza();
            }

            return pizza;
        }
    }

    public class PizzaStore {

        public Pizza orderPizza(String pizzaType){
            SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
            Pizza pizza = simplePizzaFactory.createPizza(pizzaType);

            //피자를 준비하는 과정은 전부 동일하다.
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    }        
    ```
    - `SimplePizzaFactory` 인해서 `PizzaStore`의 코드를 더이상 수정할 필요가 없어졌다.
    - `SimplePizzaFactory`에서 `createPizza`라는 인스턴스 메서드를 통해서도 사용가능하지만 ***간단한 팩토리는 정적 메서드로도 활용***을 많이한다.
        ```java
        public class SimplePizzaFactory {
            public static Pizza createPizza(String pizzaType) {
                Pizza pizza = null;
                
                if(pizzaType.equals("cheese")){
                    pizza = new ChessePizza();
                }else if(pizzaType.equals("pepperoni")){
                    pizza = new PepperoniPizza();
                }

                return pizza;
            }
        }
        ```
        - ***정적 메서드를 활용하면 `SimplePizzaFactory`인스턴스를 직접 생성할 필요가 없다는 장점이 있지만, 서브 클래스 (상속 받은 하위 클래스)에서 오버라이딩 할 수 없다는 단점***이 있다. 
- 간단한 팩토리는 말그대로 간단하게 구현은 가능하지만, 클라이언트 코드가 팩토리 클래스에 강한 결합도를 가지고 있다는 단점이 있다.

<br/>

> 개발자가 모든 확장성을 고려해 팩토리 패턴으로 생성 관련 코드를 추상화하여 작성하기란 쉽지 않다. <br/>
> 또한 다른 사람의 코드를 내가 다시 수정해 나가는 경우도 많다. <br/>
> 모든 것을 완벽하게 작성하려는 습관은 좋지만, 오히려 개발에 대해 흥미가 떨어질 수도 있고 개발 일정을 맞추기도 쉽지 않기 때문이다.<br/>
> ***때문에 간단한 팩토리 처럼 먼저 최대한 쉽게 접근을 하고, 어떠한 코드라도 수정할 수 있는 능력이 중요***하다.

### 3. 팩토리 메서드 패턴 (Factory Method Pattern)
---

- 팩토리 메서드 패턴은 ***객체의 생성 책임을 서브클래스에게 넘기는 패턴***이다.
- 객체를 생성하는 부분을 서브클래스가 담당하게 됨으로, ***객체를 사용하는 부분과 생성하는 부분을 분리***할 수 있다.
- 객체 생성 코드가 변경이 되더라도 객체를 사용하는 부분에서의 코드를 더이상 변경하지 않아도된다.
  - ***인터페이스를 바탕으로 만들어져 있기 때문에 결합도가 낮다. (느슨한 결합)***


#### 3.1. 간단한 팩토리 패턴의 문제점과 이를 해결하기 위한 팩토리 메서드 패턴

- 간단한 팩토리를 만들면서 `SimplePizzaFactory` 클래스에서 피자 생성관련 책임을 넘겼지만 문제점이 있다.
    ```java
    public class SimplePizzaFactory {
        public Pizza createPizza(String pizzaType) {
            Pizza pizza = null;
            
            if(pizzaType.equals("cheese")){
                pizza = new ChessePizza();
            }else if(pizzaType.equals("pepperoni")){
                pizza = new PepperoniPizza();
            }

            return pizza;
        }
    }

    public class PizzaStore {

        public Pizza orderPizza(String pizzaType){
            SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
            Pizza pizza = simplePizzaFactory.createPizza(pizzaType);

            //피자를 준비하는 과정은 전부 동일하다.
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    } 
    ```
- 만약 새로운 피자 공장이 추가되어야 한다면 어떻게 될까?
  - 예를 들어서 한국 스타일 피자, 미국 스타일 피자 등등...
  - ***`SimplePizzaFactory` 구현 클래스에 의존하여 만든 `PizzaStore` 클래스의 코드를 계속해서 변경***할 수 밖에 없다.
    ```java
        public class PizzaStore {
            public Pizza orderPizza(String pizzStyle, String pizzaType){
                Pizza pizza = null;

                if("KOREA".equals(pizzStyle)){
                    KoreaPizzaFactory koreaPizzaFactory = new KoreaPizzaFactory();
                    pizza = koreaPizzaFactory.createPizza(pizzaType);
                }else if("USA".equals(pizzStyle)){
                    USAPizzaFactory usaPizzaFactory = new USAPizzaFactory();
                    pizza = usaPizzaFactory.createPizza(pizzaType);
                }else {
                    SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
                    pizza = simplePizzaFactory.createPizza(pizzaType);
                }

                //피자를 준비하는 과정은 전부 동일하다.
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                return pizza;
            }
        }   
    ```
  - `SimplePizzaFactory`에 생성관련 책임을 넘겨서 `PizzaStore`는 생성된 피자를 바탕으로 주문 준비만 했으면 됬는데 ***다시 피자 생성관련 책임을 맡고 있는 문제점이 발생***한다. 
    - 뿐만아니라 `pizzStyle`이라는 매개변수도 하나더 늘어 났으니, 해당 코드를 사용하는 개발자 입장에서도 복잡도가 증가하게 될 것이다. 
  - 위의 문제를 해결하기 위해서는 생성관련 부분을 인터페이스 바탕으로 만들어야 한다.

#### 3.2. 팩토리 메서드 패턴을 활용

- 어떤 클래스의 인스턴스를 만들지는 서브크래스에서 결정하도록 한다.
```java
public abstract class PizzaStore {

    public Pizza orderPizza(String pizzaType){
        Pizza pizza = this.createPizza(pizzaType);
        //피자를 준비하는 과정은 전부 동일하다.
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
    //생성 관련 책임을 추상 메서드에게 전가
    public abstract Pizza createPizza(String pizzaType);
}


public class USAPizzaStore extends PizzaStore{
    @Override
    public Pizza createPizza(String pizzaType) {
        Pizza pizza = null;

        //타입에 따라서 미국식 피자 생성

        return pizza;

    }
}

public class KoreaPizzaStore extends PizzaStore{
    @Override
    public Pizza createPizza(String pizzaType) {
        Pizza pizza = null;

        //타입에 따라서 한국식 피자 생

        return pizza;
    }
}
```
- `PizzaStore`에서 `createPizza()` 추상 메서드를 제작하여, `PizaaStore`를 상속받은 서브클래스들이 생성관련 책임을 가지도록한다.
- `PizaaStore`는 더 이상 피자 생성 내용이 변경된다고 해도 코드가 변경되지 않는다.
> createPizza 메서드에서 String 타입의 매개변수를 활용하여 피자 생성을 하는데, 잘못된 값을 전닳여 런타임 오류가 발생할 수 있다. 이를 방지하기 위해서 정적 상수나 enum타입을 사용하는 것이 더 좋을 수 있다.  

### 4. 추상 팩토리 패턴 (Abstract Factory Pattern)
---

- 추상 팩토리 패턴은 ***오직 생성과 관련된 것(들)을 인터페이스를 통해 제공하는 패턴***이다.
  - 팩토리 메서드 패턴은 상속을 통하여 하위 클래스에게 객체 생성 책임을 맡기는 패턴이다.
    ```java
    public abstract class PizzaStore {

        public Pizza orderPizza(String pizzaType){
            Pizza pizza = this.createPizza(pizzaType);
            //피자를 준비하는 과정은 전부 동일하다.
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
        //생성 관련 책임을 추상 메서드에게 전가
        public abstract Pizza createPizza(String pizzaType);
    }
    ```
  - 추상 팩토리 패턴은 ***인터페이스를 구현한 클래스들이 객체 생성의 일을 하고, 해당 인터페이스를 주입받아서 사용(객체 구성)하는 패턴***이다.  
    ```java
    //피자 생성 관련 인터페이스
    public interface PizzaFactory {

        Pizza createPizza(String pizzaType);
    }

    public abstract class PizzaStore {
        //팩토리 객체를 구성
        private final PizzaFactory pizzaFactory;
        public PizzaStore(PizzaFactory pizzaFactory) {
            this.pizzaFactory = pizzaFactory;
        }

        public Pizza orderPizza(String pizzaType){
            Pizza pizza = pizzaFactory.createPizza(pizzaType);
            //피자를 준비하는 과정은 전부 동일하다.
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            return pizza;
        }
    }
    ```

#### 4.1. 추상 팩토리 패턴을 사용하는 경우
- `PizzaStore`를 통해서 추상 팩토리 패턴(객체 구성)과 팩토리 메서드 패턴(상속)의 차이점에 대해서 알아보았다.
- `Pizza`라는 클래스 하나를 생성하기 위해서 인터페이스를 구현하고, 해당 인터페이스를 구현한 클래스들을 각각 만드는 추상 팩토리 패턴이 번거롭다고 생각할 수 있다.
- 만약 `PizzaStore`에서 햄버거도 팔고, 피자도 팔고, 장남감도 팔고 한다면 어떻겠는가?
  - 팩토리 메서드 패턴의 경우에 생성관련 내용을 추상화 했지만, 하나의 클래스에 다양한 역할(생성, 주문)을 외부(protected, public)에 제공할 수 있기 때문에 ***해당 클래스를 사용하는 사용자들에게 너무 많은 정보를 공개한다는 문제점***이 있다. 
  - 뿐만아니라 ***하위 클래스에서 상위 클래스를 조작할 위험성이 존재***한다. (ex 오버라이딩)
- 때문에 ***추상 팩토리 패턴은 객체를 생성하는 역할만 하기 때문에 생성과 관련된 책임을 완전히 분리***시킬 수 있다.
  - 특히 ***연관된 객체 생성 메서드들을 묶어 놓은 용도로 사용할 때 유용***하다.


### 결론
---

- 객체 생성관련된 일을 분리하고자 할때 팩토리 패턴을 사용한다.
- 하지만 한정된 자원에서 모든 생성 관련 책임을 분리하고자 하는 행위는 개발 속도를 떨어트릴 뿐만 아니라 흥미도 잃을 수 있다.
  - 때문에 적재 적소에 팩토리 패턴을 사용하는 것이 좋다.
  - 오히려 모든 것을 인터페이스 바탕으로 제작하고자 할때 이해하기 힘든 코드가 될 수도 있다.
- 처음에는 간단한 팩토리 기법을 통해서 생성 관련 책임을 분리하고, 이후 팩토리 메서드 패턴이나 추상 팩토리을 사용하여 생성 관련 코드를 추상화 해보는 것이 좋다.

