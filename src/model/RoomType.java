package model;

import java.util.ArrayList;

public enum RoomType {
	OFFICE, SERVICE, PRODUCTION, ELSE;

	public static ArrayList<RoomType> getRoomType() {
		ArrayList<RoomType> roomType = new ArrayList<>();
		roomType.add(ELSE);
		roomType.add(OFFICE);
		roomType.add(SERVICE);
		roomType.add(PRODUCTION);
		return roomType;

	}
}
