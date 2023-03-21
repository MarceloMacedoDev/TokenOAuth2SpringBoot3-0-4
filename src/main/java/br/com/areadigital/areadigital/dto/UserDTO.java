package br.com.areadigital.areadigital.dto;

import br.com.areadigital.areadigital.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String email;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .email(user.getUsername())
                .build();
    }
}
