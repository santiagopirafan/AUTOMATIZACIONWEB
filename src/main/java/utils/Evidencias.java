package utils;

import com.ibm.icu.impl.InvalidFormatException;
import net.serenitybdd.core.Serenity;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTabStop;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Evidencias {
    int contador = 0;
    static XWPFDocument docx = new XWPFDocument();
    static XWPFRun run = docx.createParagraph().createRun();
    public static int numeroScreen = 1;
    public static int numDocs;
    public static String fechaYHoraEjecucion;

    Map<String, File> keyImagenes;
    public String NombreImagen;
    public String [] NombreImage = new String[8000];
    static WebDriver driver = Serenity.sessionVariableCalled("driver");

    public static void capturaPantalla(String descripcionImagen) {
        String screenshot_name = descripcionImagen + ".png";
        try {
            BufferedImage image = new Robot()
                    .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            File file = new File(
                    System.getProperty("RutaEvidencias") + File.separator + numeroScreen + "_" + screenshot_name);
            ImageIO.write(image, "png", file);
            InputStream pic = new FileInputStream(
                    System.getProperty("RutaEvidencias") + File.separator + numeroScreen + "_" + screenshot_name);
            numeroScreen++;
            run.addBreak();
            run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(350), Units.toEMU(350));
            pic.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generarReporte(String ruta, String nombreEscenario, String estadoCaso, String tiempoPrueba)
            throws InvalidFormatException, FileNotFoundException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        fechaYHoraEjecucion = formatDateInforme("yyyy-MM-dd_HH-mm-ss", new Date());

        XWPFDocument document = new XWPFDocument();

        XWPFParagraph paragraphSix = document.createParagraph();
        paragraphSix.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphFourRunOne = paragraphSix.createRun();
        paragraphFourRunOne.setBold(true);
        // paragraphFourRunOne.setUnderline(UnderlinePatterns.SINGLE);
        paragraphFourRunOne.setFontSize(15);
        paragraphFourRunOne.setFontFamily("Verdana");
        paragraphFourRunOne.setColor("000070");
        paragraphFourRunOne.addBreak();
        paragraphFourRunOne.setText("REPORTE DE EJECUCIÓN");/////////// step name or image name
        paragraphFourRunOne.addBreak();

        XWPFParagraph paragraphVersion = document.createParagraph();
        paragraphVersion.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun parrafoVersion = paragraphVersion.createRun();
        parrafoVersion.setBold(true);
        parrafoVersion.setFontSize(15);
        parrafoVersion.setFontFamily("Verdana");
        parrafoVersion.setColor("000070");
        parrafoVersion.setText("Intrallianz Integración (Regresiones 1.0.0)");/////////// step name or image name
        parrafoVersion.addBreak();

        XWPFParagraph paragraphOne = document.createParagraph();
        paragraphOne.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraph1RunOne = paragraphOne.createRun();
        paragraph1RunOne.setBold(true);
        paragraph1RunOne.setFontSize(14);
        paragraph1RunOne.setFontFamily("Verdana");
        paragraph1RunOne.setColor("000070");
        paragraph1RunOne.setText("-----------------------------------------------------------------");////////////////////
        // paragraph1RunOne.addBreak();

        XWPFParagraph paragraphTwo = document.createParagraph();
        paragraphTwo.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphOneRunOne = paragraphTwo.createRun();
        paragraphOneRunOne.setBold(true);
        paragraphOneRunOne.setFontSize(15);
        paragraphOneRunOne.setFontFamily("Verdana");
        paragraphOneRunOne.setColor("000070");
        paragraphOneRunOne.setText(nombreEscenario);/////////

        XWPFParagraph paragraphThree = document.createParagraph();
        paragraphThree.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphThreeRunOne = paragraphThree.createRun();
        paragraphThreeRunOne.setBold(false);
        paragraphThreeRunOne.setFontSize(12);
        paragraphThreeRunOne.setFontFamily("Verdana");
        paragraphThreeRunOne.setColor("000070");
        paragraphThreeRunOne.setText("" + new java.util.Date());////////////////////
        // paragraphThreeRunOne.addBreak();

        XWPFParagraph paragraphFour = document.createParagraph();
        paragraphFour.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphsixRunOne = paragraphFour.createRun();
        paragraphsixRunOne.setBold(true);
        paragraphsixRunOne.setFontSize(14);
        paragraphsixRunOne.setFontFamily("Verdana");
        paragraphsixRunOne.setColor("000070");
        paragraphsixRunOne.setText("Estado de Ejecución:");////////////////////

        if (estadoCaso.equals("PASSED")) {

            paragraphsixRunOne.setColor("0BA20B");
            paragraphsixRunOne.setText(" " + estadoCaso);////////////////////
        } else {
            paragraphsixRunOne.setColor("EF1111");
            paragraphsixRunOne.setText(" " + estadoCaso);////////////////////
        }

        // paragraphsixRunOne.addBreak();

        XWPFParagraph paragraphTime = document.createParagraph();
        paragraphTime.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraphTimeRunOne = paragraphTime.createRun();
        paragraphTimeRunOne.setBold(true);
        paragraphTimeRunOne.setFontSize(13);
        paragraphTimeRunOne.setFontFamily("Verdana");
        paragraphTimeRunOne.setColor("000070");
        paragraphTimeRunOne.setText("Tiempo de Ejecución:");////////////////////
        paragraphTimeRunOne.setColor("000070");
        paragraphTimeRunOne.setText(" " + tiempoPrueba);////////////////////

        XWPFParagraph paragraphFive = document.createParagraph();
        paragraphFive.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraph7RunOne = paragraphFive.createRun();
        paragraph7RunOne.setBold(true);
        paragraph7RunOne.setFontSize(14);
        paragraph7RunOne.setFontFamily("Verdana");
        paragraph7RunOne.setColor("000070");
        paragraph7RunOne.setText("-----------------------------------------------------------------");////////////////////
        paragraph7RunOne.addBreak();

        InputStream pic = null;

        File file = new File(ruta);
        File[] files = file.listFiles();
        keyImagenes = new HashMap<String, File>();
        for (File f : files) {
            if (f.getName().contains(".png")) {
                String posicion = f.getName().split("_")[0];
                keyImagenes.size();
                keyImagenes.put(posicion, f);
            }
        }

        for (int x = 1; x <= files.length; x++) {
            File f = keyImagenes.get(x + "");

            try {
                if (f.getName().contains(".png")) {
                    System.out.println(f.getName());
                    try {
                        pic = new FileInputStream(ruta + File.separator + f.getName());
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    try {

                        XWPFParagraph paragraphSeven = document.createParagraph();
                        paragraphSeven.setAlignment(ParagraphAlignment.CENTER);
                        XWPFRun paragraphThreeRunOne5 = paragraphSeven.createRun();
                        // paragraphThreeRunOne.setBold(true);
                        paragraphThreeRunOne5.setFontSize(12);
                        paragraphThreeRunOne5.setFontFamily("Verdana");
                        paragraphThreeRunOne5.setColor("000070");
                        paragraphThreeRunOne5.setText("-------------------");

                        paragraphThreeRunOne5.setText("Paso: " + f.getName().replaceAll(".png", ""));////////////////////
                        // paragraphThreeRunOne5.addBreak();
                        paragraphThreeRunOne5.setText("-------------------");
                        XWPFParagraph paragraphEight = document.createParagraph();
                        paragraphEight.setAlignment(ParagraphAlignment.CENTER);
                        XWPFRun paragraphFiveRunOne = paragraphEight.createRun();

                        int numeroImagen = Integer.parseInt(f.getName().split("_")[0]);

                        if (f.getName().contains("Web") || f.getName().contains("web")) {
                            paragraphFiveRunOne.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG,
                                    ruta + File.separator + f.getName(), Units.toEMU(500), Units.toEMU(300));
                        } else {
                            paragraphFiveRunOne.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG,
                                    ruta + File.separator + f.getName(), Units.toEMU(400), Units.toEMU(200));
                        }
                        if (!(f.getName().split("_")[0]).equals("1") && (numeroImagen % 2 == 0)) {
                            // paragraphEight.setPageBreak(true);
                            paragraphSeven.setPageBreak(true);
                        }

                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                pic.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        XWPFParagraph paragraph8 = document.createParagraph();
        paragraph8.setAlignment(ParagraphAlignment.LEFT);
        XWPFRun paragraph8RunOne = paragraph8.createRun();
        paragraph8RunOne.setBold(true);
        paragraph8RunOne.setFontSize(14);
        paragraph8RunOne.setFontFamily("Verdana");
        paragraph8RunOne.setColor("000070");
        paragraph8.setPageBreak(true);
        paragraph8RunOne.setText("---------------------------Caso finalizado---------------------");////////////////////

        // create header start
        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);

        XWPFHeader header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);

        XWPFParagraph paragraph = header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        CTTabStop tabStop = paragraph.getCTP().getPPr().addNewTabs().addNewTab();
        tabStop.setVal(STTabJc.RIGHT);
        int twipsPerInch = 1440;
        tabStop.setPos(BigInteger.valueOf(6 * twipsPerInch));

        XWPFRun run = paragraph.createRun();
        run.setText("Informe de evidencia");
        run.addTab();

        run = paragraph.createRun();

        String imgFile = "src/test/resources/imagenes/logoAllianz.png";
        run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(140),
                Units.toEMU(40));

        // create footer start
        XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

        paragraph = footer.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);

        run = paragraph.createRun();
        run.setText("ALLIANZ | NTT DATA | Confidencial");

        FileOutputStream outStream = null;
        try {

            outStream = new FileOutputStream(
                    ruta + File.separator + "Evidencias_" + nombreEscenario.split("_")[0].toString() + "_" + estadoCaso
                            + "_" + fechaYHoraEjecucion + ".docx");
        } catch (FileNotFoundException e) {
            System.out.println("First Catch");
            e.printStackTrace();
        }
        try {
            document.write(outStream);
            outStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Second Catch");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Third Catch");
            e.printStackTrace();
        }

        // to delete the images from folder
        numDocs = 0;
    }

    public static String homologarEstadoCaso(String estado) {
        String estadoHomologado = "";
        if (estado.toUpperCase().equals("PASSED")) {
            estadoHomologado = "Passed";
        } else {
            estadoHomologado = "Failed";
        }
        return estadoHomologado;
    }

    public void eliminarImagenes(String ruta) {
        File file = new File(ruta);
        File[] files = file.listFiles();

        for (File f : files) {
            if (f.getName().contains(".png") || f.getName().contains(".jpg") ||  f.getName().contains(".doc"))
                f.delete();
        }

    }

    public String formatDateInforme(String formato, Date fecha) {
        String dateString = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formato);
            dateString = format.format(fecha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public void capturaDispositivoPC(String descripcion) throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetFile = new File(
                System.getProperty("RutaEvidencias") + File.separator + numeroScreen + "_" + descripcion + ".png");
        NombreImagen= numeroScreen + "_"+ descripcion ;
        NombreImage[contador] = NombreImagen;
        FileUtils.copyFile(srcFile, targetFile);
        numeroScreen++;
        contador++;

    }
}
