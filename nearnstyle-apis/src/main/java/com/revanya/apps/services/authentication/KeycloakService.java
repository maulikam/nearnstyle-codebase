package com.revanya.apps.services.authentication;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class KeycloakService {

    @Inject
    Config config; // Inject the Config object

    private Client client = ClientBuilder.newClient();

    @ConfigProperty(name = "quarkus.oidc.client-id")
    String clientId;

    @ConfigProperty(name = "quarkus.oidc.credentials.secret")
    String clientSecret;

    public String getTokenForUser(String mobileNumber) {
        String keycloakUrl = config.getValue("quarkus.oidc.auth-server-url", String.class);
        String realm = config.getValue("keycloak.realm", String.class);

        Form form = new Form()
                .param("grant_type", "password")
                .param("client_id", clientId)
                .param("username", mobileNumber) // Use mobileNumber as the username
                .param("password", "") // Provide an empty password
                .param("client_secret", clientSecret);

        Response response = client.target(keycloakUrl + "/realms/" + realm + "/protocol/openid-connect/token")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.form(form));

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed to authenticate user with Keycloak: HTTP error code " + response.getStatus());
        }

        String token = response.readEntity(String.class);
        return token;
    }

    // Add other methods for other Keycloak interactions if needed.
}
