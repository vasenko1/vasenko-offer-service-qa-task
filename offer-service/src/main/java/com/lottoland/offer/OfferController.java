package com.lottoland.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/offer")
class OfferController {

    private final OfferRepository offerRepository;

    @PutMapping
    OfferData createOffer(@RequestBody @Valid OfferRequest offerRequest) {
        return offerRepository.create(offerRequest);
    }

    @PostMapping("/{offerId}")
    void updateOffer(@PathVariable("offerId") String offerId, @RequestBody OfferRequest offerRequest) {
        offerRepository.update(offerId, offerRequest);
    }

    @DeleteMapping("/{offerId}")
    void deleteOffer(@PathVariable("offerId") String offerId) {
        offerRepository.delete(offerId);
    }

    @GetMapping("/{offerId}")
    OfferData getOffer(@PathVariable("offerId") String offerId) {
        return offerRepository.getById(offerId);
    }

    @GetMapping
    Set<OfferData> getOffers() {
        return offerRepository.findAll();
    }
}
