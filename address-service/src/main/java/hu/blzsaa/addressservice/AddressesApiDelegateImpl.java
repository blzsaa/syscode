package hu.blzsaa.addressservice;

import hu.blzsaa.addressservice.api.AddressesApiDelegate;
import hu.blzsaa.addressservice.model.Address;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AddressesApiDelegateImpl implements AddressesApiDelegate {

	@Override
	public ResponseEntity<Address> getAddressById(UUID addressId) {
		log.info("Incoming getAddressById request with id: {}", addressId);
		Address address = new Address().address("123 Fake Street").id(addressId);
		log.info("Returning address: {}", address);
		return ResponseEntity.ok(address);
	}

}
