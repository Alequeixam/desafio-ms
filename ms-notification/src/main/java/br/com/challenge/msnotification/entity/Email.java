package br.com.challenge.msnotification.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_email")
public class Email implements Serializable {
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailId;
    private String fromEmail;
    private String fromName;
    private String toEmail;
    private String replyTo;
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String body;
    private String contentType;
}
