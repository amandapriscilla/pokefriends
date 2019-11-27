package imd.pokefriends.pokefriends.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import imd.pokefriends.pokefriends.domain.Friend;
import imd.pokefriends.pokefriends.domain.User;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

	@Query("FROM Friend f WHERE f.user = :user")
	public List<Friend> findByUser(@Param("user") User user);
}
