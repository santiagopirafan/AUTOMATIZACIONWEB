package utils;

import com.google.common.io.Files;
import definitions.Hooks;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ScreenShootsUtils {
    static Evidencias evidencias = new Evidencias();

    public static int numeroScreen = 1;
    static int contador = 0;

    WebDriver driver;
    static WebDriver driverOutlook;

    static Properties properties = new Properties();
    static final Logger logger = (Logger) LogManager.getLogger(ScreenShootsUtils.class);


    public void capturarFinTest(String featureTag, Boolean scenarioOk, String nombreScenario, String pathScenarioScreenShoot) throws IOException {
        File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f, new File(
                pathScenarioScreenShoot + featureTag.replace("@","").
                        toString()+ " " + nombreScenario+".jpg")
        );
    }

    public void capturaDispositivoPC(String descripcion) throws IOException {
        driver = Hooks.getDriver();
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(
                System.getProperty("RutaEvidencias") + File.separator + numeroScreen + "_" + descripcion + ".png"
        );

        evidencias.NombreImagen= numeroScreen + "_"+ descripcion ;
        evidencias.NombreImage[contador] = evidencias.NombreImagen;
        FileUtils.copyFile(srcFile, targetFile);
        numeroScreen++;
        contador++;
    }


}
