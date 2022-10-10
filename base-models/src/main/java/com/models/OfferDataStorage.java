package com.models;

public class OfferDataStorage {
    public OfferObjectModel regularOffer = new OfferObjectModel() {{
        name = java.util.UUID.randomUUID().toString();
        prize = String.valueOf(Long.MAX_VALUE - 1000);
        description = "Test Description GUID: " + name;
    }};

    public OfferObjectModel invalidPrizeOffer = new OfferObjectModel() {{
        name = java.util.UUID.randomUUID().toString();
        prize = String.valueOf(Long.MAX_VALUE + 1);
        description = "Test Description GUID: " + name;
    }};

    public OfferObjectModel editedOffer = new OfferObjectModel() {{
        name = java.util.UUID.randomUUID().toString();
        prize = "0";
        description = "Edited Description GUID: " + name;
    }};

    public OfferObjectModel emptyOffer = new OfferObjectModel() {{
        name = "";
        prize = name;
        description = name;
    }};
}

