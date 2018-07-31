import java.awt.*;

public class QiZi {
	private Color color;
	private String name;
	private int x;
	private int y;
	private boolean focus=false;//是否被选中标志
	public QiZi()
	{	
	}
	public QiZi(Color color,String name,int x,int y)
	{
		this.color=color;
		this.name=name;
		this.x=x;
		this.y=y;
	}
	public Color getColor()
	{
		return this.color;
	}
	public void setColor(Color color)
	{
		this.color=color;
	}
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public int getX()
	{
		return this.x;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public int getY()
	{
		return this.y=y;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public boolean getFocus()
	{
		return this.focus;
	}
	public void setFocus(boolean focus)
	{
		this.focus=focus;
	}
}
