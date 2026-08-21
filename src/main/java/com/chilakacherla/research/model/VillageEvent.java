package com.chilakacherla.research.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VillageEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventTitle;
    private String category; // TEMPLE_FESTIVAL, SPORTS_TOURNAMENT, GRAMA_SABHA, CULTURAL
    private String eventDate;
    private String venue;
    private String organizer;
    private String description;

    public VillageEvent() {}

    public VillageEvent(String eventTitle, String category, String eventDate, String venue, String organizer, String description) {
        this.eventTitle = eventTitle;
        this.category = category;
        this.eventDate = eventDate;
        this.venue = venue;
        this.organizer = organizer;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getEventTitle() { return eventTitle; }
    public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
