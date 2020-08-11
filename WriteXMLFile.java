import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

	public static String COMMA_DELIMITER=",";
	
    public static void main(String argv[]) throws JAXBException, IOException {
    	List<List<String>> records = readCSV();
//    	way1();
      
    }
    
    public static void way1() {
    	try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);


            staff.setAttribute("id", "1");

            // firstname elements
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("yong"));
            staff.appendChild(firstname);
//            Comment c = doc.createComment("Comment");
//            staff.appendChild(c);

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            staff.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("100000"));
            staff.appendChild(salary);

            // write the content into xml file
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty("omit-xml-declaration", "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("filePath\\file.xml"));

            
            
            
            transformer.transform(source, result);

            System.out.println("File saved!");

          } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
          } catch (TransformerException tfe) {
            tfe.printStackTrace();
          }
    }
    
    public static List<List<String>> readCSV() throws IOException {
    	List<List<String>> records = new ArrayList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader("filePath/server1_servername_ABC.csv"))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        String[] values = line.split(COMMA_DELIMITER);
    	        records.add(Arrays.asList(values));
    	    }
    	}
    	
//    	records.forEach(System.out::print);
    	
    	List<String> second = new ArrayList<String>(); 
    	
    	records.forEach(record -> second.add(record.get(1)));
    	
    	System.out.println(second);
    	
    	return records;
    }
    
}