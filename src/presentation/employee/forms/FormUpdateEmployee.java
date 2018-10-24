package presentation.employee.forms;

import presentation.util.Util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormUpdateEmployee extends JDialog {

	/*Attributes*/
	/*JTextField fields: */
	private JTextField idText;
	private JTextField idCardNumberText;
	private JTextField idMainOfficeText;
	private JTextField workedHoursText;
	private JTextField appotionmentText;
	private JTextField salaryText;

	/*JComboBox fields*/
	private JComboBox activeComboBox;
	private JComboBox typeComboBox;

	public FormUpdateEmployee(){
		setTitle("Modificar empleado");
		setResizable(false);
		Util.addEscapeListener(this);
		initGUI();
	}

	private void initGUI(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		mainPanel.add(fieldsPanel());
		mainPanel.add(buttonsPanel());

		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private JPanel fieldsPanel(){
		JPanel ret = new JPanel(new GridLayout(8, 2, 0, 7));
		Border border = ret.getBorder();
		Border margin = new EmptyBorder(10, 10, 10, 10);
		ret.setBorder(new CompoundBorder(border, margin));

		//ID
		JLabel idLabel = new JLabel("Id");
		ret.add(idLabel);

		idText = new JTextField(10);
		ret.add(idText);

		//Id card
		JLabel idCardLabel = new JLabel("DNI");
		ret.add(idCardLabel);

		idCardNumberText = new JTextField(10);
		ret.add(idCardNumberText);

		//Salary
		JLabel salaryLabel = new JLabel("Salary");
		ret.add(salaryLabel);

		salaryText = new JTextField(10);
		ret.add(salaryText);

		//Id Main Office
		JLabel idMainOfficeLabel = new JLabel("Id sede");
		ret.add(idMainOfficeLabel);

		idMainOfficeText = new JTextField(10);
		ret.add(idMainOfficeText);

		//Active
		JLabel activeLabel = new JLabel("Active");
		ret.add(activeLabel);

		selectActive();
		ret.add(activeComboBox);

		//Type
		JLabel typeLabel = new JLabel("Type");
		ret.add(typeLabel);

		selectType();

		ret.add(typeComboBox);

		//Worked hours
		JLabel workedHours = new JLabel("Worked hours");
		ret.add(workedHours);

		workedHoursText = new JTextField(10);
		ret.add(workedHoursText);

		//Apportionment
		JLabel apportionmentLabel = new JLabel("Apportionment");
		ret.add(apportionmentLabel);

		appotionmentText = new JTextField(10);
		appotionmentText.setEnabled(false);
		ret.add(appotionmentText);


		return ret;
	}



	private JPanel buttonsPanel() {
		JPanel ret = new JPanel(new FlowLayout());


		JButton update = new JButton("CREAR");
		update.setForeground(Color.white);
		update.setBackground(new Color(119,171,89));

		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					if (typeComboBox.getSelectedItem().equals("Temporary")) {
						/*This is related to JPA*/

						//Invoke the controller and execute "Set salary" operation.

					}
					else{
						/*This is related to JPA*/

						//Invoke the controller and execute "Set salary" operation.
					}
					dispose();
					//Invoke the controller and execute "Update Employee" operation.

				} catch(Exception e){
					JOptionPane.showMessageDialog(getRootPane(), e.getMessage(),
							"ERROR ALTA EMPLEADO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JButton cancel = new JButton("CANCELAR");
		cancel.setForeground(Color.white);
		cancel.setBackground(new Color(119,171,89));

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				dispose();
			}
		});

		ret.add(update);
		ret.add(cancel);

		return ret;
	}

	private void selectActive() {
		activeComboBox = new JComboBox();
		activeComboBox.addItem("True");
		activeComboBox.addItem("False");
	}

	private void selectType(){
		typeComboBox = new JComboBox();
		typeComboBox.addItem("Temporary");
		typeComboBox.addItem("Permanent");

		typeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if(typeComboBox.getSelectedItem().equals("Temporary")) {
					appotionmentText.setEnabled(false);
					workedHoursText.setEnabled(true);
				}
				else{
					workedHoursText.setEnabled(false);
					appotionmentText.setEnabled(true);
				}
			}
		});
	}

	public static void main(String[] Args){
		FormUpdateEmployee f = new FormUpdateEmployee();
		f.setVisible(true);
	}




}
