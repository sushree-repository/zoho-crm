package pageObjects;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LeadPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By signInButton = By.xpath("//a[contains(@href, 'https://accounts.zoho.in/signin')]");
    private By usernameField = By.id("login_id");
    private By nextButton = By.id("nextbtn");
    private By passwordField = By.id("password");
    private By loginButton = By.xpath("//button[@id='nextbtn'][text()='Sign in']");
    private By homeLeadLink = By.xpath("//*[@id='crmTopMenuLyteDrop']//div[1]/lyte-drop-button/span/crmutil-icon/svg");
    private By leadsLink = By.linkText("Leads");
    private By createLeadButton = By.id("create_lead_button");
    private By leadNameInput = By.id("lead_name_input");
    private By saveLeadButton = By.id("save_lead_button");
    private By leadsList = By.id("leads_list");
    private By editLeadButton = By.id("edit_lead_button");
    private By filterInput = By.id("filter_input");
    private By applyFilterButton = By.id("apply_filter_button");
    private By deleteLeadButton = By.id("delete_lead_button");

    public LeadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToUrl(String url) {
        driver.get("https://www.zoho.com/en-in/crm/");
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys("dassushree743@gmail.com");
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys("Success@123!");
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void verifyDashboardIsDisplayed() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "https://crm.zoho.in/crm/org60033930896/tab/Home/begin";
        Assert.assertEquals(actualTitle, expectedTitle, "User is not signed in successfully");
    }

    public void goToLeadsPage() {
        driver.findElement(homeLeadLink).click();
        driver.findElement(leadsLink).click();
    }

    public void clickCreateLeadButton() {
        driver.findElement(createLeadButton).click();
    }

    public void createLead(String leadName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(leadNameInput)).sendKeys("Avnish Dixit");
        driver.findElement(saveLeadButton).click();
    }

    public void verifyLeadIsPresent(String leadName) {
        WebElement list = driver.findElement(leadsList);
        Assert.assertTrue(list.getText().contains("Avnish Dixit"), "Lead is not present in the leads list");
    }

    public void verifyLeadIsAvailable(String leadName) {
        verifyLeadIsPresent("Avnish Dixit");
    }

    public void editLeadName(String newLeadName) {
        driver.findElement(editLeadButton).click();
        WebElement nameInput = driver.findElement(leadNameInput);
        nameInput.clear();
        nameInput.sendKeys("Jacob Jain");
        driver.findElement(saveLeadButton).click();
    }

    public void verifyMultipleLeadsPresent() {
        WebElement list = driver.findElement(leadsList);
        Assert.assertTrue(list.getText().split("\n").length > 1, "There are not multiple leads");
    }

    public void filterLeadsByName(String filterName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterInput)).sendKeys("Jacob Jain");
        driver.findElement(applyFilterButton).click();
    }

    public void verifyFilteredLeadsPresent() {
        WebElement list = driver.findElement(leadsList);
        Assert.assertTrue(list.getText().length() > 0, "No leads match the criteria");
    }

    public void verifyLeadToDeleteIsAvailable(String leadName) {
        verifyLeadIsAvailable("Jacob Jain");
    }

    public void deleteLead() {
        driver.findElement(deleteLeadButton).click();
    }

    public void verifyLeadIsDeleted(String leadName) {
        WebElement list = driver.findElement(leadsList);
        Assert.assertFalse(list.getText().contains("Jacob Jain"), "Lead is still present in the leads list");
    }
}
