import javax.swing.*;
import java.util.*;
import java.io.*;
public class ClientAgentThread extends Thread
{
	XiangQi father;//����XiangQi������
	boolean flag=true;//�����̵߳ı�־λ
	DataInputStream din;//����������������������� 
	DataOutputStream dout;
	String tiaozhanzhe=null;//���ڼ�¼������ս�Ķ���
	public ClientAgentThread(XiangQi father)
	{
		this.father=father;
		try
		{
			din=new DataInputStream(father.sc.getInputStream());//�����������������
			dout=new DataOutputStream(father.sc.getOutputStream());
			
			String name=father.jtfNickName.getText().trim();//����ǳ�
			dout.writeUTF("<#NICK_NAME#>"+name);//�����ǳƵ�������
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run(){
		while(flag){
			try{
				String msg=din.readUTF().trim();//��÷�������������Ϣ
				if(msg.startsWith("<#NAME_CHONGMING#>")){//�յ���������Ϣ
					this.name_chongming();
				}
				else if(msg.startsWith("<#NICK_LIST#>")){//�յ��ǳ��б�
					this.nick_list(msg);
				}
				else if(msg.startsWith("<#SERVER_DOWN#>")){//���յ��������뿪����Ϣ
					this.server_down();
				}
				else if(msg.startsWith("<#TIAO_ZHAN#>")){//���յ���ս����Ϣ
					this.tiao_zhan(msg);
				}
				else if(msg.startsWith("<#TONG_YI#>")){
					this.tong_yi();//�����û��յ��Է�������ս����Ϣʱ	
				}
				else if(msg.startsWith("<#BUTONG_YI#>")){
					this.butong_yi();//�����û��յ��Է��ܾ���ս����Ϣʱ
				}
				else if(msg.startsWith("<#BUSY#>")){//���յ��Է�æ����Ϣʱ
					this.busy();
				}
				else if(msg.startsWith("<#MOVE#>")){//���յ��������Ϣʱ
					this.move(msg);
				}
				else if(msg.startsWith("<#RENSHU#>")){//���յ��������Ϣʱ
					this.renshu();
				}
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	public void name_chongming(){
		try{
			JOptionPane.showMessageDialog(this.father,"����������Ѿ���ռ�ã���������д��",
			            "����",JOptionPane.ERROR_MESSAGE);//������������ʾ��Ϣ
			din.close();//�ر�����������
			dout.close();//�ر����������
			this.father.jtfHost.setEnabled(!false);//�������������������ı�����Ϊ����
			this.father.jtfPort.setEnabled(!false);//����������˿ںŵ��ı�����Ϊ����
			this.father.jtfNickName.setEnabled(!false);//�����������ǳƵ��ı�����Ϊ����
			this.father.jbConnect.setEnabled(!false);//��"����"��ť��Ϊ����
			this.father.jbDisconnect.setEnabled(!true);//��"�Ͽ�"��ť��Ϊ������
			this.father.jbChallege.setEnabled(!true);//��"��ս"��ť��Ϊ������
			this.father.jbYChallege.setEnabled(false);//��"������ս"��ť��Ϊ������
			this.father.jbNChallege.setEnabled(false);//��"�ܾ���ս"��ť��Ϊ������
			this.father.jbFaild.setEnabled(false);//��"����"��ť��Ϊ������
			father.sc.close();//�ر�Socket
			father.sc=null;
			father.cat=null;
			flag=false;//��ֹ�ÿͻ��˴����߳�
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void nick_list(String msg){
		String s=msg.substring(13);//�ֽⲢ�õ�������Ϣ
		String[] na=s.split("\\|");
		Vector v=new Vector();//����Vector����
		for(int i=0;i<na.length;i++){
			if(na[i].trim().length()!=0&&(!na[i].trim().equals(father.jtfNickName.getText().trim()))){
				v.add(na[i]);//���ǳ�ȫ����ӵ�Vector��
			}
		}
		father.jcbNickList.setModel(new DefaultComboBoxModel(v));//���������б��ֵ
	}
	public void server_down(){
		this.father.jtfHost.setEnabled(!false);//�������������������ı�����Ϊ����
		this.father.jtfPort.setEnabled(!false);;//����������˿ںŵ��ı�����Ϊ����
		this.father.jtfNickName.setEnabled(!false);//�����������ǳƵ��ı�����Ϊ����
		this.father.jbConnect.setEnabled(!false);//��"����"��ť��Ϊ����
		this.father.jbDisconnect.setEnabled(!true);//��"�Ͽ�"��ť��Ϊ������
		this.father.jbChallege.setEnabled(!true);//��"��ս"��ť��Ϊ������
		this.father.jbYChallege.setEnabled(false);//��"������ս"��ť��Ϊ������
		this.father.jbNChallege.setEnabled(false);//��"�ܾ���ս"��ť��Ϊ������
		this.father.jbFaild.setEnabled(false);//��"����"��ť��Ϊ������
		this.flag=false;//��ֹ�ÿͻ��˴����߳�
		father.cat=null;
		JOptionPane.showMessageDialog(this.father,"������ֹͣ������","��ʾ",
		           JOptionPane.INFORMATION_MESSAGE);//�����������뿪����ʾ��Ϣ
	}
	public void tiao_zhan(String msg){
		try{
			String name=msg.substring(13);//�����ս�ߵ��ǳ�
			if(this.tiaozhanzhe==null){//�����ҿ���
				tiaozhanzhe=msg.substring(13);//��tiaoZhanZhe��ֵ��Ϊ��ս�ߵ��ǳ�
				this.father.jtfHost.setEnabled(false);//�������������������ı�����Ϊ������
				this.father.jtfPort.setEnabled(false);//����������˿ںŵ��ı�����Ϊ������
				this.father.jtfNickName.setEnabled(false);//�����������ǳƵ��ı�����Ϊ������
				this.father.jbConnect.setEnabled(false);//��"����"��ť��Ϊ������
				this.father.jbDisconnect.setEnabled(!true);//��"�Ͽ�"��ť��Ϊ������
				this.father.jbChallege.setEnabled(!true);//��"��ս"��ť��Ϊ������
				this.father.jbYChallege.setEnabled(!false);//��"������ս"��ť��Ϊ����
				this.father.jbNChallege.setEnabled(!false);//��"�ܾ���ս"��ť��Ϊ����
				this.father.jbFaild.setEnabled(false);//��"����"��ť��Ϊ������
				JOptionPane.showMessageDialog(this.father,tiaozhanzhe+"������ս!!!",
				           "��ʾ",JOptionPane.INFORMATION_MESSAGE);//������ս��Ϣ
			}
			else{//��������æµ�� ���򷵻�һ��<#BUSY#>��ͷ����Ϣ
				this.dout.writeUTF("<#BUSY#>"+name);
			}
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void tong_yi(){
		this.father.jtfHost.setEnabled(false);//�������������������ı�����Ϊ������
		this.father.jtfPort.setEnabled(false);//����������˿ںŵ��ı�����Ϊ������
		this.father.jtfNickName.setEnabled(false);//�����������ǳƵ��ı�����Ϊ������
		this.father.jbConnect.setEnabled(false);//��"����"��ť��Ϊ������
		this.father.jbDisconnect.setEnabled(!true);//��"�Ͽ�"��ť��Ϊ������
		this.father.jbChallege.setEnabled(!true);//��"��ս"��ť��Ϊ������
		this.father.jbYChallege.setEnabled(false);//��"������ս"��ť��Ϊ������
		this.father.jbNChallege.setEnabled(false);//��"�ܾ���ս"��ť��Ϊ������
		this.father.jbFaild.setEnabled(!false);//��"����"��ť��Ϊ������
		JOptionPane.showMessageDialog(this.father,"�Է�����������ս!��������(����)",
		                           "��ʾ",JOptionPane.INFORMATION_MESSAGE);
	}
	public void butong_yi(){
		this.father.caipan=false;//��caiPan��Ϊfalse
		this.father.color=0;//��color��Ϊ0
		this.father.jtfHost.setEnabled(false);//�������������������ı�����Ϊ������
		this.father.jtfPort.setEnabled(false);//����������˿ںŵ��ı�����Ϊ������
		this.father.jtfNickName.setEnabled(false);//�����������ǳƵ��ı�����Ϊ������
		this.father.jbConnect.setEnabled(false);//��"����"��ť��Ϊ������
		this.father.jbDisconnect.setEnabled(true);//��"�Ͽ�"��ť��Ϊ����
		this.father.jbChallege.setEnabled(true);//��"��ս"��ť��Ϊ����
		this.father.jbYChallege.setEnabled(false);//��"������ս"��ť��Ϊ������
		this.father.jbNChallege.setEnabled(false);//��"�ܾ���ս"��ť��Ϊ������
		this.father.jbFaild.setEnabled(false);//��"����"��ť��Ϊ������
		JOptionPane.showMessageDialog(this.father,"�Է��ܾ�������ս!","��ʾ",
		            JOptionPane.INFORMATION_MESSAGE);//�����Է��ܾ���ս����ʾ��Ϣ
		this.tiaozhanzhe=null;
	}
	public void busy(){
		this.father.caipan=false;//��caiPan��Ϊfalse
		this.father.color=0;//��color��Ϊ0
		this.father.jtfHost.setEnabled(false);//�������������������ı�����Ϊ������
		this.father.jtfPort.setEnabled(false);//����������˿ںŵ��ı�����Ϊ������
		this.father.jtfNickName.setEnabled(false);//�����������ǳƵ��ı�����Ϊ������
		this.father.jbConnect.setEnabled(false);//��"����"��ť��Ϊ������
		this.father.jbDisconnect.setEnabled(true);//��"�Ͽ�"��ť��Ϊ����
		this.father.jbChallege.setEnabled(true);//��"��ս"��ť��Ϊ����
		this.father.jbYChallege.setEnabled(false);//��"������ս"��ť��Ϊ������
		this.father.jbNChallege.setEnabled(false);//��"�ܾ���ս"��ť��Ϊ������
		this.father.jbFaild.setEnabled(false);//��"����"��ť��Ϊ������
		JOptionPane.showMessageDialog(this.father,"�Է�æµ�У�����","��ʾ",
		            JOptionPane.INFORMATION_MESSAGE);//�����Է�æµ����ʾ��Ϣ
		this.tiaozhanzhe=null;
	}
	public void move(String msg){
		int length=msg.length();
		int startI=Integer.parseInt(msg.substring(length-4,length-3));//������ӵ�ԭʼλ��
		int startJ=Integer.parseInt(msg.substring(length-3,length-2));
		int endI=Integer.parseInt(msg.substring(length-2,length-1));//����ߺ��λ��
		int endJ=Integer.parseInt(msg.substring(length-1));
		this.father.jpz.move(startI,startJ,endI,endJ);//���÷�������
		this.father.caipan=true;//��canPan��Ϊtrue
	}
	public void renshu(){
		JOptionPane.showMessageDialog(this.father,"��ϲ��,���ʤ,�Է�����","��ʾ",
		             JOptionPane.INFORMATION_MESSAGE);//������ʤ��Ϣ
		this.tiaozhanzhe=null;//����ս����Ϊ��
		this.father.color=0;//��color��Ϊ0
		this.father.caipan=false;//��caiPan��Ϊfalse
		this.father.next();//������һ��
		this.father.jtfHost.setEnabled(false);//�������������������ı�����Ϊ������
		this.father.jtfPort.setEnabled(false);//����������˿ںŵ��ı�����Ϊ������
		this.father.jtfNickName.setEnabled(false);//�����������ǳƵ��ı�����Ϊ������
		this.father.jbConnect.setEnabled(false);//��"����"��ť��Ϊ������
		this.father.jbDisconnect.setEnabled(true);//��"�Ͽ�"��ť��Ϊ����
		this.father.jbChallege.setEnabled(true);//��"��ս"��ť��Ϊ����
		this.father.jbYChallege.setEnabled(false);//��"������ս"��ť��Ϊ������
		this.father.jbNChallege.setEnabled(false);//��"�ܾ���ս"��ť��Ϊ������
		this.father.jbFaild.setEnabled(false);//��"����"��ť��Ϊ������
	}
}