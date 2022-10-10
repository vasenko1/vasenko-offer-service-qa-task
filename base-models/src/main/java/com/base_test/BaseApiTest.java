package com.base_test;

import com.steps_api.DeleteOfferSteps;
import com.steps_api.GetOfferSteps;
import com.steps_api.PostOfferSteps;
import com.steps_api.PutOfferSteps;

public abstract class BaseApiTest{
    protected PutOfferSteps apiPutOfferSteps = new PutOfferSteps();
    protected GetOfferSteps apiGetOfferSteps = new GetOfferSteps();
    protected DeleteOfferSteps apiDeleteOfferSteps = new DeleteOfferSteps();
    protected PostOfferSteps apiPostOfferSteps = new PostOfferSteps();
}
