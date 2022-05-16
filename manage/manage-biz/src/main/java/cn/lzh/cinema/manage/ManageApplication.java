package cn.lzh.cinema.manage;

import cn.lzh.cinema.common.feign.annotation.EnableCinemaFeignClients;
import cn.lzh.cinema.common.security.annotation.EnableCinemaResourceServer;
import cn.lzh.cinema.common.swagger.annotation.EnableCinemaSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* @author pig archetype
* <p>
* 项目启动类
*/
@EnableCinemaSwagger2
@EnableCinemaFeignClients
@EnableCinemaResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class, args);
    }

}
