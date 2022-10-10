import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber.html",
                "junit:target/cucumber.xml",
                "json:target/cucumber-report.json"
        },
        //dryRun = true,
        //stepNotifications = true,
        //monochrome = true,
        features = {"src/test/resources/offer"},
        glue = {"com.lottoland.offer"})
public class RunTest {}
