package com.mk347.AddLabel;

import com.mk347.CreateWorkspace.CommandMan;
import com.mk347.jarArguments.ArgParser;

public class Main {
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		ArgParser ap = new ArgParser(args);
		CommandMan man = new CommandMan();
		
		man.connect("integrity.cummins.com", 7002);
		
		
	}

}
