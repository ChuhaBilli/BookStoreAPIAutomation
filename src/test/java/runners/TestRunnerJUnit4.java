package runners;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"src/test/java/stepsDef"},
        tags = "@xx",
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "progress",
                "summary"
        }
)
public class TestRunnerJUnit4 {
	
	@BeforeAll
	public static void beforeAll() {
		
	}
	
	@AfterAll
	public static void afterAll() {
		
	}
}

