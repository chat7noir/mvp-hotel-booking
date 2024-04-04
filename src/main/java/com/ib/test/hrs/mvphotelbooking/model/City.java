package com.ib.test.hrs.mvphotelbooking.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The City entity class.
 * Describes a *destination* city.
 * 
 * @author Igal BITAN
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "city")
public class City {

	@Id
	@GeneratedValue
	@Column(name = "city_id")
	private long id;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String state;

	@JsonBackReference
	@ToString.Exclude
	@OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private List<Hotel> hotels;

	public City(long id, String postalCode, String name, String state) {
		super();
		this.id = id;
		this.postalCode = postalCode;
		this.name = name;
		this.state = state;
	}

}
