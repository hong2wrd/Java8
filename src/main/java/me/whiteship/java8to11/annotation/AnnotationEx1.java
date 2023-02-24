package me.whiteship.java8to11.annotation;

import java.util.Arrays;

@Chicken("양념")
@Chicken("마늘간장")
public class AnnotationEx1 {

    public static void main( String[] args) {
        Chicken[] chickens = AnnotationEx1.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });
        ChickenContainer chickenContainer = AnnotationEx1.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });

    }

}
