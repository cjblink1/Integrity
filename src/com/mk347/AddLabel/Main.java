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
		
		man.connect("integrity.cummins.com", 7002);
		Command cmd = new Command(Command.SI, "addlabel");
		cmd.addOption(new Option("project","/core/core2_com_mgr/design/project.pj"));
		cmd.addOption(new Option("label","Connor3"));
		cmd.addSelection("CMGR_block_errors_sdd.vsd");
		man.execute(cmd);
		man.close();
		System.exit(0);
		
	}

}
