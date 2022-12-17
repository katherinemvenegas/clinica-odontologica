package clinicaodontologica.security.service;

import clinicaodontologica.security.dto.LoginRequestDTO;
import clinicaodontologica.security.dto.SignupDTO;

public interface IUserService {

    String login(LoginRequestDTO loginRequestDTO);

    void signUp(SignupDTO signupDTO);
}
