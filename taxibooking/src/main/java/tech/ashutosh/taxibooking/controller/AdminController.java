package tech.ashutosh.taxibooking.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tech.ashutosh.taxibooking.model.ServiceForm;
import tech.ashutosh.taxibooking.service.*;

@Controller
@RequestMapping("admin")
public class AdminController {

    private ContactFormService contactFormService;
    private AdminChangeCredentials adminChangeCredentials;
    private BookingFormService bookingFormService;
    private ServiceFormService serviceFormService;
    private TestimonialFormService testimonialFormService;

    @Autowired
    public void setTestimonialFormService(TestimonialFormService testimonialFormService) {
        this.testimonialFormService = testimonialFormService;
    }

    @Autowired
    public void setServiceFormService(ServiceFormService serviceFormService) {
        this.serviceFormService = serviceFormService;
    }

    @Autowired
    public void setAdminChangeCredentials(AdminChangeCredentials adminChangeCredentials) {
        this.adminChangeCredentials = adminChangeCredentials;
    }


    @Autowired
    public void setContactFormService(ContactFormService contactFormService) {
        this.contactFormService = contactFormService;
    }

    @Autowired
    public void setBookingFormService(BookingFormService bookingFormService) {
        this.bookingFormService = bookingFormService;
    }

    @GetMapping("dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("readAllContacts")
    public String readAllContacts(Model model) {
        model.addAttribute("allcontacts", contactFormService.readAllContactsService());

        return "admin/readAllContacts";
    }

    @GetMapping("deleteContact/{id}")
    public String DeleteContact(@PathVariable int id, RedirectAttributes redirectAttributes) {

        contactFormService.deleteContactService(id);
        redirectAttributes.addFlashAttribute("message", "Contact Deleted successfully");
        return "redirect:/admin/readAllContacts";
    }

    @GetMapping("changeCredentials")
    public String changeCredentials() {

        return "admin/changeCredentials";
    }

    @PostMapping("changeCredentials")
    public String changeCredentials(
            @RequestParam("oldusername") String oldusername,
            @RequestParam("oldpassword") String oldpassword,
            @RequestParam("newusername") String newusername,
            @RequestParam("newpassword") String newpassword,
            RedirectAttributes redirectAttributes
    ) {
        String result = adminChangeCredentials.checkAdminCredentials(oldusername, oldpassword);
        System.out.println(result);

        if(result.equals("Success")) {
            //Password Update
            result = adminChangeCredentials.updateAdminCredentials(newusername, newpassword, oldusername);
            redirectAttributes.addFlashAttribute("message", result);
        }
        else {
            redirectAttributes.addFlashAttribute("message", result);
        }

        return "redirect:/admin/dashboard";
    }


    @GetMapping("readAllBookings")
    public String readAllBookings(Model model) {
        model.addAttribute("allbookings", bookingFormService.readAllBookingService());

        return "admin/readAllBookings";
    }

    @GetMapping("deleteBooking/{id}")
    public String DeleteBooking(@PathVariable int id, RedirectAttributes redirectAttributes) {

        bookingFormService.deleteBookingService(id);
        redirectAttributes.addFlashAttribute("message", "Booking Deleted successfully");
        return "redirect:/admin/readAllBookings";
    }

    @GetMapping("addService")
    public String addServiceView() {

        return "admin/addService";
    }

    @InitBinder
    public void stopBinding(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("image");
    }

    @PostMapping("addService")
    public String addService(@Valid @ModelAttribute ServiceForm serviceForm,
                                 @RequestParam("image") MultipartFile multipartFile,
                                 RedirectAttributes redirectAttributes) {

        String originalFile=multipartFile.getOriginalFilename();
        serviceForm.setImage(originalFile);
        System.out.println(serviceForm);
        try {

           ServiceForm service = serviceFormService.addService(serviceForm, multipartFile);
            if(service!=null) {
                redirectAttributes.addFlashAttribute("message", "Service added successfully");
            }
            else {
                redirectAttributes.addFlashAttribute("message", "Something went Wrong");
            }
        }
        catch (Exception e) {

            redirectAttributes.addFlashAttribute("message", "Something went Wrong");

        }

        return "redirect:/admin/addService";
    }

    @GetMapping("TestimonialFormAdmin")
    public String readtestimonialform(Model m) {
        m.addAttribute("readtestimonialform", testimonialFormService.showAllTestimonial());

        return "admin/TestimonialFormAdmin";
    }


    @GetMapping("deleteTestimonial/{id}")
    public String DeleteTestimonial(@PathVariable int id, RedirectAttributes redirectAttributes) {

        testimonialFormService.deleteTestimonial(id);
        //redirectAttributes.addFlashAttribute("message", "Testimonial Deleted successfully");
        return "redirect:/admin/TestimonialFormAdmin";
    }
}
