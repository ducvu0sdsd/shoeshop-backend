package com.shoeshop.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.shoeshop.models.Brand;
import com.shoeshop.models.Cart;
import com.shoeshop.models.ColorSize;
import com.shoeshop.models.FeedBack;
import com.shoeshop.models.Guest;
import com.shoeshop.models.Image;
import com.shoeshop.models.OrderBuy;
import com.shoeshop.models.OrderImport;
import com.shoeshop.models.Order_Item;
import com.shoeshop.models.Product;
import com.shoeshop.models.Supplier;
import com.shoeshop.models.UserOne;



public class MySessionFactory {
	
	private SessionFactory factory = null;
	private Session session = null;
	
	public SessionFactory getSessionFactory () {
		StandardServiceRegistry registry =  new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml")
				.build();
		Metadata meta = new MetadataSources(registry)
				.addAnnotatedClass(Brand.class)
				.addAnnotatedClass(ColorSize.class)
				.addAnnotatedClass(Image.class)
				.addAnnotatedClass(Order_Item.class)
				.addAnnotatedClass(OrderBuy.class)
				.addAnnotatedClass(OrderImport.class)
				.addAnnotatedClass(Product.class)
				.addAnnotatedClass(UserOne.class)
				.addAnnotatedClass(Supplier.class)
				.addAnnotatedClass(Guest.class)
				.addAnnotatedClass(FeedBack.class)
				.addAnnotatedClass(Cart.class)
				.getMetadataBuilder()
				.build();
		SessionFactory sessionFactory = meta
				.getSessionFactoryBuilder()
				.build();
		return sessionFactory;
	}
	
	public Session getSession() {
		factory = new MySessionFactory().getSessionFactory();
		session = factory.openSession();
		return session;
	}
}
