package com.lottoland.offer;

import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.net.http.HttpResponse;

public class CreateOfferStepsDefinition {
    private OfferObjectModel _validOffer = new OfferDataStorage().regularOffer;
    private OfferObjectModel _invalidOffer = new OfferDataStorage().invalidPrizeOffer;

    @Given("^Offer page is opened$")
    public void OpenOfferPage() {
        BaseStepsDefinition.offerPageSteps.OpenPage();
    }

    @When("^User create new offer with valid data$")
    public void CreateOfferWithValidData() {
        BaseStepsDefinition.offerPageSteps.CreateNewOffer(_validOffer);
    }

    @When("^User trying to create new offer with invalid data$")
    public void CreateOfferWithInvalidData() {
        BaseStepsDefinition.offerPageSteps.CreateNewOffer(_invalidOffer);
    }

    @Then("^Saved offer data is not corrupted$")
    public void CheckOfferData() {
        OfferObjectModel receivedOffer = BaseStepsDefinition.apiGetOfferSteps.GetOfferByName(_validOffer.name);
        Assertions.assertEquals(_validOffer.name, receivedOffer.name, "Expected the same Name.");
        Assertions.assertEquals(_validOffer.description, receivedOffer.description, "Expected the same Description.");
        Assertions.assertEquals(_validOffer.prize, receivedOffer.prize, "Expected the same Prize.");
    }

    @Then("^The offer is not created$")
    public void CheckIfOfferIsNotCreated() {
        HttpResponse response = BaseStepsDefinition.apiGetOfferSteps.SendGetOfferListRequest();
        boolean isExist = BaseStepsDefinition.apiGetOfferSteps.CheckIfOfferIsInList(response, _invalidOffer.name);
        Assertions.assertFalse(isExist, "Expected offer is not exist: "+ _invalidOffer.name);
    }
}