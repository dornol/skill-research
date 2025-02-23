package dev.dornol.mybatis;

import org.springframework.boot.SpringApplication;

public class TestMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.from(MybatisApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
