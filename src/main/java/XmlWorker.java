import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;


public class XmlWorker {
    private static Randomizer rnd = new Randomizer();
    private String [] proffessions = {
            "bookeper",
            "director",
            "driver",
            "worker",
            "builder",
            "cooker",
            "hairdresser",
            "teacher",
            "programmer",
            "musician",
            "designer",
            "manager"
    };

XmlWorker(){
    try {
        xmlEmployeeGenerator();
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    }
}

   void xmlEmployeeGenerator() throws ParserConfigurationException {

       DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

       Document doc = docBuilder.newDocument();
       Element rootElement = doc.createElement("proffessions");
       doc.appendChild(rootElement);


       for(int i = 0;i<proffessions.length;i++) {
           Element proffession = doc.createElement("proffession");
           rootElement.appendChild(proffession);
           Attr attr = doc.createAttribute("id");
           String id = String.valueOf(i);
           attr.setValue(id);
           proffession.setAttributeNode(attr);
           Element vacancy = doc.createElement("vacancy");
           vacancy.appendChild(doc.createTextNode(proffessions[i]));
           proffession.appendChild(vacancy);

       }

       // write the content into xml file
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = null;
       try {
           transformer = transformerFactory.newTransformer();
       } catch (TransformerConfigurationException e) {
           e.printStackTrace();
       }
       DOMSource source = new DOMSource(doc);
       StreamResult result = new StreamResult(new File("firmEmployees.xml"));

       try {
           transformer.transform(source, result);
       } catch (TransformerException e) {
           e.printStackTrace();
       }

       System.out.println("File saved!");

   }

   ArrayList<String> readXml(String filename){
       ArrayList<String> workerVacancies = new ArrayList<String>();
       try {

           File fXmlFile = new File(filename);
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
           Document doc = dBuilder.parse(fXmlFile);
           doc.getDocumentElement().normalize();

           NodeList nList = doc.getElementsByTagName("proffession");

           for (int temp = 0; temp < nList.getLength(); temp++) {

               Node nNode = nList.item(temp);

               if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                   Element eElement = (Element) nNode;

                   String vacancy= eElement.getElementsByTagName("vacancy").item(0).getTextContent();

                   workerVacancies.add(vacancy);

               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }

       return workerVacancies;
   }

    ArrayList<String> vacancies = readXml("firmEmployees.xml");

    String getVacancy(){
        return vacancies.get(rnd.randomNumber(0,vacancies.size()-1));
    }

}