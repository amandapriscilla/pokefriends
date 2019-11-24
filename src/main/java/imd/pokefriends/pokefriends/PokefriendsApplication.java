package imd.pokefriends.pokefriends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class PokefriendsApplication {
	
	@RequestMapping("/")
    @ResponseBody
    String home() {
      return "Pokefriends API!";
    }

	public static void main(String[] args) {
		SpringApplication.run(PokefriendsApplication.class, args);
	}

}
