



public class TerminalFont
{
	static final String COLOR_PREFIX = "\u001B[";
	static final String COLOR_RESET = COLOR_PREFIX + "0m";
	static final String DEFAULT_COLOR = COLOR_PREFIX + "37m";

	private String colorPrimary = DEFAULT_COLOR;
	private String colorSecondary = DEFAULT_COLOR;

	TerminalFont()
	{}

	TerminalFont(String color1, String color2)
	{
		set(color1, color2);
	}

		
	private Boolean isValid(String color)
	{
		final String WARNING = "Last 3 characters of ANSI e.g. 31m = Red."; 
		if(color.length() != DEFAULT_COLOR.length())
		{
			Terminal.println("Invalid color. " + WARNING);
			return false;
		}
		else if(color.endsWith("m") == false)
		{
			Terminal.println("Format is missing \"m\". " + WARNING);
			return false;
		}

		return true;
	}
		

	public String getPrimary()
	{
		return colorPrimary;
	}


	
	public String getSecondary()
	{
		return colorSecondary;	
	}


	
	public void set(String color1, String color2)
	{
		if(isValid(color1))
			colorPrimary = color1;
		if(isValid(color2))
			colorSecondary = color2;
	}
}
