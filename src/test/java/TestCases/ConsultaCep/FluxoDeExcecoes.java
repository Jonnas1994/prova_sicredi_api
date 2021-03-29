package TestCases.ConsultaCep;

import Model.ResponseConsultaCep;
import Model.ResponseError;
import Services.Api;
import com.google.gson.Gson;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("EPIC - Consulta CEP")
@Feature("Verificar fluxo de Excecoes segundo regra de negocio RQ.235")
public class FluxoDeExcecoes {

    ResponseError objResponseCep;
    Api apiFunc;
    Gson gson;

    @BeforeMethod
    public void InicializaTeste() throws IOException {

        apiFunc = new Api();
        gson = new Gson();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description="Consulta CEP com formato invalido")
    @Description("Dado que o usuario inseri um CEP com formato invalido")
    public void cepFormatoInvalido() throws IOException {

        Assert.assertEquals(
                apiFunc.execComand("GET","74710.060","","*/*"), 400
        );

    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description="Consulta CEP inexistente")
    @Description("Dado que o usuário inseri um CEP que não exista na base dos Correios")
    public void cepInexistente() throws IOException {

        apiFunc.execComand("GET","17000018","","*/*");
        objResponseCep = gson.fromJson(apiFunc.responseBody, ResponseError.class);

        Assert.assertEquals(
                objResponseCep.getErro(), true
        );

    }

    @AfterMethod
    public void FinalizaTeste() {
    }
}