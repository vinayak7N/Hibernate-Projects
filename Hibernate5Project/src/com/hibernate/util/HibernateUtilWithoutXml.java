package com.hibernate.util;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

/*
 * Hibernate Configuration inside Java class without the use of hibernate.cfg.xml
 */

public class HibernateUtilWithoutXml {

	private static StandardServiceRegistry standardServiceRegistry;
	private static SessionFactory sessionFactory;

	static {
		try {
			if (sessionFactory == null) {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
				Map<String,String> dbSettings=new HashMap<>();
				dbSettings.put(Environment.URL,"jdbc:postgresql://localhost:5432/HibernateDB");
				dbSettings.put(Environment.USER,"postgres");
				dbSettings.put(Environment.PASS,"nair25031996");
				dbSettings.put(Environment.DRIVER,"org.postgresql.Driver");
				dbSettings.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
				registryBuilder.applySettings(dbSettings);
				standardServiceRegistry=registryBuilder.build();
				MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
				Metadata metadata = metadataSources
						//.addResource("hibernate.cfg.xml")
						.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (standardServiceRegistry != null)
				StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
