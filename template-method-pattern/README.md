### 1. 템플릿 메서드 패턴 (Template Method Pattern)
---

- 템플릿 메서드 패턴이란 ***알고리즘(행동 또는 행위)를 정의하고 알고리즘의 일부 단계를 서브클래스에서 구현하는 패턴***이다.
- ***전체적인 구조는 비슷하지만 특정 행위를 조금씩 다르게 사용할 때 유용한 패턴***이다.

### 2. 템플릿 메서드를 활용한 주문 시스템 만들어 보기
---

- 가게에 상품을 주문하면 배달을 할지 포장을 할지 선택할 수 있는 시스템을 제작해달라는 요구사항이 들어왔다.

#### 2.1. 주문 시스템 설계
- 주문 시스템을 설계하면서 변화하는 것과 변화하지 않는 것이 무엇인지 한번 생각해보자.
  - 변화하지 않는것 : 사용자가 상품을 주문하면 가게는 주문된 상품을 준비한다.
  - 변화하는 것 : ***사용자는 상품을 배달을 통해서 받을 지 포장을 하여 받을 지 선택***할 수 있다.
- 여기서 중요한 것은 ***주문이라는 하나의 프로세스를 통해서 위의 행위들이 순차적으로 이루어져야 한다는 것***이다.
- 상품 클래스 만들기
    ```java
    public class Product {

        private String name = "";
        private double price = 0;
        private int quantity = 0;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public void minusQuantity(int quantity){
            if(this.quantity < quantity){
                throw new IllegalArgumentException("상품 개수가 0보다 작을 수 없습니다. 현재 개수 = " + quantity);
            }

            this.quantity -= quantity;
        }

        public double totalPrice(int quantity){
            return price * quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }
    }
    ```
- 주문 과정(알고리즘)을 정의한 템플릿 클래스 만들기
    ```java
    public abstract class Order {

        private Product product;

        public Order(Product product) {
            this.product = product;
        }

        //주문을 하는 로직이다.
        //주문시 필요한 주문 준비와, 주문 완료에 대한 메서드로 구성되어 있다.
        public void order(int quantity){
            this.prepareOrder(quantity);
            this.completeOrder();
        }

        public void prepareOrder(int quantity){
            product.minusQuantity(quantity);

            System.out.println(product.getName() + "을 " + product.getQuantity() + "개 주문하였습니다.");
            System.out.println("총 " + product.totalPrice(quantity) + "원 입니다.");
            System.out.println(product.getName() + "의 남은 수량은 " + product.getQuantity() + "개 입니다.");
        }

        // 서브 클래스에서 정의한 방식대로 주문 준비가 완료된다.
        public abstract void completeOrder();

    }
    ```
- `Order` 클래스에서 `completeOrder`메서드를 통해 ***서브 클래스에서 주문 완료 과정을 마음대로 정의***할 수 있다.
  - `prepareOrder(int quantity)` 메서드를 서브클래스에서 재정의할 위험이 있기 때문에 `final`키워드를 활용하는 것도 좋은 방법이다.
  - `completeOrder()` 메서드를 외부에 공개하고 싶지 않다면 접근 제어자인 `protected`를 활용해보자. 

#### 2.2 배달과 포장 주문 클래스 구현하기

- 포장 주문 구현
    ```java
    public class PackageOrder extends Order {

        private Product product;
        public PackageOrder(Product product) {
            super(product);
            this.product = product;
        }

        //포장 주문 정의
        @Override
        public void completeOrder() {
            System.out.println(product.getName() + "이 " + product.getQuantity() + "개 포장되었습니.");
        }
    }
    ```
- 배달 주문 구현
    ```java
    public class DeliveryOrder extends Order{

        private Product product;
        public DeliveryOrder(Product product) {
            super(product);
            this.product = product;
        }

        //배달 주문 정의
        @Override
        public void completeOrder() {
            System.out.println(product.getName() + " " + product.getQuantity() + "개 배달되었습니다.");

        }
    }
    ```



### 3. hook을 활용한 포장 주문에 대해서만 할인혜택 적용해보기

---

- 포장 주문을 할 경우에 대해서 1000원을 할인되어야 한다는 요청사항이 들어왔다.

#### 3.1. 포장 주문에 대해서 할인하는 시스템 설계해보기

- 주문상품을 할인하기 위해서는 주문 준비전에 할인 로직이 추가되어야한다.
- 포장 주문이라는 특정 구현체에 대해서만 할인 로직이 적용되어야 한다.
- 배달 주문에 대해서는 기존 로직 그대로 적용되어야 한다.

