package core.concretes;

import GoogleAuthentication.GoogleManager;
import core.abstracts.AuthenticationService;
import entities.cocnretes.User;

public class GoogleManagerAdapter implements AuthenticationService {


	GoogleManager manager = new GoogleManager();
	@Override
	public void registerToSystem(String message) {
		
		manager.register(message);
		
	}

	@Override
	public void loginToSystem(String message) {
		manager.login(message);
		
	}

}
