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
public class TimeOfSensorException extends Exception{

    public TimeOfSensorException(String message) {
        super(message);
    }

    public TimeOfSensorException(String message, Throwable cause) {
        super(message, cause);
    }

    
}
