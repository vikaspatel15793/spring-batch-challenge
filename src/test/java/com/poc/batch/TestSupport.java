package com.poc.batch;

import com.poc.batch.entities.StoreOrder;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class TestSupport extends Assert {

    public TestSupport() {
    }

    static final String BASE_OAUTH_URI = "https://adix.com:8443";
    static final String BASE_URI = "http://localhost";
    static final String ACCESS_TOKEN = "access_token";
    static final Object CLIENT_ID_VALUE = "adix-client";
    static final Object GRANT_TYPE_PASSWORD = "password";
    static final Object GRANT_TYPE_CLIENT = "client_credentials";
    static final Object USERNAME = "adix@adix.com";
    static final Object PASSWORD = "com-adix";
    static final Object CLIENT_SECRET_VALUE = "fb79dff0-b096-4b39-a6d5-683718fd538e";

    protected JSONObject getTokenByPasswordFlow() throws JSONException {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("client_id", CLIENT_ID_VALUE)
                        .formParam("client_secret", CLIENT_SECRET_VALUE)
                        .formParam("grant_type", GRANT_TYPE_PASSWORD)
                        .formParam("username", USERNAME)
                        .formParam("password", PASSWORD)
                        .when()
                        .post(BASE_OAUTH_URI + "/auth/realms/adix/protocol/openid-connect/token");
        return new JSONObject(response.getBody().asString());
    }

    protected JSONObject getTokenByClientFlow() throws JSONException {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("client_id", CLIENT_ID_VALUE)
                        .formParam("client_secret", CLIENT_SECRET_VALUE)
                        .formParam("grant_type", GRANT_TYPE_CLIENT)
                        .when()
                        .post(BASE_OAUTH_URI + "/auth/realms/adix/protocol/openid-connect/token");
        return new JSONObject(response.getBody().asString());
    }

    protected StoreOrder getStoreOrder(Long id) {
        return StoreOrder.builder().id(id).customerId("customer 1").build();
    }

    protected List<StoreOrder> getListDummies() {
        List<StoreOrder> storeOrders = new ArrayList<>();
        storeOrders.add(getStoreOrder(0L));
        return storeOrders;
    }


}
