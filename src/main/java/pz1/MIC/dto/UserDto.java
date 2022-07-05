package pz1.MIC.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String login;

    private String password;

    private String email;

    private String recovery_key;

    private String username;


}
