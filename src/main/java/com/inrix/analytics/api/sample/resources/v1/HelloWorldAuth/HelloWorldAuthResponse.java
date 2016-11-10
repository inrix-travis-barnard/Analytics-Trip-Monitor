package com.inrix.analytics.api.sample.resources.v1.HelloWorldAuth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"responseId", "type", "inName", "outName"})
public class HelloWorldAuthResponse {

    // region Private Fields

    private final static String type = "helloWorldAuth";

    private final String responseId;
    private final String inName;
    private final String outName;

    // endregion Private Fields

    // region Constructor

    public HelloWorldAuthResponse(
            String responseId,
            String inName,
            String outName) {
        this.responseId = responseId;
        this.inName = inName;
        this.outName = outName;
    }

    // endregion Constructor

    // region Public Properties

    public String getResponseId() {
        return this.responseId;
    }

    public String getType() {
        return type;
    }

    public String getInName() {
        return this.inName;
    }

    public String getOutName() {
        return this.outName;
    }

    // endregion Public Properties
}
