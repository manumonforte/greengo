package presentation.main_office;

import presentation.PanelTabs;
import presentation.UI;
import presentation.client.ClientTableModel;
import presentation.util.TableModel;
import presentation.util.TablePanel;

import javax.swing.*;
import java.awt.*;

public class MainOfficePanel extends JPanel {

	private final String[] columnId = {"Id", "City", "Address", "Active"};
	private TableModel model;

	public MainOfficePanel(PanelTabs panelTabs) {
		setLayout(new BorderLayout());
		//add(new ToolBarCerveza(panelTabs), BorderLayout.NORTH);
		model = new ClientTableModel(columnId);
		add(new TablePanel<>(model), BorderLayout.CENTER);
	}

	public TableModel getModel() {
		return model;
	}
}
