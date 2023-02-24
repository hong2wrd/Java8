package me.whiteship.java8to11.annotation;

import java.util.Arrays;
import java.util.List;

@Pizza
public class AnnotationEx2 {

    public static void main(@Pizza String[] args) throws @Pizza RuntimeException{

        List<@Pizza String> names = Arrays.asList("hong");
    }

    static class FeelsLikeChicken<@Pizza T> {
        public static <@Pizza C> void print(@Pizza  C c) {
            System.out.println(c);
        }
    }
}
