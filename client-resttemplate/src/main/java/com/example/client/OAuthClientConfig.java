package com.example.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuthClientConfig {
    // Create the Okta client registration
    @Bean
    ClientRegistration keycloakClientRegistration(
            @Value("${spring.security.oauth2.client.provider.maam.token-uri}") String token_uri,
            @Value("${spring.security.oauth2.client.registration.maam.client-id}") String client_id,
            @Value("${spring.security.oauth2.client.registration.maam.client-secret}") String client_secret,
            @Value("${spring.security.oauth2.client.registration.maam.scope}") String scope,
            @Value("${spring.security.oauth2.client.registration.maam.authorization-grant-type}") String authorizationGrantType
                                             ) {
        return ClientRegistration
                .withRegistrationId("maam")
                .tokenUri(token_uri)
                .clientId(client_id)
                .clientSecret(client_secret)
                .scope(scope)
                .authorizationGrantType(new AuthorizationGrantType(authorizationGrantType))
                .build();
    }

    // Create the client registration repository
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration oktaClientRegistration) {
        return new InMemoryClientRegistrationRepository(oktaClientRegistration);
    }

    // Create the authorized client service
    @Bean
    public OAuth2AuthorizedClientService auth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    // Create the authorized client manager and service manager using the
    // beans created and configured above
    @Bean
    public AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientServiceAndManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                                                     .clientCredentials()
                                                     .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository, authorizedClientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}
