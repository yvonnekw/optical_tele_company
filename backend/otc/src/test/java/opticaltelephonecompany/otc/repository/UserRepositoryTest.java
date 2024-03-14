package opticaltelephonecompany.otc.repository;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import opticaltelephonecompany.otc.models.Role;
import opticaltelephonecompany.otc.models.Users;


public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;
    @Autowired
    private RoleRepository roleRepositoryTest;
    //@Autowired
   //private RoleRepository roleRepository;
   // @Autowired
   // private UserRepository userRepository;
 

    @Test
    void testFindByUsername() {


       // @Autowired
       // private final PasswordEncoder passwordEncoder;
        //given
         int role =1; //roleRepositoryTest.exists(new Role(1, "USER"));
        /*     Users user = new Users(
        "password",
        	 "firstName",
        	"lastName",
        	"emailAddress@mail.com",
        	"telephone",
          [
        role
        ]
        );*/

        // @Autowired
        PasswordEncoder passwordEncoder ;
            
        };

        String password = "pwd";

      //  String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepositoryTest.findByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();
        // Set<Telephone> telephone = new HashSet<>();
        //Address address = new Address();

        //authorities.add(userRole);

        /*Users registerUser = new Users(
        "username",
        "password", 
        "emailAddress", 
        "telephone")
        
        }*/

/* 
			Users callerUser = new Users();
            callerUser.setAuthorities(authorities);
            callerUser.setFirstName("Dona");
            callerUser.setLastName("Zamp");
            callerUser.setEmailAddress("dona@email.com");
            callerUser.setUsername("dona1");
            // callerUser.setPassword("password");
            callerUser.setPassword(encodedPassword);
            callerUser.setEnabled(true);

            userRepository.save(callerUser);*/

   // }


}
