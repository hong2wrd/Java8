package me.whiteship.java8to11.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//Target 은 이 애노테이션을 사용할 곳
//@Target(ElementType.TYPE_PARAMETER) // TYPE_PARAMETER 제네틱 타입에 쓸 수 있음
@Target(ElementType.TYPE_USE) // 타입을 선언하는 모든 곳에서 사용 가능
@Repeatable(ChickenContainer.class) // Repeatable 으로 감싸는 Container 는 감싸지는 대상보다 어노테이션이 크거나 같아야함
public @interface Chicken {

    String value();

}


