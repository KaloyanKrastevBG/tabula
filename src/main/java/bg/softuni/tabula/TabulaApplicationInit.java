package bg.softuni.tabula;

import bg.softuni.tabula.announcement.model.AnnouncementEntity;
import bg.softuni.tabula.announcement.repository.AnnouncementRepository;
import bg.softuni.tabula.users.model.RoleEntity;
import bg.softuni.tabula.users.model.UserEntity;
import bg.softuni.tabula.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Component
public class TabulaApplicationInit implements CommandLineRunner {

    private final AnnouncementRepository announcementRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if(announcementRepository.count() == 0){
            AnnouncementEntity announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle("Hello for the first time!");
            announcementEntity.setDescription("I am vefy glad to meet you and have a nice day!");
            announcementEntity.setCreatedOn(Instant.now());
            announcementEntity.setUpdatedOn(Instant.now());
            announcementRepository.save(announcementEntity);

        }

        if (userRepository.count() == 0 ) {
            // admin
            UserEntity admin = new UserEntity();
            admin.setEmail("koko@koko.bg");
            admin.setPasswordHash(passwordEncoder.encode("koko"));

            RoleEntity adminRole = new RoleEntity();
            adminRole.setRole("ROLE_ADMIN");

            RoleEntity userRole = new RoleEntity();
            userRole.setRole("ROLE_USER");

            admin.setRoles(List.of(adminRole, userRole));

            userRepository.save(admin);

            // user

            UserEntity user = new UserEntity();
            user.setEmail("user@user.bg");
            user.setPasswordHash(passwordEncoder.encode("user"));


            RoleEntity userUserRole = new RoleEntity();
            userUserRole.setRole("ROLE_USER");

            user.setRoles(List.of(userUserRole));

            userRepository.save(user);

        }

    }
}
