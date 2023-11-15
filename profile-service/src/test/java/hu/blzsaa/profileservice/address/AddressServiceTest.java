package hu.blzsaa.profileservice.address;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import hu.blzsaa.profileservice.client.api.AddressesApi;
import hu.blzsaa.profileservice.client.model.Address;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

class AddressServiceTest {

	@Mock
	private AddressesApi addressesApi;

	private AddressService underTest;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		underTest = new AddressService(addressesApi);
	}

	@Test
	void getAddressByIdShouldCallApiAndBlock() {
		// given
		UUID uuid = UUID.randomUUID();
		doReturn(Mono.just(new Address().address("Asd street"))).when(addressesApi).getAddressById(uuid);

		// when
		var actual = underTest.getAddressById(uuid);

		// then
		assertThat(actual).isEqualTo(new Address().address("Asd street"));
	}

}
