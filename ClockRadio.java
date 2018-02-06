package hw2;

public class ClockRadio {

	/**
	 * Number of minutes to silence the alarm when snoozing
	 */
	public static final int SNOOZE_MINUTES = 9;
	
	/**
	 * Number of minutes in a 24-hour day
	 */
	public static final int MINUTES_PER_DAY = 1440;
	
	private int rawtime;			//time in minutes after midnight
	private int rawalarmtime;		//alarm time in minutes after midnight
	private boolean alarmon;		//false-alarm is disabled, true- it is enabled
	private boolean sounding;		//true-the clock is sounding, false-the clock isn't sounding
	private boolean militarytime;	//true-clock is in 24-hr mode, false- clock is in AM/PM mode
	private boolean radiosetting;	//true- clock will play radio when sounding, false- clock will buzz when sounding
	private int effectivealarmtime;	//when snooze is used, effectivealarmtime is 9 minutes after the time, otherwise, it is equal to rawalarmtime
	
	
	/**
	 * Constructs a clock radio with initial clock time at 00:00 and alarm time at 01:00
	 * The alarm in initially off, the clock is in 24 hour mode, and radio-mode is off.
	 */
public ClockRadio() {
	rawtime=0;
	rawalarmtime=60;
	alarmon=false;
	sounding=false;
	militarytime=true;
	radiosetting=false;
	effectivealarmtime=rawalarmtime;
}


/**
 * Constructs a clock radio with the given initial clock time and with alarm time at 01:00.
 * the alarm is initially off, clock is in 24 hour mode, and radio-mode is off.
 * @param givenMinutesPastMidnight
 * The number of minutes past midnight for this clock's initial time.
 */
public ClockRadio(int givenMinutesPastMidnight) {
	rawtime=givenMinutesPastMidnight%MINUTES_PER_DAY;
	rawalarmtime=60;
	alarmon=false;
	sounding=false;
	militarytime=true;
	radiosetting=false;
	effectivealarmtime=rawalarmtime;
}

/**
 * Advances the clock time by the given hours and minutes. If the updated clock time passes or equals the effective alarm time, and the alarm is set, then the clock should go into ringing mode. 
 * If snooze is not in effect, "effective alarm time" is just the alarm time as currently set. If snooze is in effect, the "effective alarm time" is 9 minutes after the clock time 
 * at which the snooze() method was called. If the clock goes into ringing mode as a result of advancing the time, snooze is canceled. The arguments must be non-negative numbers but may be arbitrarily large. 
 * @param givenHours
 * number of hours to advance time
 * @param givenMinutes
 * number of minutes to advance time
 */
public void advanceTime(int givenHours, int givenMinutes) {
	rawtime=(rawtime+(60*givenHours)+givenMinutes);
	if (alarmon==true) {
		if ((rawtime%MINUTES_PER_DAY==effectivealarmtime%MINUTES_PER_DAY)|(((rawtime-(60*givenHours)-givenMinutes)<effectivealarmtime)&&(rawtime>effectivealarmtime))){
			sounding=true;
		}
	}
}


/**
 * Advances the clock time by the specified number of minutes. If the updated clock time passes or equals the effective alarm time, and the alarm is on,
 *  then the clock should go into sounding mode. If snooze is not in effect, "effective alarm time" is just the alarm time as currently set. 
 *  If snooze is in effect, the "effective alarm time" is 9 minutes after the clock time at which the snooze() method was last called. If the clock goes
 *   into sounding mode as a result of advancing the time, snooze is canceled. The argument must be a non-negative number but may be arbitrarily large.
 * number of minutes to advance the time
 * @param givenMinutes
 */
public void advanceTime(int givenMinutes) {
	rawtime=(rawtime+givenMinutes);
	if (alarmon==true) {
		if ((rawtime%MINUTES_PER_DAY==effectivealarmtime%MINUTES_PER_DAY)|(((rawtime-givenMinutes)<effectivealarmtime)&&(rawtime>effectivealarmtime))){
			sounding=true;
		}
	}
}

/**
 * Determines whether the alarm is currently sounding(either buzzer or radio)
 * returns true if the clock is currently sounding
 * @return
 */
public boolean isSounding() {
	return sounding;
}

/**
 * Determines whether the alarm is currently buzzing
 * returns true if the alarm is currently buzzing
 * @return
 */
public boolean isBuzzing() {
	if (sounding==true) {
		if(radiosetting==false) {
			return true;
		}
	}
		return false;
}

/**
 * Determines whether the alarm is currently playing the radio
 * returns true if the clock is currently playing the radio
 * @return
 */
public boolean isPlayingRadio() {
	if (sounding==true) {
		if (radiosetting==true) {
			return true;
		}
	}
	
		return false;
}

/**
 * Sets whether the clock should play the buzzer or radio when the alarm is going off
 * true if clock should play radio, false otherwise.
 * @param useRadio
 */
public void setRadioMode(boolean useRadio) {
	radiosetting=useRadio;
}

/**
 * Sets whether the clock should display time strings using 24-hour mode
 * (i.e. 00:00 to 23:59) or AM/PM mode(i.e. 12:00AM to 11:59 PM)
 * true if the clock should display time strings  using 24 hour mode, false otherwise
 * @param use24HourDisplay
 */
public void set24HourDisplay(boolean use24HourDisplay) {
	militarytime=use24HourDisplay;
}

/**
 * Returns the current clock time as the number of minutes past midnight
 * This value is always between 0 and 1439, inclusive.
 * number of minutes past midnight for the clock time.
 * @return
 */
public int getClockTimeRaw() {
	return rawtime%MINUTES_PER_DAY;
}

/**
 * Returns the current alarm time as the number of minutes past midnight. 
 * This value is always between 0 and 1439, inclusive.
 * number of minutes past midnight for the alarm time
 * @return
 */
public int getAlarmTimeRaw(){
	return rawalarmtime%MINUTES_PER_DAY;
}

/**
 * Returns the effective alarm time as the number of minutes past midnight.
 *  If snooze is not in effect, this value is the same as the current alarm time.
 *  This value is always between 0 and 1439, inclusive.
 * number of minutes past midnight for the effective alarm time
 * @return
 */
public int getEffectiveAlarmTimeRaw() {
	return effectivealarmtime%MINUTES_PER_DAY;
}

/**
 * Returns the current clock time as a string in one of the following forms, 
 * depending on whether the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM". 
 * If the clock is in 24-hour mode, the hours value hh is between 0 and 23, inclusive, and if not, the hours value
 * hh is between 1 and 12, inclusive, and the appropriate string "AM" or "PM" is appended. The minutes value mm 
 * is always between 0 and 59, inclusive.
 * alarm time in string form
 * @return
 */
public java.lang.String getClockTimeAsString(){
	int hours=0;
	int minutes=0;
	int time;
	time = rawtime % MINUTES_PER_DAY;
	if (militarytime==true) {
		hours=time/60;
		minutes=time%60;
		String timeString = String.format("%02d:%02d", hours, minutes);
		return timeString;
	}
	else {
			hours=(time/60);
			int pm=hours/12;
			hours=hours%12;
			if (hours==0) {
				hours=12;
			}
			minutes=time%60;
			if (pm==1) {
			String timeString = String.format("%02d:%02d PM", hours, minutes);
			return timeString;
			}
			else {
			String timeString = String.format("%02d:%02d AM", hours, minutes);
			return timeString;
			}
	}
}

/**
 * Returns the current alarm time as a string in one of the following forms, depending on whether the clock 
 * is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM". If the clock is in 24-hour mode, the 
 * hours value hh is between 0 and 23, inclusive, and if not, the hours value hh is between 1 and 12, 
 * inclusive, and the appropriate string "AM" or "PM" is appended. The minutes value mm is always between 
 * 0 and 59, inclusive.
 * alarm time in string form
 * @return
 */
public java.lang.String getAlarmTimeAsString(){
	int hours=0;
	int minutes=0;
	int time;
	time = rawalarmtime % MINUTES_PER_DAY;
	if (militarytime==true) {
		hours=time/60;
		minutes=time%60;
		String timeString = String.format("%02d:%02d", hours, minutes);
		return timeString;
	}
	else {
			hours=(time/60);
			int pm=hours/12;
			hours=hours%12;
			minutes=time%60;
			if (hours==0) {
				hours=12;
				}
			if (pm==1) {
			String timeString = String.format("%02d:%02d PM", hours, minutes);
			return timeString;
			}
			else {
			String timeString = String.format("%02d:%02d AM", hours, minutes);
			return timeString;
			}
	}
}

/**
 * Returns the current effective alarm time as a string in one of the following forms, depending on whether 
 * the clock is currently in 24-hour mode: "hh:mm", "hh:mm AM", or "hh:mm PM". If the clock is in 24-hour 
 * mode, the hours value hh is between 0 and 23, inclusive, and if not, the hours value hh is between 1 and 
 * 12, inclusive, and the appropriate string "AM" or "PM" is appended. The minutes value mm is always 
 * between 0 and 59, inclusive.
 * effective alarm time in string form
 * @return
 */
public java.lang.String getEffectiveAlarmTimeAsString(){
	int hours=0;
	int minutes=0;
	int time;
	time = effectivealarmtime % MINUTES_PER_DAY;
	if (militarytime==true) {
		hours=time/60;
		minutes=time%60;
		String timeString = String.format("%02d:%02d", hours, minutes);
		return timeString;
	}
	else {
			hours= (time/60);
			int pm=hours/12;
			hours=hours%12;
			minutes=time%60;
			if (hours==0) {
				hours=12;
				}
			if (pm==1) {
			String timeString = String.format("%02d:%02d PM", hours, minutes);
			return timeString;
			}
			else {
			String timeString = String.format("%02d:%02d AM", hours, minutes);
			return timeString;
			}
	}
}

/**
 * Turns the alarm on. This method does not cause the alarm to start sounding regardless of current clock 
 * time and alarm time. (If clock time and alarm time are equal, it will start sounding after time is advanced 
 * by 24 hours.)
 */
public void alarmEnabled() {
	alarmon=true;
}

/**
 * Turns off the alarm, stops it from sounding (if it is sounding) and cancels snooze if it is in effect.
 */
public void alarmDisabled() {
	alarmon=false;
	sounding=false;
	effectivealarmtime=rawalarmtime;
}

/**
 * Stops the clock from sounding and sets the effective alarm time for SNOOZE_MINUTES minutes after the 
 * current clock time. Does not disable the alarm or change the alarm time. Does nothing if the alarm is not 
 * currently sounding.
 */
public void snooze() {
	if (sounding==true) {
		if (alarmon==true) {
	sounding=false;
	effectivealarmtime=rawtime+SNOOZE_MINUTES;
		}
		}
}

/**
 * Sets the current clock time to the given number of minutes past midnight. The argument is assumed to 
 * be non-negative but may be arbitrarily large. This method will not cause the clock to start sounding, even 
 * if the alarm is currently set, and will not cause it to stop ringing if it is already in the ringing state. If 
 * snooze is in effect, it is canceled by this method.
 * number of minutes past midnight
 * @param givenMinutesPastMidnight
 */
public void setTime(int givenMinutesPastMidnight) {
	rawtime=givenMinutesPastMidnight;
	effectivealarmtime=rawalarmtime;
}

/**
 * Sets the current clock time to the given hours and minutes. The hours are assumed to be in the range [1, 12] 
 * and minutes are assumed to be in the range [0, 59]. The given time is interpreted as AM or PM based on the 
 * third argument, regardless of whether the clock is currently in 24-hour mode. This method will not cause the 
 * clock to start sounding, even if the alarm is currently set, and will not cause it to stop ringing if it is 
 * already in the ringing state. If snooze is in effect, it is canceled by this method.
 * hour for alarm time
 * @param givenHours
 * minutes for alarm time
 * @param givenMinutes
 * true if the given hours should be interpreted as a PM time, false if AM
 * @param isPm
 */
public void setTime(int givenHours, int givenMinutes, boolean isPm) {
	rawtime=(60*givenHours)+givenMinutes;
	effectivealarmtime=rawalarmtime;
	if (givenHours==12) {
		rawtime=givenMinutes;
	}
	if (isPm==true) {
		rawtime=rawtime+(MINUTES_PER_DAY/2);
	}
}

/**
 * Sets the alarm time to the given number of minutes past midnight. The hours and minutes are assumed 
 * to be non-negative but may be arbitrarily large. This method will not cause the clock to start sounding, 
 * and will not cause it to stop sounding if it is already in the sounding state. If snooze is in effect, it is 
 * canceled by this method.
 * number of minutes past midnight
 * @param givenMinutesPastMidnight
 */
public void setAlarmTime(int givenMinutesPastMidnight) {
	rawalarmtime=givenMinutesPastMidnight;
	effectivealarmtime=rawalarmtime;
}

/**
 * Sets the alarm time to the given hours and minutes. The hours are assumed to be in the range [1, 12] and minutes 
 * are assumed to be in the range [0, 59]. The given time is interpreted as AM or PM based on the third argument, 
 * regardless of whether the clock is currently in 24-hour mode. This method will not cause the clock to start sounding, 
 * and will not cause it to stop sounding if it is already in the sounding state. If snooze is in effect, it is canceled 
 * by this method.
 * hour for alarm time
 * @param givenHours
 * minutes for alarm time
 * @param givenMinutes
 * true if the given hours and minutes should be interpreted as a PM time, false if AM
 * @param isPm
 */
public void setAlarmTime(int givenHours, int givenMinutes, boolean isPm) {
	rawalarmtime=(60*givenHours)+givenMinutes;
	if (givenHours==12) {
		rawalarmtime=givenMinutes;
	}
	if(isPm==true) {
		rawalarmtime=rawalarmtime+(MINUTES_PER_DAY/2);
	}
	effectivealarmtime=rawalarmtime;
}
}
