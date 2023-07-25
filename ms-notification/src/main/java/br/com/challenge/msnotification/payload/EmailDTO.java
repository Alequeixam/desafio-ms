package br.com.challenge.msnotification.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    @NotBlank
    private String fromName;
    @NotBlank
    private String fromEmail;
    private String replyTo;
    @NotBlank
    private String toEmail;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    private String contentType;

}