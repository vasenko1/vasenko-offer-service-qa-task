package com.lottoland.offer;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
class OfferRepository {

    private final Set<OfferData> offers = new HashSet<>();

    OfferData create(OfferRequest offerRequest) {

        OfferData offerData = OfferData.builder()
                                       .id(String.valueOf(offers.size() + 1))
                                       .name(offerRequest.getName())
                                       .description(offerRequest.getDescription())
                                       .prize(offerRequest.getPrize())
                                       .build();
        offers.add(offerData);

        return offerData;
    }

    void update(String offerId, OfferRequest offerRequest) {
        OfferData offerData = getById(offerId);

        offers.remove(offerData);
        offers.add(offerData.toBuilder().name(offerRequest.getName()).description(offerRequest.getDescription()).build());
    }

    void delete(String offerId) {
        OfferData offerData = getById(offerId);
        offers.remove(offerData);
    }

    OfferData getById(String offerId) {
        return offers.stream().filter(offerData -> offerData.getId().equals(offerId)).findAny()
                     .orElseThrow(() -> new NoSuchElementException("Offer with id: " + offerId + " not found"));
    }

    Set<OfferData> findAll() {
        return offers.stream().map(offerData -> offerData.toBuilder().description(null).build()).collect(Collectors.toSet());
    }
}