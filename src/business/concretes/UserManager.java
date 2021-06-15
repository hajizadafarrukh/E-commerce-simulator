package business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import business.abstracts.UserService;
import core.abstracts.AuthenticationService;
import dataAccess.abstracts.UserDao;
import entities.cocnretes.User;

public class UserManager implements UserService {
	
	
	

	public final Pattern EmailChechker = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
	
	private List<String> Emails = new ArrayList<String>();
	private List<String> Passwords = new ArrayList<String>();
	private List<User> Users = new ArrayList<User>();
	
	
	private UserDao userDao;
	private AuthenticationService authService;

	public UserManager(UserDao userDao,AuthenticationService authService) {
		
		this.userDao = userDao;
		this.authService= authService;
	}
	


	@Override
	public void register(User user) {

		if (!checkEmailAndPassword(user)) {
			System.out.println("email parola bos birakildi");
		} else if (!checkNameAndSurname(user)) {
			System.out.println("Isim ve Soyisim 2 harften kucuk olamaz");
		} else if (!checkPassword(user)) {
			System.out.println("Sifre 6 karakterden kucuk olamaz");
		} else if (!checkEmailFormat(user)) {
			System.out.println("Email formati yanlistir tekrar kontrol ediniz");

		} else {

			System.out.println("Sayin " + user.getFirstName() + " " + user.getLastName() + " Siz basariyla kaydoldunuz");
			
			this.userDao.add(user);
			this.authService.registerToSystem("kullanici eklendi "+ user.getFirstName());
			
			// tekrarlanan maili bulmani yazmadim onu zoomda yaz
			Emails.add(user.getEmail());
			Passwords.add(user.getPassword());
			Users.add(user);
		}

	}

	@Override
	public void enterTheSystem(String email,String password) {
		
		for(User us: Users) {
			if(us.getEmail()==email && us.getPassword()==password) {
				System.out.println("Sayin "+ us.getFirstName()+" "+ us.getLastName()+" sisteme basarili giris yaptiniz");
				this.authService.loginToSystem("Giris yapildi "+us.getFirstName());
			}else {
				System.out.println("Haatali giris, bilgilerinizi tekrar kontrol ediniz lutfen");
			}
		}
	}

	private boolean checkEmailAndPassword(User user) {
		if (user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getEmail().isBlank()
				|| user.getPassword().isBlank()) {
			return false;
		} else {
			return true;
		}

	}

	private boolean checkPassword(User user) {
		if (user.getPassword().length() < 6) {
			return false;
		} else {
			return true;
		}

	}

	private boolean checkNameAndSurname(User user) {
		if (user.getFirstName().length() < 2 || user.getLastName().length() < 2) {
			return false;
		} else {
			return true;
		}

	}

	private boolean checkEmailFormat(User user) {
		Matcher matcher = EmailChechker.matcher(user.getEmail());
		return matcher.matches();
	}

}
