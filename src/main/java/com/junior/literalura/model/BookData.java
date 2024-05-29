package com.junior.literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookData(
        String title,
        List<AuthorData> authors,
        List<String> languages,
        double download_count

) {

}
