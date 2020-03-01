package com.hibernate.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.FourWheelerVehicle;
import com.hibernate.model.Meeting;
import com.hibernate.model.Skills;
import com.hibernate.model.TwoWheelerVehicle;
import com.hibernate.model.UserDetails;
import com.hibernate.model.Vehicle;

public class HibernateUtils {

	// private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionfactory;

	static {
		Configuration config = new Configuration();
		// StandardServiceRegistryBuilder registryBuilder = new
		// StandardServiceRegistryBuilder();
		Properties properties = new Properties();
		properties.put(Environment.URL, "jdbc:mysql://localhost:3306/employees?useSSL=false");
		properties.put(Environment.USER, "root");
		properties.put(Environment.PASS, "root");
		properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
		properties.put(Environment.SHOW_SQL, "true");
		properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		properties.put(Environment.HBM2DDL_AUTO, "create");
		config.setProperties(properties);
		config.addAnnotatedClass(UserDetails.class).addAnnotatedClass(Vehicle.class).addAnnotatedClass(Skills.class)
		.addAnnotatedClass(Meeting.class).addAnnotatedClass(TwoWheelerVehicle.class).addAnnotatedClass(FourWheelerVehicle.class);
		// registryBuilder.applySettings(dbSettings);
		// standardServiceRegistry = registryBuilder.build();
		// MetadataSources sources = new MetadataSources(standardServiceRegistry);
		// Metadata metadata = sources.getMetadataBuilder().build();
		ServiceRegistry serviceregistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties())
				.build();
		// sessionfactory = metadata.getSessionFactoryBuilder().build();
		sessionfactory = config.buildSessionFactory(serviceregistry);
	}

	public static SessionFactory getSessionFactory() {
		return sessionfactory;
	}

	public static void closeSessionFactory() {
		if (sessionfactory != null)
			sessionfactory.close();
	}
}
