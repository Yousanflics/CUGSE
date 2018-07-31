import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
import java.net.*;
import java.io.*;
public class XiangQi extends JFrame implements ActionListener{
	public static final Color bgColor=new Color(245,250,160);
	public static final Color focusbg=new Color(242,242,242);//����ѡ�к�ı���ɫ
	public static final Color focuschar =new Color(96,95,91);//����ѡ��֮�����ɫ
	public static final Color colorRed = Color.red;
	public static final Color colorWirte =new Color(255,255,255);//����Color.wirte;
	JLabel jlHost=new JLabel("������");
	JLabel jlPort =new JLabel("�˿ں�");
	JLabel jlNickName =new JLabel("�ǳ�");
	JTextField jtfHost=new JTextField("127.0.0.1");
	JTextField jtfPort =new JTextField("9999");
	public static JTextField jtfChat =new JTextField("���ѽ���ҵȵĻ�����л��");//���ʹ���
	JTextField jtfNickName =new JTextField("Play1");
	JButton jbConnect=new JButton("����");
	JButton jbDisconnect=new JButton("�Ͽ�");
	JButton jbFaild =new JButton("����");
	JButton jbChallege=new JButton("��ս");
	JButton jbYChallege=new JButton("������ս");
	JButton jbNChallege=new JButton("�ܾ���ս");
	public static Vector v=new Vector();
	public static DefaultListModel model=new DefaultListModel();
	public static TalkClient tc;
	JList jlChatHistory = new JList();//���������¼
	JScrollPane jspx_chat = new JScrollPane(jlChatHistory);//����ʾ�����¼��Jlist����JScrollpane��
	public static JButton jbSent = new JButton("����");//������Ϣ��ť
	public static JComboBox jcbNickList =new JComboBox();//������ŵ�ǰ�û��������б�
	int width=60;//������������֮��ľ��� 
	QiZi[][] qizi=new QiZi[9][10];//��������
	QiPan jpz=new QiPan(qizi, width, this);
	JPanel jpy=new JPanel();
	JSplitPane jps =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jpz,jpy);
	boolean caipan=false;//�ɷ������־
	int color=0;//0��ʾ���죬1��ʾ����
	Socket sc;//����socket����
	ClientAgentThread cat;
	public XiangQi()
	{
		this.initialComponet();//��ʼ���ؼ�

		this.addListener();//
		this.initialState();
		this.initialFrame();
		this.initialQiZi();
	}
	public void initialComponet()
	{
		jpy.setLayout(null);
		this.jlHost.setBounds(10,10,50,20);//�ĸ�����  ǰ��������λ�ã��������Ǵ�С
		jpy.add(this.jlHost);
		this.jtfHost.setBounds(70,10,80,20);
		jpy.add(this.jtfHost);
		this.jlPort.setBounds(10,40,80,20);
		jpy.add(this.jlPort);
		this.jtfPort.setBounds(70,40,80,20);
		jpy.add(this.jtfPort);
		this.jlNickName.setBounds(10,70,80,20);
		jpy.add(this.jlNickName);
		this.jtfNickName.setBounds(70,70,80,20);
		jpy.add(this.jtfNickName);//����ǳ�
		this.jbDisconnect.setBounds(100, 100, 80, 20);
		jpy.add(this.jbDisconnect);
		this.jcbNickList.setBounds(20, 130, 130, 20);
		jpy.add(this.jcbNickList);
		this.jbChallege.setBounds(10,160,80,20);
		jpy.add(this.jbChallege);
		this.jbConnect.setBounds(10,100,80,20);
		jpy.add(this.jbConnect);
		this.jbFaild.setBounds(100, 160, 80, 20);
		jpy.add(this.jbFaild);
		this.jbNChallege.setBounds(100,190,86,20);
		jpy.add(this.jbNChallege);
		this.jbYChallege.setBounds(5,190,86,20);
		jpy.add(this.jbYChallege);
		this.jspx_chat.setBounds(5,220,200,420);
		jpy.add(this.jspx_chat);
		this.jtfChat.setBounds(3, 650, 140, 20);
		jpy.add(this.jtfChat);
		this.jbSent.setBounds(142,650,60,20);
		jpy.add(this.jbSent);
	}
	public void addListener()//��Ӽ�����
	{
		jbSent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tc.sendMessage();
			}
		});
		this.jbChallege.addActionListener(this);
		this.jbConnect.addActionListener(this);
		this.jbDisconnect.addActionListener(this);
		this.jbFaild.addActionListener(this);
		this.jbNChallege.addActionListener(this);
		this.jbYChallege.addActionListener(this);
	}
	public void initialState()
	{
		this.jbDisconnect.setEnabled(false);
		this.jbChallege.setEnabled(false);
		this.jbConnect.setEnabled(true);
		this.jbFaild.setEnabled(false);
		this.jbNChallege.setEnabled(false);
		this.jbYChallege.setEnabled(false);
	}
	public void initialQiZi()//��ʼ������
	{
		qizi[0][0]=new QiZi(colorRed,"܇",0,0);
		qizi[1][0]=new QiZi(colorRed,"�R",1,0);
		qizi[2][0]=new QiZi(colorRed,"��",2,0);
		qizi[3][0]=new QiZi(colorRed,"��",3,0);
		qizi[4][0]=new QiZi(colorRed,"˧",4,0);
		qizi[5][0]=new QiZi(colorRed,"��",5,0);
		qizi[6][0]=new QiZi(colorRed,"��",6,0);		
		qizi[7][0]=new QiZi(colorRed,"�R",7,0);
		qizi[8][0]=new QiZi(colorRed,"܇",8,0);
		qizi[1][2]=new QiZi(colorRed,"�h",1,2);
		qizi[0][3]=new QiZi(colorRed,"��",0,3);
		qizi[2][3]=new QiZi(colorRed,"��",2,3);
		qizi[4][3]=new QiZi(colorRed,"��",4,3);
		qizi[7][2]=new QiZi(colorRed,"�h",7,2);
		qizi[6][3]=new QiZi(colorRed,"��",6,3);
	    qizi[8][3]=new QiZi(colorRed,"��",8,3);
//	    qizi[10][0]=new QiZi(colorRed,"˧",10,0);
	    
		qizi[0][9]=new QiZi(colorWirte,"܇",0,9);
		qizi[1][9]=new QiZi(colorWirte,"�R",1,9);
		qizi[2][9]=new QiZi(colorWirte,"��",2,9);
		qizi[3][9]=new QiZi(colorWirte,"ʿ",3,9);
		qizi[4][9]=new QiZi(colorWirte,"��",4,9);
		qizi[5][9]=new QiZi(colorWirte,"ʿ",5,9);
		qizi[6][9]=new QiZi(colorWirte,"��",6,9);		
		qizi[7][9]=new QiZi(colorWirte,"�R",7,9);
		qizi[8][9]=new QiZi(colorWirte,"܇",8,9);
		qizi[1][7]=new QiZi(colorWirte,"��",1,7);
		qizi[0][6]=new QiZi(colorWirte,"��",0,6);
		qizi[2][6]=new QiZi(colorWirte,"��",2,6);
		qizi[4][6]=new QiZi(colorWirte,"��",4,6);
		qizi[7][7]=new QiZi(colorWirte,"��",7,7);
		qizi[6][6]=new QiZi(colorWirte,"��",6,6);
	    qizi[8][6]=new QiZi(colorWirte,"��",8,6);
	}
	public void initialFrame()
	{
		this.setTitle("�й�����--�ͻ���");
		Image image=new ImageIcon("ico.gif").getImage();
		this.setIconImage(image);
		this.add(this.jps);
		jps.setDividerLocation(730);
		jps.setDividerSize(4);
		this.setBounds(30,30,960,730);
		this.setVisible(true); 
		this.addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						if(cat==null)//�ͻ��˴����߳�Ϊ�գ�ֱ���˳�
						{
							System.exit(0);//�˳�
							return;
						}
						try
						{
							if(cat.tiaozhanzhe!=null)//����������
							{
								try
								{
									//����������Ϣ
									cat.dout.writeUTF("<#RENSHU#>"+cat.tiaozhanzhe);
								}
								catch(Exception ee)
								{
									ee.printStackTrace();
								}
							}
							cat.dout.writeUTF("<#CLIENT_LEAVE#>");//������������뿪��Ϣ
							cat.flag=false;//��ֹ�ͻ��˴����߳�
							cat=null;
							
						}
						catch(Exception ee)
						{
							ee.printStackTrace();
						}
						System.exit(0);//�˳�
					}
					
				}
				);
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.jbConnect)
			 this.jbConnect_event();
		else if(e.getSource()==this.jbChallege)
			this.jbChallege_event();
		else if(e.getSource()==this.jbDisconnect)
			this.jbDisconnect_event();
		else if(e.getSource()==this.jbFaild)
			this.jbFaild_event();
		else if(e.getSource()==this.jbNChallege)
			this.jbNChallege_event();
		else if(e.getSource()==this.jbYChallege)
			this.jbYChallege_event();
	}
	public void jbFaild_event() {
		// TODO Auto-generated method stub
		try{
			this.cat.dout.writeUTF("<#RENSHU#>"+this.cat.tiaozhanzhe);//--���
			this.cat.tiaozhanzhe=null;//��tiaoZhanZhe��Ϊ��--���
			this.color=0;
			this.caipan=false;
			this.next();//��ʼ������
			this.jbConnect.setEnabled(false);
			this.jtfHost.setEnabled(false);
			this.jtfNickName.setEditable(false);
			this.jtfPort.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallege.setEnabled(!false);
			this.jbYChallege.setEnabled(false);
			this.jbNChallege.setEnabled(false);
			this.jbFaild.setEnabled(false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void next() {
		// TODO Auto-generated method stub
		for(int i=0;i<9;i++)
			for(int j=0;j<10;j++)
			{
				this.qizi[i][j]=null;
			}
		this.caipan=false;
		this.initialQiZi();//���³�ʼ������
		this.repaint();//�ػ�
		}
	public void jbNChallege_event() {
		// TODO Auto-generated method stub
		try{
			this.cat.dout.writeUTF("<#BUTONG_YI#>"+this.cat.tiaozhanzhe);//--���
			this.cat.tiaozhanzhe=null;//��tiaoZhanZhe��Ϊ��--���
			this.jbConnect.setEnabled(false);
			this.jtfHost.setEnabled(false);
			this.jtfNickName.setEditable(false);
			this.jtfPort.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallege.setEnabled(!false);
			this.jbYChallege.setEnabled(false);
			this.jbNChallege.setEnabled(false);
			this.jbFaild.setEnabled(false);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void jbYChallege_event() {
		// TODO Auto-generated method stub
		try{
			this.cat.dout.writeUTF("<#TONG_YI#>"+this.cat.tiaozhanzhe);//--���
			this.caipan=false;
			this.color=1;
			this.jbConnect.setEnabled(false);
			this.jtfHost.setEnabled(false);
			this.jtfNickName.setEditable(false);
			this.jtfPort.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallege.setEnabled(false);
			this.jbYChallege.setEnabled(false);
			this.jbNChallege.setEnabled(false);
			this.jbFaild.setEnabled(!false);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void jbDisconnect_event() {
		// TODO Auto-generated method stub
		try{
			this.cat.dout.writeUTF("<#CLIENT_LEAVE#>");//�����������������Ϣ----���
			this.cat.flag=false;//��ֹ�ͻ��˴����߳�----���
			this.cat=null;//----���
			this.jbConnect.setEnabled(!false);
			this.jtfHost.setEnabled(!false);
			this.jtfNickName.setEditable(!false);
			this.jtfPort.setEnabled(!false);
			this.jbDisconnect.setEnabled(!true);
			this.jbChallege.setEnabled(false);
			this.jbYChallege.setEnabled(false);
			this.jbNChallege.setEnabled(false);
			this.jbFaild.setEnabled(false);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void jbConnect_event() {
		// TODO Auto-generated method stub
		String playName = "";
		int port=0;
		try{
			port=Integer.parseInt(this.jtfPort.getText().trim());
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(this, "�˿�ֻ��������","����" ,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(port<0||port>65535)
		{
			JOptionPane.showMessageDialog(this, "�˿ں�ֻ��Ϊ0-65535","����",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String name =this.jtfNickName.getText().trim();
		if(name.length()==0)
		{
			JOptionPane.showMessageDialog(this, "�����������Ϊ��","����",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try{
			sc=new Socket(this.jtfHost.getText().trim(),port);
			cat=new ClientAgentThread(this);//�����ͻ��˴����߳�	----���
			cat.start();//--���
			playName = jtfNickName.getText().trim();
			this.jtfHost.setEnabled(false);
			this.jtfPort.setEnabled(false);
			this.jtfNickName.setEditable(false);
			this.jbConnect.setEnabled(false);
			this.jbDisconnect.setEnabled(true);
			this.jbChallege.setEnabled(true);
			this.jbYChallege.setEnabled(false);
			this.jbNChallege.setEnabled(false);
			this.jbFaild.setEnabled(false);
			JOptionPane.showMessageDialog(this, "���������ӳɹ�","��ʾ",
					JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this,"����������ʧ��","����",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		initTalkClient(playName, jtfChat, jcbNickList, jlChatHistory, v);
	}
	
	/*
	 * TalkClient��ʼ��
	 */
	public void initTalkClient(String name, JTextField chat, JComboBox jcbNickList, JList jlChatHistory, Vector v) {
		tc = new TalkClient("127.0.0.1", 8888, name, chat, jcbNickList, jlChatHistory, v);
	}
	
	public void jbChallege_event() {
		// TODO Auto-generated method stub
		Object o= this.jcbNickList.getSelectedItem();
		if(o==null||((String)o).equals(""))
		{
			JOptionPane.showMessageDialog(this, "��ѡ��Է�����","����",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			String name2=(String)this.jcbNickList.getSelectedItem();
			try{
				this.jbConnect.setEnabled(false);
				this.jtfHost.setEnabled(false);
				this.jtfNickName.setEditable(false);
				this.jtfPort.setEnabled(false);
				this.jbDisconnect.setEnabled(!true);
				this.jbChallege.setEnabled(false);
				this.jbYChallege.setEnabled(false);
				this.jbNChallege.setEnabled(false);
				this.jbFaild.setEnabled(false);
				this.cat.tiaozhanzhe=name2;//������ս����--���
				this.caipan=true;
				this.color=0;
				this.cat.dout.writeUTF("<#TIAO_ZHAN#>"+name2);//������ս��Ϣ---���
				JOptionPane.showMessageDialog(this, "�������ս����ȴ��ظ�","��ʾ",JOptionPane.ERROR_MESSAGE);
				}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			}
		}
public static void main(String args[]){
	new XiangQi();
	}
}

