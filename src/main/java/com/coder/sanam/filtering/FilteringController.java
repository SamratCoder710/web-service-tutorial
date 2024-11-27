package com.coder.sanam.filtering;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue getFilterBean(){
        SomeBean someBeanObj = SomeBean.builder().field1("value1").field2("value2")
                .field3("value3")
                .build();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanObj);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3"));
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> getFilterListBean(){
        return List.of(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
    }

    @PostMapping("/filter")
    public SomeBean getObjectAsString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SomeBean someBeanObj = SomeBean.builder().field1("value1").field2("value2")
                .field3("value3")
                .build();
        String jsonString = "{\"field1\":\"value1\",\"field2\":\"value2\",\"field3\":\"value3\",\"field9\":\"value9\"}";
        String s = objectMapper.writeValueAsString(someBeanObj);
        SomeBean someBean = objectMapper.readValue(jsonString, SomeBean.class);
        System.out.println(s);
        return someBean;
    }
}
