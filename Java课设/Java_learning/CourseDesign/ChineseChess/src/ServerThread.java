import java.net.*;
public class ServerThread extends Thread{
	Server father;
	ServerSocket ss;
	boolean flag=true;
	public ServerThread(Server father){
		this.father= father;
		ss=father.ss;
		}
	public void run(){
		while(flag)
		{
			try{
				Socket sc=ss.accept();//�ȴ��ͻ�����
				ServerAgentThread sat=new ServerAgentThread(father,sc);
				sat.start();//���������������������߳�
				System.out.println("������");
				}
			catch(Exception e){
				e.printStackTrace();	
			}
		}
	}

}
