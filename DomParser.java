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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyalees on 27/02/2017.
 */
public class DomParser {
    public static void main(String[] args) {
        String nodeName;
        String nodeContent;
        String nodeParent;
        Node nodeChildren;
        ArrayList <String> nodeArray = new ArrayList<>();

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
                NodeList parents = parent.getChildNodes();
                for (int x = 0; x<parents.getLength();x++){
                    Node itemParents = parents.item(x);
                    NodeList itemsParent = itemParents.getChildNodes();
                    for (int z =0; z<itemsParent.getLength();z++){
                        Node parentExtended = itemsParent.item(z);
                        NodeList parenstExt = parentExtended.getChildNodes();

                        nodeParent = parentExtended.getNodeName().trim();
                        nodeArray.add(nodeParent);

                        //System.out.println(parentExtended.getNodeType());
                        //System.out.println(parentExtended.getNodeName());
                        for (int y=0; y<parenstExt.getLength();y++){
                            Node node = parenstExt.item(y);
                            NodeList nodeList = node.getChildNodes();

                            nodeName = node.getNodeName().trim();
                            nodeContent = node.getTextContent();
                            nodeArray.add(nodeName);
                            nodeArray.add(nodeContent.trim());

                           if (!"#text".equals(nodeName) && 0!= nodeName.hashCode()) {
                               //empty
                           }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        List <String> fixList = new ArrayList<>();

        for (int a=0; a<nodeArray.size();a++){      // -#text, -" "
            if (!"#text".equals(nodeArray.get(a)) && 0!= nodeArray.get(a).hashCode()){
                fixList.add(nodeArray.get(a));
            }
        }
        for (int a=0; a<fixList.size();a++){
            System.out.println(a + " " + fixList.get(a));
        }





    }
}
