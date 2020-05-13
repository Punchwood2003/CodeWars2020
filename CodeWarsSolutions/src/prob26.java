import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class prob26 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine()) {
			Scanner temp = new Scanner(scan.nextLine());
			String[] parts = new String[5];
			for(int i = 0; i < 5; i ++) {
				parts[i] = temp.next();
			}
			
			String[] part1 = parts[0].split("-");
			String[] part2 = parts[1].split(":");
			String[] part3 = parts[2].split("-");
			String[] part4 = parts[3].split(":");
			String code = parts[4];
			
			CountDown clock = new CountDown(part1[0], part3[0], part1[1], part3[1], part1[2], part3[2], part2[0], part4[0], part2[1], part4[1], part2[2], part4[2], code);
			clock.printTimeLeft();
		}
	}
}
class CountDown {
	public int year1, year2, month1, month2, day1, day2, 
	hour1, hour2, minutes1, minutes2, seconds1, seconds2;
	public String code;
	public Calendar date1, date2;
	public CountDown(String year1, String year2, String month1, String month2, String day1, String day2, String hour1, String hour2, String minutes1, String minutes2, String seconds1, String seconds2, String code) {
		this.year1 = Integer.parseInt(year1); 
		this.year2 = Integer.parseInt(year2);
		this.month1 = Integer.parseInt(month1); 
		this.month2 = Integer.parseInt(month2);
		this.day1 = Integer.parseInt(day1); 
		this.day2 = Integer.parseInt(day2);
		this.hour1 = Integer.parseInt(hour1); 
		this.hour2 = Integer.parseInt(hour2);
		this.minutes1 = Integer.parseInt(minutes1); 
		this.minutes2 = Integer.parseInt(minutes2);
		this.seconds1 = Integer.parseInt(seconds1); 
		this.seconds2 = Integer.parseInt(seconds2);
		
		this.date1 = Calendar.getInstance();
		this.date2 = Calendar.getInstance();
		this.date1.set(this.year1, this.month1, this.day1);
		this.date2.set(this.year2, this.month2, this.day2);
		
		this.code = code;
	}
	public void printTimeLeft() {
		switch(this.code) {
			case "D" : {
				printD();
				break;
			}
			case "H" : {
				printH();
				break;
			}
			case "M" : {
				printM();
				break;
			}
			case "S" : {
				printS();
				break;
			}
			case "DHMS" : {
				printDHMS();
				break;
			}
			case "HMS" : {
				printHMS();
				break;
			}
			case "MS" : {
				printMS();
				break;
			}
			case "DH" : {
				printDH();
				break;
			}
			case "DM" : {
				printDM();
				break;
			}
			case "DS" : {
				printDS();
				break;
			}
			case "HM" : {
				printHM();
				break;
			}
			case "HS" : {
				printHS();
				break;
			}
		}
	}
	public void printD() {
		System.out.println(date2.getTimeInMillis() + " " + date1.getTimeInMillis());
		
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long days = milliseconds / 86400000;
		milliseconds -= days * 86400000;
		System.out.println("there are " + days + " days remaining until " + printEndDate());
	}
	public void printH() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		System.out.println("there are " + hours + " hours remaining until " + printEndDate());
	}
	public void printM() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		System.out.println("there are " + minutes + " minutes remaining until " + printEndDate());
	}
	public void printS() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;
		System.out.println("there are " + seconds + " seconds remaining until " + printEndDate());
	}
	public void printDHMS() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long days = milliseconds / 86400000;
		milliseconds -= days * 86400000;
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;
		System.out.println("there are " + days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds remaining until " + printEndDate());
	}
	public void printHMS() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;
		System.out.println("there are " + hours + " hours " + minutes + " minutes " + seconds + " seconds remaining until " + printEndDate());
	}
	public void printMS() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;
		System.out.println("there are " + minutes + " minutes " + seconds + " seconds remaining until " + printEndDate());
	}
	public void printDH() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long days = milliseconds / 86400000;
		milliseconds -= days * 86400000;
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		System.out.println("there are " + days + " days " + hours + " hours seconds remaining until " + printEndDate());
	}
	public void printDM() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long days = milliseconds / 86400000;
		milliseconds -= days * 86400000;
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		System.out.println("there are " + days + " days " + minutes + " minutes remaining until " + printEndDate());
	}
	public void printDS() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long days = milliseconds / 86400000;
		milliseconds -= days * 86400000;
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;
		System.out.println("there are " + days + " days " + seconds + " seconds remaining until " + printEndDate());
	}
	public void printHM() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		long minutes = milliseconds / 60000;
		milliseconds -= minutes * 60000;
		System.out.println("there are " + hours + " hours " + minutes + " minutes remaining until " + printEndDate());
	}
	public void printHS() {
		long milliseconds = (date2.getTimeInMillis() + (this.hour2 * 3600000) + (this.minutes2 * 60000) + (this.seconds2 * 1000)) - (date1.getTimeInMillis()  + (this.hour1 * 3600000) + (this.minutes1 * 60000) + (this.seconds1 * 1000));
		long hours = milliseconds / 3600000;
		milliseconds -= hours * 3600000;
		long seconds = milliseconds / 1000;
		milliseconds -= seconds * 1000;
		System.out.println("there are " + hours + " hours " + seconds + " seconds remaining until " + printEndDate());
	}
	public String printEndDate() {
		String out = String.format("%04d-%02d-%02d %02d:%02d:%02d", this.year2, this.month2, this.day2, this.hour2, this.minutes2, this.seconds2);
		return out;
	}
}
