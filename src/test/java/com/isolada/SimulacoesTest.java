package com.isolada;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;


public class SimulacoesTest {
    @Before
    public void Setup(){
        //Configurações do Rest-Assured
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/api";
    }
    @Test
    public void testConsultarUmCPFComRestricaoStatus200 () {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/restricoes/26276298085")
        .then()
            .assertThat()
                .statusCode(200)
                    .log()
                        .all();
}

    @Test
    public void testConsultarUmCPFSemRestricaoStatus204 () {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/restricoes/66414919004")
        .then()
            .assertThat()
                .statusCode(204)
                    .log()
                        .all();
}
    @Test
        public void testSimulacaoComSucessoStatus201 () {
            given()
                    .contentType(ContentType.JSON)
                    .body("{\n" +
                    "  \"nome\": \"Jony\",\n" +
                    "  \"cpf\": \"1805\",\n" +
                    "  \"email\": \"email@email.com\",\n" +
                    "  \"valor\": 30000,\n" +
                    "  \"parcelas\": 3,\n" +
                    "  \"seguro\": true\n" +
                    "}")
        .when()
            .post("/v1/simulacoes")
        .then()
            .assertThat()
                .statusCode(201)
                .body("nome", equalTo("Jony"));
    }

    @Test
    public void testSimulacaoComProblemaEmAlgumaRegraStatus400 () {
        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                        "  \"nome\": \"Cliente\",\n" +
                        "  \"cpf\": \"\",\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 30000,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
                .log()
                .all()
        .when()
            .post("/v1/simulacoes")
        .then()
            .assertThat()
                .statusCode(400);
    }

    @Test
    public void testSimulacaoParaOMesmoCPFStatus409 () {
        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                        "  \"nome\": \"Cliente\",\n" +
                        "  \"cpf\":\"3\",\n" +
                        "  \"email\": \"email@email.com\",\n" +
                        "  \"valor\": 30000,\n" +
                        "  \"parcelas\": 3,\n" +
                        "  \"seguro\": true\n" +
                        "}")
            .log()
            .all()
        .when()
            .post("/v1/simulacoes")
        .then()
                .assertThat()
                    .statusCode(409);
    }
    @Test
    public void testAlteraUmaSimulacaoJaExistenteStatus200 () {
        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                    "    \"id\": 11,\n" +
                    "    \"nome\": \"Novo Cliente\",\n" +
                    "    \"cpf\": \"66414919004\",\n" +
                    "    \"email\": \"email@email.com\",\n" +
                    "    \"valor\": 1000,\n" +
                    "    \"parcelas\": 4,\n" +
                    "    \"seguro\":true\n" +
                    "    }")
            .log()
            .all()
        .when()
            .put("/v1/simulacoes/66414919004")
        .then()
            .assertThat()
                .statusCode(200);
    }
    @Test
    public void testCPFnãoPossuiUmaSimulacaoStatus404 () {
        given()
            .contentType(ContentType.JSON)
            .body("{\n" +
                        "    \"id\": 15,\n" +
                        "    \"nome\": \"Novo Cliente\",\n" +
                        "    \"cpf\": \"777\",\n" +
                        "    \"email\": \"email@email.com\",\n" +
                        "    \"valor\": 1000,\n" +
                        "    \"parcelas\": 2,\n" +
                        "    \"seguro\":true\n" +
                        "    }")
            .log()
            .all()
        .when()
            .put("/v1/simulacoes/777")
        .then()
            .assertThat()
                .statusCode(404);
    }
    @Test
    public void testRetornaTodasAsSimulacoesCadastradasStatus200 () {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/simulacoes/")
        .then()
            .assertThat()
                .statusCode(200)
                    .log()
                        .all();
    }
    @Test
    public void testConsultarUmaSimulacaoPeloCPFStatus200 () {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/simulacoes/97093236014")
        .then()
            .assertThat()
                .statusCode(200)
                .log()
                    .all();
    }
    @Test
    public void testConsultarUmaSimulacaoQueNaoExistetatus404 ()    {
        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/v1/simulacoes/777")
        .then()
            .assertThat()
                .statusCode(404)
                    .log()
                        .all();
    }
    @Test
    public void testDeletarUmaSimulacaoStatus200 () {
        given()
            .contentType(ContentType.JSON)
        .when()
            .delete("/v1/simulacoes/13")
        .then()
            .assertThat()
                .statusCode(200)
                    .log()
                        .all();
    }
}
