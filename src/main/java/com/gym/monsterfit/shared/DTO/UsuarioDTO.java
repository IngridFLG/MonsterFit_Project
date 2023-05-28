package com.gym.monsterfit.shared.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
     * La contraseña del usuario (sin encriptar).
     */
    private String password;

    /**
     * La contraseña del usuario encriptada.
     */
    private String encryptedPassword;

    
}
