package in.ineuron.error;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// as want to convert java object to json object for this class while sending error/exception object to client 
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String message;
	private String status;
	
}
