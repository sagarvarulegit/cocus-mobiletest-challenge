# cocus-mobiletest-challenge


## Running Tests

|Description|Command|
|---|---|
|All Test| `mvn clean test`|
|Run with Tags| `mvn test -D"cucumber.filter.tags=@sanity"` |

## Test Reports
Test report is generate at `target/cucumber-reports/cucumber-html-report.html`. Screenshot are attached to HTML report as per its corresponding scenarios.