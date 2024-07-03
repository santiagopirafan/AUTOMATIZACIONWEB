package steps;
import pages.RegistroPages;

public class RegistroSteps  {



    RegistroPages registopages = new RegistroPages();

    String ingnombre = "";
    String ingemail = "";
    String ingpassword = "";



    public void clickregistro() {
        registopages.clicksing();
    }



    public void ingresardatos(String nombre, String email, String contraseña) {
        ingnombre = nombre;
        ingemail = email;
        ingpassword= contraseña;
        registopages.name(nombre);
        registopages.correo(email);
        registopages.passoword(contraseña);
        registopages.confpassword(contraseña);



    }

    public void clickboton() {
        registopages.botonsing();
    }
}
