import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class TalkServer {
	
	Socket s;
	List<Solve> solves = new ArrayList<Solve>();
	
	public TalkServer(int port) {
		try {
			// 设置sever端的链接
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				s = ss.accept();// sever等待链接
				Solve solve = new Solve(s);
				new Thread(solve).start();
				solves.add(solve);
			}

		}
		catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class Solve implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		
		public Solve(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void send(String str) {
			try {
				System.out.println("server,send");
				dos.writeUTF(str);
			} catch (IOException e) {
				solves.remove(this);
			}
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
					while(true) {
						String str = dis.readUTF();
						System.out.println(str);
						for(int i=0; i<solves.size(); i++) {
							Solve c = solves.get(i);
							c.send(str);
						}
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null)  {
						s.close();
						//s = null;
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
 

