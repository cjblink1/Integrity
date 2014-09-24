package com.mk347.jarArguments;

import java.util.ArrayList;
import java.util.Arrays;

import com.mks.api.OptionList;

/**
 * 
 * ArgParser parses the arguments from the command line and creates meaningful Option Objects
 * for executing in the command line
 * @author mk347, Connor Boyle
 * @version 9-24-2014
 */
public class ArgParser {
	
	
	private static final String SWITCH_LABEL = "--label";
	private static final String SWITCH_PROJECT = "--project";
	private static final String SWITCH_PATH = "--path";
	
	private ArrayList<String> args;
	
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
	
	public boolean hasValidOptions()
	{
		
		return false;
	}
	
	public OptionList getOptionsFromArgs()
	{
		
		
		return new OptionList();
	}

}
