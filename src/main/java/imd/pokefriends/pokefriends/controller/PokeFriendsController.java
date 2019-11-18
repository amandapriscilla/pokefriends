package imd.pokefriends.pokefriends.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import imd.pokefriends.pokefriends.dao.UserRepository;
import imd.pokefriends.pokefriends.domain.User;
import imd.pokefriends.pokefriends.utils.LoginUtils;

@RestController
@RequestMapping("/api")
public class PokeFriendsController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginUtils loginUtils;
	
	@RequestMapping(value = "/user", method =  RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public User Post(@Valid @RequestBody User user) throws Exception
    {
		user.setPassword(loginUtils.getSafePassword(user.getPassword()));
        return userRepository.save(user);
    }
	
	@RequestMapping(value = "/login",method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUser(@RequestBody User user) throws Exception {
		
		String safePassword = loginUtils.getSafePassword(user.getPassword());
		Optional<User> loggedUser = userRepository.findByUsernameAndPassword(user.getUsername(), safePassword);
       if(loggedUser.isPresent())
           return new ResponseEntity<User>(loggedUser.get(), HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> findUser(@PathVariable(value = "id") long id)
    {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


	
}
