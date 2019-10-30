package Exception;

/**
 * Description : The exception when the xpath retrieval return nothing.
 *
 * @author YinChen
 * @version 1.7
 *
 */
public class OurXPathException extends Exception{
    public OurXPathException(){
        super("Your Xpath query get nothing!");
    }
}
