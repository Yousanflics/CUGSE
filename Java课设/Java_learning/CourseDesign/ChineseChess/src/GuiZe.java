
public class GuiZe {
	QiZi[][] qizi;
	boolean canMove;
	int i;
	int j;
	public GuiZe(QiZi[][] qizi)
	{
		this.qizi=qizi;
	}
	public boolean canMove(int startI,int startJ,int endI,int endJ,String name)//��ʼλ�ã�����λ��  
	{
		int maxI;
		int minI;
		int maxJ;
		int minJ;
		canMove=true;
		if(startI>=endI)//��ʼλ�ô��ڽ���λ��
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
		if(name.equals("܇"))
		{
			this.ju(maxI,minI,maxJ,minJ);
		}
		else if(name.equals("�R"))
		{
				this.ma(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("��"))
		{
			this.xiang1(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("��"))
		{
			this.xiang2(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("��")||name.equals("ʿ"))
		{
			this.shi(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("˧")||name.equals("��"))
		{
			this.jiang(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);	
		}
		else if(name.equals("��")||name.equals("�h"))
		{
			this.pao(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if (name.equals("��"))
		{
			this.bing(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		else if(name.equals("��"))
		{
			this.zu(maxI,minI,maxJ,minJ,startI,startJ,endI,endJ);
		}
		return canMove;
		}
	public void zu(int maxI, int minI, int maxJ, int minJ, int startI,
			int startJ, int endI, int endJ) {
		// TODO Auto-generated method stub
	if(startJ>4)
	{//û�й���
		if(startI!=endI)
		{//���������ǰ��
			canMove=false;
		}
		if(endJ-startJ!=-1)
		{
			canMove=false;
		}
	}
	else
	{//����
		if(startI==endI)
		{//ǰ��
			if(endJ-startJ!=-1)
			{
				canMove=false;
			}
		}
		else if(startJ==endJ)//����
			{//�����������
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
		if(startJ<5)//û�й���
		{
			if(startI!=endI)//������ǰ��
				canMove=false;
			if(endJ-startJ!=1)
			{//qianjin   
				canMove=false;
			}
		}
		else //guohe
		{
			if(startI==endI)//zou ����
			{
				if(endJ-startJ!=1)
				{
					canMove=false;
				}
			}
			else if(endJ==startJ)//�ߺ���
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
		if(maxI==minI)//���ߴ���
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
		else if(maxJ==minJ)//���ߴ���
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
			if(startJ>4)//�Է���ʿ
			{
				if(endJ<7)
				{
					canMove=false;
				}
			}
			else//�Լ�����
			{
				if(endJ>2)
				{
					canMove=false;
				}
			}
			if(endI>5||endI<3)//����Խ��
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
			if(endJ<5)//����
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
			if(endJ>4)//����
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
		int b=maxJ-minJ;//���������֮��Ĳ�
		if(a==1&&b==2)//������
		{
			if(startJ>endJ)//ǰ��
			{
				if(qizi[startI][startJ-1]!=null)//if(qizi[startI][startJ+1]!=null)
				{
					canMove=false;
				}
			}
			else//����
			{
				if(qizi[startI][startJ+1]!=null)//if(qizi[startI][startJ-1]!=null)
				{
					canMove=false;
				}
			}
		}
		else if(a==2&&b==1)//������
		{
			if(startI>endI)//������
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
		else if(!(a==1&&b==2||a==2&&b==1))//�����㡰�ա�
		{
			canMove=false;
		}
	}
	public void ju(int maxI, int minI, int maxJ, int minJ) {
		// TODO Auto-generated method stub
		if(maxI==minI)//��һ��������
			{
				for(j=minJ+1;j<maxJ;j++)
				{
					if(qizi[maxI][j]!=null)//if(qizi[i][maxJ]!=null)//�м������� ������--�޸�
					{
						canMove=false;
						break;
					}
				}
			}
			else if (maxJ==minJ)//��һ��
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
