### 1. 퍼사드 패턴 (Facade Pattern)
---

- 퍼사드 패턴은 ***여러 복잡한 행위들을 단순하게 바꿔서 하나의 행위로 제공해주는 패턴***이다.
  - 여러 복잡한 행위란 하나 이상의 인터페이스가 여러 행위들을 해야지 결과물이 나올 때를 말한다.
  - 하나의 행위로 제공해 준다는 것은 복잡한 행위를 하나의 인터페이스로 제공해준다는 의미이다. 
- 복잡한 행위를 하나의 행위로 통합하여 제공하기 때문에 클라이언트 입장에서 편하게 사용할 수 있다.
  - 우리가 리모컨을 통해서 기계를 켜고 종료하는데 있어서 `어떻게 작동(복잡한 행위)`하는지 알 필요가 없다.
  - 그냥 리모컨의 버튼만 클릭하면 `알아서 작동(하나의 행위)`하기를 원한다.

### 2. 스프링에서 비지니스로직을 담당하는 Service 클래스를 통해 퍼사드 패턴 알아보기 
---

- 클라이언트가 회원 가입 시스템을 제작해달라는 요구사항이 들어왔다.
  1. 회원 가입 전에 회원 아이디 중복 체크를 해야한다.
  2. 회원 가입을 하게 되면 고객 정보를 DB에 적재해야한다.
  3. 회원 가입 이후 1000포인트를 고객에게 전달한 이후 포인트 데이터도 DB에 적재해야한다.
  4. 회원 가입 이후 메일을 고객에게 전달해야한다.

#### 2.1. 필요한 서브 시스템 클래스 만들기

- 회원 중복 체크 클래스
    ```java
    @Service
    public class MemberIdDoubleChecker {

        private final MemberRepository memberRepository;

        public MemberIdDoubleChecker(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        public void checkMemberId(String id){
            Optional<Member> findMember = memberRepository.findById(id);
            if(findMember.isPresent()){
                throw new IllegalArgumentException(id + "는 이미 사용중인 아이디입니다.");
            }
        }
    }
    ```
- 회원 정보 DB 적재할 클래스
    ```java
    @Repository
    public class MemberRepository {

        private Map<String, Member> members = new HashMap<>() {{
            put("id1", new Member("id1", "password1"));
            put("id2", new Member("id2", "password2"));
        }};

        public Optional<Member> findById(String id){
            Member member = members.get(id);
            if(member == null){
                return Optional.empty();
            }
            return Optional.of(members.get(id));
        }

        public Member save(Member member){
            if(members.containsKey(member.getId())){
                throw new IllegalArgumentException(member.getId() + " 키가 존재합니다.");
            }

            return members.put(member.getId(), member);
        }
    }
    ```
- 포인트 적립 클래스
    ```java
    @Service
    public class MemberPointAddition {

        private final MemberPointRepository memberPointRepository;

        public MemberPointAddition(MemberPointRepository memberPointRepository) {
            this.memberPointRepository = memberPointRepository;
        }

        public void addPoint(MemberPoint memberPoint, long addingPoint){

            if(addingPoint < 0){
                throw new IllegalArgumentException("추가 포인트는 0이하일 수 없습니다.");
            }

            memberPoint.setPoint(memberPoint.getPoint() + addingPoint);

            memberPointRepository.update(memberPoint);
        }
    }
    ```
- 메일 전송 클래스
    ```java
    @Service
    public class SendEmail {

        public void sendEmail(String message){
            System.out.println(message);
        }
    }
    ```

#### 2.2. 퍼사드 패턴 적용하여 클라이언트에게 통합 인터페이스 제공

```java
@Service
public class MemberJoinService {
    
    private final MemberIdDoubleChecker memberIdDoubleChecker;
    private final MemberRepository memberRepository;
    private final MemberPointAddition memberPointAddition;
    private final SendEmail sendEmail;

    //서브 시스템들 구성하기
    public MemberJoinService(MemberIdDoubleChecker memberIdDoubleChecker, MemberRepository memberRepository, MemberPointAddition memberPointAddition, SendEmail sendEmail) {
        this.memberIdDoubleChecker = memberIdDoubleChecker;
        this.memberRepository = memberRepository;
        this.memberPointAddition = memberPointAddition;
        this.sendEmail = sendEmail;
    }

    public void join(String id, String password){

        //중복 체크
        memberIdDoubleChecker.checkMemberId(id);

        //회원 정보 DB 적재
        Member saveMember = memberRepository.save(new Member(id, password));

        //회원가입 기념 포인트 적립
        MemberPoint memberPoint = new MemberPoint(id);
        memberPointAddition.addPoint(memberPoint, 1000L);

        //이메일 전송
        sendEmail.sendEmail(id + "님 회원가입을 해주셔서 진심으로 감사드립니다.");
    }
}
```

- 회원 가입을 하기 위한 여러 절차들을 `MemberJoinService` 클래스에서 `join()` 메서드를 활용하여 하나의 인터페이스로 제공하고 있다.
- 클라이언트는 회원 가입의 여러 절차에 대해 알 필요 없이 `MemberJoinService` 클래스만 활용하면 된다.
- 회원가입에 대한 절차가 변경되어도 실제 클라이언트 코드를 수정할 필요는 없다.


#### 퍼사드 패턴 좀더 생각해보기
- 퍼사드 패턴은 ***여러 복잡한 행위들을 단순하게 바꿔서 하나의 행위로 제공해주는 패턴***이다.
- 퍼사드 패턴을 활용 하면 클라이언트가 필요로만 하는 인터페이스를 제공해줄 수 있다.
- 퍼사드 패턴을 활용할 때 필요한 여러 객체를 구성하여 해당 객체의 메서드만 활용하게 될경우 아주 유용한 일이 발생한다.
  - `MemberJoinService`에서 자세히 보면 실제로 구성된 객체의 메서드만 절차대로 호출하고 있다.
  - 테스트 코드 검증 방식에는 상태 검증과 행위 검증이 있다.
  - 상태 검증은 객체의 메서드 실행 시 특정 값이 어떻게 변하는 지를 확인하는 것이고, 행위 검증은 특정 메서드가 호출되었는지를 검증하는 것이다.
  - `MemberJoinService`는 단순히 구성된 객체의 메서드만 호출하는 행위를 대신해 주는 역할만 한다. 때문에 행위 검증을 하는 테스트 코드를 작성할 수 있다.
  - 퍼사드 패턴 덕분에 `MemberJoinService`에 구성된 객체는 상태 검증, `MemberJoinService`는 행위 검증과 같이 명확한 구분선을 만들어 줄 수 있는 아주 유용한 패턴이다.
