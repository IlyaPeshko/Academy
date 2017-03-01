package xmlparser.SaxParser.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xmlparser.Model.Family;
import xmlparser.Model.Father;
import xmlparser.Model.Mother;
import xmlparser.Model.Person;
import xmlparser.SaxParser.XmlElements;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ilyalees on 24/02/2017.
 */
public class SimpleSaxHandler extends DefaultHandler {
    private String text;
    private StringBuilder builder;
    private Family family;
    private Person person;
    private List <Family> families;  // <Person>
    private int counter;

    private int tempChildren;
    

    @Override
    public void startDocument() throws SAXException {
        families = new ArrayList<Family>();
        System.out.println("startDocument ");
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println(families);

        for (Family families1 : families) {
            System.out.println(families1);
        }
        System.out.println("endDocument");
    }
//объекты
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("startElement " + qName);
        //System.out.println(++counter);
        //System.out.println(localName);
        //System.out.println(qName);
        //System.out.println(uri);
        builder = new StringBuilder();


        if ("family".equals(qName)){
            family = new Family();
            family.setName(attributes.getValue("name"));
            //family.setMother((attributes.Va);

        }
        if ("mother".equals(qName)){
            person = new Mother(); //mother

        }
        if ("children".equals(qName)){
            //System.out.println(qName);
            //System.out.println("attributes - " + attributes.getValue(0));
            ((Mother)person).setChildren(Integer.parseInt(attributes.getValue(0)));
        }
        if ("father".equals(qName)){
            person = new Father();
        }

    }
//обработчик событий
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //System.out.println("endElement " + qName);  //teg

        XmlElements elements = XmlElements.valueOf(qName.toUpperCase().replace("-","_"));
        //XmlElements atr = XmlElements.;

        switch (elements){

            case FAMILY:
                families.add(family);
                break;
            case MOTHER:
                family.setMother((Mother) person);
                break;
            case FATHER:
                family.setFather((Father) person);
                break;
            case NAME:
                person.setName(text);
                break;
            case SURNAME:
                person.setSurname(text);
                break;
            case AGE:
                person.setAge(Integer.parseInt(text));
                break;
            case MAIDEN_NAME:
                ((Mother)person).setMaidenName(text);
                break;
            default:
                //System.out.println("default");
        }

    }
//StringBuilder
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //System.out.print("COUNTER " + ++counter);
        //System.out.println(" / start " + start + " length " + length);
        //System.out.println("person " + person);

        text = builder.append(ch, start, length).toString().trim();


    }

}
