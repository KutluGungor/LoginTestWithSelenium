import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import java.awt.*;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    private static ScreenRecorder screenRecorder;
    protected WebDriver driver;
    LoginPage loginPage;

    @BeforeAll
    public void setUp(){

        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();

        GraphicsConfiguration gconfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        try{
            screenRecorder = new ScreenRecorder(gconfig,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
                            ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                            DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),
                            QualityKey, 1.0f,
                            KeyFrameIntervalKey, (int) (15 * 60)),
                    new Format(MediaTypeKey, MediaType.VIDEO,
                            EncodingKey,"black", FrameRateKey, Rational.valueOf(30)), null);

            screenRecorder.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Screen recording error when start");
        }

        driver.get("https://yolcu360.com/");
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
        driver.findElement(new By.ByCssSelector(".sign-in-li a.dropdown-toggle")).click();
        driver.findElement(new By.ByCssSelector("a.dropdown-item.sign-in")).click();

    }

    @AfterAll
    public void tearDown() {
        if (driver != null) {

            driver.quit();

            try {
                screenRecorder.stop();
                Runtime.getRuntime().exec("explorer.exe /click," + "C:\\Users\\"+ System.getProperty("user.name")+"\\Videos");

            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Screen recording error");
            }
        }
    }


}