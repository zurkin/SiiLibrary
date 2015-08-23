package pl.sii.library.domain.model;

import java.util.Date;

import pl.sii.library.domain.persistence.Rent;
import pl.sii.library.domain.persistence.RentStatus;
import pl.sii.library.util.DateUtil;

public class RentBO extends BaseBusinessObject<Rent>{

	public RentBO(Rent entity) {
		super(entity, Rent.class);
	}

	public void rent(int rentDuration) {
		Date startRentDate = new Date();
		this.entity.setStartDate(startRentDate);
		Date endRentDate = DateUtil.addDays(startRentDate, rentDuration);
		this.entity.setEndDate(endRentDate);
		this.entity.setStatus(RentStatus.RENTED);
	}

	public void release() {
		this.entity.setStatus(RentStatus.RELEASED);
	}
	
/*	public RentDTO prepareView() {
		RentDTO rentDTO = new RentDTO();
		rentDTO.setId(getEntity().getId());
		rentDTO.setCustomer(getEntity().getCustomer());
		return rentDTO;
	}*/
	
}
