DEMOQA - Book Store End To End Testing
---

This project tests UI and API scenarios written in Gherkin using a BDD approach. By implementing the Page Object pattern promotes maintainable and scalable test automation code, making it easier to adapt to UI changes and keep the code DRY.

The project uses:
1. Java
2. Maven
3. Selenium
4. Rest Assured
4. Junit
5. Cucumber
---

Prerequisites
---
1. JDK 11+ (Ensure that the Java class path is properly set)
2. Maven (Ensure that the .m2 class path is properly set)
3. Required IDE Plugins: Maven, Cucumber
4. Browser (There must be at least one of them): Chrome, Firefox, Edge
---
Running Suite
---
1. Execute the test cases by Maven command `mvn clean test`
2. To run specific browser(default Chrome) for example Firefox `mvn clean test -Dbrowser=firefox`
3. To run specific tags on Linux-based CI Environments (bash): `mvn clean test -Dcucumber.filter.tags="@e2e"`
4. To run specific tags on Windows-based CI Environments (powershell): `mvn clean test "-Dcucumber.filter.tags=@e2e"`
---

Reporting
---
1. A html report - Which is generated using Cucumber Report, in the `target/cucumber-html-reports/allure-maven/index.html` directory, allowing you to view it locally.
2. Takes screenshot if a scenario failed and attached to the report.

![Report](https://github.com/umitolmez/e2e-testing-bookStore/blob/master/src/test/resources/demo/reportSS.png)

---

Improvements & Suggestions
---

1. Parallel Run: As the number of test cases increases, it will take time to run tests in a single browser.
2. Reporting: If the management team does not like the reports generated, a different reporting tool can be tried.
3. Rerun: A file can be created to rerun failed test cases.
4. Branch Strategy: I did not pay attention to commit and branch issues because I was working alone on this project. Branch strategy would be better in terms of traceability.

https://demoqa.com/

https://demoqa.com/swagger/