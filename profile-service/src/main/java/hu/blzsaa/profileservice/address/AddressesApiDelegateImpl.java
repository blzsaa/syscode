package hu.blzsaa.profileservice.address;

import hu.blzsaa.profileservice.server.api.AddressesApiDelegate;
import hu.blzsaa.profileservice.model.Address;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AddressesApiDelegateImpl implements AddressesApiDelegate {

	private final AddressService addressService;

	public AddressesApiDelegateImpl(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public ResponseEntity<Address> getAddressById(UUID addressId) {
		log.info("Incoming getAddressById request with id: {}", addressId);
		Address address = addressService.getAddressById(addressId);
		log.info("Returning address: {}", address);
		return ResponseEntity.ok(address);
	}

}
