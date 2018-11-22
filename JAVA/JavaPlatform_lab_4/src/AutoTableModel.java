import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class AutoTableModel extends AbstractTableModel {

	private AutoCollection _data;
	
	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
		sort();
	}
	
	public AutoTableModel() {
		_data = new AutoCollection();
	}
	
	public AutoTableModel(AutoCollection data) {
		_data = data;
	}
	
	public AutoTableModel(LinkedList<Auto> data) {
		_data = new AutoCollection(data);
	}	
	
	public void loadFromFile(File file) {
		_data.LoadFromFile(file);
	}
	
	public void saveToFile(File file) {
		_data.SaveToFile(file);
	}
	
	public void sort() {
		_data.sort(null);
	}
	
	@Override
	public int getColumnCount() {
		return Auto.getDataFieldsCount();
	}

	@Override
	public int getRowCount() {
		return _data.size();
	}
	@Override
	public Object getValueAt(int row, int col) {
		Auto entityInRow = _data.get(row);		
		return entityInRow.getValueAtField(col);
	}
	
	public Class<?> getColumnClass(int columnIndex){
		return getValueAt(0, columnIndex).getClass();
	}
	
	public String getColumnName(int colNum) {		
		return Auto.getFieldName(colNum);		
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void setValueAt(Object value, int rowIndex, int colIndex) {
		Auto temp = _data.get(rowIndex);
		temp.setValueAtField(value, colIndex);		
	}
	
	
	public boolean addRow(Auto entity) {
		
		if (entity != null) {
			_data.add(entity);
			
			int indexOfNewEntity = _data.indexOf(entity);
			return (indexOfNewEntity == -1) ? false : true;
		}
		
		return false;
	}
	
	
	
	public boolean deleteRow(int index) {
		if (index < _data.size()) {
			_data.remove(index);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean removeRange(int[] indexes) {
		if (indexes != null) {
			for(int i = 0; i < indexes.length; ++i) {
				_data.remove(indexes[i] - i);
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void clearData() {
		_data.clear();
	}
	
	public boolean updateRow(int index, Auto entity) {
		Auto result = _data.set(index, entity);		
		return (result == null) ? false : true;
	}
	
	public boolean isExist(Auto entity) {
		Iterator<Auto> tempIter = _data.find(entity.getMark());
		return tempIter == null ? false : true;
	}
	
	public Auto getRowEntity(int index) {
		return _data.get(index);
	}

}
