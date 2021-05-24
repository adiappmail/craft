package com.intuit.craft.config;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataLoadConfig {

    @PostConstruct
    void setup() {



    }
}
