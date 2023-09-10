package in.ineuron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* DispatcherServlet will take client request & Output of each component will be given back to DispatcherServlet and 
   then DispatcherServlet will give that input to next component & it gives finally response to client

Spring MVC components - DispatcherServlet, HandlerMapper, Controller, ModelAndView, ViewResolver, View, 
	BasicErrorController(errorHtml() for Spring MVC & error() for Spring REST)

Spring REST components - Front Controller/DispatcherServlet, HandlerMapper, RestController, Model, 
	HttpMessageConverter(pojo to json)/BasicErrorController(errorHtml() for Spring MVC & error() for Spring REST)

HttpMessageConverter - Converts bean object to json object automatically used by DispatcherServlet component/Spring 
	REST jackson-databind api jar ObjectMapper class

Case 1 - If data is sent by controller endpoint to DispatcherServlet, then
DispatcherServlet gives pojo to HttpMessageConverter as input and HttpMessageConverter converts to json object as 
output back to DispatcherServlet and then DispatcherServlet sends json response to client  

Case 2 - But If error/exception is sent by controller endpoint to DispatcherServlet, then 
DispatcherServlet uses ErrorController(I) implementation class BasicErrorController with errorHtml()/ for Spring MVC &
error() for Spring REST for default exception handling as whitelabel error & json object exception response to 
client which is not user-understandable format 

// for Spring MVC, whitelabel error page
@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);	
		Map<String, Object> model = Collections
			.unmodifiableMap(getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
	}

// for Spring REST, default json exception object
	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		if (status == HttpStatus.NO_CONTENT) {
			return new ResponseEntity<>(status);
		}
		Map<String, Object> body = getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
		return new ResponseEntity<>(body, status);
	}
 
Run the entire app in debug mode as Debug as -> Debug on Server & applied breakpoints on 1st line of above two methods
F8 - to switch to breakpoints, just to check these methods get used internally & control flow stops

Working with Global Exception Handling/Spring/Spring Boot/Spring REST/Spring MVC -

As Exception will raise in service layer but not handled in service layer, so propagates to controller layer.
As we have handled exception in controller layer to avoid default exception handling, but if we don't handle in 
controller layer, it will use default exception handling i.e., controller layer endpoint will throw exception object 
to DispatcherServlet and DispatcherServlet will use BasicErrorController errorHtml() & error() bydefault.
But Handling exception in controller layer is not recommended in Spring REST, so we use Global Exception Handling.

As exception raised in service layer & propagates to controller layer, so it should be handled/go to one more 
layer/class using Spring AOP, do not handle in controller layer & then that exception response go to DispatcherServlet
directly, then from DispatcherServlet java object to HttpMessageConverter and back to DispatcherServlet as json object
& then from DispatcherServlet sends json object to user/client 

Separating service logic from business logic is AOP. 

1. Create user-defined error class entity with fields timestamp, message, status & Custom Exception class like 
TouristNotFoundException
2. Use @RestControllerAdvice at class level
3. Create rest endpoints using @ExceptionHandler at method level
4. Endpoints Return type be ResponseEntity<ErrorEntity>
*/

@SpringBootApplication
public class TicketManagementProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementProjectApplication.class, args);
	}

}
