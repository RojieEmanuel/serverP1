package ServerP1.ServerP1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ServerSocket server = new ServerSocket(5678);
			System.out.println("Server online");
			
			ExecutorService pool =  Executors.newFixedThreadPool(20);
			while(true) {
				pool.execute(new Cliente(server.accept()));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
