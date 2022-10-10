package com.lottoland.offer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class OfferRequest {
    @NotNull
    private String name;
    @NotEmpty
    private String description;

    private Long prize;
}