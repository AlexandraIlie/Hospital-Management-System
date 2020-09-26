package de.hshn.mi.pdbg.basicservice;


import de.hshn.mi.pdbg.exception.ServiceException;
import de.hshn.mi.pdbg.exception.StoreException;
import model.BasicDBServiceImpl;

import java.sql.SQLException;

/**
 * {@link BasicDBServiceFactory} define a static factory method in order to create an instance of a
 * {@link BasicDBService} object.
 * @version 1.0
 */
public class BasicDBServiceFactory
{
	/**
	 * Factory method in order to create an instance of an {@link BasicDBService} object.
	 * @return instance of a {@link BasicDBService} object
	 */
	public static BasicDBService createBasicDBService() {
		//The code needed to instantiate an implementation of a BasicDBService
		try {
			return new BasicDBServiceImpl();
		} catch (Exception e) {
			throw new ServiceException(e);
		}


		//throw exception

	}
}
		
			
			
		
