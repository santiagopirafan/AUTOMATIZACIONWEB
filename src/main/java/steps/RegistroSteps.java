package steps;
import org.springframework.util.Assert;
import pages.RegistroPages;
import utils.MetodosGenericos;

public class RegistroSteps  {

    public static boolean registroOk = false;

    RegistroPages registopages = new RegistroPages();

    static MetodosGenericos mg = new MetodosGenericos();

    String ingnombre = "";
    String ingemail = "";
    String ingpassword = "";



    public void clickregistro() {
        registopages.clicksing();
        mg.tomaEvidenciaPC("inicio de registro");

    }



    public void ingresardatos(String nombre, String email, String contraseña) {
        ingnombre = nombre;
        ingemail = email;
        ingpassword= contraseña;
        registopages.name(nombre);
        registopages.correo(email);
        registopages.passoword(contraseña);
        registopages.confpassword(contraseña);
        mg.tomaEvidenciaPC("registro de datos");






    }

    public void clickboton() {
        registopages.botonsing();
        mg.tomaEvidenciaPC("exitoso");


    }
}
