package com.xjjlearning.apache.tomcat.ex03.startup;

import com.xjjlearning.apache.tomcat.ex03.connector.http.HttpConnector;
import org.apache.tomcat.util.res.StringManager;

public final class Bootstrap {
  public static void main(String[] args) {
//    StringManager
    HttpConnector connector = new HttpConnector();
    connector.start();
  }
}