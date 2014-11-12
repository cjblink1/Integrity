package com.mk347.UnixTest;

import com.mk347.CreateSandbox.CommandMan;

public class UnixTestMain {
	
	public static void main (String[] args)
	{
		
		CommandMan man = new CommandMan();
		boolean test = man.connect("integritydev.cummins.com", 7000);
		System.out.println(test);
		
	}

}
