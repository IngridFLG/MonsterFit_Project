package com.gym.monsterfit.shared.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    
    /**
     * El ID del usuario.
     */
    private Integer id;

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * La contraseña del usuario.
     */
    private String password;


    
}
