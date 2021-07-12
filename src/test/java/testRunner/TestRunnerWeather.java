package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
/*
Author: Suraj Salunkhe
Date:12th July 2021
*/
@RunWith(Cucumber.class)
@CucumberOptions(features = { "./src/test/resources/featureFiles/WeatherReportComparator.feature" },
				 glue = {"stepDefination", "appHooks" },
				 plugin = { "pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, 
				 dryRun = false
)
public class TestRunnerWeather {

}
