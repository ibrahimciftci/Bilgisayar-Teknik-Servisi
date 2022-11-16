package com.bilgeadam.BilgisayarTeknikServisi.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bilgeadam.BilgisayarTeknikServisi.model.Booking;
import com.bilgeadam.BilgisayarTeknikServisi.repository.BookingRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookingService {

	private BookingRepository bookingRepository;

	public void save(Booking booking) {
		bookingRepository.save(booking);
	}

	public Date getDate() {
		return bookingRepository.getDate();
	}

	public void updateDate(Date zaman) {
		bookingRepository.updateDate(zaman);
	}

	public Date getNextDate(Booking booking, int hour) {
		int toplamSaat = 0;
		int serviceTime = booking.getService().getIslemSuresi();
		Date tomorrow = new Date();
		tomorrow.setDate(tomorrow.getDate() - 1);
		do {
			toplamSaat = 0;
			tomorrow = new Date(tomorrow.getTime() + 1000L * 60 * 60 * 24);
			List<Booking> bookings = bookingRepository.findByZaman(tomorrow);
			for (Booking booking2 : bookings) {
				toplamSaat += booking2.getService().getIslemSuresi();
			}

		} while ((hour - toplamSaat) - serviceTime < 0);

		return tomorrow;

	}

	public List<Booking> findByZaman(Date date) {
		return bookingRepository.findByZaman(date);
	}

	public List<Booking> findByUserId(Long id) {
		int k[] = { 2, 3 };
		System.err.println(k[3]);
		return bookingRepository.findByUserId(id);
	}

	public void deleteById(Long id) {
		bookingRepository.deleteById(id);

	}

	public Booking findById(Long id) {
		return bookingRepository.findById(id).get();
	}

	public List<Booking> getAll() {
		Date date = new Date();
		date.setDate(date.getDate() - 1);

		return bookingRepository.findAllByOrderByZamanAsc().stream().filter(b -> b.getZaman().after(date))
				.collect(Collectors.toList());

	}
}
