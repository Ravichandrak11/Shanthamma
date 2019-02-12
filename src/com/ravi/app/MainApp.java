package com.ravi.app;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.app.hibernate.*;
import com.mccoy.util.HibernateUtil;

public class MainApp {
	private static SessionFactory factory;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Session session = HibernateUtil.getSessionFactory().openSession();
		MainApp m=new MainApp();
		University u=m.addUniversity("VTU","Belgavi","KA","560091","INDIA");
		University u1=m.addUniversity("University of the Punjab","Canal Bank","Lahore","520023","Pakistan");
		Integer c1 = m.addCustomer("Manoj", "Kumar", 4000, u);
		Integer c2 = m.addCustomer("Ravi", "Chandra", 5000, u1);
		m.listCustomer();
		m.updateCustomer(c1,8000);
		m.deleteCustomer(c2);
		m.listCustomer();
	}
	public University addUniversity(String street,String city,String state,String zipcode,String country) {
		Session session=factory.openSession();
		Transaction tx = null;
		Integer addressID = null;
		University university = null;
		try {
			tx = session.beginTransaction();
			university = new University(street, city, state, zipcode,country);
			addressID = (Integer) session.save(university);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			}finally {
			session.close();
			}
		return university;
	}
	public Integer addCustomer(String cname, String email,int balance, University university){
			Session session = factory.openSession();
			Transaction tx = null;
			Integer customerID = null;
			try {
				tx = session.beginTransaction();
				Customer customer = new Customer(cname, email, balance, university);
				customerID = (Integer) session.save(customer);
				tx.commit();
			} catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace();
				}finally {
				session.close();
				}
			return customerID;
		}
	public void listCustomer( ){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List customer = session.createQuery("FROM CUSTOMER").list();
			for (Iterator iterator =
			customer.iterator(); iterator.hasNext();){
			Customer customer1 = (Customer) iterator.next();
			System.out.print("Name: " + customer1.getCname());
			System.out.print("Email: " + customer1.getEmail());
			System.out.println("Balance: " + customer1.getBalance());
			
			University add = ((Customer) customer).getUniversity();
			System.out.println("Address ");
			System.out.println("\tStreet: " + add.getStreet());
			System.out.println("\tCity: " + add.getCity());
			System.out.println("\tState: " + add.getState());
			System.out.println("\tZipcode: " + add.getZipcode());
			System.out.println("\t Country: " + add.getCountry());
		}
			tx.commit();
	} catch (HibernateException e) {
		if (tx!=null) tx.rollback();
		e.printStackTrace();
		}finally {
		session.close();
		}
		}
	public void updateCustomer(Integer CustomerID, int balance ){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Customer customer =
					(Customer)session.get(Customer.class, CustomerID);
			customer.setBalance( balance );
			session.update(customer);
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace();
			}finally {
			session.close();
			}
			}
	public void deleteCustomer(Integer CustomerID){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
		tx = session.beginTransaction();
		Customer customer =
				(Customer)session.get(Customer.class, CustomerID);
				session.delete(customer);
				tx.commit();
	} catch (HibernateException e) {
		if (tx!=null) tx.rollback();
		e.printStackTrace();
		}finally {
		session.close();
		}
		}
}

