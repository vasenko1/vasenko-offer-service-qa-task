package com.lottoland.offer;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
class OfferData {
    private String id;
    private String name;
    private String description;
    private long prize;
}
