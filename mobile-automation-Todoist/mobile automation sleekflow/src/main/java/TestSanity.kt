import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.testng.Assert
import org.testng.annotations.BeforeClass
import org.testng.annotations.Test
import java.net.URL


class TestSanity {
    private lateinit var driver: AndroidDriver

    @BeforeClass
    fun setupDesiredCapabilities(){

        val capabilities = DesiredCapabilities()
        capabilities.setCapability("appium:deviceName", "emulator-5554")
        capabilities.setCapability("platformName", "android")
        capabilities.setCapability("appium:appium.bundleId", "com.todoist")
        capabilities.setCapability("appium:appWaitForLaunch", "false")
        capabilities.setCapability("appium:app","/Users/nursektijati/Downloads/uptodown-com.todoist.apk")
        capabilities.setCapability("appium:platformVersion","14.0")
        capabilities.setCapability("appium:deviceName","emulator-5554")
        driver = AndroidDriver(URL("https://127.0.0.1:4723/wd/hub"), capabilities)
    }

    @Test
    fun testLoginApp() {

        val googleOptionsButton : WebElement
        val popUpChoosingAccount : WebElement
        val firstGoogleAccount : WebElement
        val emailField : WebElement
        val passwordField : WebElement
        val nextButton : WebElement
        val logo :WebElement
        val welcomeButton : WebElement
        val addButton : WebElement
        val navigationMenu : WebElement

        Thread.sleep(5000)
        try {
            driver.findElement(By.id("Cancel")).click()
        } catch (e: NoSuchElementException) {
        }
        googleOptionsButton = driver.findElement(By.id("com.todoist:id/btn_google"))
        logo = driver.findElement(By.id("com.todoist:id/logo"))
        welcomeButton = driver.findElement(By.id("com.todoist:id/welcome_buttons"))

        Assert.assertTrue(logo.isDisplayed())
        Assert.assertTrue(welcomeButton.isDisplayed())
        Assert.assertTrue(googleOptionsButton.isEnabled())
        googleOptionsButton.click()
        Thread.sleep(3000)

        popUpChoosingAccount = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.google.android.gms:id/title\" and @text=\"Choose an account\"]"))
        Assert.assertTrue(popUpChoosingAccount.isDisplayed())
        if(popUpChoosingAccount.isDisplayed()){
            firstGoogleAccount = driver.findElement(By.id("com.google.android.gms:id/account_display_name"))
            Assert.assertTrue(firstGoogleAccount.isDisplayed())
            firstGoogleAccount.click()
        } else{
            emailField = driver.findElement(By.id("identifierId"))
            nextButton = driver.findElement(By.id("identifierNext"))
            Assert.assertTrue(emailField.isDisplayed())
            emailField.sendKeys("testsekti@gmail.com")
            Assert.assertTrue(nextButton.isDisplayed())
            nextButton.click()
            Thread.sleep(1000)
            passwordField = driver.findElement(By.xpath("//android.view.View[@resource-id=\"password\"]//android.widget.EditText"))
            Assert.assertTrue(passwordField.isDisplayed())
            passwordField.sendKeys("TestSekti99!!")
            nextButton.click()
        }
        Thread.sleep(3000)
        addButton = driver.findElement(By.id("com.todoist:id/fab"))
        navigationMenu = driver.findElement(By.id("com.todoist:id/compose_navigation_holder"))
        Assert.assertTrue(addButton.isDisplayed())
        Assert.assertTrue(navigationMenu.isDisplayed())
    }

}