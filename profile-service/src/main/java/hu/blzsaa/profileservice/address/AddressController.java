package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.client.model.Address;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("/addresses/{addressId}")
	public Address getAddressById(@PathVariable UUID addressId) {
		log.info("Incoming getAddressById request with id: {}", addressId);
		Address address = addressService.getAddressById(addressId);
		log.info("Returning address: {}", address);
		return address;
	}

}
