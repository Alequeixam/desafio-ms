package br.com.challenge.msauthorization.service;

import br.com.challenge.msauthorization.exceptions.EmailAlreadyExistsException;
import br.com.challenge.msauthorization.exceptions.ResourceNotFoundException;
import br.com.challenge.msauthorization.repository.UserRepository;
import br.com.challenge.msauthorization.entity.User;
import br.com.challenge.msauthorization.payload.UserDTO;
import org.modelmapper.ModelMapper;
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
