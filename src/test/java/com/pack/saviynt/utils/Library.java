package com.pack.saviynt.utils;

import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.pack.saviynt.stepdefs.StepDefs;

/**
 * The Class Library.
 */
public class Library {

    /** The logger. */
    static Logger logger = Logger.getLogger(Library.class.getName());

    /**
     * Click.
     * @param objectName the object name
     * @throws Exception the exception
     */
    public static void click(String objectName) throws Exception {
        getElement(objectName).click();
    }

    /**
     * Enter text.
     * @param objectName the object name
     * @param text the text
     * @throws Exception the exception
     */
    public static void enterText(String objectName, String text) throws Exception {
        getElement(objectName).sendKeys(text);
    }

    /**
     * Gets the property.
     * @param objectName the object name
     * @param property the property
     * @return the property
     * @throws Exception the exception
     */
    public static String getProperty(String objectName, String property) throws Exception {
        if (StringUtils.equalsAnyIgnoreCase(property, "Text")) {
            return getElement(objectName).getText();
        } else {
            return getElement(objectName).getAttribute(property);
        }
    }

    /**
     * Gets the element.
     * @param objectName the object name
     * @return the element
     * @throws Exception the exception
     */
    public static WebElement getElement(String objectName) throws Exception {
        boolean elementFound = true;
        objectName = objectName.trim().toUpperCase();
        String xpath = ObjectReader.getProperty(objectName + "_XPATH");
        String htmlId = ObjectReader.getProperty(objectName + "_HTMLID");
        String cssSelector = ObjectReader.getProperty(objectName + "_CSS_SELECTOR");
        String name = ObjectReader.getProperty(objectName + "_NAME");
        String className = ObjectReader.getProperty(objectName + "_CLASSNAME");
        String linkText = ObjectReader.getProperty(objectName + "_LINKTEXT");
        String partialLinkText = ObjectReader.getProperty(objectName + "_PARTIAL_LINKTEXT");

        if (!StringUtils.isEmpty(xpath) && !StringUtils.isEmpty(htmlId) && !StringUtils.isEmpty(cssSelector)
                && !StringUtils.isEmpty(name) && !StringUtils.isEmpty(className) && !StringUtils.isEmpty(linkText)
                && !StringUtils.isEmpty(partialLinkText)) {
            throw new Exception("None of the Object properties has been specified");
        }

        if (!StringUtils.isEmpty(xpath)) {
            try {
                logger.info("Driver is trying to find the object using Xpath property");
                return StepDefs.driver.findElement(By.xpath(xpath));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound && !StringUtils.isEmpty(htmlId)) {
            try {
                logger.info("Driver is trying to find the object using Id property");
                return StepDefs.driver.findElement(By.id(htmlId));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound && !StringUtils.isEmpty(cssSelector)) {
            try {
                logger.info("Driver is trying to find the object using CSS Selector property");
                return StepDefs.driver.findElement(By.cssSelector(cssSelector));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound && !StringUtils.isEmpty(name)) {
            try {
                logger.info("Driver is trying to find the object using Name property");
                return StepDefs.driver.findElement(By.name(name));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound && !StringUtils.isEmpty(className)) {
            try {
                logger.info("Driver is trying to find the object using Class Name property");
                return StepDefs.driver.findElement(By.className(className));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound && !StringUtils.isEmpty(linkText)) {
            try {
                logger.info("Driver is trying to find the object using Link Text property");
                return StepDefs.driver.findElement(By.linkText(linkText));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound && !StringUtils.isEmpty(partialLinkText)) {
            try {
                logger.info("Driver is trying to find the object using Partial Link Text property");
                return StepDefs.driver.findElement(By.partialLinkText(partialLinkText));
            } catch (Exception e) {
                elementFound = false;
            }
        }

        if (!elementFound) {
            throw new Exception("Object could not be found with any given properties.");
        }
        return null;
    }
}
