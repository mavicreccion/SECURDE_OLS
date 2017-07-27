package test;

import java.util.Calendar;

import model.SecretQuestion;
import model.User;
import model.UserType;
import service.UserService;

public class Test {
	
	public static void main(String[] args) {
		User user = new User();
		user.setIdnumber("11427493");
		user.setUserType(UserType.LIBMNGR);
		user.setFirstName("Mavic");
		user.setMiddleInitial("B");
		user.setLastName("Reccion");
		user.setPassword("myfuckingpassword");
		user.setEmail("mavicreccion@gmail.com");
		Calendar c = Calendar.getInstance();
		c.set(1997, 8, 8);
		user.setBirthdate(c.getTime());
		SecretQuestion sq = new SecretQuestion();
		sq.setSQID(1);
		user.setSecretQuestion(sq);
		user.setSecretAnswer("bucal");
		System.out.println(UserService.registerLibStaffLibMngr(user));
	}

}
