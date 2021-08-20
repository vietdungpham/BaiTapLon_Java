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
public class LifeTimeException extends Exception{

    public LifeTimeException(String message) {
        super(message);
    }

    public LifeTimeException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
