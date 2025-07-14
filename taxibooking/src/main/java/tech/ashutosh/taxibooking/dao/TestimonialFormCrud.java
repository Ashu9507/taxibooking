package tech.ashutosh.taxibooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tech.ashutosh.taxibooking.model.TestimonialForm;

import java.util.List;

@Repository
public interface TestimonialFormCrud extends JpaRepository<TestimonialForm, Integer > {
    @Override
    public <S extends TestimonialForm> S save(S entity);

    @Transactional (readOnly = true)
    @Query( value = "Select * from testimonial_form Order by id Desc", nativeQuery = true)
    public List<TestimonialForm> showTestimonials();

    @Override
    public void deleteById(Integer integer);
}
