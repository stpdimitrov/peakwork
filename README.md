# Project Title

	Peakwork

## Getting Started

Clone or download copy of the project here: https://github.com/stpdimitrov/peakwork.git

### Prerequisites

For dev environment:
	Following software should be already installed:
	- Java 8+
	- Maven 3+
	- IDE of you choice (original developmet have been done on Eclipe)
	- Google Cloud SDK
	
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

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

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
