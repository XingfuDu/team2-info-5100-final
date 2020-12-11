package ui.CheckLead;

import dao.*;
import dto.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class UI extends JFrame {
    private JPanel panel, tablePanel;
    private JTable table;
    private JLabel first, second;
    private JButton export, detail;
    private List<Lead> forms;
    private TableModel tableModel;
    private JComboBox filter, sortBy;
    private Font tableFont, tableHeadFont;
    private JScrollPane jScrollPane;
    private LeadDataHelper helper;

    public UI() {
        helper = LeadDataHelper.instance();
        create();
        addComponents();
        display();
    }

    private void addComponents() {
        FlowLayout f = new FlowLayout();
        this.setLayout(f);
        panel = new JPanel();
        panel.add(first);
        panel.add(filter);
        panel.add(second);
        panel.add(sortBy);
        panel.add(export);
        panel.add(detail);
        this.add(panel);

        GridLayout g = new GridLayout(3, 3);

        this.setLayout(g);


        this.add(tablePanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void display() {
        setSize(400, 400);
        setVisible(true);
        pack();
    }

    private void create() {
        first = new JLabel("Filter");
        second = new JLabel("Sort by");
        export = new JButton("Export");
        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (table.isRowSelected(i)) {
                        System.out.println(forms.get(i));
                    }
                }
            }
        });
        detail = new JButton("Detail");
        detail.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    LeadDataHelper helper1 = LeadDataHelper.instance();
                    Vehicle[] vehicles={helper.getVehicle(forms.get(0).getVehicleId()),
                            helper.getVehicle(forms.get(i).getVehicleId())};
                    new DetailsWindow(forms.get(i), vehicles).buildGUI();
                    forms.get(i).setRead(true);
                    table.updateUI();
                }
            }
        });
        String[] f = {"--Select filter--", "1", "2"};
        filter = new JComboBox(f);
        //filter.setSelectedIndex(0);
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) filter.getSelectedItem();
                if (s == "car") {

                }
            }
        });

        String[] s = {"--Select sort--", "Name", "Phone Number", "Email", "Contact Preference",
                                        "Contact Time", "Use Purpose", "Contacted"};
        sortBy = new JComboBox(s);
        //sortBy.setSelectedIndex(0);
        sortBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) sortBy.getSelectedItem();
                if (s == "Name") {
                    forms.sort(Comparator.comparing(a -> a.getFirstName() + " " + a.getLastName()));
                    table.updateUI();
                }
                if (s == "Phone Number") {
                    forms.sort(Comparator.comparing(a -> a.getPhoneNumber()));
                    table.updateUI();
                }
                if (s == "Email") {
                    forms.sort(Comparator.comparing(a -> a.getEmailAddress()));
                    table.updateUI();
                }
                if (s == "Contact Preference") {
                    forms.sort(Comparator.comparing(a -> a.getContactPreference()));
                    table.updateUI();
                }
                if (s == "Contact Time") {
                    forms.sort(Comparator.comparing(a -> a.getContactTime()));
                    table.updateUI();
                }
                if (s == "Use Purpose") {
                    forms.sort(Comparator.comparing(a -> a.getUsePurpose()));
                    table.updateUI();
                }
                if (s == "Contacted") {
                    forms.sort(Comparator.comparing(a -> a.getContacted()));
                    table.updateUI();
                }
            }
        });


        createTableComponents();


    }



    private void createTableComponents() {
        forms =  helper.getLeads();
        tableModel = new LeadFormsTableModel(forms);
        table = new JTable(tableModel);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                           int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (forms.get(row).getRead() == true) {
                    c.setFont(new Font("Baskerville", Font.PLAIN, 18));
                }
                return this;
            }
        });
        tableFont = new Font("Baskerville", Font.BOLD, 18);
        tableHeadFont = new Font("Baskerville", Font.PLAIN, 19);


        JTableHeader head = this.table.getTableHeader();
        head.setFont(tableHeadFont);
        table.setShowGrid(true);
        table.setShowHorizontalLines(true);
        table.setFont(tableFont);
        table.setRowHeight(30);


        table.setBackground(getContentPane().getBackground());

        jScrollPane = new JScrollPane(this.table);
        jScrollPane.setFont(tableFont);
        Border js = BorderFactory.createEmptyBorder();
        jScrollPane.setBorder(js);

        tablePanel = new JPanel();
        tablePanel.add(jScrollPane);
        tablePanel.setBackground(getContentPane().getBackground());
        jScrollPane.setPreferredSize(new Dimension(1000, 600));
        jScrollPane.setBackground(getContentPane().getBackground());
    }

    public static void main(String[] args) {
        new UI();
    }
}

