# Auto Suggest Service
This web application allows user to get auto suggestions based on city prefix and co-ordinates (latitude and longitude).

## APIs
This project has 1 API to get suggestions

### 1.1 GET all Suggestions with city-prefix
````
Returns all suggestions based on city prefix 
localhost:8080/suggestions?q={city_prefix}

Time complexity: O(nLogn), n is the longest city, sorting adds the complexity.
Space complexity: O(nxm), m is the number of words in trie.
````
Request
```
curl -X GET \
  'http://localhost:8080/suggestions?q=Mon'
```
![image](https://user-images.githubusercontent.com/18723463/57963619-e6bcd880-78f4-11e9-926f-814f3b5d58ae.png)

### 1.2 GET all Suggestions with city-prefix and latitude-longitude
````
Returns all suggestions with score based on city prefix and latitude-longitude
localhost:8080/suggestions?q={city_prefix}&latitude=43.70011&longitude=-79.4163

Time complexity: O(n), n is the longest city.
Space complexity: O(nxm), m is the number of words in trie.
````
Request
```
curl -X GET \
  'http://localhost:8080/suggestions?q=Chicago&latitude=43.70011&longitude=-79.4163'
```
![image](https://user-images.githubusercontent.com/18723463/57963662-9bef9080-78f5-11e9-8595-6132862a7b0a.png)

## Build and Execution
This is a standard SpringBoot-maven application and can be built via simple [maven](https://maven.apache.org/install.html) commands. 
```
mvn clean
mvn test
mvn clean install
```
*The project SCM url can be further used to deploy to jenkins via freestyle or pipeline projects.*

Execute from command line
```
cd {PROJECT/TARGET}
java -jar suggestions-0.0.1-SNAPSHOT.jar
```

Execute from eclipse/ STS IDE
```
Right click on the project -> Run as Spring Boot app
```
## Snapshots

![image](https://user-images.githubusercontent.com/18723463/57963691-0bfe1680-78f6-11e9-866d-1a256c0781c6.png)

```
```
![image](https://user-images.githubusercontent.com/18723463/57963719-86c73180-78f6-11e9-8ef9-14351ad56b67.png)


```
Running via STS IDE
```
![image](https://user-images.githubusercontent.com/18723463/57963734-d6a5f880-78f6-11e9-9bd2-40209fe7cee2.png)

![image](https://user-images.githubusercontent.com/18723463/57963740-07862d80-78f7-11e9-884f-b62a0c062d2a.png)


```
Running via command line
```
![image](https://user-images.githubusercontent.com/18723463/57963760-78c5e080-78f7-11e9-9b9a-02bd377044bd.png)


```
Test Cases and Code coverage
```
![image](https://user-images.githubusercontent.com/18723463/57963774-a874e880-78f7-11e9-8e75-444b73065380.png)


```
Swagger UI integration
http://localhost:8080/swagger-ui.html#/suggestion-controller
```
![image](https://user-images.githubusercontent.com/18723463/57963794-ed008400-78f7-11e9-81fe-828ca3e96bcb.png)
![image](https://user-images.githubusercontent.com/18723463/57963803-17ead800-78f8-11e9-9ee8-f929b0759fc1.png)


```
Postman APIs
import "Auto-Suggestions.postman_collection.json" via postman import option
```
![image](https://user-images.githubusercontent.com/18723463/57963876-2685bf00-78f9-11e9-8984-0d2ffa2c7897.png)



```
```
