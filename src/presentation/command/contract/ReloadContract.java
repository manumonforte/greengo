package presentation.command.contract;

import presentation.command.Command;
import presentation.controller.LightContext;

public class ReloadContract implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
