# Everyone
Everyone is a clothing store website built for the semiannual work of the discipline Software Engineering Laboratory at [FATEC ZL](http://www.fateczl.edu.br/).

## Running Everyone locally
Everyone is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:

```
git clone https://github.com/guto-alves/everyone.git
cd everyone
./mvnw package
java -jar target/*.jar
```

You can then access everyone here: http://localhost:8080/clothes

<img width="1042" alt="everyone-screenshot" src="https://user-images.githubusercontent.com/48946749/101674460-4b8bd680-3a37-11eb-88ce-20182df01d65.png">

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

### Login as Everyone Admin
Admin Account

- Email: admin@everyone.com

- Password: 123

Logged in as an administrator, you can see a special drop-down menu in the navigation bar:

![Admin nav item](https://user-images.githubusercontent.com/48946749/101681945-b04c2e80-3a41-11eb-8864-46d8dd2b9ee4.png)


## Working with Everyone in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 9 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * [VS Code](https://code.visualstudio.com)

### Steps:

1) On the command line
    ```
    git clone https://github.com/guto-alves/everyone.git
    ```
2) Inside Eclipse or STS
    ```
    File -> Import -> Maven -> Existing Maven project
    ```

    Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the css. Run the application main method by right clicking on it and choosing `Run As -> Java Application`.

3) Inside IntelliJ IDEA
    In the main menu, choose `File -> Open` and select the Everyone [pom.xml](pom.xml). Click on the `Open` button.

    CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources` or right click on the `everyone` project then `Maven -> Generates sources and Update Folders`.

    A run configuration named `EveryoneApplication` should have been created for you if you're using a recent Ultimate version. Otherwise, run the application by right clicking on the `EveryoneApplication` main class and choosing `Run 'EveryoneApplication'`.

4) Navigate to Everyone

    Visit [http://localhost:8080/clothes](http://localhost:8080/clothes) in your browser.
