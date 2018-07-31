import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

public class TalkClient {
	public String msg;
	public String ip;
	public int port;
	public String name;
	public String opponent;
	public JTextField jtfChat;
	public Socket s1;
	public JComboBox jcbNickList;
	public JList jlChatHistory;
	public Vector v;
	
	InputStream is;
	DataInputStream dis;
	OutputStream os;
	DataOutputStream dos;
	
	public TalkClient(String ip, int port, String name, JTextField jtfChat, JComboBox jcbNickList, JList jlChatHistory, Vector v) {
		this.ip = ip;
		this.port = port;
		this.name = name;
		this.jtfChat = jtfChat;
		this.jlChatHistory = jlChatHistory;
		this.v = v;
		
		
		
		this.jcbNickList = jcbNickList;
		try {
			// ����sever��
			s1 = new Socket(ip, port);
			// ʵ����������
			is = s1.getInputStream();
			dis = new DataInputStream(is);
			// ʵ���������
			os = s1.getOutputStream();
			dos = new DataOutputStream(os);
			// ʵ������������
			Thread mcr = new MyClientReader(dis, jlChatHistory, v, name, jcbNickList);
			// ������������
			mcr.start();
			// �����쳣
			System.out.println("socket��ʼ��");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage() {
		System.out.println("sendmessage");
		msg = jtfChat.getText().trim();
		jtfChat.setText("");
		opponent = (String)jcbNickList.getSelectedItem();
		try {
			dos.writeUTF(name + "#" + opponent + "#" + msg);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
}



// ����һ�������������н��ն�ȡ����
class MyClientReader extends Thread {
	private DataInputStream dis;
	private String name;
	private JList jlChatHistory;
	private Vector v;
	private String opponent;
	public MyClientReader(DataInputStream dis, JList jlChatHistory, Vector v, String name, JComboBox jcbNickList) {
		this.dis = dis;
		this.jlChatHistory = jlChatHistory;
		this.v = v;
		this.name = name;
		opponent = (String)jcbNickList.getSelectedItem();
	}

	@Override
	public void run() {
		String msg;
		try {
				while(true) {
					msg = dis.readUTF();
					//System.out.println("receive" + msg);
					String[] msg2 = msg.split("#");
					System.out.println(msg2[0] + "  "+  msg2[1] + "  "+ msg2[2]);
					System.out.println(name);
						v.add(msg2[0]+ ":" + msg2[2]);
						jlChatHistory.setListData(v);
					
				}
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

// ����һ����������д�벢��������
class MyClientWriter extends Thread {
	private String msg;
	OutputStream os;
	DataOutputStream dos = new DataOutputStream(os);
	private String name;
	private String opponent;
	public MyClientWriter(DataOutputStream dos, String name, String opponent, String msg) {
		this.dos = dos;
		this.name = name;
		this.opponent = opponent;
		this.msg = msg;
	}

	@Override
	public void run() {
		
		try {
				dos.writeUTF(name + "#" + opponent + "#" + msg);
				dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
