package az.pasha.bank.controller;

import az.pasha.bank.entity.Account;
import az.pasha.bank.model.dto.AccountResponseDto;
import az.pasha.bank.model.enums.Currency;
import az.pasha.bank.model.enums.Status;
import az.pasha.bank.service.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public void save(@RequestBody Account account) {
        accountService.save(account);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Integer id) {
        return accountService.getById(id);
    }

    @GetMapping
    public List<AccountResponseDto> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/status")
    public List<AccountResponseDto> getAllWhereStatusIs(@RequestParam Status status) {
        return accountService.getAllWhereStatusIs(status);
    }

    @GetMapping("{userId}/currency")
    public List<AccountResponseDto> getAllAccountsByCurrencyAndUserId(@RequestParam Currency currency, @PathVariable Integer userId) {
        return accountService.getAllAccountsByCurrencyAndUserId(currency, userId);
    }
}