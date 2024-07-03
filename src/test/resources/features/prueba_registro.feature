Feature: como usuario de inlaze quiero ingresar al aplicativo para realizar un registro

  @CP1
  Scenario Outline: registrar un usuario con nombre, email y contraseña válidos
    Given el usuario hace click en registro
    When el usuario ingresa datos <Nombre> <Email> <Contraseña>
    Then el usuario finaliza el registro

    Examples:
      | Nombre           | Email                   | Contraseña    |
      | "santiago pirafan"| "santipirafan1@gmail.com" | "Colombia2024+" |
