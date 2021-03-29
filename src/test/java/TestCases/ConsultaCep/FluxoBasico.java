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
@Feature("Verificar fluxo básico segundo regra de negocio RQ.234")
public class FluxoBasico {

    ResponseConsultaCep objResponseCep;
    Api apiFunc;
    Gson gson;

    @BeforeMethod
    public void InicializaTeste() throws IOException {

        apiFunc = new Api();
        gson = new Gson();
    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description="Consulta CEP valido")
    @Description("Dado que o usuário inseri um CEP válido")
    public void cepInexistente() throws IOException {

        Assert.assertEquals(
                apiFunc.execComand("GET","74710060","","application/json"), 200
        );

    }

    @Severity(SeverityLevel.NORMAL)
    @Test(description="Verifica o código do IBGE do CEP informado (Desafio)")
    @Description("Dado que o usuário inseri um CEP válido")
    public void cepFormatoInvalido() throws IOException {

        apiFunc.execComand("GET","74710060","","*/*");
        objResponseCep = gson.fromJson(apiFunc.responseBody, ResponseConsultaCep.class);

        Assert.assertEquals(
                objResponseCep.getIbge(), "5208707"
        );

    }

    @AfterMethod
    public void FinalizaTeste() {
    }
}