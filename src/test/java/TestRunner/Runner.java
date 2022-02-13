package TestRunner;

import io.cucumber.testng.*;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinition",
        plugin = {"pretty","html:Report/cucumber-pretty.html","com.epam.reportportal.cucumber.ScenarioReporter"}
       // ,tags = "@SearchPageValidation"
)
public class Runner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
