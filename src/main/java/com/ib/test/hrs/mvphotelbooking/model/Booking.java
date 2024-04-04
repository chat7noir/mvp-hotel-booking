package com.ib.test.hrs.mvphotelbooking.model;

import java.time.Duration;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Booking entity class.
 * Describes a hotel booking by a customer.
 * 
 * @author Igal BITAN
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id", "customer", "hotel", "beginDate", "days", "details" }) // Arbitrary fields
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue
	@Column(name = "booking_id")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@ToString.Exclude
	@Column(name = "begin_date")
	private LocalDate beginDate;

	// @ToString.Exclude
	// @Column(name = "end_date")
	// private LocalDate endDate;
	// --> not a field!

	/** The number of days the customer books. */
	@Column
	private int days;

	/** The number of guests in the room. */
	@Column
	private int guests;

	/** The total price of the booking. */
	@Column
	private double price;

	@ToString.Exclude
	@Column
	private String details;

	public Booking(Customer customer, Hotel hotel, LocalDate beginDate, int days, int guests, double price, String details) {
		this.customer = customer;
		this.hotel = hotel;
		this.beginDate = beginDate;
		this.days = days;
		this.guests = guests;
		this.price = price;
		this.details = details;
	}

	/** @return the calculated end date (from begin date and booking days). */
	@JsonIgnore
	public LocalDate getEndDate() {
		return beginDate == null ? null : beginDate.plusDays(days);
	}

	/** Sets the booking days if begin and end dates are valid. */
	public void setEndDate(LocalDate endDate) {
		if (beginDate != null && endDate != null && !beginDate.isAfter(endDate)) {
			days = (int) Duration.between(endDate, beginDate).toDays();
		}
	}

}
