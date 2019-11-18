package imd.pokefriends.pokefriends.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="friend", schema="public")
public class Friend implements Serializable {

	@Id
	@GeneratedValue(generator = "friend-seq-generator")
    @GenericGenerator(
      name = "friend-seq-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "id_friend_seq"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
    private long id;

	private String username;
	
	@Column(name="friendship_level")
	private int friendshipLevel;
	
	@Column(name="days_to_upgrade")
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
