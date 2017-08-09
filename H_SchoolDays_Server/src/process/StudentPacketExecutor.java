package process;

import com.himaginus.common.data.CityData;
import com.himaginus.common.data.TestData;
import com.himaginus.common.packet.RequestPacket;
import com.himaginus.common.packet.ResponsePacket;
import com.schoolDays.himaginus.server.process.PacketExecutor;

import database.SchoolDataController;
import io.netty.channel.ChannelHandlerContext;

public class StudentPacketExecutor implements PacketExecutor{
	
	private static StudentPacketExecutor executor = new StudentPacketExecutor();
	private StudentPacketExecutor() {}
	public static StudentPacketExecutor getInstance(){
		return executor;
	}
	
	public SchoolDataController sdc = SchoolDataController.getInstance();
	
	@Override
	public void execute(ChannelHandlerContext session, RequestPacket request) {
		ResponsePacket response;
		String params[]	= request.getContext().split("\n");
		switch (request.getCode()) {
		case RequestPacket.TEST:{
			response = new ResponsePacket();
			response.setCode(ResponsePacket.TEST);
			TestData data = new TestData();
			data.cityList = sdc.getCityInfo(Integer.parseInt(params[0]));
			for(CityData city : data.cityList){
				data.cityMap.put(city.getId(), city);
			}
			response.addResponseData(data);
			session.writeAndFlush(response);
			break;
		}
		case RequestPacket.REGIST:{
			
			break;
		}
		}
	}
	
	public void send(ChannelHandlerContext ctx, ResponsePacket response){
		ctx.writeAndFlush(response);
	}
}
