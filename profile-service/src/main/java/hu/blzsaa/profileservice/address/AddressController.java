package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.client.model.Address;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping("/addresses/{addressId}")
	public Address getAddressById(@PathVariable UUID addressId) {
		return addressService.getAddressById(addressId);
	}

}
