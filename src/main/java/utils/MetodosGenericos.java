package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class MetodosGenericos {

    static ScreenShootsUtils screenS = new ScreenShootsUtils();
    static Properties properties = new Properties();
    static final Logger logger = (Logger) LogManager.getLogger(MetodosGenericos.class);

    public MetodosGenericos() {
        super();
    }

    public void tomaEvidenciaPC(String strDetalle) {
        try {
            Thread.sleep(500);
            screenS.capturaDispositivoPC(strDetalle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void finalizarTest(String featureTag, Boolean scenarioOk, String nombreScenario) throws IOException {
        String pathScenarioScreenShoot = "";
        FileInputStream fileInputStream = new FileInputStream("rutas.properties");
        properties.load(fileInputStream);

        if(!scenarioOk){
            pathScenarioScreenShoot = properties.getProperty("scenario.fail.path");
            logger.error("❌ ❌ ❌ ❌ ❌ El resultado del test {} es: FALLIDO ❌ ❌ ❌ ❌ ❌", featureTag);
            logger.error("Puede ver la imagen del error en la ruta: {}", pathScenarioScreenShoot);
            screenS.capturaDispositivoPC("Se presento una falla");
        }else{
            pathScenarioScreenShoot = properties.getProperty("scenario.passed.path");
            logger.info("✔️✔️✔️✔️✔️TEST PASSED          ✔️✔️✔️✔️✔️");
            logger.info("Puede ver la imagen de la pantalla final en la ruta: {}", pathScenarioScreenShoot);
        }
        fileInputStream.close();
        //  screenS.capturarFinTest(featureTag, scenarioOk, nombreScenario, pathScenarioScreenShoot);
    }

    public static String CalcularFechaActual(){

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = formato.format(fechaActual);

        return fecha;
    }

    public static int generarNumeroAleatorio(int maximo){
        Random rand = new Random();
        return rand.nextInt(maximo);
    }

    public static String generarDocumento(int cantidad){
        Random rand = new Random();
        StringBuilder numAleatorio = new StringBuilder();

        for(int i = 0; i < cantidad; i++){
            numAleatorio.append(rand.nextInt(cantidad));
        }

        return numAleatorio.toString();
    }

    public static String generarTexto(int maximo){
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVXXYZ0123456789 ";

        for(int i = 0; i <= maximo; i++){
            int indiceAleatorio = rand.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indiceAleatorio);
            sb.append(caracterAleatorio);
        }
        return sb.toString();
    }

    public static String horaActualOutlook(){
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatoMilitar = DateTimeFormatter.ofPattern("HH:mm:ss");
        String hora = horaActual.format(formatoMilitar);
        String[] horaSinSegundos = hora.split(":");
        hora = horaSinSegundos[0] + ":" + horaSinSegundos[1];
        return hora;
    }

    public static int calcularEdad(String fechaNacimiento){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento, formatter);
        LocalDate fechaActual = LocalDate.now();

        int edad = fechaActual.getYear() - fechaNac.getYear();

        if(fechaNac.getDayOfYear() > fechaActual.getDayOfYear()){
            edad--;
        }
        return edad;
    }

}
