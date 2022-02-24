package com.example.demo.services;


import com.example.demo.domain.News;
import com.example.demo.domain.Property;
import com.example.demo.domain.RoomType;
import com.example.demo.domain.Staff;

import com.example.demo.domain.enums.Positions;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {


    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private BedRepository bedRepository;

    @Autowired
    private NewsRepository newsRepository;

    public DBService() {
    }

    public void instantiateTestDatabase() {
        Staff firstStaff = new Staff(null, "marcus@moura", pe.encode("12345"),
                "Marcus Moura", Positions.RECEPTIONIST);
        Staff secondStaff = new Staff(null, "carol@propato", pe.encode("12345"),
                "Carol Propato", Positions.MANAGER);
        Staff thirdStaff = new Staff(null, "maya@santos", pe.encode("12345"),
                "Maya Santos", Positions.MANAGER);

        staffRepository.saveAll(Arrays.asList(firstStaff, secondStaff, thirdStaff));

        // ============================

        Property gardinerHouse = new Property(null, "Gardiner House",
                "76, Gardiner Upper Street");
        gardinerHouse.addPropertyToList(gardinerHouse);
        RoomType sixteenBedChapel = new RoomType(null, "18-bed dorm mixed",
                "6A", "Chapel", 16, gardinerHouse);

        RoomType eightBedMixed = new RoomType(null, "8-bed dorm mixed", "10", "1", 8, gardinerHouse);

        propertyRepository.save(gardinerHouse);
        roomTypeRepository.saveAll(Arrays.asList(sixteenBedChapel, eightBedMixed));
        bedRepository.saveAll(sixteenBedChapel.getBedsList());
        bedRepository.saveAll(eightBedMixed.getBedsList());
        String imageMessage = "This is a test image";
        String imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Fphotos%2Fgalway&psig=AOvVaw1Y4TJPKf76_47nl1iiYfjV&ust=1645801669435000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJiD15DPmPYCFQAAAAAdAAAAABAD";
        News news1 = new News(null, imageUrl, imageMessage);
        newsRepository.save(news1);

    }


}
