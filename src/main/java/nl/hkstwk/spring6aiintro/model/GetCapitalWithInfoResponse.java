package nl.hkstwk.spring6aiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalWithInfoResponse(
        @JsonPropertyDescription("The capitals name is") String capital,
        @JsonPropertyDescription("The capitals population is") Long population,
        @JsonPropertyDescription("The region the capital is located") String region,
        @JsonPropertyDescription("The primary language spoken") String language,
        @JsonPropertyDescription("The currency used") String currency
        ) {
}
