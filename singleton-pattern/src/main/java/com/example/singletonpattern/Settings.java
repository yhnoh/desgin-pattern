package com.example.singletonpattern;

import java.io.Serializable;

/**
 * 1. 웹 어플리케이션에서 환경은 보통 멀티스레드 환경이다.
 * 그럼 과연 이러한 방법이 안전한 방법인가?
 *
 * 스레드 세이프 하지 않다.
 *
 * 왜냐...
 * 스레드 1이 if문을 타고 인스턴스를 생성하기 전데
 * 스레드 2가 if문 코드를 접근하게 되면
 * 스레드1과 스레드2에서 동시에 생성이된다.
 *
 * 해결 방법
 * 1. synchronized 키워드를 활용하여 멀티스레드 환경에서도 동기화를 시켜준다.
 * 하지만 락이 걸리기 때문에 성능이 느려진다.
 *
 * 2. 이른 초기화 (eager initialization)
 * 애플리케이션 실행할때 static이기 때문에 바로 생성이되기 때문에 안전
 * 하지만 객체를 생성하는 것이 자원을 많이 소비하게 되면 굉장히 비효율 적일 수 있다.
 *
 * 3. double check locking (lazy initialization)
 * - 인스턴스를 생성하는 동안에는, 다른 스레드가 접근하려해도 synchronized키워드 때문에 block이된다.
 * - 이후에는 인스턴스가 계속 생성이 되어 있기 때문에 괜찮다.
 *
 * 장점
 * 효율 적이다.
 * 인스턴스가 필요한 시점에 생성을 한다.
 *
 * 단점
 * 1.5이상의 자바에서만 지원을 한다.
 * volatile 키워드와 synchronized키워드로 인해 복잡하다.
 *
 * static inner 클래스 만들기
 * 1. 멀티 스레드 환경에서 안전하다.
 * 2. 인스턴스가 필요한 시점에 생성을 한다.
 * 3. 코드 복잡도가 줄어든다.
 *
 * 싱들토 패턴을 깨트리는 방법들이 존재
 * 1. 리플렉션 사용용
 *
 * enum을 이용하여, 리플렉션 및 직렬화 역 직렬화 방식에서도 안전하다.
 * 미리 만들어진다.
 * 상속을 받지 못핟. (enum만 상속이 가능)
 * */

public class Settings implements Serializable {
    //private static Settings instance;
//    private static final Settings INSTANCE = new Settings();
    private static volatile Settings instance;



    private Settings(){}

//    public static Settings getInstance() {
//
//        if(instance == null){
//            instance = new Settings();
//        }
//
//        return instance;
//    }

//    public static synchronized Settings getInstance() {
//
//        if(instance == null){
//            instance = new Settings();
//        }
//
//        return instance;
//    }

//
//    public static Settings getInstance() {
//        return INSTANCE;
//    }

//    public static Settings getInstance(){
//        if(instance == null){
//            synchronized (Settings.class){
//                if(instance == null){
//                    instance = new Settings();
//                }
//            }
//        }
//        return instance;
//    }
    private static class SettingsHolder{
        public static final Settings INSTANCE = new Settings();
    }

    public static Settings getInstance(){
        return SettingsHolder.INSTANCE;
    }

    //역직력화 대응 방안
    //역직렬화를 할때 readResolve의 키워드를 활용하여 인스턴스를 생성하기 때문에

    protected Object readResolve(){
        return getInstance();
    }
}
