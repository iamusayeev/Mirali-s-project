package az.pasha.bank.mapper;


import az.pasha.bank.entity.User;
import az.pasha.bank.model.dto.UserResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final AccountMapper accountMapper;

    public UserResponseDto mapEntityToDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .accounts(accountMapper.mapEntitiesToDtos(user.getAccounts()))
                .build();
    }

    public List<UserResponseDto> mapEntitiesToDtos(List<User> users) {
        return users.stream()
                .map(this::mapEntityToDto)
                .toList();
    }
}