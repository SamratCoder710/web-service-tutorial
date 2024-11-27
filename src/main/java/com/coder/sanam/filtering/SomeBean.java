package com.coder.sanam.filtering;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonPropertyOrder({"field3","field2","field1"})
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;
//    @JsonIgnore
    private String field2;
//    @JsonProperty("sanam")
    private String field3;

}
