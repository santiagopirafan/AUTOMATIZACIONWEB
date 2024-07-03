package definitions;

import com.ibm.icu.impl.InvalidFormatException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Cronometro;
import utils.Evidencias;
import utils.ScreenShootsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static utils.MetodosGenericos.finalizarTest;

public class Hooks {
    Cronometro cronometro = new Cronometro();
    Evidencias evidencias = new Evidencias();

    public static WebDriver CHROME_DRIVER;

    private static WebDriver driver;


    public static WebDriver getChromeDriver() {
        return CHROME_DRIVER;
    }

    public static void setChromeDriver(WebDriver chromeDriver) {
        Hooks.CHROME_DRIVER = chromeDriver;
    }




    @Before
    public void setUP(Scenario scenario) {
        Serenity.setSessionVariable("scenario").to(scenario);

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
    public void finalTest() throws InterruptedException, IOException {
        String tiempoPrueba = cronometro.pararCronometro();
        ScreenShootsUtils screenS = new ScreenShootsUtils();

        Scenario scenario = Serenity.sessionVariableCalled("scenario");
        String featureTag = scenario.getSourceTagNames().iterator().next();
        String nombreScenario = scenario.getName();
        boolean scenarioOk = false;

        if(scenario.isFailed()){
            finalizarTest(featureTag, scenarioOk, nombreScenario);

        }else{
            scenarioOk = true;
            finalizarTest(featureTag, scenarioOk, nombreScenario);
        }
        try {
            // reporte word
            evidencias.generarReporte(System.getProperty("RutaEvidencias"), scenario.getName(), scenario.getStatus().toString(),tiempoPrueba);
        }catch (Exception e){
            e.printStackTrace();
        }

        driver.quit();
        ScreenShootsUtils.numeroScreen=1;
    }

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
