package definitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver CHROME_DRIVER;

    public static WebDriver getChromeDriver() {
        return CHROME_DRIVER;
    }

    public static void setChromeDriver(WebDriver chromeDriver) {
        Hooks.CHROME_DRIVER = chromeDriver;
    }




    @Before
    public void setUP() {

        System.out.println("-----******************** INICIO DE TEST ********************-----");

        switch(SystemEnvironmentVariables.createEnvironmentVariables().getProperty("webdriver.driver")){
            case "chrome":
                System.setProperty("webdriver.chrome.driver",SystemEnvironmentVariables.createEnvironmentVariables().getProperty("webdriver.chrome.driver"));
                iniciarChrome();
                break;





        }

        Serenity.setSessionVariable("chromeDriver").to(CHROME_DRIVER);

    }
    @After
    public void tearDown(){
        CHROME_DRIVER.close();
    }
    private void iniciarChrome(){

        CHROME_DRIVER = new ChromeDriver();
        CHROME_DRIVER.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        CHROME_DRIVER.manage().window().maximize();
        CHROME_DRIVER.get("https://test-qa.inlaze.com/auth/sign-in");
    }

    public static WebDriver getDriver(){
        return CHROME_DRIVER;
    }

}
