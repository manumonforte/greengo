package presentation.contract.forms;

import business.contract.TContract;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUpdateContract extends JDialog {

	private JTextField idText;
	private JTextField serviceLevelText;
	private JTextField idMainOfficeText;
	private JTextField idServiceText;
	private JComboBox activeComboBox;

	public FormUpdateContract() {
		setTitle("Update contract");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.white);
		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel() {
		JPanel ret = ViewHelpers.createFieldPanel(5);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Service Level
		JLabel serviceLevelLabel = new JLabel("Service Level");
		ret.add(serviceLevelLabel);

		serviceLevelText = new JTextField(10);
		ret.add(serviceLevelText);

		//ID Main Office
		JLabel idMainOfficeLabel = new JLabel("ID Main Office");
		ret.add(idMainOfficeLabel);

		idMainOfficeText = new JTextField(10);
		ret.add(idMainOfficeText);

		//ID Service
		JLabel idServiceLabel = new JLabel("ID Service");
		ret.add(idServiceLabel);

		idServiceText = new JTextField(10);
		ret.add(idServiceText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		activeComboBox = ViewHelpers.selectActive();
		ret.add(activeComboBox);

		return ret;
	}

	private JPanel buttonsPanel(){

		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton update = ViewHelpers.buttonsForms("UPDATE");

		update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TContract contract = new TContract();
				try {
					/*This is related to JPA*/
					contract.setId(Util.parseNoNegativeInt(idText.getText()));
					contract.setServiceLevel(Util.parseNoNegativeInt(serviceLevelText.getText()));
					contract.setIdMainOffice(Util.parseNoNegativeInt(idMainOfficeText.getText()));
					contract.setIdService(Util.parseNoNegativeInt(idMainOfficeText.getText()));
					contract.setActive(Util.parseActive(activeComboBox.getSelectedItem().toString()));
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR UPDATE CONTRACT", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = ViewHelpers.buttonsForms("CANCEL");

		cancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		buttonsPanel.add(update);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args) {
		FormUpdateContract formUpdateContract = new FormUpdateContract();
		formUpdateContract.setVisible(true);
	}
}