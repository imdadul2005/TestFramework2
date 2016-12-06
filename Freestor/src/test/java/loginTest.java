import common.*;
import org.testng.annotations.Test;

/**
 * Created by imdadul.hoq on 12/6/2016.
 */
public class loginTest extends Base {
    @Test
    public void Test(){
        typeByXpath("html/body/div[1]/div/div[2]/form/fieldset/div[1]/input","superadmin");
        typeByCssNEnter(".form-control.ng-pristine.ng-untouched.ng-invalid.ng-invalid-required","freestor");
    }

}
