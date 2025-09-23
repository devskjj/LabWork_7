package entity;

import java.util.ArrayList;
import java.util.List;

public class Event {
    private List<String> event;

    public Event() {
        event = new ArrayList<>();
        event.add("Обычный день");
        event.add("Дождь");
        event.add("Ровная дорога");
        event.add("Сломалось колесо");
        event.add("Река");
        event.add("Встретил местного ");
        event.add("Разбойники большой дороги");
        event.add("Придорожный трактир");
        event.add("Товар испортился");
    }

    public List<String> getEvent() {
        return event;
    }
}
