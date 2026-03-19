package com.revworkforce.services;

import com.revworkforce.dao.HolidayDAO;
import java.sql.Date;
import java.util.List;

public class HolidayService {

    private HolidayDAO holidayDAO = new HolidayDAO();

    public boolean addHoliday(String name, String desc, Date date) {
        return holidayDAO.addHoliday(name, desc, date);
    }

    public boolean deleteHoliday(int id) {
        return holidayDAO.deleteHoliday(id);
    }

    public List<String> getAllHolidays() {
        return holidayDAO.getAllHolidays();
    }
}
