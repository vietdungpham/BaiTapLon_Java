/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author longy
 */
public class WidthException extends Exception{

    public WidthException(String message) {
        super(message);
    }

    public WidthException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
