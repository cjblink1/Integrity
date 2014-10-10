package com.mk347.AddLabel;

import com.mk347.CreateSandbox.CommandMan;
import com.mk347.jarArguments.ArgParser;
import com.mks.api.Command;
import com.mks.api.Option;

public class Main {
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		ArgParser ap = new ArgParser(args);
		CommandMan man = new CommandMan();
		
		man.connect("integritydev.cummins.com", 7000);
		Command cmd = new Command(Command.SI, "addlabel");
		cmd.addOption(new Option("project","/core/core2_com_mgr/documents/project.pj"));
		cmd.addOption(new Option("label","Connor1"));
		//cmd.addSelection("CRS_CommunicationsManager.fm");
		man.execute(cmd);
		man.close();
		System.exit(0);
		
	}
}
