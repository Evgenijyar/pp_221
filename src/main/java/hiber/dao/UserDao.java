package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public interface UserDao {
   void add(User user);
   User getUser(String model, int series);
   List<User> listUsers();
}
