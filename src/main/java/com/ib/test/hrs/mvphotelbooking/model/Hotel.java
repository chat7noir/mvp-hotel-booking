package com.ib.test.hrs.mvphotelbooking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Hotel entity class.
 * 
 * @author Igal BITAN
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id", "name", "city" })
@Entity
@Table(name = "hotel")
public class Hotel {

	@Id
	@GeneratedValue
	@Column(name = "hotel_id")
	private long id;

	@Column(nullable = false)
	private String name;

	@Column
	private String description;

	@Column(nullable = false)
	private String address;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;

	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Booking> bookings;

	public Hotel(long id, String name, String description, String address, City city) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.city = city;
	}

}
