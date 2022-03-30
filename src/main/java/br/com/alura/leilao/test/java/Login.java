package br.com.alura.leilao.test.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Login {

    public static final String URL_LOGIN = "http://localhost:8080/login";
    //criou uma constante para deixar o código mais limpo, usando apenas o nome URL_LOGIN


    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Gustavo/Documents/ALURA/SELENIUM/chromedriver.exe");
        //uma classe criada para limpar o código, deixando de ter repetiçoes, pois usamos esse comando
        //no inicio de todos os testes.
    }

    @BeforeEach
    public void beforeEach() {
        //classe criada para evitar a repetiçao de codigo, pois usamos o mesmo codigo nos 2 testes
        this.browser = new ChromeDriver();
        //comando para abrir o navegador desejado, no caso o chrome
        browser.navigate().to(URL_LOGIN);
        //comando para navegar até a url declarada acima
    }

    @AfterEach
    public void afterEach() {
        //classe criada para evitar a repetiçao de codigo, pois usamos o comando de fechar o navegador
        //em todos os teste realizados.
        this.browser.quit();
        //fecha o navegador
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        //procura elemento html na pagina pelo ID da tag e sendkeys envia o comando de digitar.
        browser.findElement(By.tagName("button")).click();
        // procura o elemento pela tag e da um click, no caso em um botao
        //browser.findElement(By.id("login-form")).submit();
        // procura o elemento pela id e da um submit no caso em Form.
        Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
        //comando verifica se a URL atual é a mesma que a URL de inicio do teste.
        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        // comando verifica se a string "fulano" é a mesma encontrada na parte superior direita, quando o usuario é logado e o nome dele deverá aparecer.

    }


    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        browser.navigate().to(URL_LOGIN);
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("invalido");
        browser.findElement(By.tagName("button")).click();
        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        //maneira diferente de pegar um elemento da página - getPageSource().contains = ve se a pagina "contém"
        Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
        // esse assertThrows "joga" e verifica se irá retornar um erro, que no caso é o NoSuchElementException.class.

    }

}