#  Spring Boot Assignment

# Features
1. This assignment makes use of  Google Maps Geocoding API available at location -- https://developers.google.com/maps/documentation/geocoding/intro
2. Uses Swagger for API documentation, available at location (once assignment is up and running) -- http://localhost:8080/swagger-ui.html
3. Uses H2 In-Memory Database, available at location -- http://localhost:8080/h2-console     (no password required)
4. Uses Spring Boot to build server side api
5. Maven Build script is provided
6. Test cases are provided

# References
1. Assignment code is available at Git location : https://github.com/tapanmh/SpringBootAssignment
2. Sample JSON requests are available at location: {Project Root}/SpringBootAssignment/src/main/resources/sample_json_request/sample_request.json

# Assignment Setup
1. Get the project source from Github location https://github.com/tapanmh/SpringBootAssignment
2. Run the Spring Boot application
3. This application exposes API endpoint at URL : http://localhost:8080/shopapi
4. Please use Swagger UI for Save and Retrieve shop details API:  http://localhost:8080/swagger-ui.html
	Alternatively Chrome's Postman App can be used to execute Save and Retrieve shop API 
5. Sample JSON requests for shops be found at location : assignment-spring/src/main/resources/sample_request/sample_request.json

# Run the Samples
1. Please run the generated Jar file to start the application
2. Please use Swagger UI or POSTMAN to send a POST JSON request to save Shop Details at the application URL: http://localhost:8080/shopapi

Sample JSON Request for :

{
	"shopName" : "Parijat Mithai",
	"shopAddress" : {
		"address" : "Sai Apex, Clover Park, Viman Nagar, Pune, Maharashtra",
		"postCode" : 411014
	}
	
}

Sample JSON requests are available at location: {Project Root}/SpringBootAssignment/src/main/resources/sample_json_request/sample_request.json

3. Please use Swagger UI or POSTMAN to send a GET request to retrieve shop details at the URL http://localhost:8080/shopapi  
	this URL accepts 2 parameters:
	1. latitude
	2. longitude

Sample get URL is http://localhost:8080/shopapi?latitude=18.5649663&longitude=73.9144086



