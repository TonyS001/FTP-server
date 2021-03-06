package ftpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
    private int port;
    ServerSocket serverSocket;
    public FTPServerFrame frame = main.server_Frame;
    
    public FtpServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        
        //初始化系统信息
        
        Share.init();
    }
    
    public void listen() throws IOException {
        Socket socket = null;
            
        //这个是建立连接,三次握手的过程，当连接建立了之后，两个socket之间的通讯是直接通过流进行的，不用再通过这一步
            
        socket = serverSocket.accept();
        InetAddress ip = socket.getInetAddress();
        frame.state.setText(ip.toString().replace('/',' ')+"建立连接");
        ControllerThread thread = new ControllerThread(socket);
        thread.start();
    }
}
