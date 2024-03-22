package csi5324.event_management;

import csi5324.event_management.domain.Event;
import csi5324.event_management.domain.Location;
import csi5324.event_management.repositories.EventRepository;
import csi5324.event_management.repositories.LocationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class EventManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner dataLoader(EventRepository repo, LocationRepository locRepo) {
//        return args -> {
//            Event e = new Event();
//            e.setName("Test Event");
//            e.setDateHeld(LocalDate.of(2020, 1, 1));
//            e.setDateRegistrationBegins(LocalDate.of(2019, 11, 1));
//            e.setDescription("Test Event");
//            e.setStartTime(LocalDateTime.now());
//            e.setEndTime(LocalDateTime.now());
//            e.setCapacity(20L);
//            e.setAgeRestricted(false);
//            e.setMinimumAge(0);
//
//            repo.save(e);
//
//            Location l = new Location();
//            l.setName("Location Test");
//            l.setIdentifier("L.11");
//            l.setDescription("Location Tester");
//
//            locRepo.save(l);
//        };
//    }

}
