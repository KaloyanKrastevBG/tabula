package bg.softuni.tabula.announcement.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AnnouncementMapper {

    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    AnnouncementEntity mapAnnouncementDtotoEntity(AnnouncementDTO dto);

    AnnouncementDTO mapAnnouncementEntotytoDto(AnnouncementEntity entity);

}
