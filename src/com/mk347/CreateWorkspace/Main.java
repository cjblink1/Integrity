package com.mk347.CreateWorkspace;


import com.mks.api.Command;
import com.mks.api.Option;
import com.mks.api.OptionList;
import com.mks.api.response.Response;

public class Main {
	
	public static void main(String[] args)
	{
		
		//Label to filter - can we filter on more than one label?
		String label = "Communication Manager 05.00.03"; 
		
		//Some of the following code may throw an exeption
		try{
		//Command manager - helps manage the backend of server connection
		CommandMan man = new CommandMan();	
		man.connect("integrity.cummins.com", 7002);
		//Create Sandbox
		Command cmd = new Command(Command.SI, "createsandbox");
		OptionList o = new OptionList();
		o.add("project", "/core/core2_com_mgr/project.pj");
		o.add(new Option("recurse"));
		o.add("scope","anyrevlabellike:"+label);
		cmd.setOptionList(o);
		cmd.addSelection("D:\\Users\\mk347\\Desktop\\test5");
		
		//Execute creation command
		Response r = man.execute(cmd);
		
		//Print out sandbox ID/directory
		System.out.println(r.getResult().getPrimaryValue().getId());
		man.close();
		
		}catch(Exception e){}
	
	}

}
