package pl.sii.library.domain.model;

import pl.sii.library.domain.persistence.Rent;
import pl.sii.library.domain.persistence.RentStatus;

public class RentBO extends BaseBusinessObject<Rent>{

	public RentBO(Rent entity) {
		super(entity, Rent.class);
	}

	public void rent() {
		this.entity.setStatus(RentStatus.RENTED);
	}

	public void release() {
		this.entity.setStatus(RentStatus.RELEASED);
	}
	
}
