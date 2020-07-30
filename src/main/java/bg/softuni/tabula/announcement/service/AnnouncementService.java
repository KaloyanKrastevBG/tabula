package bg.softuni.tabula.announcement.service;

import bg.softuni.tabula.announcement.model.AnnouncementDTO;
import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.model.AnnouncementMapper;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public List<AnnouncementDTO> findAll(){

        return announcementRepository.findAll()
                .stream().
                map(AnnouncementMapper.INSTANCE::mapAnnouncementEntotytoDto)
                .collect(Collectors.toList());

    }

    public void cleanupOldAnnouncements(){
        Instant endTime = Instant.now().minus(7, ChronoUnit.DAYS);
        announcementRepository.deleteByUpdatedOnBefore(endTime);
    }

    public void createOrUpdateAnnouncement(AnnouncementDTO announcementDTO){
        AnnouncementEntity announcementEntity =
                AnnouncementMapper.INSTANCE.mapAnnouncementDtotoEntity(announcementDTO);

        if (announcementEntity.getCreatedOn() == null){
            announcementEntity.setCreatedOn(Instant.now());

        }
        announcementEntity.setUpdatedOn(Instant.now());


        announcementRepository.save(announcementEntity);

    }

    
}
