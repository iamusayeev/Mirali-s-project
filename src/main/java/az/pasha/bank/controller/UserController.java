package az.pasha.bank.controller;

import az.pasha.bank.entity.User;
import az.pasha.bank.model.dto.UserResponseDto;
import az.pasha.bank.model.enums.Status;
import az.pasha.bank.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }


    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/find-by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}