package Util;

public class Configuracao {

    public static String urlBaseApi;

    public Configuracao(){
        try {

            this.urlBaseApi = "https://viacep.com.br/ws/{{CEP}}/json/";

        }catch (Exception ex){
            new Exception("Ocorreu o seguinte erro ao definir a URL Base: " + ex.getMessage());
        }
    }

    public static byte[] screenshotFile(String fileJson)  {
        byte[] byteArray = fileJson.getBytes();
        return byteArray;
    }

}