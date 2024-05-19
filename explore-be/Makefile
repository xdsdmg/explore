jar:
	mvn package

gen:
	java -jar ./attachments/mybatis-generator-core-1.4.2.jar -configfile ./src/main/resources/generatorConfig.xml -overwrite

dev: jar
	mvn spring-boot:run -Dspring-boot.run.profiles=dev --debug

run: jar
	java -jar target/myproject-0.0.1-SNAPSHOT.jar

.PHONY: jar dev run