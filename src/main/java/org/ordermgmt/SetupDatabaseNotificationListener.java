package org.ordermgmt;


import org.apache.commons.dbutils.QueryRunner;
import org.mule.api.context.notification.MuleContextNotificationListener;
import org.mule.context.notification.MuleContextNotification;
import org.mule.transport.jdbc.JdbcConnector;

public class SetupDatabaseNotificationListener implements
		MuleContextNotificationListener<MuleContextNotification> {

	public void onNotification(MuleContextNotification notification) {
		if (notification.getAction() == MuleContextNotification.CONTEXT_STARTED)
        {
			JdbcConnector jdbcConnector = (JdbcConnector)notification.getMuleContext().getRegistry().lookupConnector("jdbcConnector");
            
			try
	        {
	            deleteTable(jdbcConnector);
	        }
	        catch (Exception e)
	        {
	            try {
					createTable(jdbcConnector);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	        }

	        try {
				populateTable(jdbcConnector);
			} catch (Exception e) {
				e.printStackTrace();
			}	        

        }
	}
	
	protected void createTable(JdbcConnector jdbcConnector) throws Exception
    {
        QueryRunner qr = jdbcConnector.getQueryRunner();
        qr.update(jdbcConnector.getConnection(), "CREATE TABLE Customers ( " + 
        		                                     "first_name VARCHAR(255) , " +
        		                                     "last_name  VARCHAR(255) , " +
        		                                     "email VARCHAR(255),  " +
        		                                     "street_address VARCHAR(255), " +
        		                                     "city  VARCHAR(255),  " + 
        		                                     "state VARCHAR(2),   " +
        		                                     "zip   VARCHAR(5)     " +
        		                                   ")"
        		                                 );        
    }
    
    protected void deleteTable(JdbcConnector jdbcConnector) throws Exception
    {
        QueryRunner qr = jdbcConnector.getQueryRunner();
        qr.update(jdbcConnector.getConnection(), "DELETE FROM Customers");
    }
    
    protected void populateTable(JdbcConnector jdbcConnector) throws Exception
    {
        QueryRunner qr = jdbcConnector.getQueryRunner();
        int updated;
        updated = qr.update(jdbcConnector.getConnection(), "INSERT INTO Customers(email, first_name, last_name, street_address, city, state, zip) VALUES ('gregori_hovenski@yahoo.com', 'Gregori','Hovenski', '123 Sesame Street', 'howard', 'wi','54313')");
        updated = qr.update(jdbcConnector.getConnection(), "INSERT INTO Customers(email, first_name, last_name, street_address, city, state, zip) VALUES ('john_lenin@gmail.com', 'John', 'Lenin', '456 Revolution Way','madison', 'wi','54909')");
        updated = qr.update(jdbcConnector.getConnection(), "INSERT INTO Customers(email, first_name, last_name, street_address, city, state, zip) VALUES ('michael_bolton@aol.com', 'Michael', 'Bolton', '789 Alinsky Avenue', 'milwaukee', 'wi','54322')");          
    }

}