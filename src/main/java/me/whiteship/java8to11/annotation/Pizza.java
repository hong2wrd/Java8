package me.whiteship.java8to11.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//Target 은 이 애노테이션을 사용할 곳
//@Target(ElementType.TYPE_PARAMETER) // TYPE_PARAMETER 제네틱 타입에 쓸 수 있음
@Target(ElementType.TYPE_USE) // 타입을 선언하는 모든 곳에서 사용 가능
public @interface Pizza {
}
