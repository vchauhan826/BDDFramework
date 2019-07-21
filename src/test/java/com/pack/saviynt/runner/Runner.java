package com.pack.saviynt.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * The Class Runner.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features", glue = "com/pack/saviynt/stepdefs", format = { "pretty",
        "html:target/featuretest/test-output", "json:target/featuretest/json_output/cucumber.json",
        "junit:target/featuretest/junit_xml/cucumber.xml" }, monochrome = true, strict = true, dryRun = false, plugin = {
                "com.cucumber.listener.ExtentCucumberFormatter:target/featuretest/output/report.html" })

public class Runner {

}
