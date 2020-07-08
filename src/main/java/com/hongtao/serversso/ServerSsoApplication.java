package com.hongtao.serversso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"com.hongtao.serversso.dao"})
public class ServerSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerSsoApplication.class, args);
    }

}
