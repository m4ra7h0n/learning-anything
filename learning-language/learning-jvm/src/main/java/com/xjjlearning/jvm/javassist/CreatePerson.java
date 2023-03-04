package com.xjjlearning.jvm.javassist;

import javassist.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.lang.reflect.Modifier;

public class CreatePerson {
    public static void createPseson() throws Exception {
        ClassPool pool = ClassPool.getDefault();
        // 1.创建一个空类
        CtClass cc = pool.makeClass("com.xjjlearning.jvm.javassist.Person");

        // 2.新增一个字段 private String name;
        CtField name = new CtField(pool.get("java.lang.String"), "name", cc);
        name.setModifiers(Modifier.PRIVATE); // 访问级别 private
        cc.addField(name, CtField.Initializer.constant("xjj")); // 初始值

        // 3.添加getter&setter
        cc.addMethod(CtNewMethod.setter("setName", name));
        cc.addMethod(CtNewMethod.getter("getName", name));

        // 4.无参构造函数(parameter, declaring)
        CtConstructor noArgConstructor = new CtConstructor(new CtClass[]{}, cc);
        noArgConstructor.setBody("{name = \"xjj\";}");
        cc.addConstructor(noArgConstructor);

        // 5.有参构造函数
        CtConstructor argsConstructor = new CtConstructor(new CtClass[]{pool.get("java.lang.String")}, cc);
        // $0=this
        // $1,$2,$3... 代表方法参数
        argsConstructor.setBody("{$0.name = $1;}");

        // 6.创建一个名为printName方法, 无参数, 无返回值, 输出name值
        CtMethod printName = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, cc);
        printName.setModifiers(Modifier.PUBLIC);
        printName.setBody("System.out.println(name);");
        cc.addMethod(printName);

        String outputPath = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-language/learning-jvm/target/classes";
        cc.writeFile(outputPath);
    }

    public static void main(String[] args) {
        try {
            createPseson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}