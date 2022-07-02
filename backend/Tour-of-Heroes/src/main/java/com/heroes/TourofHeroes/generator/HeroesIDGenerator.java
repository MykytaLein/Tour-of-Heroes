package com.heroes.TourofHeroes.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class HeroesIDGenerator implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) 
			throws HibernateException {
		Connection connection = session.connection();
		try {
	        Statement statement=connection.createStatement();

	        ResultSet rs = statement.executeQuery("select id from heroes");
        	long id = 10;

	        while(rs.next()) {
	        	if(rs.getInt("id")==id) {
	        		id++;
	        	}
	        }
	        return id;
	        

	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		return null;
	}

}
