jar:
	mvn package

dev: jar
	mvn spring-boot:run -Dspring-boot.run.profiles=dev --debug

run: jar
	java -jar target/myproject-0.0.1-SNAPSHOT.jar

.PHONY: jar dev run