package presentation.command.employee;

import business.employee.factory.ASEmployeeFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class DropEmployee implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASEmployeeFactory.getInstance().generateASEmployee().drop((Integer)in.getData());
		return new LightContext(Event.DROP_EMPLOYEE, ret);
	}
}
