package com.mk347.jarArguments;

import java.util.ArrayList;
import java.util.Arrays;

import com.mks.api.Option;
import com.mks.api.OptionList;

/**
 * 
 * ArgParser parses the arguments from the command line and creates meaningful Option Objects
 * for executing in the Integrity API
 * @author mk347, Connor Boyle
 * @version 9-24-2014
 */
public class ArgParser {
	
	
	@SuppressWarnings("unused")
	private static final String SWITCH_LABEL = "--label";
	@SuppressWarnings("unused")
	private static final String SWITCH_PROJECT = "--project";
	@SuppressWarnings("unused")
	private static final String SWITCH_PATH = "--path";
	
	private ArrayList<String> args;
	private ArrayList<String[]> splitArgs;
	
	public ArgParser(String[] args)
	{
		this.args = new ArrayList<String>(Arrays.asList(args));	
	}
	
	public void printArguments()
	{
		for(int i = 0; i < args.size(); i++)
		{
			System.out.println("Args:"+args.get(i)+ "  Index:"+i);
		}
		
	}
	
	public void printSplitArgs()
	{
		for (int i = 0; i < splitArgs.size(); i++)
		{
			System.out.println("Key:"+splitArgs.get(i)[0]+" Value:"+splitArgs.get(i)[1]);
		}
		
	}
	
	public boolean hasValidOptions()
	{
		
		return false;
	}
	
	public OptionList getOptionsFromArgs()
	{
		ArrayList<String[]> argPairs = splitListIntoArgs();
		return createOptionList(argPairs);
	}

	private OptionList createOptionList(ArrayList<String[]> argPairs) {
		
		OptionList ol = new OptionList();
		for(int i = 0; i < argPairs.size(); i++)
		{
			Option o = new Option(argPairs.get(i)[0], argPairs.get(i)[1]);
			ol.add(o);
		}
		
		return ol;
	}

	private ArrayList<String[]> splitListIntoArgs()
	{
		splitArgs = new ArrayList<String[]>();
		for (int i = 0; i < args.size(); i++)
		{
			if(args.get(i).startsWith("--"))
			{
				splitArgs.add(new String[]{args.get(i).substring(2),args.get(i+1)});
			}
		}
		return splitArgs;
	}


}
