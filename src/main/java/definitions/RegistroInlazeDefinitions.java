package definitions;

import cucumber.api.java.en.*;
import org.springframework.util.Assert;
import steps.RegistroSteps;

import static steps.RegistroSteps.registroOk;

public class RegistroInlazeDefinitions {

    RegistroSteps registroSteps = new RegistroSteps();


    @Given("^el usuario hace click en registro$")
    public void elUsuarioHaceClickEnRegistro() {
        registroSteps.clickregistro();
        Assert.assertTrue(registroOk);

    }

    @When("^el usuario ingresa datos \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void elUsuarioIngresaDatos(String nombre, String email, String contraseña) {
        registroSteps.ingresardatos(nombre, email, contraseña);
        org.junit.Assert.assertTrue(registroOk);

    }


    @Then("^el usuario finaliza el registro$")
    public void elUsuarioFinalizaElRegistro() {
        registroSteps.clickboton();
        org.junit.Assert.assertTrue(registroOk);


    }

    }

