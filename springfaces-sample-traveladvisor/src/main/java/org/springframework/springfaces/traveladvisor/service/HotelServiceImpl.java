package org.springframework.springfaces.traveladvisor.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.springfaces.traveladvisor.domain.City;
import org.springframework.springfaces.traveladvisor.domain.Hotel;
import org.springframework.springfaces.traveladvisor.domain.HotelRepository;
import org.springframework.springfaces.traveladvisor.domain.Rating;
import org.springframework.springfaces.traveladvisor.domain.Review;
import org.springframework.springfaces.traveladvisor.domain.ReviewDetails;
import org.springframework.springfaces.traveladvisor.domain.ReviewRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Component("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

	// FIXME deal with null repository return values

	private HotelRepository hotelRepository;

	private ReviewRepository reviewRepository;

	public Hotel getHotel(City city, String name) {
		Assert.notNull(city, "City must not be null");
		Assert.hasLength(name, "Name must not be empty");
		return hotelRepository.findByCityAndName(city, name);
	}

	public Page<Review> getReviews(Hotel hotel, Pageable pageable) {
		Assert.notNull(hotel, "Hotel must not be null");
		return reviewRepository.findByHotel(hotel, pageable);
	}

	// FIXME here down

	public Review getReview(Hotel hotel, int reviewNumber) {
		return new Review(hotel, 1, new ReviewDetails("Top hotel", Rating.EXCELLENT));
	}

	public Review addReview(Hotel hotel, ReviewDetails details) {
		System.out.println(details.getTitle());
		return new Review(hotel, 1, details);
	}

	public void setHotelRepository(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
}