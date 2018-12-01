package presentation.command.main_office;

import business.ASException;
import business.IncorrectInputException;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMain_OfficeFactory;
import integration.DAOException;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class UpdateMain_Office implements Command {
	@Override
	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Integer ret = ASMain_OfficeFactory.getInstance().generateASMain_Office().update((TMainOffice) in.getData());
		return new LightContext(Event.UPDATE_MAIN_OFFICE, ret);
	}
}
