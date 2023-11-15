package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.client.api.AddressesApi;
import hu.blzsaa.profileservice.client.model.Address;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AddressService {

	private final AddressesApi addressesApi;

	public AddressService(AddressesApi addressesApi) {
		this.addressesApi = addressesApi;
	}

	public Address getAddressById(UUID addressId) {
		return addressesApi.getAddressById(addressId).block();
	}

}
