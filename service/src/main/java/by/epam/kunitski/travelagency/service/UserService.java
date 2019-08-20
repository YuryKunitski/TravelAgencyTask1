package by.epam.kunitski.travelagency.service;


import by.epam.kunitski.travelagency.dao.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends EntityService<User>, UserDetailsService {

}
