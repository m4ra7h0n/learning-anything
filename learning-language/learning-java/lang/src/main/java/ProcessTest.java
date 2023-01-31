import java.io.*;

/**
 * created by xjj on 2023/1/31
 */
public class ProcessTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Process exec = Runtime.getRuntime().exec("open -a Calculator");
        // Runtime rt = Runtime.getRuntime();//获得Runtime对象
        // String arr[] = {"CLASSPATH=D://", "Path=C:\\Program Files\\Java\\jdk1.8.0_131\\bin"};//执行exec时的环境变量
        //
        // //exec方法第一个参数是执行的命令，第二个参数是环境变量，第三个参数是工作目录
        // Process pr = rt.exec("cmd /c javac a.java && java a", arr, new File("D://"));
        //
        // // 获取输出流并转换成缓冲区
        // BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));
        // bout.write("1 2");// 输出数据
        // bout.close();// 关闭流
        //
        // //SequenceInputStream是一个串联流，能够把两个流结合起来，通过该对象就可以将
        // //getInputStream方法和getErrorStream方法获取到的流一起进行查看了，当然也可以单独操作
        // SequenceInputStream sis = new SequenceInputStream(pr.getInputStream(), pr.getErrorStream());
        // InputStreamReader inst = new InputStreamReader(sis, "GBK");//设置编码格式并转换为输入流
        // BufferedReader br = new BufferedReader(inst);//输入流缓冲区
        //
        // String res = null;
        // StringBuilder sb = new StringBuilder();
        // while ((res = br.readLine()) != null) {//循环读取缓冲区中的数据
        //     sb.append(res).append("\n");
        // }
        // br.close();
        // pr.waitFor();
        // pr.destroy();
        // System.out.print(sb);//输出获取的数据

        ProcessBuilder processBuilder = new ProcessBuilder("open", "/System/Applications/Calculator.app");
        processBuilder.start();
    }
}
