package br.com.challenge.msnotification.repository;

import br.com.challenge.msnotification.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
