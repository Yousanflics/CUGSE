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
				Socket sc=ss.accept();//等待客户接入
				ServerAgentThread sat=new ServerAgentThread(father,sc);
				sat.start();//创建并启动服务器代理线程
				System.out.println("已连接");
				}
			catch(Exception e){
				e.printStackTrace();	
			}
		}
	}

}
