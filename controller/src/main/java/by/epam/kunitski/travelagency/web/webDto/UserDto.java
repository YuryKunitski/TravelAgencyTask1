package by.epam.kunitski.travelagency.web.webDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$", message = "{msg.register_error_reg_pass}")
    private String login;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$", message = "{msg.register_error_reg_pass}")
    private String password;

    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$", message = "{msg.register_error_reg_pass}")
    private String confirmPassword;

}
