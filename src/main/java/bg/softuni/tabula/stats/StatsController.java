package bg.softuni.tabula.stats;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

//  @PreAuthorize("hasRole('ADMIN')") @PreAuthorize("hasRole('ADMIN')") - fine tuning better on method
    @GetMapping("/stats")
    public String stats(Model model){

        model.addAttribute("requestCount", statsService.getRequestCount());
        model.addAttribute("startedOn", statsService.getStartedOn());


        return "stats/stats";
    }
}
