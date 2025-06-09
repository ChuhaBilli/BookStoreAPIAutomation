package com.runners;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/resources/features", 
	    glue = "com.stepsDef", 
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports/cucumber-pretty.html", 
	        "json:target/cucumber-reports/cucumber.json" 
	    },
	    monochrome = true,
	    snippets = CucumberOptions.SnippetType.CAMELCASE,
	    dryRun = false
	    //tags = "@sanity" // pass the tags from maven command line
		)

public class TestRunnerJUnit4 {
	

}

