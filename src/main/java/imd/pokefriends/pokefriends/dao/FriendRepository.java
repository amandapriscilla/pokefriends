package imd.pokefriends.pokefriends.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imd.pokefriends.pokefriends.domain.Friend;
import imd.pokefriends.pokefriends.domain.User;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	public List<Friend> findByUser(User user);
}
