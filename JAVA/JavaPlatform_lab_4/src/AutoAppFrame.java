import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class AutoAppFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private AutoTableModel tableModel;
	
	private JTable tableData;
	
	/**
	 * Create the frame.
	 */
	public AutoAppFrame() {
		super("Auto-list");
//		super("JavaPlatform_lab_4_Auto-list");
		try {
			this.BuildMainFrame();
			this.BuildTableField();
			this.BuildButtons();
		} catch(Exception ex) {
			JOptionPane.showMessageDialog(AutoAppFrame.this, ex);
		}
	}
	
	public static int GetRelativeWindowWidth(JFrame element, int proc) {
		return (int)element.getWidth() * proc / 100;
	}
	public static int GetRelativeWindowHeight(JFrame element, int proc) {
		return (int)element.getHeight() * proc / 100;
	}
	
	private void BuildMainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 700, 300);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(new BorderLayout(1,2));
		this.setContentPane(contentPane);
	}
	
	private void BuildButtons() {
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(8,1));
		
		JButton addBtn = new JButton("Add");
		JButton removeBtn = new JButton("Remove");
		JButton updateBtn = new JButton("Update");
		JButton hiddenBtn = new JButton();
		JButton newListBtn = new JButton("New list");
		JButton loadBtn = new JButton("Load From File");
		JButton saveBtn = new JButton("Save As");
		
		btnPanel.add(addBtn);
		btnPanel.add(removeBtn);
		btnPanel.add(updateBtn);
		btnPanel.add(hiddenBtn);
		btnPanel.add(newListBtn);
		btnPanel.add(loadBtn);
		btnPanel.add(saveBtn);
		
		hiddenBtn.setVisible(false);
		contentPane.add(btnPanel,BorderLayout.EAST);
		
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DataFrame(tableModel);
			}
		});
		removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int rowCount = tableData.getSelectedRowCount();
				
					if (rowCount > 1) {
							int result = JOptionPane.showConfirmDialog(AutoAppFrame.this, "Do you want delete" + rowCount + "rows");
							if (result == JOptionPane.OK_OPTION) {
								tableModel.removeRange(tableData.getSelectedRows());
								tableModel.fireTableDataChanged();
							}
							
					} else if (rowCount == 1){
						int selectedRow = tableData.getSelectedRow();
						int result = JOptionPane.showConfirmDialog(AutoAppFrame.this, "Do you want delete" + tableModel.getValueAt(selectedRow, 0));
							
						if (result == JOptionPane.OK_OPTION) {
							tableModel.deleteRow(tableData.getSelectedRow());
							tableModel.fireTableDataChanged();
						}
					}
					
				
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tableData.getSelectedRow();
				if (selectedRow != -1) {
					new DataFrame(tableModel, selectedRow);
				} else {
					JOptionPane.showMessageDialog(AutoAppFrame.this, "Not selected data");
				}
			}
		});
		
		newListBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					int result = JOptionPane.showConfirmDialog(AutoAppFrame.this, "All data from list will be cleared. Continue?");
					
					if (result == JOptionPane.OK_OPTION) {
						tableModel.clearData();
						tableModel.fireTableDataChanged();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}				
			}
		});
		
		loadBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					JFileChooser file = new JFileChooser();
					
					int result = file.showDialog(AutoAppFrame.this, "Открыть файл");
					if (result == JFileChooser.APPROVE_OPTION) {
						File fin = file.getSelectedFile();						
						tableModel.loadFromFile(fin);
						tableModel.fireTableDataChanged();
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
				
				
			}
		});

		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					JFileChooser file = new JFileChooser();
					
					int result = file.showSaveDialog(AutoAppFrame.this);
					if (result == JFileChooser.APPROVE_OPTION) {
						File fout = file.getSelectedFile();	
						tableModel.saveToFile(fout);
					}
				} catch (Exception ex) {
					System.out.println(ex.toString());
				}
				
				
			}
		});
	}

	private void BuildTableField() {
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new EmptyBorder(20,20,20,20));
		tablePanel.setLayout(new BorderLayout(5,5));
		
		
		tableModel = new AutoTableModel();
		tableData = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(tableData);
		scrollPane.add(tableData);
		scrollPane.setVisible(true);
		
		tablePanel.add(tableData,BorderLayout.CENTER);
		tablePanel.add(scrollPane,BorderLayout.EAST);
		
		contentPane.add(tablePanel,BorderLayout.CENTER);
	}
	
}


