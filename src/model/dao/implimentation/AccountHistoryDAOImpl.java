package model.dao.implimentation;

import model.Entity.AccountHistory;
import model.dao.AccountHistoryDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import util.ArrayCutUtil;
import util.DateUtil;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Description : all the method used to process data interaction with XML about account history.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class AccountHistoryDAOImpl extends XMLDAOImpl implements AccountHistoryDAO {

    private static String xmlPath = "resources/accountHistory.xml";
    private static int maxAcc = 366;
    private static int maxMon = 30;
    private static int maxWeek = 100;
    private static int maxAttr = 13;

    /**
     * @param accountID the accountID you want to get its reading history by day, week, month.
     * @return the entity AccountHistory
     */
    public AccountHistory getSpecialFamilyMemebers(String accountID){
        String accounts[][] = new String[maxAcc][];
        String accountsW[][] = new String[maxWeek][];
        String accountsM[][] = new String[maxMon][];
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();//set the factory to get xml instance
        dbf.setIgnoringElementContentWhitespace(true);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document xmldoc = db.parse(xmlPath); // use dom to resolve xml
            Element root = xmldoc.getDocumentElement();
            String xPath = "/accountHistories/accountHistory[accountID='"+ accountID +"']";

            NodeList nodelist = selectNodes(xPath, root);	//choose the specific one

            //check the week, month, year
            int weekCount = 0;
            int monthCount = 0;
            boolean weekUpdate = true;
            boolean monthUpdate = true;
            int lastWeekMonth[]; // Position 0 is Week Number and position 1 is Month Number

            String strLast = ((Element)nodelist.item(0)).getElementsByTagName("date").item(0).getTextContent();
            lastWeekMonth = DateUtil.getWeekMonth(strLast);

            accountsW[0] = new String[maxAttr];
            accountsM[0] = new String[maxAttr];
            for (int i = 0; i < nodelist.getLength(); i++) // use loop to process items
            {
                Element account = (Element)nodelist.item(i);// use list() to get nodes in set,

                //get the calendar
                String str = account.getElementsByTagName("date").item(0).getTextContent();
                System.out.println("\nstr = " + str);
                int currentWeekMonth[] = DateUtil.getWeekMonth(str);
                if(currentWeekMonth[0] != lastWeekMonth[0]) {
                    lastWeekMonth[0] = currentWeekMonth[0];
                    weekCount++;
                    accountsW[weekCount] = new String[maxAttr];
                    weekUpdate = true;
                    System.out.println("\nupdate");
                    System.out.println("\nupdate week");
                }
                if(currentWeekMonth[1] != lastWeekMonth[1]) {
                    lastWeekMonth[1] = currentWeekMonth[1];
                    monthCount++;
                    accountsM[monthCount] = new String[maxAttr];
                    monthUpdate = true;
                    System.out.println("\nnow update month");
                }
                int j = 0;
                accounts[i] = new String[maxAttr];

                for (Node node = account.getFirstChild(); node != null; node = node.getNextSibling()){
                    if (node.getNodeType() == Node.ELEMENT_NODE){  //see if it's element node
                        String name = node.getNodeName();
                        String value = node.getFirstChild().getNodeValue();
                        accounts[i][j] = value;

                        if(name.equals("accountID") || name.equals("date")){
                            if(weekUpdate)  accountsW[weekCount][j] = value;
                            if(monthUpdate) accountsM[monthCount][j] = value;
                        }
                        else{
                            double valueD = Double.parseDouble(value);
                            if(weekUpdate) {
                                accountsW[weekCount][j] = value;
                            }
                            else {
                                double lastD = Double.parseDouble(accountsW[weekCount][j]);
                                double valueDW = valueD + lastD;
                                accountsW[weekCount][j] = String.valueOf(valueDW);
                            }

                            if(monthUpdate) {
                                System.out.println("Now J " + j);
                                accountsM[monthCount][j] = value;
                                System.out.println("\nmonth add" + value);
                            }
                            else {
                                double lastD = Double.parseDouble(accountsM[monthCount][j]);
                                double valueDM = valueD + lastD;
                                accountsM[monthCount][j] = String.valueOf(valueDM);
                                System.out.println("\nmonth add" + valueDM);
                            }
                        }

                        j++;
                        System.out.println(name+" : "+value);
                    }
                }
                weekUpdate = false;
                monthUpdate = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        AccountHistory accountHistory = new AccountHistory();
        accountHistory.setAccounts(ArrayCutUtil.cutarray(accounts));
        accountHistory.setAccountsW(ArrayCutUtil.cutarray(accountsW));
        accountHistory.setAccountsM(ArrayCutUtil.cutarray(accountsM));

        return accountHistory;
    }


    /**
     * Add a new item of userHistory to XML
     * @param accountID
     * @param date
     * @param electricUsage
     * @param electricCost
     * @param gasUsage
     * @param gasCost
     */
    public void add(String accountID, String date, String electricUsage, String electricCost, String gasUsage, String gasCost) {
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(false);

        try{

            DocumentBuilder db=dbf.newDocumentBuilder();
            Document xmldoc=db.parse(xmlPath);

            Element root = xmldoc.getDocumentElement();

            Element lstNode = (Element) selectSingleNode("//accountHistories/accountHistory[last()]", root);

            String lastAccountHistoryID = lstNode.getAttribute("accountHistoryID");
            System.out.println("The id is " + lastAccountHistoryID);
            int accountHistoryID = Integer.parseInt(lastAccountHistoryID) + 1;
            System.out.println("Now account id is: \n"+accountHistoryID);

            Element account =xmldoc.createElement("accountHistory");	//create the node
            account.setAttribute("accountHistoryID", String.valueOf(accountHistoryID));	//add attribute

            Element e1 = xmldoc.createElement("accountID");
            e1.setTextContent(accountID);
            account.appendChild(e1);

            Element e2 = xmldoc.createElement("date");
            e2.setTextContent(date);
            account.appendChild(e2);

            Element e3 = xmldoc.createElement("electricUsage");
            e3.setTextContent(electricUsage);
            account.appendChild(e3);

            Element e4 = xmldoc.createElement("electricCost");
            e4.setTextContent(electricCost);
            account.appendChild(e4);

            Element e5 = xmldoc.createElement("gasUsage");
            e5.setTextContent(gasUsage);
            account.appendChild(e5);

            Element e6 = xmldoc.createElement("gasCost");
            e6.setTextContent(gasCost);
            account.appendChild(e6);

            root.appendChild(account);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer former = factory.newTransformer();
            former.transform(new DOMSource(xmldoc), new StreamResult(new File(xmlPath)));//just save it

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Object getFamilyMemebers() {
        return null;
    }
}
