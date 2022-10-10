package com.lottoland.offer;

import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static com.lottoland.offer.BaseStepsDefinition.*;

public class UpdateOfferStepsDefinition {
    // e2e tests to validate offer modification

    private OfferObjectModel _initialOffer = new OfferDataStorage().regularOffer;
    private OfferObjectModel _newOfferData = new OfferDataStorage().editedOffer;

    @Given("^Offer Page opened$")
    public void offer_page_opened() {
        offerPageSteps.OpenPage();
    }

    @Given("^New offer is already created$")
    public void new_offer_is_already_created(){
        apiPutOfferSteps.CreateOffer(_initialOffer);
    }

    @When("^User updating the offer Name$")
    public void user_updating_the_offer_name() {
        offerPageSteps.UpdateOfferName(_initialOffer, _newOfferData);
    }

    @Then("^Only offers Name value is updated$")
    public void only_offers_name_value_is_updated() {
        OfferObjectModel receivedOffer = apiGetOfferSteps.GetOfferByName(_newOfferData.name);
        Assertions.assertEquals(_newOfferData.name, receivedOffer.name, "Expected Name is updated.");
        Assertions.assertEquals(_initialOffer.prize, receivedOffer.prize, "Expected Prize is not updated.");
        Assertions.assertEquals(_initialOffer.description, receivedOffer.description, "Expected Description is not updated.");
    }

    @When("^User updating the offer Prize$")
    public void user_updating_the_offer_prize() {
        offerPageSteps.UpdateOfferPrize(_initialOffer, _newOfferData);
    }

    @Then("^Only offers Prize value is updated$")
    public void only_offers_prize_value_is_updated() {
        OfferObjectModel receivedOfferData = apiGetOfferSteps.GetOfferByName(_initialOffer.name);
        Assertions.assertEquals(_initialOffer.name, receivedOfferData.name, "Expected Name is not updated.");
        Assertions.assertEquals(_initialOffer.description, receivedOfferData.description, "Expected Description is not updated.");
        Assertions.assertEquals(_newOfferData.prize, receivedOfferData.prize, "Expected Prize is updated.");
    }

    @When("^User updating the offer Description$")
    public void user_updating_the_offer_description() {
        offerPageSteps.UpdateOfferDescription(_initialOffer, _newOfferData);
    }

    @Then("^Only offers Description value is updated$")
    public void only_offers_description_value_is_updated() {
        OfferObjectModel receivedOfferData = apiGetOfferSteps.GetOfferByName(_initialOffer.name);
        Assertions.assertEquals(_initialOffer.name, receivedOfferData.name, "Expected Name is not updated.");
        Assertions.assertEquals(_initialOffer.prize, receivedOfferData.prize, "Expected Prize is not updated.");
        Assertions.assertEquals(_newOfferData.description, receivedOfferData.description, "Expected Description is updated.");
    }
}