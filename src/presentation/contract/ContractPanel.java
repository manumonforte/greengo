package presentation.contract;

import presentation.PanelTabs;
import presentation.UI;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class ContractPanel extends JPanel {

	private final String[] columnId = { "Id Main Office","Id service", "service Level", "Active"};
	private TableModel model;

	public ContractPanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		add(new ContractToolbar(panelTabs), BorderLayout.NORTH);
		model = new ContractTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
