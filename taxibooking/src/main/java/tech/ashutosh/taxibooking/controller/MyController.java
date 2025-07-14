package tech.ashutosh.taxibooking.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.ashutosh.taxibooking.model.*;
import tech.ashutosh.taxibooking.service.BookingFormService;
import tech.ashutosh.taxibooking.service.ContactFormService;
import tech.ashutosh.taxibooking.service.ServiceFormService;
import tech.ashutosh.taxibooking.service.TestimonialFormService;


import java.util.List;

@Controller
public class MyController {

    private ContactFormService contactFormService;
    private BookingFormService bookingFormService;
    private ServiceFormService serviceFormService;
    private TestimonialFormService testimonialFormService;

    @Autowired
    public void setBookingFormServiceImpl(BookingFormService bookingFormService) {
        this.bookingFormService = bookingFormService;
    }

    @Autowired
    public void setContactFormServiceImpl(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @Autowired
    public void setServiceFormServiceImpl(ServiceFormService serviceFormService) {
        this.serviceFormService = serviceFormService;
    }

    @Autowired
    public void setTestimonialFormServiceImpl(TestimonialFormService testimonialFormService) {
        this.testimonialFormService = testimonialFormService;
    }

    @GetMapping(path={"/", "home", "welcome", "index"})
    public String welcomeView(HttpServletRequest req, Model m) {

        String requestURL=req.getRequestURI();
        m.addAttribute("mycurrentpage", requestURL);
        m.addAttribute("bookingForm", new BookingForm());
        return "index";
    }

    @GetMapping("about")
    public String aboutView(HttpServletRequest req, Model m) {

        String requestURL=req.getRequestURI();
        m.addAttribute("mycurrentpage", requestURL);
        m.addAttribute("allTestimonials", testimonialFormService.showAllTestimonial());
        return "about";
}

    @GetMapping("cars")
    public String carsView(HttpServletRequest req, Model m) {

        String requestURL=req.getRequestURI();
        m.addAttribute("mycurrentpage", requestURL);
        return "cars";
    }

    @GetMapping("services")
    public String servicesView(HttpServletRequest req, Model m) {

        String requestURL=req.getRequestURI();
        m.addAttribute("mycurrentpage",requestURL);

        //Data collection
        List<ServiceForm> allServices = serviceFormService.findAllService();
        m.addAttribute("allservices", allServices);

        return "services";
    }

    @GetMapping("contacts")
    public String contactsView(HttpServletRequest req, Model m) {

        String requestURL=req.getRequestURI();
        m.addAttribute("mycurrentpage",requestURL);
        m.addAttribute("contactForm", new ContactForm());
        return "contacts";
    }

    @GetMapping("login")
    public String adminLoginView(HttpServletRequest request, Model model) {

        ServletContext servletContext=request.getServletContext();
        Object attribute = servletContext.getAttribute("logout");
        if(attribute instanceof Boolean) {

            model.addAttribute("logout", attribute);
            servletContext.removeAttribute("logout");
        }
        return "adminlogin";
    }

    @PostMapping("bookingform")
    public String bookingForm(@Valid @ModelAttribute BookingForm bookingform,
                              BindingResult bindingResult, Model m, RedirectAttributes redirectattribute) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("bindingResult", bindingResult);
            return "index";
        }
        else if(bookingform.getAdult()+bookingform.getChildren()>4) {
            m.addAttribute("message", "The total number of people should not be more than 4");
            return "index";
        }
        //Send to service layer
        BookingForm savebookingFormService= bookingFormService.saveBookingFormService(bookingform);
        if(savebookingFormService!=null) {
            redirectattribute.addFlashAttribute("message", "Booking has been done");
        } else {
            redirectattribute.addFlashAttribute("message", "Something went Wrong");
        }

        return "redirect:/index";
    }

    @PostMapping("contactform")
    public String contactform(@Valid @ModelAttribute ContactForm contactform,
                              BindingResult bindingResult, Model m, RedirectAttributes redirectattribute) {

        if (bindingResult.hasErrors()) {
            m.addAttribute("bindingResult", bindingResult);
            return "contacts";
        }

        ContactForm saveContactFormService = contactFormService.saveContactFormService(contactform);
        if (saveContactFormService != null) {
            redirectattribute.addFlashAttribute("message", "Message sent Successfully");
        } else {
            redirectattribute.addFlashAttribute("message", "Something went wrong");
        }
        return "redirect:/contacts";
    }


    @GetMapping("testimonial")
    public String aboutTestimonial(HttpServletRequest req, Model m) {

        String requestURL = req.getRequestURI();
        m.addAttribute("mycurrentpage", requestURL);
        m.addAttribute("testimonialForm", new TestimonialForm());
        return "testimonial";
    }

    @PostMapping("testimonialForm")
    public String testimonialform(@Valid @ModelAttribute TestimonialForm testimonialform,
                                  BindingResult bindingResult, Model m, RedirectAttributes redirectattribute) {
        if (bindingResult.hasErrors()) {
            m.addAttribute("bindingResult", bindingResult);
            return "testimonial";
        }

        TestimonialForm saveTestimonialFormService = testimonialFormService.saveTestimonialFormService(testimonialform);
        if(saveTestimonialFormService!=null) {
            redirectattribute.addFlashAttribute("message", "Feedback Submitted");
        }
        else {
            redirectattribute.addFlashAttribute("message", "Feedback not Submitted");
        }
        return "redirect:/testimonial";
    }

}
