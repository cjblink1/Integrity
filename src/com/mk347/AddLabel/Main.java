package com.mk347.AddLabel;

import com.mk347.CreateSandbox.CommandMan;
import com.mk347.jarArguments.ArgParser;
import com.mks.api.Command;
import com.mks.api.Option;


/**
 * 
 * Main class (runner) for AddLabel
 * 
 * TODO - Need to integrate ArgParser functionality into AddLabel
 * 
 * @author mk347, Connor Boyle
 * @version 11-11-2014
 */
public class Main {
	
	public static void main(String[] args)
	{
		ArgParser ap = new ArgParser(args);
		CommandMan man = new CommandMan();
		
		man.connect("integritydev.cummins.com", 7000);
		Command cmd = new Command(Command.SI, "addlabel");
		cmd.addOption(new Option("project",ap.project));
		cmd.addOption(new Option("label",ap.label));
		cmd.addSelection(ap.member);
		man.execute(cmd);
		man.close();
		System.exit(0);
		
	}
}
