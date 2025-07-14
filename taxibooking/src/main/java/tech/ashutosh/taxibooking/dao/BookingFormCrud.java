package tech.ashutosh.taxibooking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ashutosh.taxibooking.model.BookingForm;

import java.util.List;

@Repository
public interface BookingFormCrud extends JpaRepository<BookingForm, Integer> {
    @Override
    public <S extends BookingForm> S save(S entity);

    @Override
    public List<BookingForm> findAll();

    @Override
    public void deleteById(Integer id);
}
