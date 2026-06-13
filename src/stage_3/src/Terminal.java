import java.lang.String;


public class Terminal
{
	private TerminalFont font;
	
	//TO-DO
	//Check for Terminal color code availability
	
	//Default white
	Terminal()
	{
		font = new TerminalFont();
	}

	Terminal(TerminalFont font)
	{
		newFont(font);
	}



	public void newFont(TerminalFont font)
	{
		if(font != null)
			this.font = font;
		else
			this.font = new TerminalFont();
	}

	
	
	public static void print(String text)
	{
		System.out.print(text);
	}



	public static void println(String text)
	{
		System.out.println(text);
	}



	public void testTheme()
	{
		String text = font.getPrimary() + "Font <USER> " + TerminalFont.COLOR_RESET;
		text += font.getSecondary() + "and you receive <MSG>." + TerminalFont.COLOR_RESET;
		
		println(text);
	}



	public void printUserMessage(String message)
	{
		final String DELIMETER = ":";
		String[] parts = message.split(DELIMETER);
		
		if(parts.length == 2 && parts[0].length() > 1 && parts[1].length() > 1)
		{
			String formated_message = font.getPrimary() + parts[0] + TerminalFont.COLOR_RESET;
			formated_message += DELIMETER;
			formated_message += font.getSecondary() + parts[1] + TerminalFont.COLOR_RESET;

			println(formated_message);
		}
		else
			println(message);
	}
	
	/*
	public static void main(String[] args)
	{
		Terminal term = new Terminal("33m", "32m");
		System.out.println(color_first + "hello" + COLOR_RESET);
		term.printUserMessage("Annon : Hello");
	}
	*/
}