- 주문 템플릿에 할인 관련 로직 추가하기
```java
public abstract class Order {

    private Product product;

    public Order(Product product) {
        this.product = product;
    }

    //할인 로직이 추가된 주문 로직이다.
    public void order(int quantity){
        if(isDiscount()){
            discount1000Won();
        }
        this.prepareOrder(quantity);
        this.completeOrder();
    }

    //할인을 할지 말지 결정하는 로직
    public boolean isDiscount(){
        return false;
    }
    //1000원을 할인해주는 로직
    public void discount1000Won(){
        product.discountPrice(1000);
    }

    public void prepareOrder(int quantity){
        product.minusQuantity(quantity);

        System.out.println(product.getName() + "을 " + product.getQuantity() + "개 주문하였습니다.");
        System.out.println("총 " + product.totalPrice(quantity) + "원 입니다.");
        System.out.println(product.getName() + "의 남은 수량은 " + product.getQuantity() + "개 입니다.");
    }

    // 서브 클래스에서 정의한 방식대로 주문 준비가 완료된다.
    public abstract void completeOrder();

}
```
- 포장 주문에서 `isDiscount()`메서드 재정의하기  
```java
public class PackageOrder extends Order {

    private Product product;
    public PackageOrder(Product product) {
        super(product);
        this.product = product;
    }

    //포장 주문 정의
    @Override
    public void completeOrder() {
        System.out.println(product.getName() + "이 " + product.getQuantity() + "개 포장되었습니.");
    }
    //할인 가능하도록 재정의
    @Override
    public boolean isDiscount() {
        return true;
    }

}
```

- `Order`클래스에서 `order(int quantity)` 메서드를 통해서 주문이 가능하지만 주문 전 할인이 가능한 로직이 추가되었다.
- `PackageOrder`에서는 `isDiscount()`메서드를 재정의하여 1000원이 할인 가능하도록 정의하였다.
- 위와 같은 방식을 후크 (hook)라고 한다.
  - ***hook를 재정의하여 알고리즘 진행방식을 변경할 수 있다.***
  - hook를 사용하기 위해서는 서브클래스에서 hook를 재정의 해야한다.


### 4. 템플릿 메서드 패턴에 대해 생각해보기
---

- `주문(Order)`이라는 템플릿을 만들고 `서브클래스(PackageOrder, DeliveryOrder)`에서 일부 단계를 구현한 템플릿 메서드 패턴을 활용해 보았다.
- 템플릿 메서드 패턴은 전체적인 구조는 비슷하지만 특정 행위를 조금씩 다르게 사용할 때 유용한 패턴이다.
- 템플릿 메서드 패턴을 이용하며 어떠한 장점이 눈에 보이는가?
  - 중복된 코드 삭제되었다. `(prepareOrder)`
  - `Order`라는 객체를 통해서 ***다양한 주문 방식`(PackageOrder, DeliveryOrder)`들을 활용 가능***하다.
  - ***추가적인 주문 방식 구현도 `Order`클래스를 상속받아 특정 메서드만 구현하면 되서 간단***하다. (다른 개발자들 간의 의사소통의 간결화가 가능)
  - `Order`객체내에 있는 공통 메서드들을 변경해도 서브 클래스들은 변경하지 않아도 된다. (행위 변경, 상위 클래스의 구현된 메서드 변경)
    - 물론 서브 클래스에 영향이 갈 수도 있다.
- 단점은 어떤것들이 있을까?
  - 서브 클래스가 ***구현해야할 로직들이 많아질수록 템플릿을 사용하는 입장에서는 혼란을 야기***할 수 있다.
    - 하나의 행위를 하기 위해서 하나의 클래스에서 많은 행위를 재정의 해야한다면 템플릿 메서드 뿐만 아니라 다른 패턴도 함께 고려해야한다.
    - 팩토리 패턴, 전략 패턴, 템플릿 메서드 패턴 등등....
  - 상위 클래스의 변경이 모든 서브클래스에게 영향을 줄 수 있다.
  > 사내에서 외부 주문 API를 연동하는 로직들이 템플릿 메서드 패턴으로 이루어져 있는데 너무 많은 행위들을 재정의해야해서 오히려 더 작업하기 어려운 부분이 있다. <br/>
  > 뿐만 아니라 상위클래스를 새롭게 수정하면서 하위 클래스에게 영향을 줄 때 검증 로직이 없어서 로직을 추가할 수 없는 문제도 있다. <br/>
  > 때문에 너무 많은 역할을 하나의 클래스에게 의존하게 만들면 안된다. 만약 그렇게 한다고 하더라도 검증 로직 자체가 없으면 수정이 힘들 수 있기 때문에 꼭 검증 로직을 만들어라. 