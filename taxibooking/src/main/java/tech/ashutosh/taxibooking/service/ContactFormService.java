package tech.ashutosh.taxibooking.service;

import tech.ashutosh.taxibooking.model.ContactForm;

import java.util.List;

public interface ContactFormService  {

    public ContactForm saveContactFormService(ContactForm contactForm);

    public List<ContactForm> readAllContactsService();

    public void deleteContactService(int id);

}
