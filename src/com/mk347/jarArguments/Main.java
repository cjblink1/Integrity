package com.mk347.jarArguments;

public class Main {
	
	public static void main(String[] args)
	{
		
		
		ArgParser ap = new ArgParser(args);
		ap.getOptionsFromArgs();
		ap.printSplitArgs();
		
//		for (String s: args)
//		{
//			
//			System.out.println(s);
//		}
//		
//		System.out.println("DONE");
	}

}
