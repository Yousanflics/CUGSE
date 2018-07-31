import sys
import socket
import random

import Checksum#为了后面的检查包情况

class BasicSender(object):
    def __init__(self, dest, port, filename, debug=False):
        self.debug = debug
        self.dest = dest
        self.dport = port
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)#创建UDP传输对象，启动通讯
        self.sock.settimeout(None)  #设置延迟
        self.sock.bind(('', random.randint(10000, 40000)))#绑定端口，IP为本地端口
        if filename == None:
            self.infile = sys.stdin
        else:
            self.infile = open(filename, "rb")#rb 以二进制格式打开文件只用来读

    #等待，直到前面的数据包发送到了服务端
    #接收端
    def receive(self, timeout=None):#未设置延时
        self.sock.settimeout(timeout)
        try:
            return self.sock.recv(4096)#接收到的最大字节数
        except (socket.timeout, socket.error):#如果出现了时间延时和socket的链接如端口被占据或者说允许接入的客户端达到了上限
            return None

    #发送消息给接收端，对应的接收端是在创建发送端时指定的
    def send(self, message, address=None):
        if address is None:
            address = (self.dest, self.dport)
        self.sock.sendto(message, address)

    #创建一个BEARS-TP包，并指定该包的消息类型
    def make_packet(self, msg_type, seqno, message):
        body = "%s|%d|%s|" % (msg_type, seqno, message)#%将前面的变量和后面的字符关联起来
        checksum = Checksum.generate_checksum(body)#嵌入方便绑定
        packet = "%s%s" % (body, checksum)
        return packet

    #将给定的一个BEAR-TP包转换成(msg_type,seqno,message)形式的元组(数组)
    def split_packet(self, message):
        pieces = message.split('|')
        #前两个肯定是msg_type和seqno
        msg_type, seqno = pieces[0:2]
        checksum = pieces[-1]  #最后一个肯定是checksum
        #这个其中的就是我们需要的数据了
        data = '|'.join(pieces[2:-1])#从第二个开始到最后一个
        return msg_type, seqno, data, checksum

    #此处没有必要实例化,因此将其作为一个接口,让后面的程序去实现
    def start(self):
        raise NotImplementedError
