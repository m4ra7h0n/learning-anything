package com.xjjlearning.hack.springboot.speldemo;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.thymeleaf.standard.expression.VariableExpression;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xjj on 2023/4/19.
 */
public class SpelDemo {

    /**
     * https://zhuanlan.zhihu.com/p/174786047
     * 1）创建解析器：SpEL使用ExpressionParser接口表示解析器，提供SpelExpressionParser默认实现；
     * 2）解析表达式：使用ExpressionParser的parseExpression来解析相应的表达式为Expression对象。
     * 3）构造上下文：准备比如变量定义等等表达式需要的上下文数据。
     * 4）求值：通过Expression接口的getValue方法根据上下文获得表达式值。
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchMethodException {

        /*1*/
        ExpressionParser parser = new SpelExpressionParser();

        /*2*/
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        /*3*/
        StandardEvaluationContext context = new StandardEvaluationContext("我是root对象");
        context.setVariable("end", "!");
        /*4*/
        System.out.println(expression.getValue(context));

        // basic
        String a = parser.parseExpression("'Hello World'").getValue(String.class);
        Long a1 = parser.parseExpression("-1L").getValue(long.class);
        // boolean
        Boolean b = parser.parseExpression("1>2").getValue(boolean.class);
        Boolean b1 = parser.parseExpression("1 GT 2").getValue(boolean.class);
        Boolean b2 = parser.parseExpression("1 between {1, 2}").getValue(boolean.class);
        Boolean b3 = parser.parseExpression("2>1 and (!true or !false)").getValue(boolean.class);
        Boolean b4 = parser.parseExpression("2>1 && (!true || !false)").getValue(boolean.class);
        Boolean b5 = parser.parseExpression("2>1 and (NOT true or NOT false)").getValue(boolean.class);
        Boolean b6 = parser.parseExpression("2>1 && (NOT true || NOT false)").getValue(boolean.class);
        Character c = parser.parseExpression("'Hello World!'[0]").getValue(Character.class);
        Boolean d = parser.parseExpression("'123' matches '\\d{3}'").getValue(boolean.class);
        // 使用“T(Type)”来表示java.lang.Class实例 “Type”必须是类全限定名，“java.lang”包除外，即该包下的类可以不指定包名；
        // 使用类类型表达式还可以进行访问类静态方法及类静态字段。
        Class<String> e = parser.parseExpression("T(String)").getValue(Class.class);
        Class<SpelDemo> e1 = parser.parseExpression("T(com.xjjlearning.hack.springboot.speldemo.SpelDemo)").getValue(Class.class);
        Integer e2 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        Integer e3 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        // 类实例化同样使用java关键字“new”，类名必须是全限定名，但java.lang包内的类型除外
        String f = parser.parseExpression("new String('xjj')").getValue(String.class);
        Date f2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        // instanceof表达
        Boolean g = parser.parseExpression("'xjj' instanceof T(String)").getValue(Boolean.class);

        // 设置环境变量与获取
        context.setVariable("name", "xjj");
        context.setVariable("lesson", "Spring");
        String name = parser.parseExpression("#name").getValue(context, String.class);
        String lesson = parser.parseExpression("#lesson").getValue(context, String.class);
        // 构造context时传入的rootObj
        String rootObj = parser.parseExpression("#root").getValue(context, String.class);
        //#this用来访问当前上线文中的对象跟rootObj一样
        String thisObj = parser.parseExpression("#this").getValue(context, String.class);


        //定义2个函数,registerFunction和setVariable都可以，不过从语义上面来看用registerFunction更恰当
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt1", parseInt);
        context.setVariable("parseInt2", parseInt);
        Integer h = parser.parseExpression("#parseInt1('3')").getValue(context, int.class);
        Integer h1 = parser.parseExpression("#parseInt2('3')").getValue(context, int.class);
        boolean res = parser.parseExpression("#parseInt1('3') == #parseInt2('3')").getValue(context, boolean.class); // true

        // 类以及元素的一些操作
        User user = new User();
        context.setVariable("user", user);
        parser.parseExpression("#user.name").setValue(context, "xjj");
        Object i = parser.parseExpression("#user").getValue(context, user.getClass());
        //使用安全访问符号?.，可以规避null错误
        String j = parser.parseExpression("#user?.car?.name").getValue(context, String.class);

        // Bean
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        Car car = new Car();
        car.setName("保时捷");
        user.setCar(car);
        factory.registerSingleton("user", user);
        context.setBeanResolver(new BeanFactoryResolver(factory));
        // SpEL支持使用“@”符号来引用Bean，在引用Bean时需要使用BeanResolver接口实现来查找Bean，Spring提供BeanFactoryResolver实现。
        User userBean = parser.parseExpression("@user").getValue(context, User.class);
        boolean k = userBean == factory.getBean("user");

        // 数组和List
        List<Integer> l = parser.parseExpression("{1, 2, 3}").getValue(List.class);
        int[] l2 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);

        // 对Collection元素支持访问
        Collection<Integer> collection = new HashSet<Integer>();
        collection.add(1);
        collection.add(2);
        context.setVariable("collection", collection);
        int m = parser.parseExpression("#collection[1]").getValue(context, int.class);


        //SpEL对Map字典元素访问的支持
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        context.setVariable("map", map);
        int n = parser.parseExpression("#map['a']").getValue(context, int.class);
        parser.parseExpression("#map['a']").setValue(context, 3);

        // 集合投影
        // SpEL使用"(list|map).![投影表达式]"来进行投影运算：
        // SpEL使用“(list|map).?[选择表达式]”
        // “#this”代表每个集合或数组元素，可以使用比如“#this.property”来获取集合元素的属性
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        context.setVariable("list", list);
        // 相当于map
        Collection<Integer> o = parser.parseExpression("#list.![#this+1]").getValue(context, Collection.class);
        Collection p = parser.parseExpression("#list.?[#this>4]").getValue(context, Collection.class);


        // 模版
        SpelExpressionParser parserTemplate = new SpelExpressionParser();
        //创建解析器上下文
        ParserContext contextTemplate = new TemplateParserContext("%{", "}");
        Expression expressionTemplate = parserTemplate.parseExpression("你好:%{#name},我们正在学习:%{#lesson}", contextTemplate);
        //创建表达式计算上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name", "xjj");
        evaluationContext.setVariable("lesson", "spring系列");
        //获取值
        String value = expressionTemplate.getValue(evaluationContext, String.class);
        System.out.println(value);

//        VariableExpression variableExpression = new VariableExpression("");
//        variableExpression.execute("")


    }

    private static void calc() {
        ExpressionParser parser = new SpelExpressionParser();

        // 操作类弹计算器, java.lang包下的类是可以省略包名的.
        String spel = "T(java.lang.Runtime).getRuntime().exec(\"open -a Calculator\")";
        // String spel = "T(java.lang.Runtime).getRuntime().exec(\"calc\")";

        Expression expression = parser.parseExpression(spel);
        System.out.println(expression.getValue());
    }

    // 其他的类
    public static class Car {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static class User {
        private Car car;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        String name;

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        @Override
        public String toString() {
            return "User{" +
                    "car=" + car +
                    '}';
        }
    }

}
