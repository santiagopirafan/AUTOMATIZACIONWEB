package definitions;

import cucumber.api.java.en.*;
import steps.RegistroSteps;

public class RegistroInlazeDefinitions {

    RegistroSteps registroSteps = new RegistroSteps();


    @Given("^el usuario hace click en registro$")
    public void elUsuarioHaceClickEnRegistro() {
        registroSteps.clickregistro();

    }

    @When("^el usuario ingresa datos \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void elUsuarioIngresaDatos(String nombre, String email, String contraseña) {
        registroSteps.ingresardatos(nombre, email, contraseña);

    }


    @Then("^el usuario finaliza el registro$")
    public void elUsuarioFinalizaElRegistro() {
        registroSteps.clickboton();

    }

    }

