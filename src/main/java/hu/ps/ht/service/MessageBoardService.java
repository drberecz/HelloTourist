package hu.ps.ht.service;

import hu.ps.ht.dao.BookingDAO;
import hu.ps.ht.entity.BookingEntity;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class MessageBoardService {

    @Inject
    BookingDAO bookingDAO;

    public static Map<Long, StringBuffer> messageCache;

    static {
        messageCache = new HashMap<>();
    }

    public void addCommentToBoard(Long bookingId, String comment) {

        BookingEntity bookingEntity = bookingDAO.find(BookingEntity.class, bookingId);

        StringBuffer sb = bookingEntity.getMessageBoard();
        sb.append("\n\n").append(comment);
        bookingEntity.setMessageBoard(sb);
        bookingDAO.updateEntity(bookingEntity);
        messageCache.put(bookingId, sb);
    }

    public String msgAjax(Long bookingId, String username) {

        if (messageCache.containsKey(bookingId)) {
            return messageCache.get(bookingId).toString();
        }

        addCommentToBoard(bookingId, "\nUser: " + username + " is online.");
        return "NO ENTRY...yet.";
    }

}
