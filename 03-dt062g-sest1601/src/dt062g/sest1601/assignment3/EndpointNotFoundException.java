/**
* <h1>Assignment 3 - EndpointNotFoundException.java</h1>
* <p>This class inherits (extends) Exception and defines a special exception
* </p>
*
*
* @author Sebastian Strindlund (sest1601)
* @version 1.0
* @since 2017-11-08
*/

package dt062g.sest1601.assignment3;

public class EndpointNotFoundException extends Exception { // Inherits from Exception
    
    public static final long serialVersionUID = 1L;

    public EndpointNotFoundException() {}
    
    public EndpointNotFoundException(String message) {
        super(message);
    }
}