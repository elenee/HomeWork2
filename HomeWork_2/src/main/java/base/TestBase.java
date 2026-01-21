package base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import utils.DriverFactory;
public class TestBase {
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}

