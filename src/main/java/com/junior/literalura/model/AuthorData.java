package com.junior.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record AuthorData(
        String name,
        Double birth_year,
        Double death_year
) {
}
