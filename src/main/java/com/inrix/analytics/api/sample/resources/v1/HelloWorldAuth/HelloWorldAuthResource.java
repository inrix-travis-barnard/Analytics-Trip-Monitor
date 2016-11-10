package com.inrix.analytics.api.sample.resources.v1.HelloWorldAuth;

import com.inrix.analytics.api.RequestProperties;
import com.inrix.analytics.api.RequestType;
import com.inrix.analytics.api.ResponseType;
import com.inrix.analytics.api.auth.UasPrincipalOptional;
import com.inrix.analytics.logging.ILogContext;
import com.inrix.analytics.logging.IRequestContext;
import com.inrix.analytics.logging.LogArguments;
import com.inrix.analytics.logging.LoggerFactory;
import io.dropwizard.auth.Auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/v1/helloWorldAuth")
@Consumes(RequestType.APPLICATION_JSON_UTF8)
@Produces(ResponseType.APPLICATION_JSON_UTF8)
public class HelloWorldAuthResource {

    @POST
    public HelloWorldAuthResponse helloWorldAuth(
            @Auth UasPrincipalOptional uasPrincipal,
            HelloWorldAuthPost helloWorldPost) throws Exception {

        // get the request context
        IRequestContext requestContext = RequestProperties.get().getRequestContext();

        // log
        ILogContext logContext = LoggerFactory.getLogger().start(requestContext, "helloWorldAuth");

        LogArguments logArguments = new LogArguments();
        logArguments.add("inName", helloWorldPost.getName());
        logArguments.add("outName", uasPrincipal.getName());

        HelloWorldAuthResponse helloWorldAuthResponse = new HelloWorldAuthResponse(requestContext.getRequestId(), helloWorldPost.getName(), uasPrincipal.getName());

        logContext.stop();

        return helloWorldAuthResponse;
    }
}
