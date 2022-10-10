package com.lottoland.offer;

import com.models.OfferDataStorage;
import com.models.OfferObjectModel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static com.lottoland.offer.BaseStepsDefinition.apiPutOfferSteps;
import static com.lottoland.offer.BaseStepsDefinition.offerPageSteps;

public class ReadOfferStepsDefinition {
    // e2e tests to validate offer info
    private OfferObjectModel _offer = new OfferDataStorage().regularOffer;

    @Given("^New offer is just created$")
    public void new_offer_is_just_created() {
        apiPutOfferSteps.CreateOffer(_offer);
    }

    @Given("^Offer Page is opened$")
    public void offer_page_is_opened() {
        offerPageSteps.OpenPage();
    }

    @When("^User clicking the offer Info button$")
    public void user_clicking_the_offer_info_button() {
        offerPageSteps.ClickRowInfoButton(_offer.name);
    }

    @Then("^Unchanged offer data is shown in Info popup$")
    public void unchanged_offer_data_is_shown_in_info_popup() {
        OfferObjectModel actualOfferData = offerPageSteps.GetOfferDataFromInfoPopup();
        Assertions.assertEquals(_offer.name, actualOfferData.name, "Expected the same Name.");
        Assertions.assertEquals(_offer.prize, actualOfferData.prize, "Expected the same Prize.");
        Assertions.assertEquals(_offer.description, actualOfferData.description, "Expected the same Description.");
    }

    @Then("^Unchanged offer Name is show in table row$")
    public void unchanged_offer_name_is_show_in_table_row() {
        boolean isOfferExist = offerPageSteps.IsTableRowExist(_offer.name);
        Assertions.assertTrue(isOfferExist,"Offer name is not shown in table: " + _offer.name);
    }

    @When("^User clicking the offer Edit button$")
    public void user_clicking_the_offer_edit_button() {
        offerPageSteps.ClickRowEditButton(_offer.name);
    }

    @Then("^Unchanged offer data is shown in Edit modal$")
    public void unchanged_offer_data_is_shown_in_edit_modal() {
        OfferObjectModel receivedOfferData = offerPageSteps.GetOfferDataFromEditModal();
        Assertions.assertEquals(_offer.name, receivedOfferData.name, "Expected Name is the same.");
        Assertions.assertEquals(_offer.prize, receivedOfferData.prize, "Expected Prize is the same.");
        Assertions.assertEquals(_offer.description, receivedOfferData.description, "Expected Description is the same.");
    }
}