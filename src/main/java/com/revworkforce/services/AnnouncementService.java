package com.revworkforce.services;

import com.revworkforce.dao.AnnouncementDAO;
import java.util.List;

public class AnnouncementService {
    private AnnouncementDAO announcementDAO = new AnnouncementDAO();

    public boolean addAnnouncement(String type, Integer empId, String msg) {
        return announcementDAO.addAnnouncement(type, empId, msg);
    }

    public List<String> getAllAnnouncements() {
        return announcementDAO.getAllAnnouncements();
    }
}
