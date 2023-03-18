package az.pasha.bank.service;

import static az.pasha.bank.model.constants.ExceptionConstants.*;

import az.pasha.bank.entity.User;
import az.pasha.bank.mapper.UserMapper;
import az.pasha.bank.model.constants.ExceptionConstants;
import az.pasha.bank.model.dto.UserResponseDto;
import az.pasha.bank.model.enums.Status;
import az.pasha.bank.model.exception.NotFoundException;
import az.pasha.bank.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<UserResponseDto> findAll() {
        return userMapper.mapEntitiesToDtos(userRepository.findAll());

    }

    public UserResponseDto getById(Integer id) {
        return userMapper.mapEntityToDto(fetchUserIfExist(id));
    }

    public UserResponseDto getByEmail(String email) {
        User user = userRepository.findByEmailContaining(email)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, "email" + email), NOT_FOUND_CODE));
        return userMapper.mapEntityToDto(user);
    }

    public void updateUser(Integer id, User userDto) {
        User user = fetchUserIfExist(id);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setBirthDate(userDto.getBirthDate());
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.delete(fetchUserIfExist(id));
    }

    private User fetchUserIfExist(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_MESSAGE, "id " + id),
                NOT_FOUND_CODE));
    }
}