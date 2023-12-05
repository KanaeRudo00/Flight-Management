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
public class StringFormatException extends Exception {

    public StringFormatException() {
    }

    public StringFormatException(String message) {
        super(message);
    }

    public StringFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringFormatException(Throwable cause) {
        super(cause);
    }

    public StringFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        if (getMessage() == null) {
            return "StringFormatException Occured: String didn't match regex!";
        }
        return "StringFormatExpcetion Occurred:  " + getMessage(); //To change body of generated methods, choose Tools | Templates.
    }
}
