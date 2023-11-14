package hu.blzsaa.addressservice;

import hu.blzsaa.addressservice.api.AddressesApiDelegate;
import hu.blzsaa.addressservice.model.Address;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressesApiDelegateImpl implements AddressesApiDelegate {

	@Override
	public ResponseEntity<Address> getAddressById(UUID addressId) {
		return ResponseEntity.ok(new Address().address("123 Fake Street").id(addressId));
	}

}
