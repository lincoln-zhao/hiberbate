package com.lincoln.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
	private static Configuration cfg = null;
	private static SessionFactory sf = null;
	
	static {
		cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory () {
		return sf;
	}
}
