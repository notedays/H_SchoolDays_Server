package process;

import java.util.Hashtable;

import data.StudentData;

public class WorldMap {

	private static final int MAX_USER = 4000;

	private Hashtable<Integer, StudentData> allStudents;

	public WorldMap() {
		allStudents = new Hashtable<>(MAX_USER);
	}

	public StudentData addStudent(int id, StudentData data) {
		return allStudents.put(id, data);
	}

	public StudentData removeStudent(int id) {
		return allStudents.remove(id);
	}
}
