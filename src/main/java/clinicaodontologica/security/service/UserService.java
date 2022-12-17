package clinicaodontologica.security.service;

import clinicaodontologica.security.config.JwtUtil;
import clinicaodontologica.security.dto.LoginRequestDTO;
import clinicaodontologica.security.dto.SignupDTO;
import clinicaodontologica.security.persistence.entities.RolName;
import clinicaodontologica.security.persistence.entities.Usuario;
import clinicaodontologica.security.persistence.repository.IUsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService implements IUserService {

    private AuthenticationService authenticationService;

    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder;

    private IUsuarioRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public UserService(AuthenticationService authenticationService, JwtUtil jwtUtil, IUsuarioRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginRequestDTO loginRequestDTO) {
        UserDetails userDetails = authenticationService.loadUserByUsername(loginRequestDTO.getUsername());

        String token;

        if (passwordEncoder.matches(loginRequestDTO.getPassword(), userDetails.getPassword())) {

            token = jwtUtil.generateToken(userDetails);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos incorrectos");
        }
        return token;
    }

    @Override
    public void signUp(SignupDTO signupDTO) {
        if (userRepository.existByUsername(signupDTO.getUsername()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ya existe");
        } else {
            rolValid(signupDTO.getRol().getRolName());
            Usuario usuario = modelMapper.map(signupDTO, Usuario.class);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            userRepository.save(usuario);
        }
    }

    private boolean rolValid(String rolName) {
        if (!rolName.matches(RolName.ADMIN.name()) && !rolName.matches(RolName.PATIENT.name()) && !rolName.matches(RolName.DENTIST.name())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rol no disponible");
        } else {
            return true;
        }
    }


}
