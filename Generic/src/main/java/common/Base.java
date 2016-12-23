package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by Imdadul  on 8/21/2016.
 */
public class Base {


    public WebDriver driver = null;
    private WebElement element = null;
    String currentURL = null;
    String newURL = null;

    //    public static Logger logger = LogManager.getLogger(Base.class);
    @Parameters({"browserName", "url", "username", "domain", "password"})
    @BeforeMethod
    public void setUp(@Optional("firefox") String browserName, @Optional("http://www.cnn.com") String url, @Optional("superadmin") String username, @Optional("") String domain, @Optional("freestor") String password) {

        System.out.print(System.getProperty("user.dir"));
        getLocalDriver(browserName);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        login(username, domain, password);
        driver.manage().window().maximize();
        currentURL = driver.getCurrentUrl();
    }

    public WebDriver getLocalDriver(String browserName) {
        System.out.println(System.getProperty("user.dir"));
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\selenium-browser-driver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\selenium-browser-driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/browser-driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/browser-driver/MicrosoftWebDriver.exe");
            driver = new EdgeDriver();
        }
        // System.out.println(System.getProperty("user.dir"));
        return driver;

    }

    @AfterMethod
    public void cleanUp() {
        // driver.quit();
    }

    public void login(String username, String domain, String password) {
        typeByCss("input[placeholder=Username]",username);
        typeByCss("input[placeholder=Domain]",domain);
        typeByCssNEnter("input[placeholder=Password]",password);
    }

    public void clickByCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }
    public void clickByCss(WebElement selector, String locator) {
        selector.findElement(By.cssSelector(locator)).click();
    }

    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void typeByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeByCssNEnter(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }

    public void typeByNameNEnter(String locator, String value) {
        driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
    }

    public void typeByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void takeEnterKeys(String locator) {
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }

    public void clearInputField(String locator) {
        driver.findElement(By.cssSelector(locator)).clear();
    }

    public List<WebElement> getListOfWebElementsById(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));
        return list;
    }

    public List<WebElement> getListOfWebElementsByCss(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.cssSelector(locator));
        return list;
    }

    public List<WebElement> getListOfWebElementsByTag(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.tagName(locator));
        return list;
    }
    public List<WebElement> getListOfWebElementsByXpath(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.tagName(locator));
        return list;
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public String getTextByCss(String locator) {
        String st = driver.findElement(By.cssSelector(locator)).getText();
        return st;
    }

    public String getTextByXpath(String locator) {
        String st = driver.findElement(By.xpath(locator)).getText();
        return st;
    }

    public String getTextById(String locator) {
        return driver.findElement(By.id(locator)).getText();
    }

    public String getTextByName(String locator) {
        String st = driver.findElement(By.name(locator)).getText();
        return st;
    }

    public List<String> getListOfString(List<WebElement> list) {
        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getAttribute("title"));
        }
        return items;
    }

    public void selectOptionByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public void sleepFor(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    public void mouseHoverByCSS(String locator) {
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);

        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    public void mouseHoverByXpath(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    //handling Alert
    public void okAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    //iFrame Handle
    public void iframeHandle(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow() {
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator) {
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    //Taking Screen shots
    public void takeScreenShot() throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("screenShots.png"));
    }

    //Synchronization
    public void waitUntilClickAble(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilSelectable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public void upLoadFile(String locator, String path) {
        driver.findElement(By.cssSelector(locator)).sendKeys(path);
        /* path example to upload a file/image
           path= "C:\\Users\\rrt\\Pictures\\ds1.png";
         */
    }

    public List<WebElement> getListOfWebElementsById_Element(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = element.findElements(By.id(locator));
        return list;
    }

    public List<WebElement> getListOfWebElementsByCss_Element(WebElement element, String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = element.findElements(By.cssSelector(locator));
        return list;
    }
    public List<WebElement> getListOfWebElementsByXpath_Element(WebElement element, String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = element.findElements(By.xpath(locator));
        return list;
    }
    public List<WebElement> getListOfWebElementsByTag_Element(WebElement element, String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = element.findElements(By.tagName(locator));
        return list;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }

    public WebElement getElement() {
        return this.element;
    }

    public List<WebElement> menuBar() {
        List<WebElement> navList = driver.findElements(By.cssSelector(".nav.navbar-nav li"));
        return navList;
    }

    public void controlBar(String topMenu, String subMenu) {
        List<WebElement> menuList = driver.findElements(By.cssSelector(".nav.navbar-nav li"));
        System.out.println("controlBar () --top Menu : " + topMenu + " subMenu " + subMenu);
        for (WebElement item : menuList) {
            if (topMenu.equalsIgnoreCase(item.getText())) {
                System.out.println("controlBar () --top Menu inner: " + item.getText());
                item.click();
                newURL = driver.getCurrentUrl();
                if (topMenu.equalsIgnoreCase("manage"))
                    upDateURL();
                else {
                    getListOfWebElementsByCss(item, subMenu);
                    upDateURL();
                }
                break;
            }
        }
        // Need to through an exception when subMenu is not available
    }

    public void getListOfWebElementsByCss(WebElement element, String locator) {
        try {
            List<WebElement> list = element.findElements(By.cssSelector(".ng-binding.ng-scope"));
            findItemOnlist(list, locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element Not Found");
        }

    }

    public void manageNavigation(String tabName) {
        WebElement temp = driver.findElement(By.xpath("html/body/div[1]/div/div[2]/div/div[1]/div[1]/ul"));
        List<WebElement> navList = temp.findElements(By.cssSelector("li"));
        findItemOnlist(navList, tabName);
    }

    public void upDateURL() {
        newURL = driver.getCurrentUrl();

        System.out.println("upDateURL() --current url :" + currentURL);
        System.out.println("upDateURL() --new url :" + newURL);
        if (!newURL.equalsIgnoreCase(currentURL)) {
            currentURL = newURL;
            driver.navigate().to(newURL);

        }
    }

    public void findItemOnlist(List<WebElement> listOfItem, String findme) {
        for (WebElement item : listOfItem) {
            if (item.getText().equalsIgnoreCase(findme)) {
                System.out.println("findItemOnlist() --Requested Dropdown List: " + item.getText());
                item.click();
                break;
            }
        }
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void manageNavigationWith(String actionType) throws InterruptedException {


        controlBar("Manage", "");
        WebElement manageVdev= driver.findElement(By.cssSelector(".panel.panel-default.ng-scope[ng-controller=ManageVirtualDevicesCtrl]"));

        WebElement temp = null;

       /* for (int i = 0; i <= 3; i++) {
            try {
                manageVdev= driver.findElement(By.cssSelector(".panel.panel-default.ng-scope[ng-controller=ManageVirtualDevicesCtrl]"));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            manageVdev= driver.findElement(By.cssSelector(".panel.panel-default.ng-scope[ng-controller=ManageVirtualDevicesCtrl]"));
        }*/
        manageVdev= driver.findElement(By.cssSelector(".panel.panel-default.ng-scope[ng-controller=ManageVirtualDevicesCtrl]"));

        List<WebElement> actionItemList = manageVdev.findElements(By.cssSelector("button"));
        //mouse hover each element to get the correct hover message
        // Text box field, where we mouse hover
        sleepFor(2);
        for (WebElement item : actionItemList) {
           // if (item.isEnabled()) {
               System.out.println(item.getText());
                Actions action = new Actions(driver);
                action.click(item).build().perform();

                sleepFor(2);
                driver.switchTo().activeElement();
            modalFooter("cancel");
             //   action.moveToElement(item).build().perform();
            //    String toolTipText = item.getAttribute("bs-tooltip");
           //     System.out.println(toolTipText);
          //  }
        }
    }

    public void settings(String actionItem) throws InterruptedException {
        //First go to manage->Setting page
        manageNavigation("Settings");
        //Then find out all the setting options
        // ".title.ng-binding
        sleepFor(2);
        //waitUntilVisible(By.xpath("html/body/div[1]/div/div[2]/div/div[2]/div/div"));
        WebElement setttingsBox = driver.findElement(By.xpath("html/body/div[1]/div/div[2]/div/div[2]/div/div"));
        List<WebElement> tempItemList = getListOfWebElementsByCss_Element(setttingsBox, ".title.ng-binding");
        printList(tempItemList);
        findItemOnlist(tempItemList,actionItem);
    }

    public void printList(List<WebElement> listOfItem) {
        for (WebElement item : listOfItem) {
                System.out.println("printList() --List: " + item.getText()+ " text : " + item.getAttribute("text"));
            }
        }

    public void goTo(String topTab){
        controlBar(topTab, "");
    }

    public void goTo(String topTab, String dropdown){
        controlBar(topTab,dropdown);
    }

    public void goTo(String topTab, String dropdown, String lowerTab, String actionItem) throws InterruptedException {
        controlBar(topTab,dropdown);
        manageNavigation(lowerTab);

        // Special Case to display setting
        if ((topTab.equalsIgnoreCase("manage")) && (lowerTab.equalsIgnoreCase("settings"))){
            settings(actionItem);
        }
    }

    public void modalFooter(String selection){
        WebElement temp = driver.findElement(By.cssSelector(".modal-footer"));
       List<WebElement> buttonList = getListOfWebElementsByXpath_Element(temp,"//button");
        findItemOnlist(buttonList, selection);
      //  printList(tempList); clickByXpath(temp,"//button[@text='Cancel']");





      /*  if (selection.equalsIgnoreCase("cancel"))
            clickByXpath("//button[@type='button']");
        else
            clickByXpath("//button[@type='submit' and @value='selection']");
*/
    }

    private void clickByXpath(WebElement element, String locator) {
        element.findElement(By.xpath(locator)).click();

    }

    public void serverList(String selectedServer){
        controlBar("Manage", "");
        WebElement arrayInfo = driver.findElement(By.cssSelector(".array-info1"));
        List<WebElement> serverList = getListOfWebElementsByXpath_Element(arrayInfo,"//button");
        printList(serverList);
        findItemOnlist(serverList,selectedServer);

    }
}


//setting : .title.ng-binding

