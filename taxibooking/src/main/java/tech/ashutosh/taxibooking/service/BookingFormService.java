package tech.ashutosh.taxibooking.service;

import tech.ashutosh.taxibooking.model.BookingForm;

import java.util.List;

public interface BookingFormService {

    public BookingForm saveBookingFormService(BookingForm bookingform);

    public List<BookingForm> readAllBookingService();

    public void deleteBookingService(int id);
}
