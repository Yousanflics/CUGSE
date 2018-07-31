import java.util.*;
import java.awt.*;
import java.awt.event.*;//event-�¼�����
import javax.swing.*;
import java.net.*;

public class Server extends JFrame implements ActionListener{
	JLabel jlPort=new JLabel("�˿ں�");
	JTextField jtfPort = new JTextField("9999");
	JButton jbStart =new JButton("����");
	JButton jbStop = new JButton("ֹͣ");
	JPanel jps =new JPanel();
	JList jlUersOnline = new JList();//����������ʾ��ǰ�û���Jlist
	JScrollPane jspx = new JScrollPane(jlUersOnline);//����ʾ��ǰ�û���Jlist����JScrollpane��
	JSplitPane jspz = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jspx,jps);
	ServerSocket ss;//����Socket����
	ServerThread st;//����Thread����
	Vector onlineList = new Vector();//������ŵ�ǰ�����û���Vector����    �Ƚϰ�ȫ   �����Ҫ�ٶȱȽϿ� ����ʹ��ArrayList
	public Server(){
		this.initialComponent();
		this.addListener();
		this.initalFrame();
		new TalkServer(8888);
	}
	//��ʼ���ؼ�
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
	//δ��Ӧ�Ŀؼ�ע���¼�������
	public void addListener(){
		this.jbStart.addActionListener(this);
		this.jbStop.addActionListener(this);
	}
	public void initalFrame(){
		this.setTitle("�й�����");
		Image image =new ImageIcon("icon.gif").getImage();
		this.setIconImage(image);
		this.add(jspz);
		jspz.setDividerLocation(250);	
		jspz.setDividerSize(4);//�ָ��ߴ�ϸ
		this.setBounds(20,20,420,320);
		this.setVisible(true);
		this.addWindowListener(//Ϊ�رմ���ע�������
				new WindowAdapter() 
				{
					public void windowClosing(WindowEvent e)
					{
						if(st==null)//�����������߳�Ϊ��ʱֱ���˳�
						{
							System.exit(0);//�˳�
							return;
						}
						try{
							Vector v=onlineList;
							int size=v.size();
							for(int i=0;i<size;i++)
							{
								ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
								satTemp.dout.writeUTF("<#SERVER_DOWN#>");
								satTemp.flag=false;//��ֹ�����������߳�
							}
							st.flag=false;
							st=null;
							ss.close();//�ر�ServeSocket
							v.clear();//�û��б����
							refreshList();//ˢ���б�
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
			//����û�����Ķ˿ںţ���ת��Ϊ��������
			port=Integer.parseInt(this.jtfPort.getText().trim());//trim����ȥ���ո�
		}
		catch(Exception ee)
		{
			JOptionPane.showMessageDialog(this, "�˿ں�ֻ��������������","����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(port>65535||port<0)
		{
		   JOptionPane.showMessageDialog(this, "�˿ں�ֻ��Ϊ0-65535������","����",JOptionPane.ERROR_MESSAGE);
		   return;
		}
		try{
			this.jbStart.setEnabled(false);//����ʼ��ť����Ϊ������
			this.jbStop.setEnabled(true);//���رհ�ť����Ϊ����
			this.jtfPort.setEnabled(!true);
			ss=new ServerSocket(port);
			st= new ServerThread(this);//�����������߳�
			st.start();
			JOptionPane.showMessageDialog(this, "�����������ɹ�","��ʾ",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this, "����������ʧ��","����",JOptionPane.ERROR_MESSAGE);
			this.jbStart.setEnabled(true);//����ʼ��ť����Ϊ������
			this.jbStop.setEnabled(false);//���رհ�ť����Ϊ����
			this.jtfPort.setEnabled(true);	
		}
		//��������ʼ����ť�������
	}
	public void jbStop_event(){
		//������ֹͣ����ť�������
		try
		{
			Vector v=onlineList;
			int size=v.size();
			for(int i=0;i<size;i++)
			{
				ServerAgentThread satTemp=(ServerAgentThread)v.get(i);
				satTemp.dout.writeUTF("<#SERVER_DOWN#>");
				satTemp.flag=false;//�رշ����������߳�
			}
			st.flag=false;
			st=null;
			ss.close();
			v.clear();//�����û��б����
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
		//���������û���Ϣ�б�
		Vector v=new Vector();
		int size=this.onlineList.size();
		for(int i=0;i<size;i++)
		{
			ServerAgentThread satTemp=(ServerAgentThread)this.onlineList.get(i);
			String temps=satTemp.sc.getInetAddress().toString();
			temps=temps+"|"+satTemp.getName();//���������Ϣ
			v.add(temps);//��ӵ�vector��
		}
		this.jlUersOnline.setListData(v);//�����б�
	}
	public static void main(String args[]){
		//������
		new Server();
		System.out.println("����������ִ�����");
	}
	}