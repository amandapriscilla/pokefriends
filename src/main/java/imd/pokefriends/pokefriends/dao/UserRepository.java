package imd.pokefriends.pokefriends.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imd.pokefriends.pokefriends.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsernameAndPassword(String login, String password);
	
	public Optional<User> findByUsername(String login);

}
