/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.sii.library.domain.persistence;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import pl.sii.library.util.DateUtil;
import static pl.sii.library.domain.persistence.RentStatus.*;

/*The Model uses JPA Entity as well as Hibernate Validators
 * 
 */

@Entity
@XmlRootElement
@Table(name = "Book", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@NamedQueries({
		@NamedQuery(name=Book.FIND_ALL, query="select b from Book b order by b.title"),
		@NamedQuery(name=Book.FIND_RESERVED_BY_STATUS, query="select b from Book b where b.rent is not null and b.rent.status = :status order by b.rent.customer.nick asc"),
		@NamedQuery(name=Book.FIND_EXPIRED, query="select b from Book b where b.rent is not null and b.rent.status = pl.sii.library.domain.persistence.RentStatus.RENTED and b.rent.endDate < current_date() order by b.rent.customer.nick asc")})
public class Book implements Serializable {
	/**
	 * Default value included to remove warning. Remove or modify at will. *
	 */
	private static final long serialVersionUID = 1L;
	private static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String FIND_ALL = "Book.findAll";
	public static final String FIND_RESERVED_BY_STATUS = "Book.findReserved";
	public static final String FIND_EXPIRED = "Book.findExpired";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String title;

	/**
	 * using hibernate4 validators *
	 */
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String author;

	private String description;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "RENT_ID")
	private Rent rent;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "BOOK_ID")
	private List<RentQue> rentQue;
	
	@Transient
	private boolean available;
	
	@Transient
	private String availabilityDate;
	
	@PostLoad
	@PostPersist
	@PostUpdate
	private void checkAvailability() {
		this.available = this.rent == null || RELEASED == this.rent.getStatus();
		if (this.rent != null && RENTED == this.rent.getStatus()) {
			Date endDate = this.rent.getEndDate();
			Date availableDate = DateUtil.addDays(endDate, 1);
			this.availabilityDate = SDF.format(availableDate);
		}
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

	public List<RentQue> getRentQue() {
		return rentQue;
	}

	public void setRentQue(List<RentQue> rentQue) {
		this.rentQue = rentQue;
	}

	public boolean isAvailable() {
		return available;
	}

	public String getAvailabilityDate() {
		return availabilityDate;
	}
}
