package topbar;
import common.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Created by imdadul.hoq on 12/6/2016.
 */
public class loginTest extends Base {
    @Test
    public void Test(){
        setElement(driver.findElement(By.cssSelector(".nav.navbar-nav")));
        List<WebElement> navList = getListOfWebElementsByTag_Element("li");
        System.out.println(getListOfString(navList));
    }
}

