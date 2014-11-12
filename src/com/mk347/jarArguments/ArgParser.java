package com.mk347.jarArguments;

import java.util.ArrayList;
import java.util.Arrays;

import com.mks.api.Option;
import com.mks.api.OptionList;

/**
 * 
 * ArgParser parses the arguments from the command line and creates meaningful Option Objects
 * for adding to Command object in Main.main() prior to command execution
 * @author mk347, Connor Boyle
 * @version 10-7-2014
 */
public class ArgParser {
	
	//Default accepted switches. Anything else will throw an exception!!!!
	private static final String SWITCH_LABEL = "label";
	private static final String SWITCH_PROJECT = "project";
	private static final String SWITCH_PATH = "path";
	private static final String SWITCH_MEMBER = "member";
	
	//Arraylists to store Arguments and split-Arguments
	private ArrayList<String> args;
	private ArrayList<String[]> splitArgs;
	
	//String path to store the sandbox's path
	public String path;
	
	//Used in case of addLabel
	public String label;
	public String member;
	public String project;
	
	/**
	 * Constructs an ArgParser object
	 * 
	 * Called by Main.main()
	 * 
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
	 * Can be called by Main.main()
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
	 * 
	 * Can be called by Main.main()
	 * 
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
	 * Print the passed-in splitArgs ArrayList with formatting
	 * (Used for debugging)
	 * 
	 * Can be called by Main.main()
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
	 * Makes sure that the passed in array of splitArgs will make
	 * a set of valid option objects
	 * 
	 * Called by this.getOptionsFromArgs()
	 * 
	 * @param splitArgs - array to check
	 * @return - checked array
	 * @throws Exception - thrown if the key in key/value pair is not one of the default accepted
	 * keys which are SWITCH_LABEL, SWITCH_PROJECT, and SWITCH_PATH
	 */
	private ArrayList<String[]> ensureValidOptions(ArrayList<String[]> splitArgs) throws Exception
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
				//System.exit() is called in this.getOptionsFromArgs()
			}
			
			//If key is label...
			if(splitArgs.get(i)[0].equals(SWITCH_LABEL))
			{
				//change to scope and anyrevlabellike: 
				//(correct integrity command syntax)
				splitArgs.get(i)[0] = "scope";
				splitArgs.get(i)[1] = "anyrevlabellike:"+splitArgs.get(i)[1];
			}
			
			//If key is path...
			if(splitArgs.get(i)[0].equals(SWITCH_PATH))
			{	
				//Assign path to instance variable so that it can be returned to the main program 
				//(Because path is a selection not an Option object)
				path = splitArgs.get(i)[1];
			}
			
			//If key is member...
			if(splitArgs.get(i)[0].equals(SWITCH_MEMBER))
			{
				member = splitArgs.get(i)[1];
			}
			
			//If key is project...
			if(splitArgs.get(i)[0].equals(SWITCH_PROJECT))
			{
				project = splitArgs.get(i)[1];
			}
		}
		return splitArgs;
	}
	
	/**
	 * Checks to see if the incoming string is a valid key
	 * 
	 * Called by this.ensureValidOptions()
	 * 
	 * @param string - key to be checked
	 * @return - true if key is one of the default accepted
	 * keys which are SWITCH_LABEL, SWITCH_PROJECT, and SWITCH_PATH; false otherwise
	 */
	private boolean isValidKey(String string) {
		//Better way to write this logic
		if(string.equals(SWITCH_LABEL) || string.equals(SWITCH_PROJECT)|| string.equals(SWITCH_PATH) || string.equals(SWITCH_MEMBER)) return true;
		else return false;
	}
	
	/**
	 * Overarching method in this class - calls other methods and outputs Integrity OptionList
	 * 
	 * Called by Main.main()
	 * 
	 * @return - OptionList - Integrity OptionList
	 */
	public OptionList getOptionsFromArgs()
	{
		//Creates splitArgs from Args
		//(splitArgs are just Args that are split into key/value pairs)
		ArrayList<String[]> argPairs = splitListIntoArgs();
		
		//The following may cause an exception!
		try {
			//Ensure that the splitArgs will make a set of valid Option objects
			argPairs = ensureValidOptions(argPairs);
		} catch (Exception e) {
			//If the splitArgs don't make valid objects...
			System.out.println("Error: "+ 1); // Exit code 1 = syntax error
			e.printStackTrace();
		}
		
		//Return coveted prize - the OptionList!!!
		return createOptionList(argPairs);
	}

	/**
	 * Takes a list of valid splitArgs and turns them into an Integrity OptionList
	 * 
	 * Called by this.getOptionsFromArgs()
	 * 
	 * @param argPairs - array of valid splitArgs
	 * @return - Integrity OptionList
	 */
	private OptionList createOptionList(ArrayList<String[]> argPairs) {
		
		//Create new OptionList
		OptionList ol = new OptionList();
		
		//Loop through array of splitArgs
		for(int i = 0; i < argPairs.size(); i++)
		{
			//If the key/value pair is not the path...
			if(!argPairs.get(i)[0].equals(SWITCH_PATH))
			{
				//Create new Integrity Option with arg pair at index "i"
				Option o = new Option(argPairs.get(i)[0], argPairs.get(i)[1]);
				//Add Integrity Option to Integrity OptionList
				ol.add(o);
			}
		}
		//Return the coveted prize - the OptionList!!!
		return ol;
	}

	/**
	 * Creates ArrayList of splitArgs from ArrayList of args
	 * 
	 * Called by this.getOptionsFromArgs()
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
	
	/**
	 * Returns the value of path
	 * (because path is a selection and not an Option object)
	 * 
	 * Called by Main.main()
	 * 
	 * @return - path
	 */
	public String getPath()
	{
		return path;
	}
}
