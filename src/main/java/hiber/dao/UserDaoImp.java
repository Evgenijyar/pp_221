package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUser (String model, int series) {
      User user = null;
      try {
         String HQL="from Car as car where car.model = :carModel and car.series = :carSeries";
         Car car = sessionFactory.getCurrentSession().createQuery(HQL, Car.class)
                 .setParameter("carModel", model)
                 .setParameter("carSeries", series)
                 .uniqueResult();
         user = car.getUser();
      } catch (HibernateException e) {
         e.printStackTrace();
      }
      return user;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
