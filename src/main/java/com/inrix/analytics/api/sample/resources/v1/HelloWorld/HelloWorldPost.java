package com.inrix.analytics.api.sample.resources.v1.HelloWorld;

public class HelloWorldPost {

    // region Private Fields

    private String name;

    // endregion Private Fields

    // region Constructor

    public HelloWorldPost() {

    }

    public HelloWorldPost(
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
