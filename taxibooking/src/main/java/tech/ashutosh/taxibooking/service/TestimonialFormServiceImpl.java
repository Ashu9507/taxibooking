package tech.ashutosh.taxibooking.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ashutosh.taxibooking.dao.TestimonialFormCrud;
import tech.ashutosh.taxibooking.model.TestimonialForm;

import java.util.List;
import java.util.Optional;

@Service
public class TestimonialFormServiceImpl implements TestimonialFormService{

    private TestimonialFormCrud testimonialFormCrud;

    @Autowired
    public void setTestimonialFormCrud(TestimonialFormCrud testimonialFormCrud) {
        this.testimonialFormCrud = testimonialFormCrud;
    }

    public TestimonialForm saveTestimonialFormService(TestimonialForm testimonialForm) {
        return testimonialFormCrud.save(testimonialForm);
    }

    @Override
    public void deleteTestimonial(int id) {
        testimonialFormCrud.deleteById(id);
    }

    @Override
    public List<TestimonialForm> showAllTestimonial() {
        return testimonialFormCrud.showTestimonials();
    }

}
