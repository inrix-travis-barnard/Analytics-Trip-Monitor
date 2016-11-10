package com.inrix.analytics.api.sample.resources.v1.HelloWorld;

import com.inrix.analytics.api.RequestProperties;
import com.inrix.analytics.api.RequestType;
import com.inrix.analytics.api.ResponseType;
import com.inrix.analytics.logging.ILogContext;
import com.inrix.analytics.logging.IRequestContext;
import com.inrix.analytics.logging.LogArguments;
import com.inrix.analytics.logging.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/v1/helloWorld")
@Consumes(RequestType.APPLICATION_JSON_UTF8)
@Produces(ResponseType.APPLICATION_JSON_UTF8)
public class HelloWorldResource {

    @POST
    public HelloWorldResponse helloWorld(
            HelloWorldPost helloWorldPost) throws Exception {

        // get the request context
        IRequestContext requestContext = RequestProperties.get().getRequestContext();

        // log
        ILogContext logContext = LoggerFactory.getLogger().start(requestContext, "helloWorld");

        LogArguments logArguments = new LogArguments();
        logArguments.add("inName", helloWorldPost.getName());

        HelloWorldResponse helloWorldResponse = new HelloWorldResponse(requestContext.getRequestId(), helloWorldPost.getName(), helloWorldPost.getName());

        logContext.stop();

        return helloWorldResponse;
    }
}
