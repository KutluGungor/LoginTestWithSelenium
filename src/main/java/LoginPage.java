import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private By email = By.cssSelector(".email input");
    private By password = By.cssSelector(".password input");
    private By emailError = By.cssSelector(".email .error-label");
    private By passwordError = By.cssSelector(".password .error-label");
    private By signInButton = By.cssSelector("button.btn.login-btn.form-control");
    private By forgotPasswordButton = By.cssSelector(".forgot-password a");
    private By forgotModalTitle = By.cssSelector(".forgot-password-dropdown .header span");
    private By backLinkButton = By.cssSelector("a.back-link");
    private By loginModalTitle = By.cssSelector(".header span");
    private By logoutButton = By.cssSelector("#logged_in_username a.sign-out-link");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void clickSignIn() {
        WebElement button = driver.findElement(signInButton);
        button.click();
    }

    public void clickforgotPassword() {
        waitElement(forgotPasswordButton);
        WebElement button = driver.findElement(forgotPasswordButton);
        button.click();
    }
    public void clickBackLink() {
        WebElement button = driver.findElement(backLinkButton);
        button.click();
    }

    public String getEmail() {
        WebElement emailInput = driver.findElement(email);
        return emailInput.getAttribute("value");
    }

    public String getLogout() {
        waitElement(logoutButton);
        WebElement emailInput = driver.findElement(logoutButton);
        return emailInput.getText();
    }

    public String getforgotModalTitle() {
        WebElement modalTitle = driver.findElement(forgotModalTitle);
        return modalTitle.getText();
    }

    public String getLoginModalTitle() {
        WebElement modalTitle = driver.findElement(loginModalTitle);
        return modalTitle.getText();
    }

    public void setEmail(String emailText) {
        WebElement emailInput = driver.findElement(email);
        emailInput.click();
        emailInput.sendKeys(emailText);
    }

    public void clearEmail(){
        WebElement emailInput = driver.findElement(email);
        emailInput.clear();
    }

    public void setPassword(String passwordText) {
        WebElement passwordInput = driver.findElement(password);
        passwordInput.click();
        passwordInput.sendKeys(passwordText);
    }
    public void clearPassword(){
        WebElement passwordInput = driver.findElement(password);
        passwordInput.clear();
    }

    public String getEmailError() {
        waitElement(emailError);
        WebElement emailSpan = driver.findElement(emailError);
        return emailSpan.getAttribute("innerText");
    }

    public String getPasswordError() {
        waitElement(passwordError);
        WebElement passwordSpan = driver.findElement(passwordError);
        return passwordSpan.getAttribute("innerText");
    }

    public void waitElement(By element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }


}
