package ntic.tlsi.safqabe.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class RegisterResponseDTO {
    private String message;

    public RegisterResponseDTO(String message) {
        this.message = message;
    }


}

