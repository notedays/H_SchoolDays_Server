package main;

import java.io.File;
import java.io.IOException;

import com.himaginus.server.main.ServerMain;
import com.himaginus.server.process.PacketHandler;

import process.StudentPacketExecutor;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		File config = new File("config.properties");
		StudentPacketExecutor executor = StudentPacketExecutor.getInstance();
		ServerMain.load(config, new PacketHandler(executor));
		System.out.println("Netty Server Open");
	}
}
