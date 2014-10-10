package com.mk347.CreateSandbox;

	import java.io.IOException;
	import java.util.Iterator;

	import com.mks.api.CmdRunner;
	import com.mks.api.Command;
	import com.mks.api.IntegrationPoint;
	import com.mks.api.IntegrationPointFactory;
	import com.mks.api.Session;
	import com.mks.api.response.APIException;
	import com.mks.api.response.Field;
	import com.mks.api.response.Response;
	import com.mks.api.response.WorkItem;
	import com.mks.api.response.WorkItemIterator;

	/**
	 * Command man helps to manage commands and connect to the server. 
	 * It makes everything with the Java API for PTC Integrity easier to work with
	 * 
	 * @author MK347, Connor Boyle
	 * @version 10-6-2014
	 *
	 */

	public class CommandMan {
		

		private String username;
		private String password;
		
		private CmdRunner cr = null;
		private Session session = null;
		
		/**
		 * Connects to the Integration Point and creates Session and CmdRunner
		 * 
		 * Called by Main.main()
		 * 
		 * @param hostname - hostname for integrity server
		 * @param port - port of integrity server
		 * @param username - username used to connect to server
		 * @param password - password used to connect to server
		 * @return - true if connected; false if error
		 */
		public boolean connect(String hostname, int port)
		{
			
			//Should be changed to "server user" or "api" account
			this.username = "mk347";
			this.password = "password";
			
			//The following creates the integration point, session, and command runner
			try {
				boolean useClientIP = true;
	                        if (useClientIP) {
	                            // Client Integration Point
	                            IntegrationPoint ip = IntegrationPointFactory.getInstance().createLocalIntegrationPoint(4, 12);
	                            ip.setAutoStartIntegrityClient(true);
	                            session = ip.getCommonSession(username, password);
	                            session.setAutoReconnect(true);
	                            session.setDefaultHostname(hostname);
	                            session.setDefaultPort(port);
	                            session.setDefaultUsername(username);
	                            session.setDefaultPassword(password);
	                        } else {
	                            // Server Integration Point
	                            session = IntegrationPointFactory.getInstance().createIntegrationPoint(hostname, port, 4, 12).createSession(username, password);
	                            session.setAutoReconnect(true);
	                        }
	                        //Command runner
	                        cr = session.createCmdRunner();
	                                  
	                        
	                    } 
	                catch (APIException apie) {
				System.out.println("Error occurred during initialization: "
						+ apie.getMessage());
				apie.printStackTrace();
				//System.exit() is called by Main.main() if exception is thrown				
				return false;
			}
			
			return true;
		}
		/**
		 * Executes the passed-in command and returns the response object
		 * 
		 * Called by Main.main()
		 * 
		 * @param cmd - command object to be executed
		 * @return - Response to be parsed by this.printResponse()
		 */
		public Response execute(Command cmd)
		{
			
			try {
				return cr.execute(cmd);
			} catch (APIException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
		

		/**
		 * Prints the Response work items to the console
		 * 
		 * Called by Main.main()
		 * 
		 * @param response - from the executed command
		 */
		public void printResponse(Response response)
		{
			
			try{
				if (response != null) {
		            System.out.println("Response not null");
		            WorkItemIterator wii = response.getWorkItems();
		            while (wii.hasNext()) {
		            	WorkItem wi = wii.next();
		            	@SuppressWarnings("unchecked")
						Iterator<Field> iterator = wi.getFields();
		            	while (iterator.hasNext()) {
		            		Field field = iterator.next();
		            		System.out.println(field.getName() + " : "
		            				+ field.getValueAsString());
		            	}
		            }
				
				}
			}catch(APIException e)
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
		
		/**
		 * Closes the server connection
		 * 
		 * Called by Main.main()
		 */
		public void close()
		{
			
			try {
				session.release();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (APIException e) {
				e.printStackTrace();
			}
		}
	}