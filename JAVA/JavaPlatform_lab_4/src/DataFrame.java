import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DataFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	private AutoTableModel _dataTable;	
	
	final static int width = 700;
	final static int height = 150;
	final static int layoutRowCount = 2;
	final static int layoutColCount = 4;
	
	JLabel labelForMarkField = new JLabel("Mark:");
	JLabel labelForYearField = new JLabel("Produce year:");
	JLabel labelForEnfineVolField = new JLabel("Engine volume");
	JLabel labelForMaxSpeedField = new JLabel("Max speed");
	JTextField markField = new JTextField();
	JTextField yearField = new JTextField();
	JTextField engineVolField = new JTextField();
	JTextField maxSpeedField = new JTextField();
		
	JButton btnOk = new JButton("Add");
	JButton btnCancel = new JButton("Reset");
	
	public DataFrame(AutoTableModel dataTable) {
		try {
			_dataTable = dataTable;
			InitFrame();			

			markField = new JTextField();
			yearField = new JTextField();
			engineVolField = new JTextField();
			maxSpeedField = new JTextField();
			
			BuildFields();
			BuildButtons(-1);
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Inner exception" + ex.getMessage());
		}
	}
	
	public DataFrame(AutoTableModel dataTable, int index) {
		try {
			_dataTable = dataTable;
			InitFrame();			
			
			Auto entity = _dataTable.getRowEntity(index);
			
			markField = new JTextField(entity.getMark());
			yearField = new JTextField(String.valueOf(entity.getProductionYear()));
			engineVolField = new JTextField(String.valueOf(entity.getEngineVolume()));
			maxSpeedField = new JTextField(String.valueOf(entity.getMaxSpeed()));
			
			BuildFields();
			BuildButtons(index);			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Inner exception" + ex.getMessage());
		}
	}
	
	private void InitFrame() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		this.setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(15,15,15,15));
		panel.setLayout(new BorderLayout());
		
		this.setContentPane(panel);		
	}
	
	private void BuildFields() {
		try {				
			JPanel fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(layoutRowCount, layoutColCount));
//			fieldPanel.setLayout(new BorderLayout(layoutRowCount, layoutColCount));
			
			fieldPanel.add(labelForMarkField);
			fieldPanel.add(labelForYearField);
			fieldPanel.add(labelForEnfineVolField);
			fieldPanel.add(labelForMaxSpeedField);
			fieldPanel.add(markField);		
			fieldPanel.add(yearField);		
			fieldPanel.add(engineVolField);
			fieldPanel.add(maxSpeedField);	
			
			this.add(fieldPanel, BorderLayout.CENTER);	
		} catch (Exception ex) {
			throw new ExceptionInInitializerError();
		}
	}
	

	private void BuildButtons(int index) {
		try {
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(2, 1));
			
			btnOk.setEnabled(true);			
			btnCancel.setEnabled(true);
			buttonPanel.add(btnOk);
			buttonPanel.add(btnCancel);			
			btnOk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						boolean result = false;
						if (index == -1) {
							result = _dataTable.addRow(
									new Auto(markField.getText(),
											yearField.getText(),
											engineVolField.getText(),
											maxSpeedField.getText()
											)
									);
						} else {
							result = _dataTable.updateRow(index, new Auto(markField.getText(),
									yearField.getText(),
									engineVolField.getText(),
									maxSpeedField.getText()
									));
							DataFrame.this.dispose();
						}
						
						if (!result) {
							return;
						}
						cleanAllFields();
					} catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(DataFrame.this, "Data not added, Fill all field or enter correctly format");
					}
					finally {
						_dataTable.fireTableDataChanged();
					}
								
				}
			});
			this.add(buttonPanel,BorderLayout.EAST);
			btnCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cleanAllFields();
				}
			});
			
		} catch (Exception ex) {
			throw new ExceptionInInitializerError();
		}
	}
	
	private void cleanAllFields() {
		markField.setText("");
		yearField.setText("");
		engineVolField.setText("");
		maxSpeedField.setText("");	
	}
}