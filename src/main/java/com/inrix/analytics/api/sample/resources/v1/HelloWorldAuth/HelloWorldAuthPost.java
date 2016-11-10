package com.inrix.analytics.api.sample.resources.v1.HelloWorldAuth;

public class HelloWorldAuthPost {

    // region Private Fields

    private String name;

    // endregion Private Fields

    // region Constructor

    public HelloWorldAuthPost() {

    }

    public HelloWorldAuthPost(
            String name) {
        this.name = name;
    }

    // endregion Constructor

    // region Public Properties

    public String getName() {
        return this.name;
    }

    public void setName(
            String name) {
        this.name = name;
    }

    // endregion Public Properties
}
