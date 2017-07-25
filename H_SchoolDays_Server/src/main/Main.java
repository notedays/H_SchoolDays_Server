package main;

import java.io.File;
import java.io.IOException;

import com.schoolDays.himaginus.server.main.ServerMain;

import process.StudentPacketExecutor;
import process.PacketHandler;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		File config = new File("config.properties");
		StudentPacketExecutor executor = StudentPacketExecutor.getInstance();
		ServerMain.load(config, new PacketHandler(executor), executor);
	}
}
