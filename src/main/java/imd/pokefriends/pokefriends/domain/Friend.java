package imd.pokefriends.pokefriends.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="friend", schema="public")
public class Friend implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	private String username;
	
	@Column(name="friendship_level")
	private int friendshipLevel;
	
	@Column(name="days_to_upgrade")
	private int daysToUpgrade;
	
	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable=false)
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_user_friend", nullable=true)
	private User friendUser;

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
	
	public User getFriendUser() {
		return friendUser;
	}

	public void setFriendUser(User friend) {
		this.friendUser = friend;
	}
	
	
}
