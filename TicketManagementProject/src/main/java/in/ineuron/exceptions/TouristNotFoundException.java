package in.ineuron.exceptions;

// as Exception is a partially checked Exception, if extends it then throws custom exception
// As Runtime Exception is not checked, so no need to throws it
public class TouristNotFoundException extends RuntimeException {	
	
	private static final long serialVersionUID = 1L;

	public TouristNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}
