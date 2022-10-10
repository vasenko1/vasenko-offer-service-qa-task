package com.lottoland.offer;

import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import java.net.http.HttpResponse;

import static com.lottoland.offer.BaseStepsDefinition.*;

public class DeleteOfferStepsDefinition {
    private OfferObjectModel _validOffer = new OfferDataStorage().regularOffer;

    @Given("^New offer just created$")
    public void new_offer_just_created() {
        apiPutOfferSteps.CreateOffer(_validOffer);
    }

    @Given("^Offer page opened$")
    public void offer_page_opened() {
        offerPageSteps.OpenPage();
    }

    @When("^User clicking the offer delete button$")
    public void user_clicking_the_offer_delete_button() {
        offerPageSteps.ClickRowDeleteButton(_validOffer.name);
    }

    @Then("^The offer is not found$")
    public void the_offer_is_not_found() {
        HttpResponse response = apiGetOfferSteps.SendGetOfferListRequest();
        boolean isExist = apiGetOfferSteps.CheckIfOfferIsInList(response, _validOffer.name);
        Assertions.assertFalse(isExist, "Expected offer is deleted: "+ _validOffer.name);
    }
}