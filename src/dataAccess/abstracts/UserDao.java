package dataAccess.abstracts;

import java.util.List;

import entities.cocnretes.User;



public interface UserDao {

	void add(User user);
	void update(User user);
	void delete(User user);
	User get(String email);
	List<User> getAll();
}
