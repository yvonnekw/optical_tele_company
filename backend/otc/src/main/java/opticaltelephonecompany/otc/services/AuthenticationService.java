package opticaltelephonecompany.otc.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import opticaltelephonecompany.otc.dto.LoginResponseDto;
import opticaltelephonecompany.otc.models.Address;
import opticaltelephonecompany.otc.models.Users;
import opticaltelephonecompany.otc.models.Role;
import opticaltelephonecompany.otc.repository.RoleRepository;
import opticaltelephonecompany.otc.repository.UserRepository;


public interface AuthenticationService {

    public Users registerUser(String username, String password, String emailAddress, String telephone);

    public LoginResponseDto loginUser(String username, String password);

}
