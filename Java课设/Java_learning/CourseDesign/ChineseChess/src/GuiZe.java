
public class GuiZe {
	QiZi[][] qizi;
	boolean canMove;
	int i;
	int j;
	public GuiZe(QiZi[][] qizi)
	{
		this.qizi=qizi;
	}
	public boolean canMove(int startI,int startJ,int endI,int endJ,String name)//起始位置，结束位置  
	{
		int maxI;
		int minI;
		int maxJ;
		int minJ;
		canMove=true;
		if(startI>=endI)//起始位置大于结束位置
		{
			maxI=startI;
			minI=endI;
		}
		else
		{
			maxI=endI;
			minI=startI;
		}
		if(startJ>=endJ)
		{
			maxJ=startJ;
			minJ=endJ;
		}
		else
		{
			maxJ=endJ;
			minJ=startJ;
		}
		if(name.equals("車"))
		{
			this.ju(maxI,minI,maxJ,minJ);
		}
		else if(name.equals("馬"))
		{
				this.ma(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("相"))
		{
			this.xiang1(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("象"))
		{
			this.xiang2(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("仕")||name.equals("士"))
		{
			this.shi(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("帅")||name.equals("将"))
		{
			this.jiang(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);	
		}
		else if(name.equals("炮")||name.equals("砲"))
		{
			this.pao(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if (name.equals("兵"))
		{
			this.bing(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("卒"))
		{
			this.zu(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		return canMove;
		}
	public void zu(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
	if(startJ>4)
	{//没有过河
		if(startI!=endI)
		{//如果不是向前走
			canMove=false;
		}
		if(endJ-startJ!=-1)
		{
			canMove=false;
		}
	}
	else
	{//过河
		if(startI==endI)
		{//前进
			if(endJ-startJ!=-1)
			{
				canMove=false;
			}
		}
		else if(startJ==endJ)//横线
			{//向左或者向右
			   if(maxI-minI!=1)
			   {
				   canMove=false;
			   }
			}
		else if(startI!=endI&&startJ!=endJ)
		{
			canMove=false;
		}
	}
	}
	public void bing(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		if(startJ<5)//没有过河
		{
			if(startI!=endI)//不是向前走
				canMove=false;
			if(endJ-startJ!=1)
			{//qianjin   
				canMove=false;
			}
		}
		else //guohe
		{
			if(startI==endI)//zou 竖线
			{
				if(endJ-startJ!=1)
				{
					canMove=false;
				}
			}
			else if(endJ==startJ)//走横线
			{
				if(maxI-minI!=1)//if(endI-startI!=1)
				{
					canMove=false;
				}
			}
			else if(startI!=endI&&startJ!=endJ)
			{
				canMove=false;
			}
		}
	}
	public void pao(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		if(maxI==minI)//竖线处理
		{
			if(qizi[endI][endJ]!=null)
			{
				int count=0;
				for(j=minJ+1;j<maxJ;j++)
				{
					if(qizi[minI][j]!=null)
						count++;
				}
				if(count!=1)
				{
					canMove=false;
				}
			}
			else if(qizi[endI][endJ]==null)
			{
				for(j=minJ+1;j<maxJ;j++)
				{
					if(qizi[minI][j]!=null)
					{
						canMove=false;
						break;
					}
				}
			}
		}
		else if(maxJ==minJ)//横线处理
		{
			if(qizi[endI][endJ]!=null)
			{
				int count=0;
				for(i=minI+1;i<maxI;i++)
				{
					if(qizi[i][maxJ]!=null)
						count++;
				}
				if(count!=1)
				{
					canMove=false;
				}	
			}
			else if(qizi[endI][endJ]==null)
			{
				for(i=minI+1;i<maxI;i++)
				{
					if(qizi[i][maxJ]!=null)
					{
						canMove=false;
						break;
					}
				}
			}
		}
		else
		{
			canMove=false;
		}
	}
	public void jiang(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		int a=maxI-minI;
		int b=maxJ-minJ;
		if(a==1&&b==0||a==0&&b==1)
		{
			if(startJ>4)
			{
				if(endJ<7)
				{
					canMove=false;
				}
			}
			else
			{
				if(endJ>2)
				{
					canMove=false;
				}
			}
			if(endI>5||endI<3)
			{
				canMove=false;
			}
		}
		else
			canMove=false;
	}
	public void shi(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		int a=maxI-minI;
		int b=maxJ-minJ;
		if(a==1&&b==1)
		{
			if(startJ>4)//对方的士
			{
				if(endJ<7)
				{
					canMove=false;
				}
			}
			else//自己的仕
			{
				if(endJ>2)
				{
					canMove=false;
				}
			}
			if(endI>5||endI<3)//左右越界
			{
				canMove=false;
			}
		}
		else
		{
			canMove=false;
		}
	}
	public void xiang2(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		int a=maxI-minI;
		int b=maxJ-minJ;
		if(a==2&&b==2)
		{
			if(endJ<5)//过河
				canMove=false;
			if(qizi[(startI+endI)/2][(startJ+endJ)/2]!=null)
				canMove=false;
		}
		else
		{
			canMove=false;
		}
	}
	public void xiang1(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		int a=maxI-minI;
		int b=maxJ-minJ;
		if(a==2&&b==2)
		{
			if(endJ>4)//过河
				canMove=false;
			if(qizi[(startI+endI)/2][(startJ+endJ)/2]!=null)
				canMove=false;
		}
		else
		{
			canMove=false;
		}
	}
	public void ma(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
		int a=maxI-minI;
		int b=maxJ-minJ;//获得两坐标之间的差
		if(a==1&&b==2)//竖着走
		{
			if(startJ>endJ)//前进
			{
				if(qizi[startI][startJ-1]!=null)//if(qizi[startI][startJ+1]!=null)
				{
					canMove=false;
				}
			}
			else//后退
			{
				if(qizi[startI][startJ+1]!=null)//if(qizi[startI][startJ-1]!=null)
				{
					canMove=false;
				}
			}
		}
		else if(a==2&&b==1)//横着走
		{
			if(startI>endI)//向左走
			{
				if(qizi[startI-1][startJ]!=null)
				{
					canMove=false;
				}
			}
			else
			{
				if(qizi[startI+1][startJ]!=null)
				{
					canMove=false;
				}
			}
		}
		else if(!(a==1&&b==2||a==2&&b==1))//不满足“日”
		{
			canMove=false;
		}
	}
	public void ju(int maxI, int minI, int maxJ, int minJ) {
		// TODO Auto-generated method stub
		if(maxI==minI)//在一条横线上
			{
				for(j=minJ+1;j<maxJ;j++)
				{
					if(qizi[maxI][j]!=null)//if(qizi[i][maxJ]!=null)//中间有棋子 不可走--修改
					{
						canMove=false;
						break;
					}
				}
			}
			else if (maxJ==minJ)//在一列
			{
				for(i=minJ+1;i<maxJ;i++)
				{
					if(qizi[i][maxJ]!=null)//if(qizi[maxI][j]!=null)
					{
						canMove=false;
						break;
					}
				}
			}
			else if(maxJ!=minJ&&maxI!=minI)
			{
				canMove=false;
			}
		}
	}
