package clinicaodontologica.security.service;

import clinicaodontologica.security.config.JwtUtil;
import clinicaodontologica.security.dto.LoginRequestDTO;
import clinicaodontologica.security.dto.SignupDTO;
import clinicaodontologica.security.persistence.entities.Usuario;
import clinicaodontologica.security.persistence.repository.IUsuarioRepository;
import clinicaodontologica.utils.UsuarioFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUsuarioRepository iUsuarioRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;

    @BeforeTestClass
    public void setUp() {
        authenticationService = new AuthenticationService(iUsuarioRepository, bCryptPasswordEncoder);
        jwtUtil = new JwtUtil();

    }

    @Test
    void login() {

        //arrange
        LoginRequestDTO loginRequestDTO = UsuarioFactory.createRequest();

        UserDetails userDetails = UsuarioFactory.getUserDetails();

        //act
        when(authenticationService.loadUserByUsername(loginRequestDTO.getUsername())).thenReturn(userDetails);
        when(bCryptPasswordEncoder.matches(loginRequestDTO.getPassword(), userDetails.getPassword())).thenReturn(true);
        userService.login(loginRequestDTO);

        //assert

        verify((authenticationService), times(1)).loadUserByUsername(any());

    }

    @Test
    void signUp() {
        //arrange

        SignupDTO expected = UsuarioFactory.createSignUpDTO();
        Usuario usuario = UsuarioFactory.getUser();

        //act

        when(iUsuarioRepository.save(any())).thenReturn(usuario);
        userService.signUp(expected);

        //assert

        verify(iUsuarioRepository, times(1)).save(any());
    }
}