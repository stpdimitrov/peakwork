# Project Title
	Peakwork
	
##Project description

 The microservice aims to serves as a comuttator to Iex market data.
 It gathers stock market data for configurable set of companies symbols and periodical timing.
 The collected data is then persisted with aid of google datastore service.
 The application have one endpoint for retrieving historical records of stock data in Json array type.
	
 E.g. of endpoint output format 
 {  
   "stockRecordId":6294518445899776,
   "symbol":"AAPL",
   "companyName":"Apple Inc.",
   "logo":"https://storage.googleapis.com/iex/api/logos/AAPL.png",
   "price":"207.48",
   "created":"2018-11-05T10:42:33.263"
 }
	
## Getting Started

 Clone or download copy of the project here: https://github.com/stpdimitrov/peakwork.git

### Prerequisites

 For dev environment:
	Following software should be already installed:
	- Java 8+
	- Maven 3+
	- IDE of you choice (original developmet have been done on Eclipe)
	- Google Cloud SDK
	
### Installing

 Make you project up and running
 - Simple mvn package should work for local perposes.
 - To fire your app just run : mvn spring-boot:run
 - To debug your app : mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005" and listen to port 5005 on your IDE
 - Google Cloud Data store need some app authentication for remote service processing so you should make sure your spring.cloud.gcp.datastore.credentials.location evnironment property 
 is pointing to the right location. For the demo purposess the Peakwork-c3f22c4a7d94.json is directly included in the project.

 At this point You should be able to run the app and connect remotely to the Google Cloud Datastore

 Make sure that you have properly your set of symbols to process by the service uder iex.symbols property name in application.properties file.
 Also you can configure the poll interval under iex.interval property name in application.properties file.

 Example of http request for getting stock data.

 http://localhost:8080/stocks/AAPL,FB/interval?from=2018-11-05T00:00:00&to=2018-11-05T23:59:00

## Running the tests

N/A

## Deployment

### Prerequisites

 -Google Cloud SDK shell contains all gcloud, docker, and kubectl command-line tools.

 Your project contains a Docker file wich will be used to make a container image.
 - Build an image : docker build -t gcr.io/peakwork-221411/<name_of_your_peakwork_project>:<version> .
 - Probably you will need to configure your container registry at first try : gcloud auth configure-docker
 - Push your image to container registry : push gcr.io/peakwork-221411/peakwork:v1


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

N/A

## Versioning

N/A

## Authors

* **Stefan Tabakov** - https://github.com/stabakov
* **Stoyan Dimitrov** - *Initial work* - https://github.com/stpdimitrov

## License

This project is licensed under the MIT License

## Acknowledgments