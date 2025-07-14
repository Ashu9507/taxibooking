package tech.ashutosh.taxibooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ashutosh.taxibooking.dao.ContactFormCrud;
import tech.ashutosh.taxibooking.model.ContactForm;

import java.util.List;

@Service
public class ContactFormServiceImpl implements ContactFormService {

    private ContactFormCrud contactformcrud;

    @Autowired
    public void setContactformcrud(ContactFormCrud contactformcrud) {
        this.contactformcrud = contactformcrud;
    }

    @Override
    public ContactForm saveContactFormService(ContactForm contactForm) {
      return  contactformcrud.save(contactForm);
    }

    @Override
    public List<ContactForm> readAllContactsService() {
        return contactformcrud.findAll();
    }

    @Override
    public void deleteContactService(int id) {
        contactformcrud.deleteById(id);
    }
}
