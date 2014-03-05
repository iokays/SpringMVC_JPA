package com.iokays.test;

import java.lang.reflect.Field;

import com.iokays.article.domain.Article;

public class Test {
    public static void main(String[] args) {
        Field[] fields = Article.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Class<?> type = fields[i].getType();
            if (String.class == type) {
                System.out.println(String.class.toString());
            }
        }
    }
}
