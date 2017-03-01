package xmlparser.SaxParser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import xmlparser.SaxParser.SAX.SimpleSaxHandler;

import java.io.IOException;

/**
 * Created by ilyalees on 24/02/2017.
 */
public class SimpleSaxParser {

    public static void main(String[] args) {
        try {
            SimpleSaxHandler handler = new SimpleSaxHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);

            //reader.parse("/Users/ilyalees/IdeaProjects/CompareCollection/src/main/resources/families.xml");
            reader.parse("/Users/ilyalees/IdeaProjects/CompareCollection/src/main/resources/families.xml");
        } catch (SAXException e) {
            System.out.println("handler error");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SimpleSaxParser simpleSaxParser = new SimpleSaxParser();

        //System.out.println(ch);
    }


}
