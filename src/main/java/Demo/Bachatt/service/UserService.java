package Demo.Bachatt.service;
import Demo.Bachatt.model.User;
import Demo.Bachatt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByPhone(String email){
        return userRepository.findByPhone(email);
    }

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUid(int uid){
        return userRepository.findById(uid);
    }

    public Optional<User> editUser(User user){
        return Optional.of(userRepository.save(user));
    }

}