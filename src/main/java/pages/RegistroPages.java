package pages;

import definitions.Hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class RegistroPages {

    WebDriver chromeDriver = Hooks.getChromeDriver();

    static By btn_registro = By.xpath("//a[@class='font-bold text-primary']");
    static By name_locator = By.xpath("//input[@id='full-name']");
    static By email_locator = By.xpath("//input[@id='email']");
    static By password_locator = By.xpath("//input[@id='password']");
    static By confirm_password = By.xpath("//input[@id='confirm-password']");
    static By btn_sing = By.xpath("//button[@class='btn btn-primary']");




    public void clicksing() {


        chromeDriver.findElement(btn_registro).click();

        try {
            Thread.sleep(3000);

        }catch (Exception e){}


    }

    public void name(String nombre) {
        chromeDriver.findElement(name_locator).sendKeys(nombre);

        try {
            Thread.sleep(3000);

        }catch (Exception e){}


    }

    public void correo(String email) {
        chromeDriver.findElement(email_locator).sendKeys(email);

        try {
            Thread.sleep(3000);

        }catch (Exception e){}
    }

    public void passoword(String contraseña) {
        chromeDriver.findElement(password_locator).sendKeys(contraseña);

        try {
            Thread.sleep(3000);

        }catch (Exception e){}

    }


    public void confpassword(String contraseña) {
        chromeDriver.findElement(confirm_password).sendKeys(contraseña);

        try {
            Thread.sleep(3000);

        }catch (Exception e){}
    }

    public void botonsing() {
        chromeDriver.findElement(btn_sing).click();
        try {
            Thread.sleep(3000);
        }catch (Exception e){}
    }
}
