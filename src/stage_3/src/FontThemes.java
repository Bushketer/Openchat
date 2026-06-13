import java.util.ArrayList;
import java.util.List;



public class FontThemes
{
	private static ArrayList<TerminalFont> fonts = new ArrayList<TerminalFont>();
	final static String FONT_CHANGE_CMD = "#font ";


	public static TerminalFont newRedLime()
	{
		String color1 = TerminalFont.COLOR_PREFIX + "41m";
		String color2 = TerminalFont.COLOR_PREFIX + "32m";

		return new TerminalFont(color1, color2);
	}



	public static TerminalFont newYellowLime()
	{
		String color1 = TerminalFont.COLOR_PREFIX + "33m";
		String color2 = TerminalFont.COLOR_PREFIX + "32m";

		return new TerminalFont(color1, color2);
	}



	public static TerminalFont newBlueRoyal()
	{
		String color1 = TerminalFont.COLOR_PREFIX + "35m";
		String color2 = TerminalFont.COLOR_PREFIX + "34m";

		return new TerminalFont(color1, color2);
	}



	public static TerminalFont newSunFlower()
	{
		String color1 = TerminalFont.COLOR_PREFIX + "37m";
		String color2 = TerminalFont.COLOR_PREFIX + "33m";

		return new TerminalFont(color1, color2);
	}



	public static TerminalFont newLapis()
	{
		String color1 = TerminalFont.COLOR_PREFIX + "36m";
		String color2 = TerminalFont.COLOR_PREFIX + "34m";

		return new TerminalFont(color1, color2);
	}

	
	public static void initialize()
	{
		fonts.add(newRedLime());
		fonts.add(newYellowLime());
		fonts.add(newBlueRoyal());
		fonts.add(newLapis());
		fonts.add(newSunFlower());
	}

	
	public static TerminalFont selectFont(int id)
	{
		if(id < fonts.size() && id >= 0)
			return fonts.get(id);

		return null;
	}
	
	

	public static void showcase()
	{
		String text = "Hero : I have the best smile and style.";
		Terminal term = new Terminal();	
		
		for(int i = 0; i < fonts.size(); i++)
		{
			term.newFont(fonts.get(i));
			term.print("#" + i + " ");
			term.printUserMessage(text);
		}

		term.println(FONT_CHANGE_CMD + "{number} to select a font");
	}
}
