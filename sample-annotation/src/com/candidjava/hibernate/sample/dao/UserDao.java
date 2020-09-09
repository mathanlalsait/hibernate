package com.candidjava.hibernate.sample.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDao 
{
	private static boolean login(String name, String pass) 
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool
		
		Session session = factory.openSession(); // single connection
		
		Criteria ct=session.createCriteria(User.class); // select * from std
					
								ct.add(Restrictions.eq("username", name));		// where username=? 
								ct.add(Restrictions.eq("password", pass));    // and password=?
				User user=(User)	ct.uniqueResult();                            // limit=1
			
			if(user==null)
			{
				return false;
			}
				System.out.println(user.getUsername() +"   " +user.getPassword() );
			
		
		session.close();
		
		System.out.println("record inserted");
		return true;
	}
	
private static void getByuserName(String name) 
{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool
		
		Session session = factory.openSession(); // single connection
		
			User userList=	 (User) session.createCriteria(User.class)
					.add(Restrictions.eq("username", name))
					.uniqueResult();
			
			//for (User user : userList) {
				
				System.out.println(userList.getUsername() +"   " +userList.getPassword() );
			//}
		
		session.close();
		
		System.out.println("record inserted");
	}
	
private static void getAlluser() {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool
		
		
		Session session = factory.openSession(); // single connection
		
		Criteria c=	session.createCriteria(User.class);
		c.add(Restrictions.eq("city", "chennai")); // where city=chennai
		c.add(Restrictions.eq("gender", "Male")) // and gender ='Male'
		.addOrder(Order.asc("id")) // and orderby asc id
		.setFirstResult(26)  // limit 26,51
		.setMaxResults(25);
		
		List<User> userList=c.list();
//c.list();
		
		userList=	session.createSQLQuery("select * from std").addEntity(User.class).list();
		
		session.createQuery("from user where username=:x").setString("x", "mathan"). list();
		
		
		/*
		 * // c.add(Restrictions.eq("gender", "MALE"));
		 * c.add(Restrictions.in("city", new String[] {"Chennai","Tiruchy"}));
		 * c.add(Restrictions.between("age", 30, 50));
		 * 
		 * c.addOrder(Order.asc("id")); //ordering 
		 * 
		 *  c.setFirstResult(101); c.setMaxResults(10); // pagination
		 */
				
				
			
		for (User user : userList) {
				
				System.out.println(user.getUsername() +"   " +user.getPassword() );
			}
		
		session.close();
		
		System.out.println("record inserted");
	}
	
	private static void getuser() 
	{
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool
		
		Session session = factory.openSession(); // single connection
		//Transaction tx=session.beginTransaction();
		
		User u1=(User) session.load(User.class, 1l);  // load
		
		System.out.println("---------------------------------");
		//System.out.println(u1.getUsername());
		
		//tx.commit();
		
		
		System.out.println(u1.getUsername());
		System.out.println(u1.getPassword());
		System.out.println("record retrived");
		session.close();
	}
	
	private static void insert() {
	
		User u=new User();
		u.setUsername("mathan");
		u.setPassword("12233");
		u.setAge(21);
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool - 5
		
		Session session = factory.openSession(); // single connection
		
		Transaction tx=session.beginTransaction();// setAutoCommit(false)
		
			session.save(u);  // insert into std values(id,?,?);
		
		tx.commit();
		session.close();
		
		System.out.println("record inserted");
	}
	
	private static void updateUser() {
		User u=new User();
		u.setPassword("123456"); // new password
		u.setId(2l);
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool
		
		Session session = factory.openSession(); // single connection
		
		Transaction tx=session.beginTransaction();
		
		User u1=(User) session.load(User.class, u.getId()); //2
		u1.setPassword(u.getPassword());
		
			session.update(u1);  // 2 , candid, 123456
		
		tx.commit();
		session.close();
		
		System.out.println("record inserted");
	}
	
	
	private static void deleteUser() {
		User u=new User();
		
		u.setId(2l);
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory(); // driver, connectionpool
		
		Session session = factory.openSession(); // single connection
		
		Transaction tx=session.beginTransaction();
		
		//User u1=(User) session.load(User.class, u.getId());
		
			session.delete(u);  //
		
		tx.commit();
		session.close();
		
		System.out.println("record inserted");
	}
	public static void main(String[] args) {
		UserDao.insert();
	}	
}
