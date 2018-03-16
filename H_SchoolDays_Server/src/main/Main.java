package main;

import java.io.File;
import java.io.IOException;

import com.himaginus.common.goback.packet.GoBackRequestPacket;
import com.himaginus.common.goback.packet.GoBackResponsePacket;
import com.himaginus.server.main.ServerMain;
import com.himaginus.server.process.PacketHandler;

import process.GoBackPacketExecutor;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		File config = new File("config.properties");
		GoBackPacketExecutor executor = GoBackPacketExecutor.getInstance();
		ServerMain.load(config, new GoBackRequestPacket(), new GoBackResponsePacket(), new PacketHandler(executor));
		System.out.println("Netty Server Open");
	}
}
