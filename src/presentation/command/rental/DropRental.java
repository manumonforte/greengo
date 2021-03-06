package presentation.command.rental;

import business.ASException;
import business.IncorrectInputException;
import business.rental.TRental;
import business.rental.factory.ASRentalFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropRental implements Command {

	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Integer ret = ASRentalFactory.getInstance().generateASRental().drop(((TRental)in.getData()).getId());
		return new LightContext(Event.DROP_RENTAL, ret);
	}
}
