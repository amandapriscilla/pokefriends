package imd.pokefriends.pokefriends.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imd.pokefriends.pokefriends.domain.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	
}
