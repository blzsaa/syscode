package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.client.api.AddressesApi;
import hu.blzsaa.profileservice.client.model.Address;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddressService {

	private final AddressesApi addressesApi;

	public AddressService(AddressesApi addressesApi) {
		this.addressesApi = addressesApi;
	}

	public Address getAddressById(UUID addressId) {
		log.info("Sending getAddressById request to address-service with id: {}", addressId);
		Address address = addressesApi.getAddressById(addressId);
		log.info("Address-service returned with address: {}", address);
		return address;
	}

}
