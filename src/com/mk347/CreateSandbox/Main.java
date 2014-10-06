package com.mk347.CreateSandbox;


import com.mk347.jarArguments.ArgParser;
import com.mks.api.Command;
import com.mks.api.Option;
import com.mks.api.OptionList;

public class Main {
	
	public static void main(String[] args)
	{
		
		//Label to filter - can we filter on more than one label?
//		String label = "Communication Manager 05.00.03"; 
		
		
		ArgParser ap = new ArgParser(args);
		OptionList ol = ap.getOptionsFromArgs();
		
		
		//Some of the following code may throw an exeption
		try{
		//Command manager - helps manage the backend of server connection
		CommandMan man = new CommandMan();	
		man.connect("integrity.cummins.com", 7002);
		//Create Sandbox
		Command cmd = new Command(Command.SI, "createsandbox");
		cmd.addSelection(ap.getPath());
		ol.add(new Option("recurse"));
		cmd.setOptionList(ol);
		
		
		//Execute creation command
		man.execute(cmd);
		

		//Close the Connection
		man.close();
		
		System.exit(0);

		
		}catch(Exception e){
			
			System.exit(2);
		}
	
	}

}
