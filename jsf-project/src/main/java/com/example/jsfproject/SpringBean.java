package com.example.jsfproject;

import org.springframework.stereotype.Component;

@Component
public class SpringBean {

private String welcomeMessage = "Populated by spring created bean";

public String getWelcomeMessage() {
    return welcomeMessage;
}
}