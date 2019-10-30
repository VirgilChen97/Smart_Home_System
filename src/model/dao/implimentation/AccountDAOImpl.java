package model.dao.implimentation;

import model.Entity.Account;
import model.dao.AccountDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.ArrayCutUtil;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Description : all the method used to process data interaction with XML about account.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class AccountDAOImpl extends XMLDAOImpl implements AccountDAO {

    private static String xmlPath = "resources/account.xml";
    private static int maxAcc = 100;
    private static int maxAtt = 13;

    /**
     * Iterate all the data in the nodeList and put them in the string. In addition, judge if the state of certain account is '-1' which means it has been deleted.
     * @param string he 2-d string array to store the data you want.
     * @param nodeList the nodeList of all data you want to retrieval.
     * @param maxAtt the corresponding number of attributes
     */
    public void getXMLcontent(String string[][], NodeList nodeList, int maxAtt){

        for (int i = 0; i < nodeList.getLength(); i++) // use loop to process items
        {
            Element targetNode = (Element)nodeList.item(i);// use list() to get nodes in set,
            if(targetNode.getElementsByTagName("state").item(0).getTextContent().equals("-1"))	// skip who is deleted
            {
                string[i] = new String[21];
                string[i][0] = "\0!";
                System.out.println("right!");
                continue;
            }
            int j = 1;
            string[i] = new String[maxAtt];
            string[i][0] = targetNode.getAttribute("accountID");
            getOneXMLcontent(string, targetNode, i, j);

        }
    }


    /**
     * Obtain all the information in account.xml
     * @return the arrayList of account entities which contain all the information of all accounts.
     */
    public Object getFamilyMemebers(){
        String accounts[][] = new String[maxAcc][];
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();//set the factory to get xml instance
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlPath); // use dom to resolve xml

            NodeList nodelist = doc.getElementsByTagName("account"); // return all the items with tag 'account'
            getXMLcontent(accounts, nodelist, maxAtt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        accounts = ArrayCutUtil.cutarray(accounts);
        return Str2Acc(accounts);
    }

    /**
     * Add a new account in xml
     * @param electricMeterID
     * @param gasMeterID
     * @param userName
     * @param electricMeterReading
     * @param gasMeterReading
     * @param electricBudgets
     * @param gasBudgets
     * @param password
     * @param state -1 means deleted, 0 means active
     * @param balance
     */
    public void add(String electricMeterID, String gasMeterID, String userName, String electricMeterReading, String gasMeterReading, String electricBudgets, String gasBudgets, String password, String state, double balance) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(false);

        try{

            int accountID;

            DocumentBuilder db=dbf.newDocumentBuilder();
            Document xmldoc=db.parse(xmlPath);

            Element root = xmldoc.getDocumentElement();

            Element lstNode = (Element) selectSingleNode("//accounts/account[last()]", root);
            for (Node node = lstNode.getFirstChild(); node != null; node = node.getNextSibling()){
                if (node.getNodeType() == Node.ELEMENT_NODE){  //see if it's element node
                    String name = node.getNodeName();
                    String value = node.getFirstChild().getNodeValue();
                    System.out.println(name+" : "+value);
                }
            }
            if(lstNode == null){
                accountID = 0;
            }
            else{
                String lastAccountID = lstNode.getAttribute("accountID");
                System.out.println("The id is " + lastAccountID);
                accountID = Integer.parseInt(lastAccountID) + 1;
            }


            System.out.println("Now account id is: \n"+accountID);

            Element account =xmldoc.createElement("account");	//create the node
            account.setAttribute("accountID", String.valueOf(accountID));	//add attribute

            Element e1 = xmldoc.createElement("electricMeterID");
            e1.setTextContent(electricMeterID);
            account.appendChild(e1);

            Element e2 = xmldoc.createElement("gasMeterID");
            e2.setTextContent(gasMeterID);
            account.appendChild(e2);

            Element e3 = xmldoc.createElement("userName");
            e3.setTextContent(userName);
            account.appendChild(e3);

            Element e4 = xmldoc.createElement("electricMeterReadingHis");
            e4.setTextContent("0");
            account.appendChild(e4);

            Element e5 = xmldoc.createElement("gasMeterReadingHis");
            e5.setTextContent("0");
            account.appendChild(e5);

            Element e6 = xmldoc.createElement("electricMeterReading");
            e6.setTextContent(electricMeterReading);
            account.appendChild(e6);

            Element e7 = xmldoc.createElement("gasMeterReading");
            e7.setTextContent(gasMeterReading);
            account.appendChild(e7);

            Element e8 = xmldoc.createElement("electricBudgets");
            e8.setTextContent(electricBudgets);
            account.appendChild(e8);

            Element e9 = xmldoc.createElement("gasBudgets");
            e9.setTextContent(gasBudgets);
            account.appendChild(e9);

            Element e10 = xmldoc.createElement("password");
            e10.setTextContent(password);
            account.appendChild(e10);

            Element e11 = xmldoc.createElement("state");
            e11.setTextContent(state);
            account.appendChild(e11);

            Element e12 = xmldoc.createElement("balance");
            e12.setTextContent(String.valueOf(balance));
            account.appendChild(e12);

            root.appendChild(account);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));//just save it

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Delete certain account, in fact, I let its state be -1.
     * @param accountID the id you want to delete. (Because energy provider just want to input id to delete someone)
     * return true means succeed, false means false
     */
    public Boolean delete(String accountID){
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try{

            DocumentBuilder db=dbf.newDocumentBuilder();
            Document xmldoc=db.parse(xmlPath);

            Element root = xmldoc.getDocumentElement();

            String xPath = "/accounts/account[@accountID='"+ accountID +"']";

            Element per = (Element) selectSingleNode(xPath, root);	//choose the specific one
            if(per.getElementsByTagName("state").item(0).getTextContent().equals("-1")){
                System.out.println("already deleted!");
                return false;
            }
            per.getElementsByTagName("state").item(0).setTextContent("-1");	//we never retrive -1

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Modify certain information of an specific account and put the result in xml, for example: update the readings. (the state can't be changed here)
     * @param acc the account entity you want to modify
     */
    public void modify(Account acc){
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        try{

            DocumentBuilder db=dbf.newDocumentBuilder();
            Document xmldoc=db.parse(xmlPath);

            Element root = xmldoc.getDocumentElement();

            String xPath = "/accounts/account[@accountID='"+ acc.getAccountID() +"']";

            Element per = (Element) selectSingleNode(xPath, root);	//choose the specific one
            per.getElementsByTagName("electricMeterID").item(0).setTextContent(acc.getElectricMeterReading());
            per.getElementsByTagName("gasMeterID").item(0).setTextContent(acc.getGasMeterReading());
            per.getElementsByTagName("userName").item(0).setTextContent(acc.getUserName());
            per.getElementsByTagName("electricMeterReadingHis").item(0).setTextContent(acc.getElectricMeterReadingHis());
            per.getElementsByTagName("gasMeterReadingHis").item(0).setTextContent(acc.getGasMeterReadingHis());
            per.getElementsByTagName("electricMeterReading").item(0).setTextContent(acc.getElectricMeterReading());
            per.getElementsByTagName("gasMeterReading").item(0).setTextContent(acc.getGasMeterReading());
            per.getElementsByTagName("electricBudgets").item(0).setTextContent(acc.getElectricBudgets());
            per.getElementsByTagName("gasBudgets").item(0).setTextContent(acc.getGasBudgets());
            per.getElementsByTagName("password").item(0).setTextContent(acc.getPassword());
            per.getElementsByTagName("balance").item(0).setTextContent(acc.getBalance());

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Get an account entity according to the id.
     * @param accountID the ID you want to obtain its information.
     * @return the account entity of the id.
     */
    public Account retrieve(String accountID) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        Account acc = new Account();
        try{
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document xmldoc=db.parse(xmlPath);

            Element root = xmldoc.getDocumentElement();

            System.out.println("accountID = " + accountID);

            String expre = "/accounts/account[@accountID='"+ accountID +"']";
            System.out.println("\nexpression = " + expre);
            Element accNode =(Element) selectSingleNode(expre, root);	//choose the specific one
            System.out.println("\nacc = " + accNode);

            if(accNode.getElementsByTagName("state").item(0).getTextContent().equals("-1"))	// skip who is deleted
            {
                System.out.println("already deleted!");
                accNode = null;
            }

            if(accNode == null){
                return null;
            }

            String elements[] = new String[12];

            elements[0] = accNode.getElementsByTagName("electricMeterID").item(0).getTextContent();
            elements[1] = accNode.getElementsByTagName("gasMeterID").item(0).getTextContent();
            elements[2] = accNode.getElementsByTagName("userName").item(0).getTextContent();
            elements[3] = accNode.getElementsByTagName("electricMeterReadingHis").item(0).getTextContent();
            elements[4] = accNode.getElementsByTagName("gasMeterReadingHis").item(0).getTextContent();
            elements[5] = accNode.getElementsByTagName("electricMeterReading").item(0).getTextContent();
            elements[6] = accNode.getElementsByTagName("gasMeterReading").item(0).getTextContent();
            elements[7] = accNode.getElementsByTagName("electricBudgets").item(0).getTextContent();
            elements[8] = accNode.getElementsByTagName("gasBudgets").item(0).getTextContent();
            elements[9] = accNode.getElementsByTagName("password").item(0).getTextContent();
            elements[10] = accNode.getElementsByTagName("state").item(0).getTextContent();
            elements[11] = accNode.getElementsByTagName("balance").item(0).getTextContent();

            acc.setAccountID(accountID);
            acc.setElectricMeterID(elements[0]);
            acc.setGasMeterID(elements[1]);
            acc.setUserName(elements[2]);
            acc.setElectricMeterReadingHis(elements[3]);
            acc.setGasMeterReadingHis(elements[4]);
            acc.setElectricMeterReading(elements[5]);
            acc.setGasMeterReading(elements[6]);
            acc.setElectricBudgets(elements[7]);
            acc.setGasBudgets(elements[8]);
            acc.setPassword(elements[9]);
            acc.setState(elements[10]);
            acc.setBalance(elements[11]);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));

            return acc;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * Transfer a 2-D array to an arrayList of account
     * @param accounts a 2-D array
     * @return an arrayList of account
     */
    public ArrayList<Account> Str2Acc(String accounts[][]){
        int length = accounts.length;

        System.out.println("The length is: "+ length + " ");

        ArrayList<Account> arrayList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Account acc = new Account();
            acc.setAccountID(accounts[i][0]);
            acc.setElectricMeterID(accounts[i][1]);
            acc.setGasMeterID(accounts[i][2]);
            acc.setUserName(accounts[i][3]);
            acc.setElectricMeterReadingHis(accounts[i][4]);
            acc.setGasMeterReadingHis(accounts[i][5]);
            acc.setElectricMeterReading(accounts[i][6]);
            acc.setGasMeterReading(accounts[i][7]);
            acc.setElectricBudgets(accounts[i][8]);
            acc.setGasBudgets(accounts[i][9]);
            acc.setPassword(accounts[i][10]);
            acc.setState(accounts[i][11]);
            acc.setBalance(accounts[i][12]);
            arrayList.add(acc);
        }

        return arrayList;
    }
}