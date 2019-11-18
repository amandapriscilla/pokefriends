package imd.pokefriends.pokefriends.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Friend implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	private String username;
	
	private int friendshipLevel;
	
	private int daysToUpgrade;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_user_friend", nullable=true)
	private User friend;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFriendshipLevel() {
		return friendshipLevel;
	}

	public void setFriendshipLevel(int friendshipLevel) {
		this.friendshipLevel = friendshipLevel;
	}

	public int getDaysToUpgrade() {
		return daysToUpgrade;
	}

	public void setDaysToUpgrade(int daysToUpgrade) {
		this.daysToUpgrade = daysToUpgrade;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}
	
	
}
