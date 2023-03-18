package az.pasha.bank.mapper;

import az.pasha.bank.entity.Account;
import az.pasha.bank.model.dto.AccountResponseDto;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDto mapEntityToDto(Account account) {
        return AccountResponseDto.builder()
                .balance(account.getBalance())
                .number(account.getNumber())
                .build();
    }

    public List<AccountResponseDto> mapEntitiesToDtos(List<Account> accounts) {
        return accounts.stream()
                .map(this::mapEntityToDto)
                .toList();
    }
}