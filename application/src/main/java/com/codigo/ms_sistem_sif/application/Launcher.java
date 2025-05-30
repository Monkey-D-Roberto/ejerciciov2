package com.codigo.ms_sistem_sif.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.codigo.ms_sistem_sif.*")
@EnableJpaRepositories("com.codigo.ms_sistem_sif.")
@EntityScan("com.codigo.ms_sistem_sif.*")
@EnableFeignClients("com.codigo.ms_sistem_sif.*")

public class Launcher {
    public static void main(String[] args) {
        SpringApplication.run(Launcher.class,args);
    }
}
