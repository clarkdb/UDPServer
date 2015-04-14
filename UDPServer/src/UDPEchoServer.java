import java.net.*; // for DatagramSocket, DatagramPacket, and InetAddress 
import java.io.*; // for IOException
public class UDPEchoServer {
  private static final int ECHOMAX = 65535; // Maximum size of echo datagram
  public static void main(String[] args) throws IOException {
    int servPort = Integer.parseInt(args[0]);
    DatagramSocket socket = new DatagramSocket(servPort);
    DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
    for (;;) { // Run forever, receiving and echoing datagrams
      socket.receive(packet); // Receive packet from client
      byte[] data = packet.getData();	//Get data from packet
	  String fileName = new String(data, 0, packet.getLength());
//Remove extra bytes of information from buffer outputs
	  FileOutputStream fout = new FileOutputStream(fileName.trim());
//Create output stream for fileName
	  while(true){
	  socket.receive(packet);	//Get the bytes of the packet
	  data = packet.getData();
	  String tmp = new String(data, 0, packet.getLength());
	  if(tmp.indexOf("--------MagicStringCSE283Miami") != -1){
//Check to see if terminating
	  break;	//Exit the loop
	  }
	  fout.write(data, 0, packet.getLength());	//Write to the file
	  fout.flush();	//Flush the buffer to write the bytes to the file
	  }
	  fout.close();
    }
	//socket.close();
    /* NOT REACHED */
  }
}
