package com.healthcare.uman.dto;

import com.healthcare.uman.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
    private UserDTO userDTO;

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
