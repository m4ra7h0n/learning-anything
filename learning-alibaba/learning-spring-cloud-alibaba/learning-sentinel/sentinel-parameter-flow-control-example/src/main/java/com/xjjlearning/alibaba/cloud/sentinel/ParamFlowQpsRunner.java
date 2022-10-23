package com.xjjlearning.alibaba.cloud.sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ParamFlowQpsRunner {
    private volatile boolean stop = false;
    List<Persion> person = new ArrayList<>();

    class Persion {
        Persion(String name, String age, String sex) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        String name;
        String age;
        String sex;
    }

    public void ParamFlowQpsDemo(){}

    public ParamFlowQpsRunner() {
        person.add(new Persion("xjj", "22", "boy"));
        person.add(new Persion("sandi", "29", "girl"));
        person.add(new Persion("zhangsan", "18", "boy"));
    }

    @RequestMapping("/say")
    @SentinelResource(value = "sayHelloTest", blockHandler = "sayHelloBlock")
    public String sayHello(@RequestParam("name") String name, @RequestParam("age") String age, @RequestParam("sex") String sex) {
        return "hello, " + name + "[" + sex + "|" + age + "]";
    }

    public static String sayHelloBlock(String name, String age, String sex, BlockException ex) {
        return "blocked !";
    }
}
