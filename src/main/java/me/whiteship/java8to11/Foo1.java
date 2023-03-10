package me.whiteship.java8to11;

public interface Foo1 {

    void printName();

    /**
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    };
    /**
     * default 가 붙으면 구현체에서 오버라이딩을 하지 않아도 기본적으로 사용됨
     * 구현체에서 재정의할 수 있음
     *
     * Object 에 있는 toString, equals 등은 재정의 불가능함
     */

    String toString();

    static void printAnything() {
        System.out.println("Foo");
    }

    String getName();
}
