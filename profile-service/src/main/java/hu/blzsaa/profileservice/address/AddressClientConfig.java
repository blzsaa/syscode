package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.client.ApiClient;
import hu.blzsaa.profileservice.client.api.AddressesApi;
import hu.blzsaa.profileservice.client.auth.HttpBasicAuth;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressClientConfig {

	@Bean
	public AddressesApi getAddressById(@Value("${address-service.basic-auth.username}") String username,
			@Value("${address-service.basic-auth.password}") String password,
			@Value("${address-service.base-path}") String basePath) {
		ApiClient defaultClient = new ApiClient();
		HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
		basicAuth.setUsername(username);
		basicAuth.setPassword(password);
		defaultClient.setBasePath(basePath);
		return new AddressesApi(defaultClient);
	}

}
