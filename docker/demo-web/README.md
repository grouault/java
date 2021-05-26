## demo-docker
* projet spring-boot simple pour démo déploiement docker
* expose un simple service API Rest

## docker build
$ docker build -t demo-web-spb .

## docker run
$ docker run -d -p 8089:8080 demo-web-spb

## docker test
$ http://host:8089/test