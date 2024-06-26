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
 * The Customer entity class.
 * Describes a hotel user.
 * 
 * @author Igal BITAN
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id", "firstname", "lastname", "phone" })
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name = "customer_id")
	private long id;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = true)
	private String address;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id", nullable = true)
	private City city;

	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Booking> bookings;

	public Customer(long id, String firstname, String lastname, String phone, String address, City city) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.address = address;
		this.city = city;
	}

}
