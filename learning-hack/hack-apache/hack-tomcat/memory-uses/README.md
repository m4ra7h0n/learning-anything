1.注册监听器
2.使用反射将监听器注册到对方的context
3.在context.fireRequestDestroyEvent中发现context是从Context context = request.getContext();来的
4.使用jsp内置request获取request, 其中他内置的是requestFacade，我们在jsp中通过反射获取其内部的request


# 问题
最后使用springboot，尝试一次不成功，直接使用java+Tomcat
https://blog.csdn.net/gaoqingliang521/article/details/108677301

使用纯tomcat+java之后换了Tomcat版本为9，成功,也可以调试