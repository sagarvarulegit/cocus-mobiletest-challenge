# cocus-mobiletest-challenge


## Running Tests

|Description|Command|
|---|---|
|Run all Test| `mvn clean test`|
|Run all with Tags| `mvn test -D"cucumber.filter.tags=@sanity"` |
|Run all on specfic platform| `mvn clean test -DplatformName=android` or `mvn clean test -DplatformName=iOS`|

Alternatively we can also specify configurations properties in `src\test\resources\config\testconfig.yml` file. Framework will first check for maven parameter, if not given it will read from `testconfig.yml`

## Test Reports
Test report is generate at `target/cucumber-reports/cucumber-html-report.html`. Screenshot are attached to HTML report as per its corresponding scenarios.