package presentation.service.forms;

import business.service.TService;
import presentation.controller.AppController;
import presentation.controller.Event;
import presentation.controller.LightContext;
import presentation.util.Util;
import presentation.util.ViewHelpers;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDropService extends JDialog{

	private JTextField idText;

	public FormDropService() {
		setTitle("Drop service");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel(){
		JPanel ret = ViewHelpers.createFieldPanel(1);

		//ID
		JLabel idLabel = new JLabel("ID");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		return  ret;
	}

	private JPanel buttonsPanel() {
		//Buttons
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		JButton drop = ViewHelpers.buttonsForms("DROP");

		drop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TService service = new TService();
				try {
					/*This is related to JPA*/
					service.setId(Util.parseNoNegativeInt(idText.getText()));
					dispose();
					AppController.getInstance().execute(new LightContext(Event.DROP_SERVICE, service));
				}catch (Exception ex){
					JOptionPane.showMessageDialog(getRootPane(), ex.getMessage(), "ERROR DROP SERVICE", JOptionPane.ERROR_MESSAGE);
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

		buttonsPanel.add(drop);
		buttonsPanel.add(cancel);

		return buttonsPanel;
	}

	public static void main(String[] args){
		FormDropService formDropService = new FormDropService();
		formDropService.setVisible(true);
	}
}
