package in.ineuron.swagger;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/* Swagger API - 
1. Open-source library to provide responsive api documentation for RestController
2. From single place, you can create API documentation for all RestControllers 
3. Responsive Documentation means you can test immediately by providing i/p and get o/p, so postman is optional needed 
4. Swagger documentation have url, endpoints info, model info.
5. To use swagger in Spring Boot api, SpringFox + Swagger released a library.
 
	Procedure to use Swagger -

1. RESTful Service/Producer/REST API project should be ready

2. Add these 2 dependencies - springfox-swagger & springfox-swaggerui jars 
	<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>

3. Create a SwaggerAPI Configuration class, enable swagger with Docket class Bean(for pre-defined class) 

	marked this class as Configuration not Component as if Spring IOC doesn't find this class bean in main Configuration 
	class, then find in this configuration class as well
	
	@Configuration	// to create object/bean of this user-defined class by Spring IOC
	@EnablingSwagger2	// to enable swagger2
	public class SwaggerDocsConfig {
	
		@Bean // to create bean/object for this pre-defined class by Spring IOC
		public Docket createDocket() {
			;;; 
			copy & paste code internet 
			
			Docket docket=new Docket(DocumentationType.SWAGGER_2)  	// to enable swagger ui screen type
			  .select()											// specify rest controllers
			  .apis(RequestHandlerSelectors.any()) 				// specify rest controller base package             
			  .paths(PathSelectors.any())						// specify request path                          
			  .build();											// build docket object with required info
			
			return docket;
		}
	}

	Docket object have 
	1. Documentation type 
	2. specifying the base package of rest controller 
	3. specifying the request path info 
	4. other details of API(company name, licence, url, ...)	

4. This issue is caused by Spring Fox 2.9.2/3.0.0 not supporting new PathPattern Based Path Matching Strategy for Spring MVC
	which is now the new default from spring-boot 2.6.0, so add below property to enable Spring Boot/MVC to use old path matching strategy
	spring.mvc.pathmatch.matching-strategy= ant-path-matcher
	
5. @ApiOperation - use at controller/restcontroller endpoints level, to change the default method/rest endpoint name with description in Swagger UI	
	
6. Test url - http://localhost:9999/ContextPath/swagger-ui.html
*/

@EnableSwagger2	
@Configuration	
public class SwaggerDocsConfig {

	@Bean	
	public Docket createDocketBean() {
		
		/* Run first with default configuration
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();*/	
		
		// Run after applying configuration
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("in.ineuron.restcontroller"))
				.paths(PathSelectors.regex("/api/tourist.*"))	// anything after base url
				.build()
				.useDefaultResponseMessages(true)
				.apiInfo(getApiInfo());		// getting terms of service, license info, contact details
		
		return docket;
	}
	
	private  ApiInfo getApiInfo() {
		Contact contact=new Contact("Shivam Garg", "https://www.google.com/", "shivamgargmi05@gmail.com");
		
		return new ApiInfo("TouristInfo", "Gives Information about Tourist activities", "3.4.RELEASE", "http:", 
				contact, "GNU PUBLIC", "http://apache.org/license/guru", Collections.emptyList() );
	}
	
}
