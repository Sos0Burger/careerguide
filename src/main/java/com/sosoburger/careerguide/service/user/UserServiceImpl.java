package com.sosoburger.careerguide.service.user;

import com.sosoburger.careerguide.dao.UserDAO;
import com.sosoburger.careerguide.exception.NotFoundException;
import com.sosoburger.careerguide.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDAO user = userRepository.findByLogin(login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ login));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                authorities);
    }

    public UserDAO findById(Integer id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        throw new NotFoundException("Пользователь не найден");
    }
}
