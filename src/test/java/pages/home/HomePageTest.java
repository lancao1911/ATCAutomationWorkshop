package pages.home;

import com.autotrader.consumersite.ConsumerSite;
import com.autotrader.consumersite.FeatureFlagManager;
import com.autotrader.pages.home.HomePage;
import com.autotrader.pages.home.modules.HeroBadgeModule;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by nghiatrongtran on 06/07/2017.
 */
public class HomePageTest {

    @Test
    public void shouldMakeModelPriceWorkProperly(){
        String make = "Audi";
        String model = "4000";
        String price = "Under $6,000";

        SoftAssert softAssert = new SoftAssert();

        ConsumerSite consumerSite = new ConsumerSite(FeatureFlagManager.FeatureFlag.SPDY_HP);
        HomePage homePage = consumerSite.getHomePage();
        homePage.navigateTo();

        HeroBadgeModule heroBadgeModule = homePage.getHeroBadgeModule();
        heroBadgeModule.selectMakeDropdown(make);
        heroBadgeModule.selectModelDropdown(model);
        heroBadgeModule.selectPriceDropdown(price);

        Assert.assertEquals(heroBadgeModule.getMakeSelectedDropdownText(), make, "Make is not selected");
        Assert.assertEquals(heroBadgeModule.getModelSelectedDropdownText(), model, "Model is not selected");
        Assert.assertEquals(heroBadgeModule.getPriceSelectedDropdownText(), price, "Price is not selected");

        softAssert.assertEquals(heroBadgeModule.getMakeSelectedDropdownText(), make, "Make is not selected");
        softAssert.assertEquals(heroBadgeModule.getModelSelectedDropdownText(), model, "Model is not selected");
        softAssert.assertEquals(heroBadgeModule.getPriceSelectedDropdownText(), price, "Price is not selected");
        softAssert.assertAll();
    }
}
