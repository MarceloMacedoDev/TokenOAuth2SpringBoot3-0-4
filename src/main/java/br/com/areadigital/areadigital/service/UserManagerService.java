package br.com.areadigital.areadigital.service;

import br.com.areadigital.areadigital.model.User;
import br.com.areadigital.areadigital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserManagerService implements UserDetailsService   {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    
    public User createUser(UserDetails user) {
        ((User) user).setPassword(passwordEncoder.encode(user.getPassword()));
       return  userRepository.save((User) user);
    }

    
    public void updateUser(UserDetails user) {

    }

    
    public void deleteUser(String username) {

    }

    
    public void changePassword(String oldPassword, String newPassword) {

    }

    
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("User with username {0} not found", username)
                ));
    }
}
