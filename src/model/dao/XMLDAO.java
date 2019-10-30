package model.dao;

import Exception.OurXPathException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Description : The DAO interface to make the business implementation do not depend on the details of the persistence layer.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public interface XMLDAO {

    /**
     * Iterate all the data in the nodeList and put them in the string.
     *
     * @param string   he 2-d string array to store the data you want.
     * @param nodeList the nodeList of all data you want to retrieval.
     */
    void getXMLcontent(String string[][], NodeList nodeList, int maxAtt);

    /**
     * Iterate one xml node to get all its elements' content and put them into the string array.
     *
     * @param string     the 2-d string array to store the data you want.
     * @param targetNode the node with one specific item in xml you want to retrieval.
     * @param i          the first index of the string array.
     * @param j          the second index of the string array.
     */
    void getOneXMLcontent(String string[][], Node targetNode, int i, int j);

    /**
     * Obtain all the information in xml
     * @return the arrayList which contain all the information.
     */
    Object getFamilyMemebers();

    /**
     * Find the node which match the expression by xpath.
     *
     * @param express the expression used in xpath retrieval
     * @param source  the root node in xml you want to retrieval
     * @return the node meet the expression
     * @throws OurXPathException xpath retrieval return nothing
     */
    Node selectSingleNode(String express, Element source) throws OurXPathException;

    /**
     * Find the nodeList which match the expression by xpath.
     *
     * @param express the expression used in xpath retrieval
     * @param source  the root node in xml you want to retrieval
     * @return the nodeList meet the expression
     * @throws OurXPathException xpath retrieval return nothing
     */
    NodeList selectNodes(String express, Element source) throws OurXPathException;
}
