package topbar;
import common.Base;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by imdadul.hoq on 12/6/2016.
 */
public class loginTest extends Base {
    @Test
    public void Test() throws InterruptedException {
        manageNavigation("virtual devices");
        driver.findElement(By.xpath("html/body/div[1]/div/div[2]/div/div[2]/div/div/div[2]/div/div/div[1]/div[2]/div/div[1]/div/div[1]/div[1]/div[1]/div[2]/span[2]/input")).click();
        manageNavigationWith("Create");

     }
}

