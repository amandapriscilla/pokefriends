package imd.pokefriends.pokefriends.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import imd.pokefriends.pokefriends.dao.FriendRepository;
import imd.pokefriends.pokefriends.dao.UserRepository;
import imd.pokefriends.pokefriends.domain.Friend;
import imd.pokefriends.pokefriends.domain.User;
import imd.pokefriends.pokefriends.utils.LoginUtils;

@RestController
@RequestMapping("/api")
public class PokeFriendsController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public List<User> getUsers() {
		return userRepository.findAll();
    }
	
	
	@RequestMapping(value = "/user", method =  RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public User create(@Valid @RequestBody User user) throws Exception
    {
		user.setPassword(LoginUtils.getSafePassword(user.getPassword()));
        return userRepository.save(user);
    }
	
	@RequestMapping(value = "/login",method = RequestMethod.POST, 
			consumes = { MediaType.APPLICATION_JSON_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUser(@RequestBody User user) throws Exception {
		
		String safePassword = LoginUtils.getSafePassword(user.getPassword());
		Optional<User> loggedUser = userRepository.findByUsernameAndPassword(user.getUsername(), safePassword);
       if(loggedUser.isPresent())
           return new ResponseEntity<User>(loggedUser.get(), HttpStatus.OK);
       else
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<User> findUser(@PathVariable(value = "id") long id)
    {
        Optional<User> possibleUser = userRepository.findById(id);
        if(possibleUser.isPresent()) {
        	User user = possibleUser.get();
        	user.setFriends(friendRepository.findByUser(user));
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	@PutMapping("/users/{id}")
	public User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

	    return userRepository.findById(id)
	      .map(user -> {
	        user.setUsername(newUser.getUsername());
	        user.setFriends(newUser.getFriends());
	        return userRepository.save(user);
	      })
	      .orElseGet(() -> {
	    	newUser.setId(id);
	    	try {
	    		newUser.setPassword(LoginUtils.getSafePassword(newUser.getPassword()));
	    	} catch (Exception e) {
				// TODO: handle exception
			}
	        return userRepository.save(newUser);
	      });
	  }

	@RequestMapping(value = "/friend", method =  RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Friend create(@Valid @RequestBody Friend friend) throws Exception
    {
		User user = userRepository.getOne(friend.getUser().getId());
		friend.setUser(user);
		Optional<User> userFriend = userRepository.findByUsername(friend.getUsername());
		if (userFriend.isPresent()) {
			friend.setFriend(userFriend.get());
		}
		
        return friendRepository.save(friend);
    }
	
	
	@RequestMapping(value = "/message/{id}", method =  RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Boolean sendMessage(@RequestBody String message, @PathVariable Long id) throws Exception
    {
		try {
			User user = userRepository.getOne(id);
			if (user.getMessages() == null || user.getMessages().trim().length() == 0) {
				user.setMessages(message);
			} else {
				user.setMessages(user.getMessages() +  "\n" + message);
			}
					
	        userRepository.save(user);
	        return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
    }
}
