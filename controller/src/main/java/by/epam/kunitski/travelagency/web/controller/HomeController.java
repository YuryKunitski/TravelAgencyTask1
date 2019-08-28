package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.daoDto.TourDto;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.specification.impl.TourSpecification;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

  private static final int MULTIPLE_OF_FIVE = 5;
  private static final int UP_TO_MULTIPLE_OF_FIVE = 4;
  private static final int PAGE_SIZE = 10;

    @Autowired
    private TourService tourService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/search_tours")
    public String searchTours(@Valid @ModelAttribute("tourDto") TourDto tourDto,
                              BindingResult result, ModelMap model, Locale locale,
                              @RequestParam("page") Optional<Integer> page) {

        int currentPage = page.orElse(1);
        TourSpecification tourSpecification = new TourSpecification(tourDto);
        Page<Tour> tourPage = tourService.findPaginated(PageRequest.of(currentPage - 1, PAGE_SIZE), tourSpecification);

        model.addAttribute("tourPage", tourPage);
        int totalPages = tourPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("lastPage", totalPages);

            if (totalPages > MULTIPLE_OF_FIVE) {
                if (currentPage != totalPages && (currentPage + UP_TO_MULTIPLE_OF_FIVE) % MULTIPLE_OF_FIVE == 0) {
                    pageNumbers = pageNumbers.subList(currentPage - 1, currentPage + UP_TO_MULTIPLE_OF_FIVE);
                } else {
                    int temp = 0;
                    if (totalPages - currentPage >= UP_TO_MULTIPLE_OF_FIVE) {
                        temp = (currentPage % MULTIPLE_OF_FIVE != 0) ? currentPage + 1 - (currentPage % MULTIPLE_OF_FIVE) : currentPage - UP_TO_MULTIPLE_OF_FIVE;
                        pageNumbers = pageNumbers.subList(temp - 1, temp + UP_TO_MULTIPLE_OF_FIVE);
                    } else {
                        temp = currentPage + (totalPages - currentPage);
                        pageNumbers = pageNumbers.subList(temp - MULTIPLE_OF_FIVE, temp);
                    }
                }
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        if (tourDto.getMaxDate() != null && tourDto.getMinDate() != null && tourDto.getMinDate().isAfter(tourDto.getMaxDate())) {
            String dateErrorMsg = messageSource.getMessage("msg.wrongDate", new Object[]{}, locale);
            result.rejectValue("minDate", "error.tourDto", dateErrorMsg);
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
        model.addAttribute("countries", countryService.findAll());

        return "home";
    }

}
