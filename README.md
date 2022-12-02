# cocus-mobiletest-challenge
## Pre-requiste
- Java 17
- Appium 8.2.0
- Apache Maven 3.8.6
- Node.js 18.12.0
- NPM 8.19.0


## How to Run
1. Clone this repository
2. Open Command prompt. Go to cloned repository folder location for eg: `cd <your_location>/cocus-mobiletest-challenge`
3. Execute Command  `mvn clean test -DplatformName=android -DdeviceName=7b38ef97` or `mvn clean test -DplatformName=iOS -DdeviceName=7b38ef97`.
   <br/>To get android deviceName, run `adb devices` command in command prompt and for iOS run `instruments -s devices` command in command prompt.
   <br/> *Alternatively we can also specify configuration properties like deviceName, platformName in `src\test\resources\config\testconfig.yml` file. Framework will first check for maven parameter, if not given it will read from `testconfig.yml`*
    <br/> See all list of run command [here](https://github.com/sagarvarulegit/cocus-mobiletest-challenge#running-tests).
4. Execute Command: `mvn cluecumber-report:reporting` to generate report. Generates report in `target\generated-report\index.html`. 
   <br/>*Note: Default Cucumber report is also generated in `target\cucumber-report\cucumber.html`*


## Running Tests

|Description|Command|
|---|---|
|Run all Test| `mvn clean test` (*will pick parameters from `src\test\resources\config\testconfig.yml`*)|
|Run all with Tags| `mvn clean test -D"cucumber.filter.tags=@sanity"` |
|Run all on specfic platform| `mvn clean test -DplatformName=android -DdeviceName=7b38ef97` or `mvn clean test -DplatformName=iOS -DdeviceName=7b38ef97`|
|Run all on Cloud BROWSER STACK | `mvn clean test -DshouldRunOnBrowserStack=true -DdeviceName="Google Pixel 3" -DplatformVersion="9.0"`|
### List of Devices on Browserstack
Full devices list can be found [here](https://www.browserstack.com/list-of-browsers-and-platforms/app_automate)



## Test Reports
- Comprehensive Test report is generated in `target\generated-report\index.html`
- Default cucumber report is generate at `target/cucumber-reports/cucumber-html-report.html`. 
- Latest report shows 4 failures which are bugs/defects reported in [Issues](https://github.com/sagarvarulegit/cocus-mobiletest-challenge#issues) section.
  <br/> *Note: Screenshot are attached to HTML report as per corresponding scenarios.*


## Issues
```diff
-  Large Text in Note is Trimmed after saving.
-  Clicking on "Add Picture" deletes all existing notes 
-  Saved Notes are not retrieved after App is Restarted
-  Spelling error in Statistics Screen shows label as "Statistcs"
```
*Above issue are captured in Test Reports*

## Enhancements
```diff
+  Ids of Element should be platform independent. Currently it is android specific.
+  Note Edit feature should be added.
+  Statistics Screen needs to be implemented. Curently it is just a placeholder.
+  Vertical seperator is missing in Notes List Screen.
+  Notes List Screen should have a search feature.
+  Notes List Screen should have a Sort feature.
+  Notes should have a Delete feature.
```


## Latest Run Report
Cloud Run on Browserstack - [Report](https://app-automate.browserstack.com/dashboard/v2/builds/916a0c4fb23b1d9c95827d12327a56496ede42d8/sessions/eda2ebede5b4f1c4a3da0ae80d76cf0c2ffae504?auth_token=7c1ae7304d9a8a4a32a324fa9d0c159e1d0d3465ef62600e1b9e3cafb83ebc02)

`target\generated-report\index.html`
