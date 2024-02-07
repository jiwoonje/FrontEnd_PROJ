package user;

import lombok.Data;

@Data
public class UserDTO {
	
	private String userID;
	private String password;
	private String nickName;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String role;
	
}
