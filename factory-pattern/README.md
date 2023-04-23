
### 팩토리 패턴
---

- 팩토리 패턴은 ***객체를 생성하는 부분만 따로 캡슐화를 하고 추상화한 것***이 바로 팩토리 패턴의 핵심이다.
  - ***객체를 생성하는 코드를 추상화하여 코드를 한곳에서 관리하지 않으면, 변화(생성,수정,삭제)가 발생 했을 때 해당 클라이언트 코드를 전부 수정***해줘야 한다.
  > 인터페이스를 바탕으로 만들어진 코드는 확장 가능한 코드가 된다.

#### 인터페이스를 바탕으로 만들어진 코드란 무엇인가?
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


### 간단한 팩토리 (Simple Factory)
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



