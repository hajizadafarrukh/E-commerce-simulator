package business.abstracts;

import entities.cocnretes.User;

public interface UserService {

	void register(User user);
	
	void enterTheSystem(String email, String password);
}
