### 1. 어댑터 패턴 (Adpater Pattern)
---

- 어댑터 패턴은 ***특정 클래스 인터페이스를 클라이언트에서 요구하는 인터페이스로 변환해주는 패턴***이다.
    - 변환해주는 하나의 어댑터(Adapter)와 어댑터에게 행위를 위임할 하나의 어댑티(Adaptee)가 필요하다.
- 인터페이스를 변환해주는 ***어댑터를 만들면 호환되지 않는 인터페이스를 클라이언트가 요구한 인터페이스로 만들어 줄 수 있다.***

### 2. 어댑터 패턴 활용해보기

---

- `java.util.Collection`의 요소를 반환하기 위해서 `Enumeration`와 `Iterator`를 사용한다.
- `Enumeration`은 기존에 사용했던 요소 반환만 존재하는 인터페이스이고 `Iterator`는 `Enumeration`의 기능뿐만 아니라 요소를 제거할 수 있는 기능이 추가된 인터페이스다.
- `Enumeration`은 구형 인터페이스이기 때문에 새로운 인터페이스인 `Iterator`를 사용하기를 원하는 클라이언트의 요구사항이 들어왔다.
- 기존 `Enumeration`를 전부 `Iterator`로 변환하기 보다는 어댑터를 활용하여 `Enumeration` 코드는 건들이지 않고 클라이언트의 요구사항을 쉽게 해결할 수 있다.

#### 2.1. 전체적인 구조
![](./img/adpater_pattern.png)

#### 2.2. 어댑터 패턴 구현해보기
- `Enumeration`
    ```java
    public interface Enumeration<E> {
        boolean hasMoreElements();

        E nextElement();

        //...
    }
    ```
- `Iterator`
    ```java
    public interface Iterator<E> {

        boolean hasNext();

        E next();

        //추가된 요소 삭제 기능
        void remove();

        //...
    }
    ```
- `Enumeration`을 위한 어댑터 만들기
    ```java
        public class EnumerationIterator<E> implements Iterator<E> {

            private final Enumeration<E> enumeration;

            public EnumerationIterator(Enumeration<E> enumeration) {
                this.enumeration = enumeration;
            }

            @Override
            public boolean hasNext() {
                return this.enumeration.hasMoreElements();
            }

            @Override
            public E next() {
                return this.enumeration.nextElement();
            }

            @Override
            public void remove() throws UnsupportedOperationException {
                throw new UnsupportedOperationException("Not supported");
            }
        }
    ```

- 어댑티인 `Enumeration` 인터페이스는 어댑터인 `EnumerationIterator` 클래스에 객체 구성이 되었다.
- `특정 클래스 인터페이스(Enumeration)`를 `클라이언트에서 요구(Iterator)`하는 인터페이스로 `변환(EnumerationIterator)`하였다.
- 클라이언트의 요구사항대로 변환하였기 때문에 클라이언트는 `EnumerationIterator`를 활용하기만 하면 되며 기존 코드인 `Enumeration`을 수정할 필요가 없어졌다.
- `EnumerationIterator`의 `remove()`메서드의 경우 딱히 작동시킬 내용이 없어 예외를 발생시킨다.
  - 모든 어댑터에 완벽하게 적용하기 힘든 경우도 발생할 수 있기 때문에 해당 메서드를 사용지 않게 하기 위해서 예외로 처리해 두었다.
  - 예외로 처리해두면 적어도 런타임 오류가 발생하면서 클라이언트가 알 수 있다.
  - 또한 이러한 예외의 경우에는 클라이언트가 쉽게 알 수 있도록 문서 작성을 하는 것이 좋다.


### 3. 어댑터 패턴 생각해보기
- 어댑터 패턴은 ***특정 클래스 인터페이스를 클라이언트에서 요구하는 인터페이스로 변환해주는 패턴***이며 이를 활용해 기존 인터페이스를 클라이언트가 요구하는 인터페이스로 변환해 줄 수 있는 패턴이다.
- 어댑터 패턴을 사용하기 위해서 하나의 어댑터(Adapter)와 어댑터에게 행위를 위임할 하나의 어댑티(Adaptee)가 필요하다.
- 인터페이스의 크기가 작은 경우에는 어댑터를 쉽게 사용하는 것이 가능하다.
- 하지만 ***지원해야하는 인터페이스의 크기가 클 경우 어댑터의 코드 작성에 대한 복잡도가 올라간다.***
    - 때문에 인터페이스의 크기가 크다면 다른 대안은 없는지 생각해 보아야 한다.
    - 인터페이스의 크기가 크다면 인터페이스의 크기를 먼저 분리해야하는 것은 아닌가에 대한 고민을 하고, 인터페이스를 분리한 이후 어댑터 패턴을 적용시켜도 된다.
    - 모든 인터페이스의 행위에 대해 지원할 필요가 없다면 어댑터를 사용하지 않고 해당 인터페이스를 상속받아 직접 구현하여도 된다.
- 때문에 무작정 어댑터 패턴먼저 만들기 보다는 코드 작성전 먼저 어댑터를 만들어야 하는 이유와 이 어댑터를 만들었을 경우 다른 대안은 없는지에 대해 고민하여 코드를 작성하는 것이 좋다.
