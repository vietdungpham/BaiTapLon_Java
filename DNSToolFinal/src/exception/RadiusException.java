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
public class RadiusException extends Exception {

    public RadiusException(String message) {
        super(message);
    }

    public RadiusException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
