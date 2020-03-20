package com.example.jsfproject;

import javax.annotation.ManagedBean;

@ManagedBean
public class JsfBean {

private String welcomeMessage = "Populated by JSF created bean";

public String getWelcomeMessage() {
    return welcomeMessage;
}
}