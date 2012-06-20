## Create User

$ mongo categolj
MongoDB shell version: 1.6.5
connecting to: categolj
> db.user.save({ "_id" : "<USER ID>", "password" : "<PASSWORD>", "role" : "USER" })

## Run

    $ MAVEN_OPTS=-Xmx512m mvn tomcat:run

(in case of Windows `set MAVEN_OPTS=-Xmx512m`)

access http://localhost:8080

you can login using default account (username:<USER ID>, password:<PASSWORD>).
