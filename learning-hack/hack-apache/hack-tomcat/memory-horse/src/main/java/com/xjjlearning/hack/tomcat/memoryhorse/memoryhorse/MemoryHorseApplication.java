package com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse;

import org.apache.catalina.ContainerServlet;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.core.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionListener;
import java.util.EventListener;

@SpringBootApplication
@ServletComponentScan
public class MemoryHorseApplication {

    public static void main(String[] args) {
//        RequestFacade
//        Connector
//        StandardWrapper
//        StandardContext
//        StandardHost
//        StandardEngine


//        LifecycleListener
//        EventListener
//        ServletContextListener
//        HttpSessionListener
//        HttpServletRequest

//        ContainerServlet
//        ApplicationContext

//        StandardWrapperValve
//        DispatcherServlet

        SpringApplication.run(MemoryHorseApplication.class, args);
    }

}
