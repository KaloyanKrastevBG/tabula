package bg.softuni.tabula.announcement.web;

import bg.softuni.tabula.announcement.model.AnnouncementDTO;
import bg.softuni.tabula.announcement.service.AnnouncementService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String announcement(Model model){
        model.addAttribute("announcements", announcementService.findAll());

        return "announcement/announcements";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String newAnnouncement(Model model){
        if (!model.containsAttribute("formData")){
            model.addAttribute("formData", new AnnouncementDTO());

        }

        return "announcement/new";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("formData") AnnouncementDTO announcementDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("formData", announcementDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.formData",
                    bindingResult);
            return "redirect:/announcements/new";

        }

        announcementService.createOrUpdateAnnouncement(announcementDTO);

        return "redirect:/announcements";


    }


}
