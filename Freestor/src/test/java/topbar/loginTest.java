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
       goTo("Manage","", "Groups","");
    // manageNavigation("Groups");
      //  serverList("FUJITSU-40");
     }
     @Test
     public void Test2() throws InterruptedException {
        goTo("Manage","", "virtual devices","");
         // manageNavigation("Groups");
         serverList("FUJITSU-40");
     }
}

