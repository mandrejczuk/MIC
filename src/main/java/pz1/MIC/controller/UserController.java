package pz1.MIC.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pz1.MIC.dto.UserDto;
import pz1.MIC.model.User;
import pz1.MIC.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;


    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.add(user);
    }

    @GetMapping("{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
        return userService.show(id);
    }



    @DeleteMapping
    public void delete(@RequestBody Long id){
        userService.delete(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<UserDto> all(@RequestParam int page, @RequestParam int size) {

        return userService.showAll(page, size).stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }


    @PutMapping("/updateUserName")
    public String changeUserName(Principal principal,@RequestBody UserDto userDto)
    {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        userService.updateUserName(userDto.getUsername(),user.getId());
     return user.getUserName()+ userDto.getUsername();
    }


}

