port ?= 8081
name = "multiple-line-string"
v ?= v0.0.1

build:
	mvn clean install
test:
	mvn test
run:
	mvn spring-boot:run
test:
	mvn test
