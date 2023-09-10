package in.ineuron.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.model.Tourist;
import in.ineuron.service.ITouristService;
import io.swagger.annotations.ApiOperation;

// Handling Exception in Controller layer is not good approach(commented), so exception propagated from service layer will be managed by Global Exception Handling mechanism using @RestControllerAdvice

@RequestMapping("/api/tourist")	// api for tourist
@RestController
public class TouristController {

	@Autowired
	private ITouristService service;
	
	// Customer data will be sent in request body in json format by client and will be received by server as java object automatically conversion using Spring REST jackson-databind api ObjectMapper class/HttpMessageConvertor
	@PostMapping("/register")
	@ApiOperation(value="For New Tourist Registration")
	public ResponseEntity<String> registerTourist(@RequestBody Tourist t) {
		
		/* Handling raised exceptions if any, as default exception will not be in an user-understandable format
		try {
			String message=service.saveTourist(t);
			
			// ResponseEntity to send any type of data as response from server to client along with the Http Status code
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		} catch(Exception e) {	// if any exception raised in service/dao layer
			return new ResponseEntity<String>("O/P : Problem Occured while registration", HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		
		String message=service.saveTourist(t);
		
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	// ? is generic type i.e., any type of data, if no exception occured send list data else send String exception message as response
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTourists() {
		/*
		try {
			List<Tourist> l=service.fetchAllTourists();
		
			// List of java objects will be sent by server on response but will be received as json array(list/multiple values of json objects) by client automatically conversion/HttpMessageConvertor
			return new ResponseEntity<List<Tourist>>(l, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("O/P : Problem Occured while fetching All Tourists Details", HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
		
		List<Tourist> l=service.fetchAllTourists();
	
		return new ResponseEntity<List<Tourist>>(l, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getTouristById(@PathVariable("id") Integer id) {
		/*try {
			Tourist t=service.fetchTouristById(id);
		
			// Tourist java object data will be sent from server but will be received by client as json format on response automatically conversion/HttpMessageConvertor
			return new ResponseEntity<Tourist>(t, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}*/
		
		Tourist t=service.fetchTouristById(id);
		
		return new ResponseEntity<Tourist>(t, HttpStatus.OK);
	}
	
	// for complete updation through entity object, & conversion of json sent by client & received by server as java object conversion automatically
	@PutMapping("/modify")	
	public ResponseEntity<String> modifyTourist(@RequestBody Tourist t) {
		/*try {
			String message=service.updateTourist(t);
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}*/
		
		String message=service.updateTourist(t);
	
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	// for partial updation through entity field
	@PatchMapping("/modifyBudget/{id}/{budgetPercent}")
	public ResponseEntity<String> modifyTouristBudget(@PathVariable Integer id, @PathVariable Float budgetPercent) {
		/*try {
			String message=service.updateTouristBudget(id, budgetPercent);
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}*/
		
		String message=service.updateTouristBudget(id, budgetPercent);
	
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTourist(@PathVariable("id") Integer id) {
		/*try {
			String message=service.deleteTourist(id);
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}*/
		
		String message=service.deleteTourist(id);
	
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
}
