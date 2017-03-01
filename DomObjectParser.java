package xmlparser.DomParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmlparser.Model.Family;
import xmlparser.Model.Father;
import xmlparser.Model.Mother;
import xmlparser.Model.Person;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ilyalees on 28/02/2017.
 */
public class DomObjectParser {

    public static void main(String[] args) {
        List <Family> list = new ArrayList<>();
        Person person = null;
        Family family = null;
        int counter = 1;
        List <Mother> arrayMother = null;
        List <Father> arrayFather = null;

        try {
            File inputFile = new File("/Users/ilyalees/IdeaProjects/CompareCollection/src/main/resources/families.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);

            //arrayMother = new ArrayList<>();
            //arrayFather = new ArrayList<>();

            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();  // корневой элемент
            NodeList families = root.getElementsByTagName("family");
            NodeList nodeList = document.getElementsByTagName("parents");

            for (int i=0; i<nodeList.getLength();i++){
                family = new Family();
                family.setName(families.item(i).getAttributes().getNamedItem("name").toString());

                Node node = nodeList.item(i);
                NodeList parents = node.getChildNodes();

                for (int j = 0; j<parents.getLength();j++){
                    Node parent = parents.item(j);
                    if ("mother".equals(parent.getNodeName())){
                        if (parent.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) parent;
                            person = new Mother();

                            ((Mother)person).setName(element.getElementsByTagName("name").item(0).getTextContent());
                            ((Mother)person).setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
                            ((Mother)person).setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));
                            ((Mother)person).setMaidenName(element.getElementsByTagName("maiden-name").item(0).getTextContent());

                            NodeList childNodeList = ((Element) parent).getElementsByTagName("children");
                            Node childNode = childNodeList.item(0);
                            ((Mother)person).setChildren(Integer.parseInt(childNode.getAttributes().item(0).getTextContent()));

                            family.setMother(((Mother)person));
                            //arrayMother.add(((Mother)person));
                        }

                    }if ("father".equals(parent.getNodeName())){
                        if (parent.getNodeType() == Node.ELEMENT_NODE){
                            Element element = (Element) parent;

                            person = new Father();
                            ((Father)person).setName(element.getElementsByTagName("name").item(0).getTextContent());
                            ((Father)person).setSurname(element.getElementsByTagName("surname").item(0).getTextContent());
                            ((Father)person).setAge(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()));

                            family.setFather(((Father) person));
                            //arrayFather.add(((Father) person));

                        }
                    }
                }
                list.add(family);
            }
    } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        for (Family family1 :list) {
            System.out.println(list);
        }




    }
}
