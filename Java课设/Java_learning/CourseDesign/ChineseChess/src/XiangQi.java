import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import java.util.*;
import java.net.*;
import java.io.*;
public class XiangQi extends JFrame implements ActionListener{
	public static final Color bgColor=new Color(245,250,160);
	public static final Color focusbg=new Color(242,242,242);//棋子选中后的背景色
	public static final Color focuschar =new Color(96,95,91);//棋子选中之后的颜色
	public static final Color colorRed = Color.red;
	public static final Color colorWirte =new Color(255,255,255);//或者Color.wirte;
	JLabel jlHost=new JLabel("主机名");
	JLabel jlPort =new JLabel("端口号");
	JLabel jlNickName =new JLabel("昵称");
	JTextField jtfHost=new JTextField("127.0.0.1");
	JTextField jtfPort =new JTextField("9999");
	public static JTextField jtfChat =new JTextField("快点呀，我等的花儿都谢了");//发送窗口
	JTextField jtfNickName =new JTextField("Play1");
	JButton jbConnect=new JButton("连接");
	JButton jbDisconnect=new JButton("断开");
	JButton jbFaild =new JButton("认输");
	JButton jbChallege=new JButton("挑战");
	JButton jbYChallege=new JButton("接受挑战");
	JButton jbNChallege=new JButton("拒绝挑战");
	public static Vector v=new Vector();
	public static DefaultListModel model=new DefaultListModel();
	public static TalkClient tc;
	JList jlChatHistory = new JList();//放置聊天记录
	JScrollPane jspx_chat = new JScrollPane(jlChatHistory);//将显示聊天记录的Jlist放在JScrollpane中
	public static JButton jbSent = new JButton("发送");//发送消息按钮
	public static JComboBox jcbNickList =new JComboBox();//创建存放当前用户的下拉列表
	int width=60;//设置棋盘两线之间的距离 
	QiZi[][] qizi=new QiZi[9][10];//棋子数组
	QiPan jpz=new QiPan(qizi, width, this);
	JPanel jpy=new JPanel();
	JSplitPane jps =new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jpz,jpy);
	boolean caipan=false;//可否走棋标志
	int color=0;//0表示红旗，1表示白棋
	Socket sc;//声明socket引用
	ClientAgentThread cat;
	public XiangQi()
	{
		this.initialComponet();//初始化控件

		this.addListener();//
		this.initialState();
		this.initialFrame();
		this.initialQiZi();
	}
	public void initialComponet()
	{
		jpy.setLayout(null);
		this.jlHost.setBounds(10,10,50,20);//四个参数  前边两个是位置，后两个是大小
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
		jpy.add(this.jtfNickName);//添加昵称
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
	public void addListener()//添加监听器
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
	public void initialQiZi()//初始化棋子
	{
		qizi[0][0]=new QiZi(colorRed,"車",0,0);
		qizi[1][0]=new QiZi(colorRed,"馬",1,0);
		qizi[2][0]=new QiZi(colorRed,"相",2,0);
		qizi[3][0]=new QiZi(colorRed,"仕",3,0);
		qizi[4][0]=new QiZi(colorRed,"帅",4,0);
		qizi[5][0]=new QiZi(colorRed,"仕",5,0);
		qizi[6][0]=new QiZi(colorRed,"相",6,0);		
		qizi[7][0]=new QiZi(colorRed,"馬",7,0);
		qizi[8][0]=new QiZi(colorRed,"車",8,0);
		qizi[1][2]=new QiZi(colorRed,"砲",1,2);
		qizi[0][3]=new QiZi(colorRed,"兵",0,3);
		qizi[2][3]=new QiZi(colorRed,"兵",2,3);
		qizi[4][3]=new QiZi(colorRed,"兵",4,3);
		qizi[7][2]=new QiZi(colorRed,"砲",7,2);
		qizi[6][3]=new QiZi(colorRed,"兵",6,3);
	    qizi[8][3]=new QiZi(colorRed,"兵",8,3);
//	    qizi[10][0]=new QiZi(colorRed,"帅",10,0);
	    
		qizi[0][9]=new QiZi(colorWirte,"車",0,9);
		qizi[1][9]=new QiZi(colorWirte,"馬",1,9);
		qizi[2][9]=new QiZi(colorWirte,"象",2,9);
		qizi[3][9]=new QiZi(colorWirte,"士",3,9);
		qizi[4][9]=new QiZi(colorWirte,"将",4,9);
		qizi[5][9]=new QiZi(colorWirte,"士",5,9);
		qizi[6][9]=new QiZi(colorWirte,"象",6,9);		
		qizi[7][9]=new QiZi(colorWirte,"馬",7,9);
		qizi[8][9]=new QiZi(colorWirte,"車",8,9);
		qizi[1][7]=new QiZi(colorWirte,"炮",1,7);
		qizi[0][6]=new QiZi(colorWirte,"卒",0,6);
		qizi[2][6]=new QiZi(colorWirte,"卒",2,6);
		qizi[4][6]=new QiZi(colorWirte,"卒",4,6);
		qizi[7][7]=new QiZi(colorWirte,"炮",7,7);
		qizi[6][6]=new QiZi(colorWirte,"卒",6,6);
	    qizi[8][6]=new QiZi(colorWirte,"卒",8,6);
	}
	public void initialFrame()
	{
		this.setTitle("中国象棋--客户端");
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
						if(cat==null)//客户端代理线程为空，直接退出
						{
							System.exit(0);//退出
							return;
						}
						try
						{
							if(cat.tiaozhanzhe!=null)//正在下棋中
							{
								try
								{
									//发送认输信息
									cat.dout.writeUTF("<#RENSHU#>"+cat.tiaozhanzhe);
								}
								catch(Exception ee)
								{
									ee.printStackTrace();
								}
							}
							cat.dout.writeUTF("<#CLIENT_LEAVE#>");//向服务器发送离开信息
							cat.flag=false;//终止客户端代理线程
							cat=null;
							
						}
						catch(Exception ee)
						{
							ee.printStackTrace();
						}
						System.exit(0);//退出
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
			this.cat.dout.writeUTF("<#RENSHU#>"+this.cat.tiaozhanzhe);//--添加
			this.cat.tiaozhanzhe=null;//将tiaoZhanZhe设为空--添加
			this.color=0;
			this.caipan=false;
			this.next();//初始化棋盘
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
		this.initialQiZi();//重新初始化棋子
		this.repaint();//重绘
		}
	public void jbNChallege_event() {
		// TODO Auto-generated method stub
		try{
			this.cat.dout.writeUTF("<#BUTONG_YI#>"+this.cat.tiaozhanzhe);//--添加
			this.cat.tiaozhanzhe=null;//将tiaoZhanZhe设为空--添加
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
			this.cat.dout.writeUTF("<#TONG_YI#>"+this.cat.tiaozhanzhe);//--添加
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
			this.cat.dout.writeUTF("<#CLIENT_LEAVE#>");//向服务器发送离线消息----添加
			this.cat.flag=false;//终止客户端代理线程----添加
			this.cat=null;//----添加
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
			JOptionPane.showMessageDialog(this, "端口只能是整数","错误" ,
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(port<0||port>65535)
		{
			JOptionPane.showMessageDialog(this, "端口号只能为0-65535","错误",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String name =this.jtfNickName.getText().trim();
		if(name.length()==0)
		{
			JOptionPane.showMessageDialog(this, "玩家姓名不能为空","错误",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try{
			sc=new Socket(this.jtfHost.getText().trim(),port);
			cat=new ClientAgentThread(this);//创建客户端代理线程	----添加
			cat.start();//--添加
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
			JOptionPane.showMessageDialog(this, "服务器连接成功","提示",
					JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this,"服务器连接失败","错误",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		initTalkClient(playName, jtfChat, jcbNickList, jlChatHistory, v);
	}
	
	/*
	 * TalkClient初始化
	 */
	public void initTalkClient(String name, JTextField chat, JComboBox jcbNickList, JList jlChatHistory, Vector v) {
		tc = new TalkClient("127.0.0.1", 8888, name, chat, jcbNickList, jlChatHistory, v);
	}
	
	public void jbChallege_event() {
		// TODO Auto-generated method stub
		Object o= this.jcbNickList.getSelectedItem();
		if(o==null||((String)o).equals(""))
		{
			JOptionPane.showMessageDialog(this, "请选择对方名字","错误",JOptionPane.ERROR_MESSAGE);
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
				this.cat.tiaozhanzhe=name2;//设置挑战对象--添加
				this.caipan=true;
				this.color=0;
				this.cat.dout.writeUTF("<#TIAO_ZHAN#>"+name2);//发送挑战信息---添加
				JOptionPane.showMessageDialog(this, "已提出挑战，请等待回复","提示",JOptionPane.ERROR_MESSAGE);
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

