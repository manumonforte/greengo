package presentation.command.main_office;

import presentation.command.Command;
import presentation.controller.LightContext;

public class ReloadMainOffice implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
