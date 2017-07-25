package database;

import java.util.List;

import com.himaginus.common.data.CityData;
import com.schoolDays.himaginus.server.database.DataController;
import com.schoolDays.himaginus.server.database.DataMap;

public class SchoolDataController extends DataController {
	private static SchoolDataController sdc = new SchoolDataController();

	private SchoolDataController() {
	}

	public static SchoolDataController getInstance() {
		return sdc;
	}

	public CityData getCityInfo(int id) {
		CityData data = new CityData();
		String sql = "select * from city where ID = ?";
		Object[] params = { id };
		List<DataMap<String, Object>> list = simpleQuerySelect(sql, params);
		for (DataMap<String, Object> map : list) {
			data.setId(map.getInt("ID"));
			data.setName(map.getString("Name"));
			data.setCountryCode(map.getString("CountryCode"));
			data.setDistrict(map.getString("District"));
			data.setPopulation(map.getInt("Population"));
		}
		return data;
	}
}
