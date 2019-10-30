package model.dao.implimentation;

import model.Entity.Tariff;
import model.dao.TariffDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Description : all the method used to process data interaction with XML about tariff.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class TariffDAOImpl extends XMLDAOImpl implements TariffDAO {

    private static String xmlPath = "resources/tariff.xml";
    private static int maxTariff = 2;
    private static int maxAtt = 2;

    /**
     * Obtain all the information in tariff.xml
     * @return the Tariff entity which contains all the information of current tariff.
     */
    public Object getFamilyMemebers(){
        String tariffs[][] = new String[maxTariff][];
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();//set the factory to get xml instance
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlPath); // use dom to resolve xml

            NodeList nodelist = doc.getElementsByTagName("tariff");
            getXMLcontent(tariffs, nodelist, maxAtt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Str2Tariff(tariffs);
    }


    /**
     * Modify certain information of current tariff and put the result into xml.
     * @param tariff the entity with new electric and gas information.
     */
    public void modify(Tariff tariff){
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try{

            DocumentBuilder db=dbf.newDocumentBuilder();
            Document xmldoc=db.parse(xmlPath);

            Element root = xmldoc.getDocumentElement();

            String xPath = "/tariffs/tariff[@id='0']";

            Element per = (Element) selectSingleNode(xPath, root);	//choose the specific one
            per.getElementsByTagName("price").item(0).setTextContent(tariff.getGas());
            per.getElementsByTagName("future").item(0).setTextContent(tariff.getGasFuture());

            String xPath2 = "/tariffs/tariff[@id='1']";

            Element per2 = (Element) selectSingleNode(xPath2, root);	//choose the specific one
            per2.getElementsByTagName("price").item(0).setTextContent(tariff.getElectricity());
            per2.getElementsByTagName("future").item(0).setTextContent(tariff.getElectricityFuture());

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Transfer the 2-D array to an tariff entity.
     * @param tariffs the string array of tariff.
     * @return the tariff entity.
     */
    public Tariff Str2Tariff(String tariffs[][]){

        Tariff tf = new Tariff();
        tf.setGas(tariffs[0][0]);
        tf.setGasFuture(tariffs[0][1]);
        tf.setElectricity(tariffs[1][0]);
        tf.setElectricityFuture(tariffs[1][1]);

        return tf;
    }
}