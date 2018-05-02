package com.ca.rs;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json" ,"junit:shippable/testresults/cucumber-results.xml"},
        tags = {"@regression"},
        features={"src/test/resources/com/ca/rs/features"},
        glue = {"com/ca/rs/"}
        )
public class RunCukesTest {
}
