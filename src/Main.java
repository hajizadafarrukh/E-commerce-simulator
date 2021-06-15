

import business.concretes.UserManager;
import core.concretes.GoogleManagerAdapter;
import dataAccess.concretes.HibernateUserDao;
import entities.cocnretes.User;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		

		
		User user1 = new User("Faruk","Hacizade"," ","   ");
		User user2 = new User("Fa","", "salam","salam");
		User user3 = new User("Mirza", "Ismayilov","mirza@gmail.com","1234567");
		
		UserManager userManager = new UserManager(new HibernateUserDao(), new GoogleManagerAdapter());
		userManager.register(user3);
		userManager.enterTheSystem("mirza@gmail.com", "1234567");

		
		

	}

}
