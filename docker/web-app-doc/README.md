## demo
* application static web demo pour Docker
* simple page html à afficher via un serveur web

## builder l'image
$ docker build -t web-app-doc .

## builder l'image
$ docker run -d -p 8084:80 web-app-doc