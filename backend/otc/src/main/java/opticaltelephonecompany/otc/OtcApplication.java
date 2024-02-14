package opticaltelephonecompany.otc;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import opticaltelephonecompany.otc.repository.UserRepository;
import opticaltelephonecompany.otc.models.Users;
import opticaltelephonecompany.otc.models.Role;
import opticaltelephonecompany.otc.repository.RoleRepository;

@SpringBootApplication
public class OtcApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtcApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			Role role = roleRepository.save(new Role(1, "USER"));

			Set<Role> roles = new HashSet<>();

			roles.add(role);

			Users callerUser = new Users();
			callerUser.setAuthorities(roles);
			callerUser.setFirstName("Yodal");
			callerUser.setLastName("Pinky");
			callerUser.setEmailAddress("yodal@email.com");
			callerUser.setUsername("yodalpinky1");
			callerUser.setPassword("password");
			callerUser.setEnabled(true);

			userRepository.save(callerUser);
		};
	}

}
