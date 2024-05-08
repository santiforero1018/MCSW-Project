package edu.eci.mcsw.services.user;

import edu.eci.mcsw.Model.enums.UserRoles;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.InvalidCredentialException;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registerNewUser(UserEnt newUser) throws InvalidCredentialException {
        if(!this.userRepository.existsByEmail(newUser.getEmail())){
            if(this.userRepository.findAll().isEmpty()){
                newUser.addRole(UserRoles.ADMIN);
            }
            this.userRepository.save(newUser);
        } else {
            throw new InvalidCredentialException(newUser.getEmail());
        }
    }

    @Override
    public UserEnt getUserByName(String name) throws UserNotFoundException {
        return this.userRepository.findByName(name).orElseThrow(() -> new UserNotFoundException(name));
    }

    @Override
    public UserEnt getUserByEmail(String email) throws UserNotFoundException {
        return this.userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserEnt> allUsers() {return this.userRepository.findAll();}

    @Override
    public void deleteUser(String email){
        if(this.userRepository.existsByEmail(email)){
            this.userRepository.deleteByEmail(email);
        } else {
            throw new UserNotFoundException();
        }
    }
    @Override
    public void updateUser(UserEnt userData) {
        if(this.userRepository.existsByName(userData.getName())){
            this.userRepository.save(userData);
        }
    }
}
