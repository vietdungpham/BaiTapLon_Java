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
public class CoverageException extends Exception{

    public CoverageException(String message) {
        super(message);
    }

    public CoverageException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
