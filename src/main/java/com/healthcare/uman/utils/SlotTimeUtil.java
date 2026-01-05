package com.healthcare.uman.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.healthcare.uman.dto.booking.SlotTimeEnumDTO;

public class SlotTimeUtil {

    private static final String TIME_FORMAT = "HH:mm";

    public static Date convertSlotTimeToTime(SlotTimeEnumDTO slotTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        return sdf.parse(slotTime.getValue());
    }

    public static boolean isSlotTimeBetween(Date timeToCheck, SlotTimeEnumDTO startTime, SlotTimeEnumDTO endTime) throws ParseException {
        Date startTimeDate = convertSlotTimeToTime(startTime);
        Date endTimeDate = convertSlotTimeToTime(endTime);
        return (timeToCheck.after(startTimeDate) || timeToCheck.equals(startTimeDate)) && timeToCheck.before(endTimeDate);
    }
}
