package com.ms.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.test.beans.CustomUserPrincipal;
import com.ms.test.beans.User;
import com.ms.test.repository.UserRepository;

/**
 * UserRepository Class
 * @author Manish
 *
 */
@RestController
@RequestMapping("/users")
public class UserController implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	protected static User sessionUser = null;
	
	protected static int sessionUserId = 0;

	/**
	 * Service get user by user-id
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/getuserbyid/{userid}", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public User getUserById(@PathVariable("userid") int userid) throws Exception {
		 User result = null;
		 Optional<User> user = userRepository.findById(userid);
		 if (user.isPresent()) result = user.get();
		 return result;
	}

	/**
	 * Service get all user list
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/allusers", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public List<User> getAllUsers() throws Exception {
		return (List<User>) userRepository.findAll();
	}

	/**
	 * Service create/update user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/saveupdateuser", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public User saveUpdateUser(@RequestBody User user) throws Exception {
		//check if user_id is not in request but user is already exist in database throw error
		if (user.getUserid() < 1)
		{
			User oldUser = userRepository.findByUsername(user.getUsername());
			if (oldUser != null) throw new Exception("username already exist please try another combination");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	/**
	 * Service delete user
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping(value = "/deleteuser", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public String deleteUser(@RequestBody User user) throws Exception {
		userRepository.delete(user);
		return "User deledted";
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		sessionUser = userRepository.findByUsername(username);
		sessionUserId = sessionUser.getUserid();
		if (sessionUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomUserPrincipal(sessionUser);
	}
	
}
