import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by xjj on 2023/7/12.
 */
@WebServlet("/inject")
public class DeserializeController extends HttpServlet {

    final String evalFile = "/Volumes/ONETSSD/IdeaProjects/learning-anything/learning-hack/hack-apache/hack-tomcat/memory-uses/web/WEB-INF/serializedfiles/ccfilter.ser";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        TemplatesImpl

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(evalFile));
            inputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        super.doPost(req, resp);
    }
}
