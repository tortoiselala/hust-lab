package com.tortoiselala.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    private final static Object[] LOCK = new Object[0];

    public static SqlSessionFactory getSqlSessionFactory() {
        if(sqlSessionFactory == null){
            synchronized (LOCK){
                if(sqlSessionFactory == null){
                    String resource = "mybatis-config.xml";
                    InputStream in = null;
                    try {
                        in = Resources.getResourceAsStream(resource);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
                }
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();
    }
}