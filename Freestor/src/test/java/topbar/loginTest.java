package topbar;
import common.Base;
import org.testng.annotations.Test;

/**
 * Created by imdadul.hoq on 12/6/2016.
 */
public class loginTest extends Base {
    @Test
    public void Test() throws InterruptedException {
        controlBar("Monitor","client view");
        controlBar("Administration","AD/LDAP Settings");
        controlBar("Administration","Users");
        manageNavigation("clients");
        manageNavigation("Settings");
        manageNavigation("clients");
        manageNavigation("Settings");
        manageNavigation("clients");
        manageNavigation("Settings");
        controlBar("Administration","Servers");
     }
}

