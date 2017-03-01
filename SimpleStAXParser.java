package xmlparser.StAXparser.StAX.TestStAX;

import xmlparser.Model.Family;
import xmlparser.Model.Father;
import xmlparser.Model.Mother;
import xmlparser.Model.Person;
import xmlparser.StAXparser.XmlElements;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilyalees on 27/02/2017.
 */
public class SimpleStAXParser {
    public static void main(String[] args) throws XMLStreamException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try
        {
            InputStream input = new FileInputStream("/Users/ilyalees/IdeaProjects/CompareCollection/src/main/resources/families.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List <Family> families = process(reader);
            for (Family family : families) {
                System.out.println(family);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List <Family> process (XMLStreamReader reader) throws XMLStreamException {
        List <Family> families = new ArrayList<Family>();
        Family family = null;
        Person person = null;
        XmlElements elementName = null;
        int counter = 1;


        while (reader.hasNext()){
            int type = reader.next();
            switch (type){
                case XMLStreamConstants.START_ELEMENT:
                    elementName = XmlElements.getElementTagName(reader.getLocalName());
                    switch (elementName){
                        case FAMILY:
                            family = new Family();
                            String familyName = reader.getAttributeValue(null, "name");
                            family.setName(familyName);
                            break;
                        case MOTHER:
                            person = new Mother();
                            break;
                        case CHILDREN:
                            Integer child = Integer.parseInt(reader.getAttributeValue(0));
                            ((Mother)person).setChildren(child);
                            break;
                        case FATHER:
                            person = new Father();
                            break;
                    }
                    break;


                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }

                    switch (elementName){
                        case NAME:
                            person.setName(text);
                            break;
                        case SURNAME:
                            person.setSurname(text);
                            break;
                        case AGE:
                            Integer age = Integer.parseInt(text);
                            person.setAge(age);
                            break;
                        case MAIDEN_NAME:
                            ((Mother)person).setMaidenName(text);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    elementName = XmlElements.getElementTagName(reader.getLocalName());
                    switch (elementName){
                        case MOTHER:
                            family.setMother((Mother)person);
                            break;
                        case FATHER:
                            family.setFather((Father) person);
                            break;
                        case FAMILY:
                            families.add(family);
                            break;
                    }
            }
        }
        return families;
    }
}
