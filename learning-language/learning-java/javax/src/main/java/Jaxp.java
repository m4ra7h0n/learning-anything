import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.validation.Schema;

/**
 * created by xjj on 2023/2/1
 */
public class Jaxp {
    // https://zh.wikipedia.org/wiki/JAXP

    private void dom() throws ParserConfigurationException {
        // https://docs.oracle.com/javase/7/docs/api/org/w3c/dom/package-summary.html
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Schema schema = documentBuilder.getSchema();
    }
    private void sax() throws ParserConfigurationException, SAXException {
        //
        SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
    }
    private void stAx() {}
    private void xslt() {}

    public static void main(String[] args) {
    }
}
