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
|Run all with Tags| `mvn test -D"cucumber.filter.tags=@sanity"` |
|Run all on specfic platform| `mvn clean test -DplatformName=android` or `mvn clean test -DplatformName=iOS`|
|Run all on BROWSER STACK Cloud| `mvn clean test -DshouldRunOnBrowserStack=true -DdeviceName=Google Pixel 3 -DplatformVersion=9.0`|
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
```

## Enhancements
```diff
+  Ids of Element should be same for all platforms
+  Note Edit feature should be added

```

## Browserstack
`curl -u "sagarvarule_Nv2k8l:xTzj2eNxZytnJbrx1e6C" -X POST "https://api-cloud.browserstack.com/app-automate/upload" -F "file=@C:\SagarV\Projects\COCUS-Challenge\cocus-mobiletest-challenge\cocus-mobile-test\src\test\resources\apk\app-mock-debug.apk"
-F "custom_id=CalculatorApp"`

`{"app_url":"bs://e2a2c4bfaf464c02f5ea94b604af6e44995a6ae3","custom_id":"CocusNoteAppSV","shareable_id":"sagarvarule_Nv2k8l/CocusNoteAppSV"}`

