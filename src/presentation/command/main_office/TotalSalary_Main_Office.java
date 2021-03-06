package presentation.command.main_office;

import business.ASException;
import business.IncorrectInputException;
import business.mainoffice.TMainOffice;
import business.mainoffice.factory.ASMainOfficeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class TotalSalary_Main_Office implements Command {
	@Override

	public LightContext execute(LightContext in) throws ASException, IncorrectInputException {
		Float ret = ASMainOfficeFactory.getInstance().
                generateASMainOffice().
                showSalary(((TMainOffice)in.getData()).getId());
		return new LightContext(Event.TOTAL_SALARY_MAIN_OFFICE, ret);
	}
}
