package com.ivoiceafrica.ivoiceafrica.IdGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.ivoiceafrica.ivoiceafrica.utility.RandomIdGenerator;

public class FreelancerDeliveryAttachmentGenerator implements IdentifierGenerator {
	
	private String prefix;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) 
				throws HibernateException {
		
		prefix = RandomIdGenerator.generateId(3);
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(attachment_id) as Id from delivery_freelancer_attachments");

            if(rs.next())
            {
                int id=rs.getInt(1)+101;
                String generatedId = prefix + Integer.toString(id);//new Integer(id).toString() depreciated, use this instead Integer.toString(id);
                System.out.println("Generated Id: " + generatedId);
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
	
	
//	@Override
//    public void configure(Type type, Properties properties, 
//      ServiceRegistry serviceRegistry) throws MappingException {
//        prefix = properties.getProperty("prefix");
//    }
	
}

