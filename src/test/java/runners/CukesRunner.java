package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = "json:target/cucumber.json",
        features = "src/test/resources/features",
        glue = "stepDefs",
        dryRun = false,
        tags = "@e2e"
)
public class CukesRunner {
}