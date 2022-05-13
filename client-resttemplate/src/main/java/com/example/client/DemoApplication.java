package com.example.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

import java.util.Objects;

@SpringBootApplication
@Configuration
public class DemoApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(CommandLineRunner.class);

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    // Inject the OAuth authorized client service and authorized client manager
    // from the OAuthClientConfiguration class
    @Autowired
    private AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager;

    @Override
    public void run(String... args) throws Exception {
        ////////////////////////////////////////////////////
        //  STEP 1: Retrieve the authorized JWT
        ////////////////////////////////////////////////////

        // Build an OAuth2 request for the Okta provider
        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("maam")
                                                                        .principal("Demo Service")
                                                                        .build();

        // Perform the actual authorization request using the authorized client service and authorized client
        // manager. This is where the JWT is retrieved from the Okta servers.
        OAuth2AuthorizedClient authorizedClient = this.authorizedClientServiceAndManager.authorize(authorizeRequest);

        // Get the token from the authorized client object
        OAuth2AccessToken accessToken = Objects.requireNonNull(authorizedClient).getAccessToken();

        logger.info("Issued: " + accessToken.getIssuedAt().toString() + ", Expires:" + accessToken.getExpiresAt()
                                                                                                  .toString());
        logger.info("Scopes: " + accessToken.getScopes().toString());
        logger.info("Token: " + accessToken.getTokenValue());

    }
}
