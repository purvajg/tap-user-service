package component;

import common.RequestType;
import implementations.UserProfile;
import interfaces.IProfile;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

public class RouterComponent {

    GenericComponent genericComponent;
    public RouterComponent(GenericComponent genericComponent) {
        this.genericComponent = genericComponent;
        Spark.port(Integer.parseInt(System.getenv("PORT")));
    }
    public void route() {
        System.out.println("Initialized router");
        path("/profile", () -> {
            post("/create", ((request, response) -> process(request, response, RequestType.CREATE_PROFILE)));
            get("/get", ((request, response) -> process(request, response, RequestType.GET_PROFILE)));
        });
    }

    private String process(Request request, Response response, RequestType requestType) {
        switch (requestType) {
            case CREATE_PROFILE:
                System.out.println("Routing to profile creation");
                return genericComponent.process(request, requestType);
            case GET_PROFILE:
                System.out.println("Routing to get profile");
                return genericComponent.process(request, requestType);
            default: throw new RuntimeException("Request type "+requestType+" not implemented");
        }
    }


}
