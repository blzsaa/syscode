package hu.blzsaa.profileservice.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import hu.blzsaa.profileservice.client.model.Address;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AddressControllerTest {

	@Mock
	private AddressService addressesService;

	private AddressController underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new AddressController(addressesService);
	}

	@Test
	void getAddressByIdShouldDelegateToService() {
		// given
		UUID uuid = UUID.randomUUID();
		doReturn(new Address().address("Asd street")).when(addressesService).getAddressById(uuid);

		// when
		var actual = underTest.getAddressById(uuid);

		// then
		assertThat(actual).isEqualTo(new Address().address("Asd street"));
	}

}
