DOCKER_TAG ?= snapshot-`date +'%Y%m%d-%H%M'`

.PHONY: clean
clean:
	mvn clean

all: clean java docker

java:
	mvn install

docker:
	docker build -t adobeapiplatform/echo-microservice .

.PHONY: docker-ssh
docker-ssh:
	mkdir -p ~/tmp/docker/apiplatform/echo-microservice; cp ./target/echo-service*.jar ~/tmp/docker/apiplatform/echo-microservice/echo-microservice.jar
	docker run --rm=true -p 8080:8080 --volume=${HOME}/tmp/docker/apiplatform/echo-microservice:/usr/local/echo-microservice -ti --entrypoint='bash' adobeapiplatform/echo-microservice:latest

.PHONY: docker-run
docker-run:
	mkdir -p ~/tmp/docker/apiplatform/echo-microservice
	mkdir -p ~/tmp/docker/apiplatform/echo-microservice; cp ./target/echo-service*.jar ~/tmp/docker/apiplatform/echo-microservice/echo-microservice.jar
	docker run --rm=true -p 8080:8080 --volume=${HOME}/tmp/docker/apiplatform/echo-microservice:/usr/local/echo-microservice adobeapiplatform/echo-microservice:latest ${DOCKER_ARGS}

.PHONY: composer-run
composer-run:
	docker-compose up

.PHONY: docker-push
docker-push:
	docker tag adobeapiplatform/echo-microservice adobeapiplatform/echo-microservice:$(DOCKER_TAG)
	docker push adobeapiplatform/echo-microservice:$(DOCKER_TAG)

.PHONY: docker-push-latest
docker-push-latest:
	docker tag -f adobeapiplatform/echo-microservice adobeapiplatform/echo-microservice:latest
	docker push adobeapiplatform/echo-microservice:latest