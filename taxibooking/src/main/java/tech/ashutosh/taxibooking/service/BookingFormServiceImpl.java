package tech.ashutosh.taxibooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ashutosh.taxibooking.dao.BookingFormCrud;
import tech.ashutosh.taxibooking.model.BookingForm;

import java.util.List;

@Service
public class BookingFormServiceImpl implements BookingFormService {

    private BookingFormCrud bookingFormCrud;

    @Autowired
    public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
        this.bookingFormCrud = bookingFormCrud;
    }

    @Override
    public BookingForm saveBookingFormService(BookingForm bookingform) {
        return bookingFormCrud.save(bookingform);
    }

    @Override
    public List<BookingForm> readAllBookingService() {
        return bookingFormCrud.findAll();
    }

    @Override
    public void deleteBookingService(int id) {
        bookingFormCrud.deleteById(id);
    }
}
