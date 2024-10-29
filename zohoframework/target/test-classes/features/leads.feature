Feature: Leads Module 

  Scenario: Sign in to Zoho CRM
    Given The Zoho CRM URL "<url>" is provided
    When I sign in to the account using username "<username>" and password "<password>"
    And I click on the Sign In button
    Then I should be signed in successfully

  Scenario: Create a New Lead
    Given I am on the leads page
    When I click on the create lead button
    And I create a new lead with the name "<leadName>"
    Then the lead should be present in the leads list "<leadName>"

  Scenario: Edit a Lead
    Given I have a lead named "<currentLeadName>"
    When I edit the lead name to "<newLeadName>"
    Then the new lead should be present in the leads list "<newLeadName>"

  Scenario: Filter Leads
    Given I have multiple leads
    When I filter leads by the name "<filterName>"
    Then I should see leads that match the criteria

  Scenario: Delete a Lead
    Given I have the lead "<leadToDelete>"
    When I delete the lead
    Then the lead should not be present in the leads list "<leadToDelete>"
