import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestLoginPage extends BaseTest{

    @Test
    @Order(1)
    public void returnErrorWhenEmailIsEmpty(){
        loginPage.clickSignIn();
        Assertions.assertEquals("Bu alanın doldurulması zorunludur.",loginPage.getEmailError(), "Email can not be empty");
    }

    @Test
    @Order(2)
    public void returnErrorWhenPasswordIsEmpty(){
        Assertions.assertEquals("Bu alanın doldurulması zorunludur.",loginPage.getPasswordError(), "Password can not be empty");
    }

    @Test
    @Order(3)
    public void returnErrorWhenEmailIsEmptyPasswordIsFilled(){
        loginPage.setPassword("deneme");
        loginPage.clickSignIn();
        Assertions.assertEquals("Bu alanın doldurulması zorunludur.",loginPage.getEmailError(), "Email can not be empty");
    }

    @Test
    @Order(4)
    public void returnErrorWhenSetEmailIsInvalid(){
        loginPage.setEmail("kutlu");
        Assertions.assertEquals("Lütfen geçerli bir e-posta adresi giriniz.",loginPage.getEmailError(), "Email format is not correct");
    }

    @Test
    @Order(5)
    public void setEmail(){
        loginPage.clearEmail();
        loginPage.setEmail("*****");
        Assertions.assertEquals("*****",loginPage.getEmail(), "Email value is not correct");
    }

    @Test
    @Order(6)
    public void returnErrorWhenFailedLogin(){
        loginPage.clickSignIn();
        Assertions.assertEquals("Bilinmeyen kullanıcı veya eşleşmeyen şifre.",loginPage.getPasswordError(), "Failed Login Error");
    }
    @Test
    @Order(7)
    public void clickForgotPasswordButtonAndGetModalTitle(){
        loginPage.clickforgotPassword();
        Assertions.assertEquals("Şifremi Unuttum",loginPage.getforgotModalTitle(), "Modal title is not equal");
    }

    @Test
    @Order(8)
    public void clickForgotPasswordButtonAndGetEmailAdress(){
        Assertions.assertEquals("*****",loginPage.getEmail(), "Cannot get email");
    }

    @Test
    @Order(9)
    public void clickReturnButtonAndGetLoginModal(){
        loginPage.clickBackLink();
        Assertions.assertEquals("Giriş Yap",loginPage.getLoginModalTitle(), "Modal title is not equal");
    }

    @Test
    @Order(10)
    public void SuccesLogin(){
        loginPage.clearEmail();
        loginPage.setEmail("******");
        loginPage.clearPassword();
        loginPage.setPassword("*****");
        loginPage.clickSignIn();
        Assertions.assertEquals("Çıkış Yap", loginPage.getLogout().trim(),"Not Logged In");
    }


}
