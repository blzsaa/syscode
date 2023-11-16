package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.client.ApiClient;
import hu.blzsaa.profileservice.client.api.AddressesApi;
import hu.blzsaa.profileservice.client.auth.HttpBasicAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AddressClientConfig {

	@Bean
	public AddressesApi getAddressById(@Value("${address-service.basic-auth.username}") String username,
			@Value("${address-service.basic-auth.password}") String password,
			@Value("${address-service.base-path}") String basePath, RestTemplate restTemplate) {
		ApiClient defaultClient = new ApiClient(restTemplate);
		HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
		basicAuth.setUsername(username);
		basicAuth.setPassword(password);
		defaultClient.setBasePath(basePath);
		return new AddressesApi(defaultClient);
	}

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
