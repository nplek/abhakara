# abhakara
Abhakara

[ ]|Service | Description
---|------------ | -------------
[ ] admiral-app | Manage user and system
[x] admiral-api | API user and system

## Admiral-app

### Buile package
cd admiral-app
mvn package

## Admiral-api

### Compile
cd admiral-api
mvn package

### Buile docker
`<addr>` docker build -t admiral-api admiral-api/.

### Run docker
docker run --name api -d admiral-api