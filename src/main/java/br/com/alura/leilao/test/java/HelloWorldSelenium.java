package br.com.alura.leilao.test.java;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenium {

    @Test
    public void hello() {
        //seta onde está o chromedriver, no caso do chrome, é possivel achar esse comando na doc do selenium
        System.setProperty("webdriver.chrome.driver", "C:/Users/Gustavo/Documents/ALURA/SELENIUM/chromedriver.exe");
        //comando para abrir o navegador
        WebDriver browser = new ChromeDriver();
        //comando para navegar ate a determinada url
        browser.navigate().to("http://localhost:8080/leiloes");
        //comando para fechar o navegador
        browser.quit();
    }
}
