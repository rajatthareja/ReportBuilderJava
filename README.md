# ReportBuilderJava
[![Build status](https://travis-ci.org/rajatthareja/ReportBuilderJava.svg?branch=master)](https://travis-ci.org/rajatthareja/ReportBuilderJava)
[![Maven Central](https://img.shields.io/maven-central/v/com.rajatthareja/reportbuilder.svg)](https://mvnrepository.com/artifact/com.rajatthareja/reportbuilder/1.0.2)

Java API to merge Cucumber JSON reports and build HTML Test Report

## Sample Reports

[View Sample Report](http://reportbuilderjava.rajatthareja.com/sample/report.html)

## Include Report Builder in your project

Maven dependency for Report Builder:

```xml

<dependency>
    <groupId>com.rajatthareja</groupId>
    <artifactId>reportbuilder</artifactId>
    <version>1.0.2</version>
</dependency>

```

## Build your test report

```java

import com.rajatthareja.reportbuilder.ReportBuilder;
import com.rajatthareja.reportbuilder.Color;
import org.json.JSONObject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class MyClass {
    
    public static void main(String ... args) throws Exception{
        // Create ReportBuilder Object
        ReportBuilder reportBuilder = new ReportBuilder();
        
        // Set output Report Dir 
        reportBuilder.setReportDirectory("output/");
        
        // Set output report file name
        reportBuilder.setReportFileName("test_results");
        
        // Set Report Title
        reportBuilder.setReportTitle("My Test Report");
        
        // Set Report Color for more visit http://materializecss.com/color.html
        reportBuilder.setReportColor(Color.PURPLE);
        
        // Add additional info for Report
        reportBuilder.setAdditionalInfo("Environment", "My Environment");

        // Create list or report Files or Directories or URLs or JSONObject or JSONString
        List<Object> cucumberJsonReports = new ArrayList<>();
        cucumberJsonReports.add(new File("my/report/path/report.json"));
        cucumberJsonReports.add(new File("myReportDir/"));
        cucumberJsonReports.add(new URL("http://myReportUrl/report.json"));
        cucumberJsonReports.add(new JSONObject("report Json String"));
        
        // Build your report
        reportBuilder.build(cucumberJsonReports);
    }
}

```

**Note:** Java 8 is required.

[View Java Docs](https://oss.sonatype.org/service/local/repositories/releases/archive/com/rajatthareja/reportbuilder/1.0.2/reportbuilder-1.0.2-javadoc.jar/!/index.html) for more details.

## Report Builder Ruby Gem

[Report Builder](http://reportbuilder.rajatthareja.com)

## Contributing

We're open to any contribution. It has to be tested properly though.

## License

Copyright (c) 2018 [MIT LICENSE](https://github.com/rajatthareja/ReportBuilderJava/blob/master/LICENSE)
