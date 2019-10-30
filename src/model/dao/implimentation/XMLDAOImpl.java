package model.dao.implimentation;

import Exception.OurXPathException;
import model.dao.XMLDAO;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Description : The parent class of all the class used to process data interaction.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public abstract class XMLDAOImpl implements XMLDAO {

    /**
     * Iterate all the data in the nodeList and put them in the string.
     * @param string he 2-d string array to store the data you want.
     * @param nodeList the nodeList of all data you want to retrieval.
     * @param maxAtt the corresponding number of attributes
     */
    public void getXMLcontent(String string[][], NodeList nodeList, int maxAtt){
        for (int i = 0; i < nodeList.getLength(); i++) // use loop to process items
        {
            Element targetNode = (Element)nodeList.item(i);// use list() to get nodes in set,
            int j = 0;
            string[i] = new String[maxAtt];
            getOneXMLcontent(string, targetNode, i, j);
        }
    }

    /**
     * Iterate one xml node to get all its elements' content and put them into the string array.
     * @param string the 2-d string array to store the data you want.
     * @param targetNode the node with one specific item in xml you want to retrieval.
     * @param i the first index of the string array.
     * @param j the second index of the string array.
     */
    public void getOneXMLcontent(String string[][], Node targetNode, int i, int j){
        for (Node node = targetNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE) {  //see if it's element node
                String name = node.getNodeName();
                String value = node.getFirstChild().getNodeValue();
                string[i][j] = value;
                j++;
                System.out.println(name + " : " + value);
            }
        }
     }

    /**
     * Obtain all the information in xml
     * @return the arrayList which contain all the information.
     */
    public abstract Object getFamilyMemebers();

    /**
     * Find the node which match the expression by xpath.
     *
     * @param express the expression used in xpath retrieval
     * @param source the root node in xml you want to retrieval
     * @return the node meet the expression
     * @throws OurXPathException xpath retrieval return nothing
     */
    public Node selectSingleNode(String express, Element source) throws OurXPathException{
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
            if(result == null)
                throw new OurXPathException();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Find the nodeList which match the expression by xpath.
     *
     * @param express the expression used in xpath retrieval
     * @param source the root node in xml you want to retrieval
     * @return the nodeList meet the expression
     * @throws OurXPathException xpath retrieval return nothing
     */
    public NodeList selectNodes(String express, Element source) throws OurXPathException{
        NodeList result = null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result = (NodeList) xpath.evaluate(express, source, XPathConstants.NODESET);

            if(result == null)
                throw new OurXPathException();

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
