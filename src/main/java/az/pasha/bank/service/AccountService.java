package az.pasha.bank.service;

import az.pasha.bank.entity.Account;
import az.pasha.bank.mapper.AccountMapper;
import az.pasha.bank.model.dto.AccountResponseDto;
import az.pasha.bank.model.enums.Currency;
import az.pasha.bank.model.enums.Status;
import az.pasha.bank.repository.AccountRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Account getById(Integer id) {
        return fetchAccountIfExist(id);
    }

    public List<AccountResponseDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accountMapper.mapEntitiesToDtos(accounts);
    }

    public List<AccountResponseDto> getAllWhereStatusIs(Status status) {
        List<Account> accounts = accountRepository.findAllWhereStatusIs(status);
        return accountMapper.mapEntitiesToDtos(accounts);
    }

    public List<AccountResponseDto> getAllAccountsByCurrencyAndUserId(Currency currency, Integer userId) {
        List<Account> accounts = accountRepository.findAllAccountsByCurrencyAndUserId(currency, userId);
        return accountMapper.mapEntitiesToDtos(accounts);
    }


    private Account fetchAccountIfExist(Integer id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }
}