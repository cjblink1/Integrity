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
	
	//Default accepted switches. Anything else will throw an exception!!!!
	private static final String SWITCH_LABEL = "label";
	private static final String SWITCH_PROJECT = "project";
	private static final String SWITCH_PATH = "path";
	
	//Arraylists to store arguments and split Arguments
	private ArrayList<String> args;
	private ArrayList<String[]> splitArgs;
	
	/**
	 * Constructs an ArgParser object
	 * @param args - list of arguments from the command line
	 */
	public ArgParser(String[] args)
	{
		//Set incoming args array to private variable
		this.args = new ArrayList<String>(Arrays.asList(args));	
	}
	/**
	 * Print the args private variable with formatting
	 * (Used for debugging)
	 * 
	 */
	public void printArguments()
	{
		//Loop through ArrayList
		for(int i = 0; i < args.size(); i++)
		{
			//Print the argument and the Index with formatting
			System.out.println("Args:"+args.get(i)+ "  Index:"+i);
		}
	}
	
	/**
	 * Print the splitArgs private variable with formatting
	 * (Used for debugging)
	 */
	public void printSplitArgs()
	{
		//Loop through splitArgs
		for (int i = 0; i < splitArgs.size(); i++)
		{
			//Output key/value pairs
			System.out.println("Key:"+splitArgs.get(i)[0]+" Value:"+splitArgs.get(i)[1]);
		}
	}
	
	/**
	 * Print the passed in splitArgs array with formatting
	 * (Used for debugging)
	 * 
	 * @param splitArgs - array of SplitArgs to print
	 */
	public void printSplitArgs(ArrayList<String[]> splitArgs)
	{
		//Loop through passed-in splitArgs
		for (int i = 0; i < splitArgs.size(); i++)
		{
			//Output key/value pairs
			System.out.println("Key:"+splitArgs.get(i)[0]+" Value:"+splitArgs.get(i)[1]);
		}
	}
	/**
	 * Makes sure that the passed in array of splitArgs will make valid options
	 * @param splitArgs - array to check
	 * @return - checked array
	 * @throws Exception - thrown if the key in key/value pair is not one of the default accepted
	 * keys which are SWITCH_LABEL, SWITCH_PROJECT, and SWITCH_PATH
	 */
	public ArrayList<String[]> ensureValidOptions(ArrayList<String[]> splitArgs) throws Exception
	{
		//Loop through array of splitArgs
		for(int i = 0; i < splitArgs.size(); i++)
		{
			//If key is invalid...
			if(!isValidKey(splitArgs.get(i)[0]))
			{
				//Yell at user
				System.out.println(splitArgs.get(i)[0]);
				throw new Exception("Invalid key/value pair! " +
						"Did you enter your arguments correctly?");
			}
			
			//If key is label
			if(splitArgs.get(i)[0].equals(SWITCH_LABEL))
			{
				//change to scope and anyrevlabellike: 
				//(Property Integrity syntax)
				splitArgs.get(i)[0] = "scope";
				splitArgs.get(i)[1] = "anyrevlabellike:"+splitArgs.get(i)[1];
			}
		}
		return splitArgs;
	}
	
	/**
	 * Checks to see if the incoming string is a valid key
	 * @param string - key to be checked
	 * @return - true if key is one of the default accepted
	 * keys which are SWITCH_LABEL, SWITCH_PROJECT, and SWITCH_PATH; false otherwise
	 */
	private boolean isValidKey(String string) {
		if(string.equals(SWITCH_LABEL) || string.equals(SWITCH_PROJECT)|| string.equals(SWITCH_PATH)) return true;
		else return false;
	}
	/**
	 * Overarching method in this class - calls other methods and outputs Integrity OptionList
	 * 
	 * @return - OptionList - Integrity OptionList
	 */
	public OptionList getOptionsFromArgs()
	{
		//Creates splitArgs from Args
		ArrayList<String[]> argPairs = splitListIntoArgs();
		
		//The following may cause an exception!
		try {
			//Ensure that the splitArgs will make valid Options
			argPairs = ensureValidOptions(argPairs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Print splitArgs for debugging purposes - Should be erased
		printSplitArgs(argPairs);
		
		//Return coveted prize - the OptionList!!!
		return createOptionList(argPairs);
	}

	/**
	 * Takes a list of valid splitArgs and turns them into an Integrity OptionList
	 * @param argPairs - array of valid splitArgs
	 * @return - Integrity OptionList
	 */
	private OptionList createOptionList(ArrayList<String[]> argPairs) {
		
		//Create new OptionList
		OptionList ol = new OptionList();
		
		//Loop through array of splitArgs
		for(int i = 0; i < argPairs.size(); i++)
		{
			//Create new Integrity Option with arg pair at index "i"
			Option o = new Option(argPairs.get(i)[0], argPairs.get(i)[1]);
			//Add Integrity Option to Integrity OptionList
			ol.add(o);
		}
		//Return the coveted prize - the OptionList!!!
		return ol;
	}

	/**
	 * Creates ArrayList of splitArgs from ArrayList of args
	 * 
	 * @return - ArrayList of splitArgs
	 */
	private ArrayList<String[]> splitListIntoArgs()
	{
		//Create new ArrayList of splitArgs
		splitArgs = new ArrayList<String[]>();
		
		//Loop through args array
		for (int i = 0; i < args.size(); i++)
		{
			//If it starts with a -- it must be a key/value pair!
			if(args.get(i).startsWith("--"))
			{
				//Add the key/value pair to the list of splitArgs
				splitArgs.add(new String[]{args.get(i).substring(2),args.get(i+1)});
			}
		}
		//Return the ArrayList of splitArgs
		return splitArgs;
	}
}
