/*
 * Copyright 2011-2013 Hippo B.V. (http://www.onehippo.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onehippo.gogreen;

import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public abstract class GoGreenTest {

    protected static Selenium selenium;
    private static WebDriver driver;

    @BeforeSuite
    @Parameters("driver.url")
    public void startSelenium(@Optional("http://localhost:8080") String url) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("extensions.checkCompatibility.5.0", false);
        driver = new FirefoxDriver(profile);

        selenium = new WebDriverBackedSelenium(driver, url);
    }

    @AfterSuite
    public void stopSelenium() {
        driver.quit();
    }

}
