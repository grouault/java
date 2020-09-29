package fr.exagone.datetime.calendar;

public class TimeTest {

	public static void main(String[] args) {
		
		long time1 = System.currentTimeMillis();
		long time2;
		try {
			Thread.sleep(1000);
			time2 = System.currentTimeMillis();
			formatTime(time2 - time1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void formatTime(long millis) {
		long second = (millis / 1000) % 60;
		long minute = (millis / (1000 * 60)) % 60;
		long hour = (millis / (1000 * 60 * 60)) % 24;
		String time = String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
		System.out.println(time);
	}
}

