package br.com.challenge.msproduct.service;

import br.com.challenge.msproduct.exception.EmailAlreadyExistsException;
import br.com.challenge.msproduct.exception.ResourceNotFoundException;
import br.com.challenge.msproduct.repository.UserRepository;
import br.com.challenge.msproduct.entity.User;
import br.com.challenge.msproduct.payload.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public User createUser(User user) {

        List<User> allCustomers = userRepository.findAll();
        for (User u: allCustomers) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new EmailAlreadyExistsException("Email already registered");
            }
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userId));
        return mapToDTO(user);
    }

    public User updateUser(Long userId, User user) {
        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userId));

        user.setId(userId);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }

    private UserDTO mapToDTO(User user) {
        return mapper.map(user, UserDTO.class);
    }
    private User mapToEntity(UserDTO userDTO) {
        return mapper.map(userDTO, User.class);
    }
}
