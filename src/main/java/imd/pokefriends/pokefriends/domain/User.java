package imd.pokefriends.pokefriends.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="user", schema="public")
public class User implements Serializable {

	@Id
	@GeneratedValue(generator = "user-seq-generator")
    @GenericGenerator(
      name = "user-seq-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
        @Parameter(name = "sequence_name", value = "id_user_seq"),
        @Parameter(name = "initial_value", value = "1"),
        @Parameter(name = "increment_size", value = "1")
        }
    )
    private long id;

    @Column(nullable = false)
    private String username;
    
	@Column(nullable = false)
    private String password;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Friend> friends;

	public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}
}
