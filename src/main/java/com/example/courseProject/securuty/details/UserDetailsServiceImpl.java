package com.example.courseProject.securuty.details;

import com.example.courseProject.model.Client;
import com.example.courseProject.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Client client = clientRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("Client not found"));
        return new UserDetailsImpl(client);
    }
}
