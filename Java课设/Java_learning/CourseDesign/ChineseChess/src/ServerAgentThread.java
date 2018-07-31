import java.util.*;
import java.net.*;
import java.io.*;

public class ServerAgentThread extends Thread
{
	Server father;//����Server������
	Socket sc;//����Socket������
	DataInputStream din;//���������������������������
	DataOutputStream dout;
	boolean flag=true;//�����̵߳ı�־λ
	public ServerAgentThread(Server father,Socket sc)
	{
		this.father=father;
		this.sc=sc;
		try
		{
			din=new DataInputStream(sc.getInputStream());//��������������
			dout=new DataOutputStream(sc.getOutputStream());//�������������
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run()
	{
		while(flag)
		{
			try
			{
				String msg=din.readUTF().trim();//���տͻ��˴�������Ϣ
				if(msg.startsWith("<#NICK_NAME#>"))//�յ����û�����Ϣ
				{
					this.nick_name(msg);
				}
				else if(msg.startsWith("<#CLIENT_LEAVE#>")){//�յ��û��뿪����Ϣ
					this.client_leave(msg);
				}
				else if(msg.startsWith("<#TIAO_ZHAN#>")){//�յ��û���������ս��Ϣ
					this.tiao_zhan(msg);
				}
				else if(msg.startsWith("<#TONG_YI#>")){//�ܵ�������ս����Ϣ
					this.tong_yi(msg);
				}
				else if(msg.startsWith("<#BUTONG_YI#>")){//�ܵ��ܾ���ս����Ϣ
					this.butong_yi(msg);
				}
				else if(msg.startsWith("<#BUSY#>")){//�յ�����ս��æ����Ϣ
					this.busy(msg);
				}
				else if(msg.startsWith("<#MOVE#>")){//�յ��������Ϣ
					this.move(msg);
				}
				else if(msg.startsWith("<#RENSHU#>")){//�յ�ĳ�û��������Ϣ
					this.renshu(msg);
				}	
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void nick_name(String msg)
	{
		try
		{
			String name=msg.substring(13);//����û����ǳ�
			this.setName(name);//�ø��ǳƸ����߳�ȡ��
			Vector v=father.onlineList;//��������û��б�
			boolean isChongMing=false;
			int size=v.size();//����û��б�Ĵ�С
			for(int i=0;i<size;i++)
			{//�����б��鿴�Ƿ��Ѿ��и��û���
				ServerAgentThread tempSat=(ServerAgentThread)v.get(i);
				if(tempSat.getName().equals(name))
				{
					isChongMing=true;//������������־λ��Ϊtrue
					break;
				}
			}
			if(isChongMing==true)//�������
			{
				dout.writeUTF("<#NAME_CHONGMING#>");//��������Ϣ���͸��ͻ���
				din.close();//�ر�����������
				dout.close();//�ر����������
				sc.close();//�ر�Socket
				flag=false;//��ֹ�÷����������߳�
			}
			else//���������
			{
				v.add(this);//�����߳���ӵ������б�
				father.refreshList();//ˢ�·�����������Ϣ�б�
				String nickListMsg="";
				size=v.size();//��������б��С
				for(int i=0;i<size;i++)
				{
					ServerAgentThread tempSat=(ServerAgentThread)v.get(i);
					nickListMsg=nickListMsg+"|"+tempSat.getName();
				}//�������б�����ס��֯���ַ���
				nickListMsg="<#NICK_LIST#>"+nickListMsg;
				Vector tempv=father.onlineList;
				size=tempv.size();
				for(int i=0;i<size;i++)
				{//���������б�
					ServerAgentThread satTemp=(ServerAgentThread)tempv.get(i);
					satTemp.dout.writeUTF(nickListMsg);//�����µ��б���Ϣ���͵������ͻ���
					if(satTemp!=this)
					{//�������ͻ��˷������û����ߵ���Ϣ
						satTemp.dout.writeUTF("<#MSG#>"+this.getName()+"������...");
					}
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void client_leave(String msg){
		try{
			Vector tempv=father.onlineList;//��������б�
			tempv.remove(this);//�Ƴ����û�
			int size=tempv.size();
			String nl="<#NICK_LIST#>";
			for(int i=0;i<size;i++){//���������б�
				ServerAgentThread satTemp=(ServerAgentThread)tempv.get(i);
				//������ͻ��˷����û�������Ϣ
				satTemp.dout.writeUTF("<#MSG#>"+this.getName()+"������...");
				//��֯��Ϣ�������û��б�
				nl=nl+"|"+satTemp.getName();
			}
			for(int i=0;i<size;i++){//�����µ��б���Ϣ���͵������ͻ���
				ServerAgentThread satTemp=(ServerAgentThread)tempv.get(i);
				satTemp.dout.writeUTF(nl);
			}
			this.flag=false;//��ֹ�÷����������߳�
			father.refreshList();//���·����������û��б�
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void tiao_zhan(String msg)
	{
		try
		{
			String name1=this.getName();//��÷�����ս��Ϣ�û�������
			String name2=msg.substring(13);//��ñ���ս���û�����
			Vector v=father.onlineList;//��������û��б�
			int size=v.size();//��������û��б�Ĵ�С
			for(int i=0;i<size;i++)
			{//�����б���������ս���û�
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				if(satTemp.getName().equals(name2))
				{//����û�������ս��Ϣ�����������ս�û�������
					satTemp.dout.writeUTF("<#TIAO_ZHAN#>"+name1);
					break;
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void tong_yi(String msg){
		try{
			String name=msg.substring(11);//��������ս���û�������
			Vector v=father.onlineList;//��������û��б�
			int size=v.size();//��������û��б�Ĵ�С
			for(int i=0;i<size;i++){//�����б����������ս���û�
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				if(satTemp.getName().equals(name)){//����û����ͶԷ�������ս����Ϣ
					satTemp.dout.writeUTF("<#TONG_YI#>");
					break;
				}
			}
		}
		catch(Exception e){e.printStackTrace();}
	}
	public void butong_yi(String msg){
		try{
			String name=msg.substring(13);//��������ս���û�������
			Vector v=father.onlineList;//��������û��б�
			int size=v.size();//��������û��б�Ĵ�С
			for(int i=0;i<size;i++)
			{//�����б����������ս���û�
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				if(satTemp.getName().equals(name)){//����û����ͶԷ��ܾ���ս����Ϣ
					satTemp.dout.writeUTF("<#BUTONG_YI#>");
					break;
				}
			}
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void busy(String msg){
		try{
			String name=msg.substring(8);//��������ս���û�������
			Vector v=father.onlineList;//��������û��б�
			int size=v.size();//��������û��б�Ĵ�С
			for(int i=0;i<size;i++){//�����б����������ս���û�
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				if(satTemp.getName().equals(name)){//����û����ͶԷ�����æ����Ϣ
					satTemp.dout.writeUTF("<#BUSY#>");
					break;
				}
			}
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void move(String msg){
		try{
			String name=msg.substring(8,msg.length()-4);//��ý��շ�������
			Vector v=father.onlineList;//��������û��б�
			int size=v.size();//��������û��б�Ĵ�С
			for(int i=0;i<size;i++){//�����б��������շ�
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				if(satTemp.getName().equals(name)){//������Ϣת�������շ�
					satTemp.dout.writeUTF(msg);
					break;
				}
			}
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void renshu(String msg){
		try{
			String name=msg.substring(10);//��ý��շ�������
			Vector v=father.onlineList;//��������û��б�
			int size=v.size();//��������û��б�Ĵ�С
			for(int i=0;i<size;i++){//�����б��������շ�
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				if(satTemp.getName().equals(name)){//������Ϣת�������շ�
					satTemp.dout.writeUTF(msg);
					break;
				}
			}
		}
		catch(IOException e){e.printStackTrace();}
	}
}