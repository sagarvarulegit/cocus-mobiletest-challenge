# cocus-mobiletest-challenge

## Pre-requiste
- Java 17
- Appium 8.2.0
- Apache Maven 3.8.6
- Node.js 18.12.0
- NPM 8.19.0

## Running Tests

|Description|Command|
|---|---|
|Run all Test| `mvn clean test`|
|Run all with Tags| `mvn clean test -D"cucumber.filter.tags=@sanity"` |
|Run all on specfic platform| `mvn clean test -DplatformName=android` or `mvn clean test -DplatformName=iOS`|
|Run all on BROWSER STACK Cloud| `mvn clean test -DshouldRunOnBrowserStack=true -DdeviceName="Google Pixel 3" -DplatformVersion="9.0"`|
### List of Devices on Browserstack
Full devices list can be found [here](https://www.browserstack.com/list-of-browsers-and-platforms/app_automate)

Alternatively we can also specify configurations properties in `src\test\resources\config\testconfig.yml` file. Framework will first check for maven parameter, if not given it will read from `testconfig.yml`

## Test Reports
Test report is generate at `target/cucumber-reports/cucumber-html-report.html`. Screenshot are attached to HTML report as per its corresponding scenarios.


## Issues
```diff
-  Large Text in Note is Trimmed
-  Click on Add Image Deletes All existing notes 
-  Notes are not persisted after App is Restarted
-  Spelling error in Statistics Screen shows label as "Statistcs"
```

## Enhancements
```diff
+  Ids of Element should be same for all platforms
+  Note Edit feature should be added

```


## Latest Run Report
Cloud Run on Browserstack - [Report](https://app-automate.browserstack.com/dashboard/v2/builds/916a0c4fb23b1d9c95827d12327a56496ede42d8/sessions/eda2ebede5b4f1c4a3da0ae80d76cf0c2ffae504)
