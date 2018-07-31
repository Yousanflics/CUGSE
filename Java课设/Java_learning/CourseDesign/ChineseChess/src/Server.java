import java.util.*;
import java.awt.*;
import java.awt.event.*;//event-事件处理
import javax.swing.*;
import java.net.*;

public class Server extends JFrame implements ActionListener{
	JLabel jlPort=new JLabel("端口号");
	JTextField jtfPort = new JTextField("9999");
	JButton jbStart =new JButton("启动");
	JButton jbStop = new JButton("停止");
	JPanel jps =new JPanel();
	JList jlUersOnline = new JList();//创建用于显示当前用户的Jlist
	JScrollPane jspx = new JScrollPane(jlUersOnline);//将显示当前用户的Jlist放在JScrollpane中
	JSplitPane jspz = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspx,jps);
	ServerSocket ss;//声明Socket引用
	ServerThread st;//声明Thread引用
	Vector onlineList = new Vector();//创建存放当前在线用户的Vector对象    比较安全   如果需要速度比较快 可以使用ArrayList
	public Server(){
		this.initialComponent();
		this.addListener();
		this.initalFrame();
		new TalkServer(8888);
	}
	//初始化控件
	public void initialComponent(){
		jps.setLayout(null);
		jlPort.setBounds(20, 20, 50, 20);
		jps.add(this.jlPort);
		this.jtfPort.setBounds(85,20,60,20);
		jps.add(this.jtfPort);
		this.jbStart.setBounds(18,50,60,20);
		jps.add(this.jbStart);
		this.jbStop.setBounds(85,50,60,20);
		jps.add(this.jbStop);
		this.jbStop.setEnabled(false);		
		}
	//未相应的控件注册事件监听器
	public void addListener(){
		this.jbStart.addActionListener(this);
		this.jbStop.addActionListener(this);
	}
	public void initalFrame(){
		this.setTitle("中国象棋");
		Image image =new ImageIcon("icon.gif").getImage();
		this.setIconImage(image);
		this.add(jspz);
		jspz.setDividerLocation(250);	
		jspz.setDividerSize(4);//分割线粗细
		this.setBounds(20,20,420,320);
		this.setVisible(true);
		this.addWindowListener(//为关闭窗口注册监听器
				new WindowAdapter() 
				{
					public void windowClosing(WindowEvent e)
					{
						if(st==null)//当服务器端线程为空时直接退出
						{
							System.exit(0);//退出
							return;
						}
						try{
							Vector v=onlineList;
							int size=v.size();
							for(int i=0;i<size;i++)
							{
								ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
								satTemp.dout.writeUTF("<#SERVER_DOWN#>");
								satTemp.flag=false;//终止服务器代理线程
							}
							st.flag=false;
							st=null;
							ss.close();//关闭ServeSocket
							v.clear();//用户列表清空
							refreshList();//刷新列表
						}
						catch(Exception ee)
						{
							ee.printStackTrace();
						}
						System.exit(0);
					}
				}
				);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() ==this.jbStart)
		{
			this.jbStart_event();
		}
		else if(e.getSource()==this.jbStop)
		{
			this.jbStop_event();
		}
	}
	public void jbStart_event(){
		int port=0;
		try
		{
			//获得用户输入的端口号，并转化为整型数字
			port=Integer.parseInt(this.jtfPort.getText().trim());//trim（）去掉空格
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(this, "端口号只能是整数，错误","错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(port>65535||port<0)
		{
		   JOptionPane.showMessageDialog(this, "端口号只能为0-65535的整数","错误",JOptionPane.ERROR_MESSAGE);
		   return;
		}
		try{
			this.jbStart.setEnabled(false);//将开始按钮设置为不可用
			this.jbStop.setEnabled(true);//将关闭按钮设置为可用
			this.jtfPort.setEnabled(!true);
			ss=new ServerSocket(port);
			st= new ServerThread(this);//创建服务器线程
			st.start();
			JOptionPane.showMessageDialog(this, "服务器启动成功","提示",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this, "服务器启动失败","错误",JOptionPane.ERROR_MESSAGE);
			this.jbStart.setEnabled(true);//将开始按钮设置为不可用
			this.jbStop.setEnabled(false);//将关闭按钮设置为可用
			this.jtfPort.setEnabled(true);	
		}
		//单击“开始”按钮处理代码
	}
	public void jbStop_event(){
		//单击“停止”按钮处理代码
		try
		{
			Vector v=onlineList;
			int size=v.size();
			for(int i=0;i<size;i++)
			{
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				satTemp.dout.writeUTF("<#SERVER_DOWN#>");
				satTemp.flag=false;//关闭服务器代理线程
			}
			st.flag=false;
			st=null;
			ss.close();
			v.clear();//在线用户列表清空
			refreshList();
			this.jbStart.setEnabled(true);
			this.jbStop.setEnabled(false);
			this.jtfPort.setEnabled(true);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
	public void refreshList(){
		//更新在线用户信息列表
		Vector v=new Vector();
		int size=this.onlineList.size();
		for(int i=0;i<size;i++)
		{
			ServerAgentThread satTemp=(ServerAgentThread)this.onlineList.get(i);
			String temps=satTemp.sc.getInetAddress().toString();
			temps=temps+"|"+satTemp.getName();//获得所需信息
			v.add(temps);//添加到vector中
		}
		this.jlUersOnline.setListData(v);//更新列表
	}
	public static void main(String args[]){
		//主程序
		new Server();
		System.out.println("服务器端已执行完毕");
	}
	}