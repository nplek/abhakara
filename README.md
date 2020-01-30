# abhakara
Abhakara

Service | Description
------------ | -------------
admiral-app | Manage user and system
admiral-api | API user and system

## Require
* java
* maven
* docker

## Admiral-app

### Build package
```javascript
cd admiral-app

mvn package
```

## Admiral-api
API mange user and system.

### Compile
```javascript
cd admiral-api

mvn package
```

### Build docker
```javascript
cd admiral-api

docker build -t admiral-api .
```

### Run docker
```javascript
docker run --name api -d admiral-api
```

## Task list
- [ ] Task 1
- [ ] Task 2
- [ ] Task 3
- [ ] Task 4