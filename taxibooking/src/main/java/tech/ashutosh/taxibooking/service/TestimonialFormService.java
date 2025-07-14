package tech.ashutosh.taxibooking.service;

import tech.ashutosh.taxibooking.model.TestimonialForm;

import java.util.List;

public interface TestimonialFormService {

    public TestimonialForm saveTestimonialFormService(TestimonialForm testimonialForm);

    public void deleteTestimonial(int id);

    List<TestimonialForm> showAllTestimonial();
}
