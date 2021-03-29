package TestCases.ConsultaCep;

import Model.ResponseConsultaCep;
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

    ResponseConsultaCep objPessoa;
    Api apiFunc;
    Gson gson;

    @BeforeMethod
    public void InicializaTeste() throws IOException {

        apiFunc = new Api();
        gson = new Gson();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description="Consulta CEP com formato inválido")
    @Description("Dado que o usuário inseri um CEP com formato inválido")
    public void cepFormatoInvalido() throws IOException {

        Assert.assertEquals(
                apiFunc.execComand("GET","74710.060","","*/*"), 200
        );

    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description="Consulta CEP inexistente")
    @Description("Dado que o usuário inseri um CEP que não exista na base dos Correios")
    public void cepInexistente() throws IOException {

        Assert.assertEquals(
                apiFunc.execComand("GET","17000018","","*/*"), 200
        );

    }

    @AfterMethod
    public void FinalizaTeste() {
    }
}