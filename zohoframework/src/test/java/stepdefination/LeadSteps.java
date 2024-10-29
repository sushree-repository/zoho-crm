package stepdefination;

import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import pageObjects.LeadPage;

public class LeadSteps {

    WebDriver driver;
    LeadPage leadPage;

    @Given("The Zoho CRM URL {string} is provided")
    public void theZohoCRMUrlIsProvided(String url) {
        driver = DriverManager.getDriver();
        leadPage = new LeadPage(driver);
        leadPage.goToUrl(url);
    }

    @When("I sign in to the account using username {string} and password {string}")
    public void iSignInToTheAccountUsingUsernameAndPassword(String username, String password) {
        leadPage.enterUsername(username);
        leadPage.enterPassword(password);
    }

    @And("I click on the Sign In button")
    public void iClickOnTheSignInButton() {
        leadPage.clickSignIn();
    }

    @Then("I should be signed in successfully")
    public void iShouldBeSignedInSuccessfully() {
        leadPage.verifyDashboardIsDisplayed();
    }

    @Given("I am on the leads page")
    public void iAmOnTheLeadsPage() {
        leadPage.goToLeadsPage();
    }

    @When("I click on the create lead button")
    public void iClickOnTheCreateLeadButton() {
        leadPage.clickCreateLeadButton();
    }

    @And("I create a new lead with the name {string}")
    public void iCreateANewLeadWithTheName(String leadName) {
        leadPage.createLead(leadName);
    }

    @Then("the lead should be present in the leads list {string}")
    public void theLeadShouldBePresentInTheLeadsList(String leadName) {
        leadPage.verifyLeadIsPresent(leadName);
    }

    @Given("I have a lead named {string}")
    public void iHaveALeadNamed(String currentLeadName) {
        leadPage.verifyLeadIsAvailable(currentLeadName);
    }

    @When("I edit the lead name to {string}")
    public void iEditTheLeadNameTo(String newLeadName) {
        leadPage.editLeadName(newLeadName);
    }

    @Then("the new lead should be present in the leads list {string}")
    public void theNewLeadShouldBePresentInTheLeadsList(String newLeadName) {
        leadPage.verifyLeadIsPresent(newLeadName);
    }

    @Given("I have multiple leads")
    public void iHaveMultipleLeads() {
        leadPage.verifyMultipleLeadsPresent();
    }

    @When("I filter leads by the name {string}")
    public void iFilterLeadsByTheName(String filterName) {
        leadPage.filterLeadsByName(filterName);
    }

    @Then("I should see leads that match the criteria")
    public void iShouldSeeLeadsThatMatchTheCriteria() {
        leadPage.verifyFilteredLeadsPresent();
    }

    @Given("I have the lead {string}")
    public void iHaveTheLead(String leadToDelete) {
        leadPage.verifyLeadToDeleteIsAvailable(leadToDelete);
    }

    @When("I delete the lead")
    public void iDeleteTheLead() {
        leadPage.deleteLead();
    }

    @Then("the lead should not be present in the leads list {string}")
    public void theLeadShouldNotBePresentInTheLeadsList(String leadToDelete) {
        leadPage.verifyLeadIsDeleted(leadToDelete);
    }
}
