package presentation.command.client;

import business.client.TClient;
import business.client.factory.ASClientFactory;
import presentation.command.Command;
import presentation.controller.Event;
import presentation.controller.LightContext;

public class CreateClient implements Command {
	@Override
	public LightContext execute(LightContext in) {
		Integer ret = ASClientFactory.getInstance().generateASClient().create((TClient)in.getData());
		return new LightContext(Event.CREATE_CLIENT, ret);
	}
}
