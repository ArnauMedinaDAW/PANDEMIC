package Pandemic;
import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ParametrosXML {
    
    private File file; // Fitxer XML

    // Constructor que rep la ruta del fitxer XML
    public ParametrosXML(String filePath) {
        this.file = new File(filePath);
    }
    
    // Mètode per llegir el contingut del fitxer XML
    public void readXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            // Normalitzar el document per manejar espais en blanc
            doc.getDocumentElement().normalize();

            // Obtindre la llista de nodes del document
            NodeList nodeList = doc.getDocumentElement().getChildNodes();

            // Recòrrer els nodes i imprimir el seu contingut
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(node.getNodeName() + ": " + node.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Mètode per editar el contingut d'un node del fitxer XML
    public void editXML(String nodo, String nuevoValor) {
    	
    	try {
            // Carregar el fitxer XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
 
            NodeList nodeList = doc.getElementsByTagName(nodo);
            Node nodeToEdit = nodeList.item(0); 

            // Editar el contingut del node
            if (nodeToEdit != null) {
                // Guardar els canvis en el fitxer XML
                nodeToEdit.setTextContent(nuevoValor);
                guardarXML(doc, file);
            } else {
                System.out.println("No s'ha trobat el node a editar.");
            }
        } catch (Exception e) {
        	  System.err.println("Error en editar el fitxer XML: " + e.getMessage());
        }
    }
    
    // Mètode per guardar els canvis al fitxer XML
    private static void guardarXML(Document doc, File file) {
    	
    
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("Fitxer XML modificat amb èxit.");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
}
    // Mètode principal
	public static void main(String[] args) {
		
        String filePath = "parametrosFacil.xml";

        // Crear una instància de ParametrosXML
        ParametrosXML parametrosXML = new ParametrosXML(filePath);

        // Llegir el fitxer XML abans de l'edició
        System.out.println("Contingut del fitxer XML abans de l'edició:");
        parametrosXML.readXML();
        System.out.println();

//        // Editar el node numCiudadesInfectadasInicio
//        String nodoAEditar = "numCiudadesInfectadasInicio";
//        String nuevoValor = "9"; // El nou valor que vols establir
//        parametrosXML.editXML(nodoAEditar, nuevoValor);
//
//        // Llegir de nou el fitxer XML després de l'edició
//        System.out.println("Contingut del fitxer XML després de l'edició:");
//        parametrosXML.readXML();
		
	}
}
