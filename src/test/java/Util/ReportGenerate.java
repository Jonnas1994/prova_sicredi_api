package Util;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReportGenerate {

    @Test
    public static void run(){
        try {

            gerarReport();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void gerarReport() throws IOException, InterruptedException {

        System.out.println("Gerando relatorio...");

        LocalDateTime then = LocalDateTime.now();
        String dirLocal = new File(".").getCanonicalPath();

        if (new File(dirLocal + "\\allure-results").exists()) {

            System.out.println("Gerando Report do Allure...");

            Runtime.getRuntime().exec("cmd.exe /K " +
                    "cd "+ dirLocal +" && allure generate  "+ dirLocal +"\\allure-results --clean -o "+ dirLocal +"\\folderAllureVM\\default\\reports\\latest");

            System.out.println("Report gerado com sucesso!");
            Thread.sleep(5000);

        }else{
            throw new FileNotFoundException("Ops! Diretorio [allure-results] não encontrado :(");
        }
    }

}