import javax.swing.*;
import java.util.*;
import java.io.*;
public class ClientAgentThread extends Thread
{
	XiangQi father;//声明XiangQi的引用
	boolean flag=true;//控制线程的标志位
	DataInputStream din;//声明数据输入输出流的引用 
	DataOutputStream dout;
	String tiaozhanzhe=null;//用于记录正在挑战的对手
	public ClientAgentThread(XiangQi father)
	{
		this.father=father;
		try
		{
			din=new DataInputStream(father.sc.getInputStream());//创建数据输入输出流
			dout=new DataOutputStream(father.sc.getOutputStream());
			
			String name=father.jtfNickName.getText().trim();//获得昵称
			dout.writeUTF("<#NICK_NAME#>"+name);//发送昵称到服务器
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void run(){
		while(flag){
			try{
				String msg=din.readUTF().trim();//获得服务器发来的信息
				if(msg.startsWith("<#NAME_CHONGMING#>")){//收到重名的信息
					this.name_chongming();
				}
				else if(msg.startsWith("<#NICK_LIST#>")){//收到昵称列表
					this.nick_list(msg);
				}
				else if(msg.startsWith("<#SERVER_DOWN#>")){//当收到服务器离开的信息
					this.server_down();
				}
				else if(msg.startsWith("<#TIAO_ZHAN#>")){//当收到挑战的信息
					this.tiao_zhan(msg);
				}
				else if(msg.startsWith("<#TONG_YI#>")){
					this.tong_yi();//当该用户收到对方接受挑战的信息时	
				}
				else if(msg.startsWith("<#BUTONG_YI#>")){
					this.butong_yi();//当该用户收到对方拒绝挑战的信息时
				}
				else if(msg.startsWith("<#BUSY#>")){//当收到对方忙的信息时
					this.busy();
				}
				else if(msg.startsWith("<#MOVE#>")){//当收到走棋的信息时
					this.move(msg);
				}
				else if(msg.startsWith("<#RENSHU#>")){//当收到认输的信息时
					this.renshu();
				}
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	public void name_chongming(){
		try{
			JOptionPane.showMessageDialog(this.father,"该玩家名称已经被占用，请重新填写！",
			            "错误",JOptionPane.ERROR_MESSAGE);//给出重名的提示信息
			din.close();//关闭数据输入流
			dout.close();//关闭数据输出流
			this.father.jtfHost.setEnabled(!false);//将用于输入主机名的文本框设为可用
			this.father.jtfPort.setEnabled(!false);//将用于输入端口号的文本框设为可用
			this.father.jtfNickName.setEnabled(!false);//将用于输入昵称的文本框设为可用
			this.father.jbConnect.setEnabled(!false);//将"连接"按钮设为可用
			this.father.jbDisconnect.setEnabled(!true);//将"断开"按钮设为不可用
			this.father.jbChallege.setEnabled(!true);//将"挑战"按钮设为不可用
			this.father.jbYChallege.setEnabled(false);//将"接受挑战"按钮设为不可用
			this.father.jbNChallege.setEnabled(false);//将"拒绝挑战"按钮设为不可用
			this.father.jbFaild.setEnabled(false);//将"认输"按钮设为不可用
			father.sc.close();//关闭Socket
			father.sc=null;
			father.cat=null;
			flag=false;//终止该客户端代理线程
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void nick_list(String msg){
		String s=msg.substring(13);//分解并得到有用信息
		String[] na=s.split("\\|");
		Vector v=new Vector();//创建Vector对象
		for(int i=0;i<na.length;i++){
			if(na[i].trim().length()!=0&&(!na[i].trim().equals(father.jtfNickName.getText().trim()))){
				v.add(na[i]);//将昵称全部添加到Vector中
			}
		}
		father.jcbNickList.setModel(new DefaultComboBoxModel(v));//设置下拉列表的值
	}
	public void server_down(){
		this.father.jtfHost.setEnabled(!false);//将用于输入主机名的文本框设为可用
		this.father.jtfPort.setEnabled(!false);;//将用于输入端口号的文本框设为可用
		this.father.jtfNickName.setEnabled(!false);//将用于输入昵称的文本框设为可用
		this.father.jbConnect.setEnabled(!false);//将"连接"按钮设为可用
		this.father.jbDisconnect.setEnabled(!true);//将"断开"按钮设为不可用
		this.father.jbChallege.setEnabled(!true);//将"挑战"按钮设为不可用
		this.father.jbYChallege.setEnabled(false);//将"接受挑战"按钮设为不可用
		this.father.jbNChallege.setEnabled(false);//将"拒绝挑战"按钮设为不可用
		this.father.jbFaild.setEnabled(false);//将"认输"按钮设为不可用
		this.flag=false;//终止该客户端代理线程
		father.cat=null;
		JOptionPane.showMessageDialog(this.father,"服务器停止！！！","提示",
		           JOptionPane.INFORMATION_MESSAGE);//给出服务器离开的提示信息
	}
	public void tiao_zhan(String msg){
		try{
			String name=msg.substring(13);//获得挑战者的昵称
			if(this.tiaozhanzhe==null){//如果玩家空闲
				tiaozhanzhe=msg.substring(13);//将tiaoZhanZhe的值赋为挑战者的昵称
				this.father.jtfHost.setEnabled(false);//将用于输入主机名的文本框设为不可用
				this.father.jtfPort.setEnabled(false);//将用于输入端口号的文本框设为不可用
				this.father.jtfNickName.setEnabled(false);//将用于输入昵称的文本框设为不可用
				this.father.jbConnect.setEnabled(false);//将"连接"按钮设为不可用
				this.father.jbDisconnect.setEnabled(!true);//将"断开"按钮设为不可用
				this.father.jbChallege.setEnabled(!true);//将"挑战"按钮设为不可用
				this.father.jbYChallege.setEnabled(!false);//将"接受挑战"按钮设为可用
				this.father.jbNChallege.setEnabled(!false);//将"拒绝挑战"按钮设为可用
				this.father.jbFaild.setEnabled(false);//将"认输"按钮设为不可用
				JOptionPane.showMessageDialog(this.father,tiaozhanzhe+"向你挑战!!!",
				           "提示",JOptionPane.INFORMATION_MESSAGE);//给出挑战信息
			}
			else{//如果该玩家忙碌中 ，则返回一个<#BUSY#>开头的信息
				this.dout.writeUTF("<#BUSY#>"+name);
			}
		}
		catch(IOException e){e.printStackTrace();}
	}
	public void tong_yi(){
		this.father.jtfHost.setEnabled(false);//将用于输入主机名的文本框设为不可用
		this.father.jtfPort.setEnabled(false);//将用于输入端口号的文本框设为不可用
		this.father.jtfNickName.setEnabled(false);//将用于输入昵称的文本框设为不可用
		this.father.jbConnect.setEnabled(false);//将"连接"按钮设为不可用
		this.father.jbDisconnect.setEnabled(!true);//将"断开"按钮设为不可用
		this.father.jbChallege.setEnabled(!true);//将"挑战"按钮设为不可用
		this.father.jbYChallege.setEnabled(false);//将"接受挑战"按钮设为不可用
		this.father.jbNChallege.setEnabled(false);//将"拒绝挑战"按钮设为不可用
		this.father.jbFaild.setEnabled(!false);//将"认输"按钮设为不可用
		JOptionPane.showMessageDialog(this.father,"对方接受您的挑战!您先走棋(红棋)",
		                           "提示",JOptionPane.INFORMATION_MESSAGE);
	}
	public void butong_yi(){
		this.father.caipan=false;//将caiPan设为false
		this.father.color=0;//将color设为0
		this.father.jtfHost.setEnabled(false);//将用于输入主机名的文本框设为不可用
		this.father.jtfPort.setEnabled(false);//将用于输入端口号的文本框设为不可用
		this.father.jtfNickName.setEnabled(false);//将用于输入昵称的文本框设为不可用
		this.father.jbConnect.setEnabled(false);//将"连接"按钮设为不可用
		this.father.jbDisconnect.setEnabled(true);//将"断开"按钮设为可用
		this.father.jbChallege.setEnabled(true);//将"挑战"按钮设为可用
		this.father.jbYChallege.setEnabled(false);//将"接受挑战"按钮设为不可用
		this.father.jbNChallege.setEnabled(false);//将"拒绝挑战"按钮设为不可用
		this.father.jbFaild.setEnabled(false);//将"认输"按钮设为不可用
		JOptionPane.showMessageDialog(this.father,"对方拒绝您的挑战!","提示",
		            JOptionPane.INFORMATION_MESSAGE);//给出对方拒绝挑战的提示信息
		this.tiaozhanzhe=null;
	}
	public void busy(){
		this.father.caipan=false;//将caiPan设为false
		this.father.color=0;//将color设为0
		this.father.jtfHost.setEnabled(false);//将用于输入主机名的文本框设为不可用
		this.father.jtfPort.setEnabled(false);//将用于输入端口号的文本框设为不可用
		this.father.jtfNickName.setEnabled(false);//将用于输入昵称的文本框设为不可用
		this.father.jbConnect.setEnabled(false);//将"连接"按钮设为不可用
		this.father.jbDisconnect.setEnabled(true);//将"断开"按钮设为可用
		this.father.jbChallege.setEnabled(true);//将"挑战"按钮设为可用
		this.father.jbYChallege.setEnabled(false);//将"接受挑战"按钮设为不可用
		this.father.jbNChallege.setEnabled(false);//将"拒绝挑战"按钮设为不可用
		this.father.jbFaild.setEnabled(false);//将"认输"按钮设为不可用
		JOptionPane.showMessageDialog(this.father,"对方忙碌中！！！","提示",
		            JOptionPane.INFORMATION_MESSAGE);//给出对方忙碌的提示信息
		this.tiaozhanzhe=null;
	}
	public void move(String msg){
		int length=msg.length();
		int startI=Integer.parseInt(msg.substring(length-4,length-3));//获得棋子的原始位置
		int startJ=Integer.parseInt(msg.substring(length-3,length-2));
		int endI=Integer.parseInt(msg.substring(length-2,length-1));//获得走后的位置
		int endJ=Integer.parseInt(msg.substring(length-1));
		this.father.jpz.move(startI,startJ,endI,endJ);//调用方法走棋
		this.father.caipan=true;//将canPan设为true
	}
	public void renshu(){
		JOptionPane.showMessageDialog(this.father,"恭喜你,你获胜,对方认输","提示",
		             JOptionPane.INFORMATION_MESSAGE);//给出获胜信息
		this.tiaozhanzhe=null;//将挑战者设为空
		this.father.color=0;//将color设为0
		this.father.caipan=false;//将caiPan设为false
		this.father.next();//进入下一盘
		this.father.jtfHost.setEnabled(false);//将用于输入主机名的文本框设为不可用
		this.father.jtfPort.setEnabled(false);//将用于输入端口号的文本框设为不可用
		this.father.jtfNickName.setEnabled(false);//将用于输入昵称的文本框设为不可用
		this.father.jbConnect.setEnabled(false);//将"连接"按钮设为不可用
		this.father.jbDisconnect.setEnabled(true);//将"断开"按钮设为可用
		this.father.jbChallege.setEnabled(true);//将"挑战"按钮设为可用
		this.father.jbYChallege.setEnabled(false);//将"接受挑战"按钮设为不可用
		this.father.jbNChallege.setEnabled(false);//将"拒绝挑战"按钮设为不可用
		this.father.jbFaild.setEnabled(false);//将"认输"按钮设为不可用
	}
}