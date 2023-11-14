package hu.blzsaa.addressservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class AddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressServiceApplication.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(a -> a.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults())
			.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(@Value("${basic-auth.username}") String username,
			@Value("${basic-auth.password}") String password) {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username(username)
			.password(password)
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user);
	}

}
