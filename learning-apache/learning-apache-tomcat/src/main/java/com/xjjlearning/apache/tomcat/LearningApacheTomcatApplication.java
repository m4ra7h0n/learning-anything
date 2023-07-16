package com.xjjlearning.apache.tomcat;

import org.apache.catalina.Service;
import org.apache.catalina.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Socket;

@SpringBootApplication
public class LearningApacheTomcatApplication {

    public static void main(String[] args) {
//        Socket
//        StandardContext
//        ContainerBase
//        StandardWrapper
//        StandardHost
//        StandardEngine
//        Service
        SpringApplication.run(LearningApacheTomcatApplication.class, args);
    }

}
