package presentation.command.service;

import presentation.command.Command;
import presentation.controller.LightContext;

public class ReloadService implements Command {
	@Override
	public LightContext execute(LightContext in) {
		return in;
	}
}
