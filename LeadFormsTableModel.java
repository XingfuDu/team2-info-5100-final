//
//import javax.swing.*;
//import javax.swing.event.TableModelListener;
//import javax.swing.table.AbstractTableModel;
//import java.util.List;
//
//public class LeadFormsTableModel extends AbstractTableModel{
//
//    enum ColumnIdx {
//        Car, Model, Name, Phone, Email, Comments,
//    };
//
//    private List<LeadForm> form;
//
//    private final String[] columnNames = {"Car", "Model", "Name", "Phone", "Email", "Comments"};
//
//
//    public LeadFormsTableModel(List<LeadForm> forms) {
//
//    }
//    @Override
//    public void addTableModelListener(TableModelListener arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//    public void setRows(List<LeadForm> form) {
//        this.form = form;
//    }
//
//    @Override
//    public int getColumnCount() {
//        return ColumnIdx.Comments.ordinal();
//    }
//
//    @Override
//    public String getColumnName(int col) {
//        return columnNames[col];
//    }
//
//    @Override
//    public int getRowCount() {
//        if (this.form == null)
//            return 0;
//
//        return this.form.size();
//    }
//
//
//    private Object getValue(LeadForm form, int col){
//        if (form != null) {
//            ColumnIdx colIdx = ColumnIdx.values()[col];
//            switch (colIdx) {
//                case Car:
//                    return form.getCar();
//                case Model:
//                    return form.getModel();
//                case Name:
//                    return form.getName();
//                case Phone:
//                    return form.getPhone();
//                case Email:
//                    return form.getEmail();
//                case Comments:
//                    return form.getComments();
//            }
//        }
//
//        return null;
//    }
//
//
//    public Object getValueAt(LeadForm form, int col) {
//        return this.getValue(form, col);
//    }
//
//    @Override
//    public Object getValueAt(int row, int col) {
//        if (row >= form.size() && row < 0) {
//            return null;
//        }
//
//        try {
//            LeadForm f = form.get(row);
//            return this.getValue(f, col);
//        }
//        catch (IndexOutOfBoundsException exception) {
//            System.out.println("exception:" + exception);
//        }
//        return null;
//    }
//
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return getValueAt(0, columnIndex).getClass();
//    }
//
//
//
//    @Override
//    public void removeTableModelListener(TableModelListener arg0) {
//        // TODO Auto-generated method stub
//
//    }
//
//    @Override
//    public void setValueAt(Object arg0, int arg1, int arg2) {
//        // TODO Auto-generated method stub
//
//    }
//
//}
//
////    jTable1.getColumn("Con1").setCellRenderer(new ButtonRenderer());
////      jTable1.getColumn("Con1").setCellEditor(new ButtonEditor(new JCheckBox()));

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;


public class LeadFormsTableModel extends AbstractTableModel {
    public List<LeadForm> forms;
    public LeadFormsTableModel(List forms) {
        this.forms = forms;
    }

    @Override
    public int getRowCount() {
        return forms.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        //car, String model, String name, String phone, String email, String comments
        if (columnIndex == 0) {
            return "car";
        }
        if (columnIndex == 1) {
            return "model";
        }
        if (columnIndex == 2) {
            return "name";
        }
        if (columnIndex == 3) {
            return "phone";
        }
        if (columnIndex == 4) {
            return "email";
        }
        if (columnIndex == 5) {
            return "comments";
        }
        if (columnIndex == 6) {
            return "operation";
        }
//        if (columnIndex == 7) {
//            return "car";
//        }
        return "operation";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LeadForm f = forms.get(rowIndex);
        if (columnIndex == 0) {
            return f.getCar();
        }
        if (columnIndex == 1) {
            return f.getModel();
        }
        if (columnIndex == 2) {
            return f.getName();
        }
        if (columnIndex == 3) {
            return f.getPhone();
        }
        if (columnIndex == 4) {
            return f.getEmail();
        }
        if (columnIndex == 5) {
            return f.getComments();
        }
        if (columnIndex == 6) {
            return f.getOperation();
        }

        return null;
    }


}