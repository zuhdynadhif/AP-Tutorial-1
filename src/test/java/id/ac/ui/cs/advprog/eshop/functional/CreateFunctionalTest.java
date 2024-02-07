package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = baseUrl + ":" + serverPort;
    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception {
        // Setup
        String name = "Sampo Cap Bambang";
        String quantity = "100";

        driver.get(baseUrl+"/product/list");
        WebElement createButton = driver.findElement(By.tagName("a"));
        createButton.click();

        driver.get(baseUrl+"/product/create");
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.sendKeys(name);

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.sendKeys(quantity);

        WebElement submitButton = driver.findElement(By.tagName("button"));
        submitButton.click();

        // Exercise
        driver.get(baseUrl+"/product/list");
        String productName = driver.findElement(By.className("name")).getText();
        String productQuantity = driver.findElement(By.className("quantity")).getText();

        // Verify
        assertEquals(name, productName);
        assertEquals(quantity, productQuantity);
    }
}
