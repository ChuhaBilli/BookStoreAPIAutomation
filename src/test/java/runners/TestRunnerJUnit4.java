package runners;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "src/test/resources/features", // Path to your feature files
	    glue = "stepsDef", // Package where your step definitions are
	    plugin = {
	        "pretty",
	        "html:target/cucumber-reports/cucumber-pretty.html", // Standard HTML report (optional, but good for quick view)
	        "json:target/cucumber-reports/cucumber.json" // IMPORTANT: JSON report for the Maven plugin
	    },
	    monochrome = true,
	    snippets = CucumberOptions.SnippetType.CAMELCASE,
	    dryRun = false,
	    tags = "@test" // Example tag
		)

public class TestRunnerJUnit4 {
	
	@BeforeAll
	public static void beforeAll() {
		
	}
	
	@AfterAll
	public static void afterAll() {
		
	}
}

