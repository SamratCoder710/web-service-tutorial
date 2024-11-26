package com.coder.sanam.versioning;

import com.coder.sanam.model.PersonDetails;
import com.coder.sanam.model.PersonV1;
import com.coder.sanam.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionPerson(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionPerson(){
        return new PersonV2(new PersonDetails("Bob","Marley"));
    }

    @GetMapping( path = "/person", params = "version=1")
    public PersonV1 getFirstVersionPersonParam(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionPersonParam(){
        return new PersonV2(new PersonDetails("Bob","Marley"));
    }

    @GetMapping( path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionPersonHeader(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionPersonHeader(){
        return new PersonV2(new PersonDetails("Bob","Marley"));
    }

    @GetMapping( path = "/person/accept", produces = "application/app-v1+json")
    public PersonV1 getFirstVersionPersonAcceptHeader(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping(path = "/person/accept", produces = "application/app-v2+json")
    public PersonV2 getSecondVersionPersonAcceptHeader(){
        return new PersonV2(new PersonDetails("Bob","Marley"));
    }

}
