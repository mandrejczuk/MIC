package pz1.MIC.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pz1.MIC.Helpers.JWTTokenHelper;
import pz1.MIC.Requests.AuthenticationRequest;
import pz1.MIC.Requests.RegistrationRequest;
import pz1.MIC.Responses.LoginResponse;
import pz1.MIC.Responses.UserInfo;
import pz1.MIC.model.Authority;
import pz1.MIC.model.Character;
import pz1.MIC.model.User;
import pz1.MIC.repository.UserRepository;
import pz1.MIC.service.CharacterService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    private final CharacterService characterService;


    public Authority createAuthority(String user, String description) {
        Authority authority = new Authority();

        authority.setRoleCode(user);
        authority.setRoleDescription(description);
        return authority;
    }

    public ResponseEntity<?> getResponseEntity(String userName, String password) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userName,
                password
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User principalUser = (User)authentication.getPrincipal();

        String jwtToken = jwtTokenHelper.generateToken(principalUser.getUsername());

        LoginResponse response = new LoginResponse(jwtToken, principalUser.getId()+"");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest registrationRequest){
        List<Authority> authorityList = new ArrayList<>();



        authorityList.add(createAuthority("USER", "user role"));
        authorityList.add(createAuthority("ADMIN", "admin role"));

        User user = new User();
        user.setUserName(registrationRequest.getUserName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEnabled(true);

        user.setAuthorities(authorityList);

        userRepository.save(user);

        return getResponseEntity(registrationRequest.getUserName(), registrationRequest.getPassword());


    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){

        return getResponseEntity(authenticationRequest.getUserName(), authenticationRequest.getPassword());
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        User userObj = (User) userDetailsService.loadUserByUsername(user.getName());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userObj.getUserName());
        userInfo.setRoleList(new ArrayList<GrantedAuthority>(userObj.getAuthorities()));
        userInfo.setCharacterId(userObj.getId());
        return ResponseEntity.ok(userInfo);
    }
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public Long currentUserId(Principal principal) {

        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());

        return  userObj.getId();
    }
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String currentUsername(Authentication authentication) {
        return authentication.getName();
    }

    @GetMapping("/character")
    public Long currentUsercharacter(Principal principal)
    {
        User userObj = (User) userDetailsService.loadUserByUsername(principal.getName());
        Character ch = characterService.findbyuserid(userObj);
        if (ch == null)
            return -1L;
        else
            return ch.getId();
    }


}
