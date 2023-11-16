package hu.blzsaa.profileservice.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import hu.blzsaa.profileservice.model.Address;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class AddressesApiDelegateImplTest {

	@Mock
	private AddressService addressesService;

	private AddressesApiDelegateImpl underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new AddressesApiDelegateImpl(addressesService);
	}

	@Test
	void getAddressByIdShouldDelegateToService() {
		// given
		UUID uuid = UUID.randomUUID();
		doReturn(new Address().address("Asd street")).when(addressesService).getAddressById(uuid);

		// when
		var actual = underTest.getAddressById(uuid);

		// then
		assertThat(actual).isEqualTo(ResponseEntity.ok(new Address().address("Asd street")));
	}

}
