package com.mk347.CreateSandbox;


import com.mk347.jarArguments.ArgParser;
import com.mks.api.Command;
import com.mks.api.Option;
import com.mks.api.OptionList;

public class Main {
	
	public static void main(String[] args)
	{
				
		ArgParser ap = new ArgParser(args);
		OptionList ol = ap.getOptionsFromArgs();
		
		
		//Some of the following code may throw an exeption
		try{
		//Command manager - helps manage the server connection
		CommandMan man = new CommandMan();	
		man.connect("integrity.cummins.com", 7002);//uses username and password defined in CommandMan
		//Create Sandbox
		Command cmd = new Command(Command.SI, "createsandbox");//si createsandbox
		cmd.addSelection(ap.getPath());//with path
		ol.add(new Option("recurse"));//with recursion
		cmd.setOptionList(ol);//with other options
		
		
		//Execute command
		man.execute(cmd);
		

		//Close the Connection
		man.close();
		
		System.exit(0);// Exit code 0 = all good!

		}catch(Exception e){
			
			System.exit(2);//Exit code 2 = connection error
		}
	}
}