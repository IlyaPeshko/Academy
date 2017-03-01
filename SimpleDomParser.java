package xmlparser.DomParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by ilyalees on 27/02/2017.
 */
public class SimpleDomParser {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse("/Users/ilyalees/IdeaProjects/CompareCollection/src/main/resources/families.xml");

            Element root = document.getDocumentElement();  // корневой элемент
            NodeList families = root.getElementsByTagName("family");

            for (int i=0; i<families.getLength();i++){
                System.out.println(families.item(i).getAttributes().getNamedItem("name"));
                Node parent = families.item(i);
                System.out.println(parent.getTextContent());



            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
