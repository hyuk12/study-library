package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.user.UserServiceV1;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    private final UserServiceV1 userServiceV1;
    private final FruitService fruitService;

    public UserController(UserServiceV1 userServiceV1, @Qualifier("main") FruitService fruitService) {

        this.userServiceV1 = userServiceV1;
        this.fruitService = fruitService;
    }

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userServiceV1.saveUser(request);
    }

    @GetMapping("/user") // GET /user"
    public List<UserResponse> getUsers(){
        return userServiceV1.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userServiceV1.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userServiceV1.deleteUser(name);

    }

}
