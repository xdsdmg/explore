jar:
	mvn package

dev: jar
	mvn spring-boot:run -Dspring-boot.run.profiles=dev --debug

run: jar
	java -jar target/explore-0.0.1-SNAPSHOT.jar

clean:
	rm -rf target

.PHONY: jar dev run clean