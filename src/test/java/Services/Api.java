package Services;

import Util.Configuracao;
import io.qameta.allure.Allure;
import okhttp3.*;

import java.io.IOException;

public class Api extends Configuracao {

    public Response response = null;
    public String responseBody;

    public int execComand(String method, String cep, String bodyRequest, String accept) throws IOException {
        try {
            OkHttpClient client = new OkHttpClient();
            Request.Builder urlReq = new Request.Builder().url(urlBaseApi.replace("{{CEP}}",cep));
            RequestBody body;
            Request request;

            switch (method)
            {
                case "GET":
                    urlReq.get();
                    break;
            }
            urlReq
                    .addHeader("accept", accept)
                    .addHeader("content-type", "application/json")
                    .addHeader("cache-control", "no-cache");

            request = urlReq.build();
            response = client.newCall(request).execute();
            responseBody = response.body().string();

            if(responseBody != ""){
                Allure.getLifecycle().addAttachment("StackTrace","text/json","json", screenshotFile(responseBody));
            }

            if(response.code() != 200){
                Allure.getLifecycle().addAttachment("StackTrace","text/json","json", screenshotFile("Code:" + response.code() + " - " + response.message()));
            }

            return response.code();

        } catch (IOException ex) {
            throw ex;
        }
    }
}