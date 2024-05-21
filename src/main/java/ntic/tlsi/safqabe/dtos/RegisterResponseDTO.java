package ntic.tlsi.safqabe.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter  @Setter
public class RegisterResponseDTO {
    private String message;
    private Long id;

    public RegisterResponseDTO(Long id,String message) {
        this.id = id ;
        this.message = message;
    }


}

