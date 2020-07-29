package bg.softuni.tabula.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;


}
