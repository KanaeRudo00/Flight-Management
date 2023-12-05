/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXC;

/**
 *
 * @author RLappc.com
 */
public class BooleanFormatException extends Exception{

    public BooleanFormatException() {
    }

    public BooleanFormatException(String message) {
        super(message);
    }

    public BooleanFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public BooleanFormatException(Throwable cause) {
        super(cause);
    }

    public BooleanFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        if(getMessage() == null){
            return "BooleanFormatException Occured: String didn't match regex!";
        }
        return "StringFormatExpcetion Occurred:  "+getMessage(); //To change body of generated methods, choose Tools | Templates.
    }    
}
    
    

