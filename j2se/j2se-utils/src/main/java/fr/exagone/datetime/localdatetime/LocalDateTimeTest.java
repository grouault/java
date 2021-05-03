package fr.exagone.datetime.localdatetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class LocalDateTimeTest {

	public static void main(String[] args) {
		
		// Default Time-zone of System
		System.out.println("System : default-timezone = " + ZoneId.systemDefault());
		
		// Get current date and time in the local timezone
		LocalDateTime now = LocalDateTime.now();
		System.out.println("now = " + now);
		
		// Get current date and time at UTC
		LocalDateTime nowUTC = LocalDateTime.now(ZoneOffset.UTC);
		System.out.println("nowUTC = " + nowUTC);
		
		// Get current date and time in a different timezone
		ZonedDateTime nowInLosAngeles = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
		System.out.println("nowInLosAngeles = " + nowInLosAngeles);
		
		// Get Paris offset
		ZoneId zone = ZoneId.of("Europe/Paris");
		ZoneOffset zoneOffSet = zone.getRules().getOffset(now);
		System.out.println("Offset Paris = " + zoneOffSet);
		
	}
	
}
