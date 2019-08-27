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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

  @Autowired
  private TourService tourService;

  @Autowired
  private CountryService countryService;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/search_tours")
  public String searchTours(@Valid @ModelAttribute("tourDto") TourDto tourDto,
                            BindingResult result, ModelMap model, Locale locale,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {

    int currentPage = page.orElse(1);
    int pageSize = size.orElse(10);

    TourSpecification tourSpecification = new TourSpecification(tourDto);

    Page<Tour> tourPage = tourService.findPaginated(PageRequest.of(currentPage - 1, pageSize), tourSpecification);

    System.out.println("tour page - " + tourPage);

    model.addAttribute("tourPage", tourPage);
    int totalPages = tourPage.getTotalPages();
    if (totalPages > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
              .boxed()
              .collect(Collectors.toList());
      int lastPage = pageNumbers.size()-1;
      model.addAttribute("lastPage", lastPage);

      System.out.println("currentPage - " + currentPage);
      if (pageNumbers.size() > 5) {
        System.out.println("1------------- pageNumber.size()"+ pageNumbers);
        int temp = 0;
        if ((currentPage + 4) % 5 == 0) {
          System.out.println("2-------------- curentPage - " +currentPage);
          temp = currentPage-1;
          pageNumbers = pageNumbers.subList(currentPage - 1, currentPage + 4);
        } else {
          System.out.println("2-------- temp - "+temp);
          pageNumbers = pageNumbers.subList(temp, temp+5);
        }

        System.out.println("pageNumbers after subslit - " + pageNumbers);
      }

      System.out.println("pageNumbers - " + pageNumbers);
      model.addAttribute("pageNumbers", pageNumbers);
    }


    if (tourDto.getMaxDate() != null && tourDto.getMinDate() != null && tourDto.getMinDate().isAfter(tourDto.getMaxDate())) {
      String dateErrorMsg = messageSource.getMessage("msg.wrongDate", new Object[]{}, locale);
      result.rejectValue("minDate", "error.tourDto", dateErrorMsg);
    }

    model.addAttribute("tours", tourService.findAllByCriteria(tourSpecification));
    model.addAttribute("countries", countryService.findAll());
//
//    UriBuilder uriBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
//    uriBuilder.replaceQueryParam("1", "2");

    return "home";
  }

}
