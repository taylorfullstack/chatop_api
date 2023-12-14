package com.chatop.api.controller;

import com.chatop.api.dto.UserDTO;
import com.chatop.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Read - Get one user
     * @param id The id of the user
     * @return A User DTO fulfilled
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO getUser(@PathVariable("id") final Long id) {
        return userService.getUserById(id);
    }
}
