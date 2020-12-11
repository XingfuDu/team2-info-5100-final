package ui.CheckLead;


import dao.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class UI extends JFrame {
    private JPanel panel, tablePanel;
    private JTable table;
    private JLabel first, second;
    private JButton export, detail;
    private List<LeadForm> forms;
    private TableModel tableModel;
    private JComboBox filter, sortBy;
    private Font tableFont, tableHeadFont;
    private JScrollPane jScrollPane;

    public UI() {
        creat();
        addComponents();
        display();
    }

    private void addComponents() {
        FlowLayout f = new FlowLayout();
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.add(first);
        panel.add(filter);
        panel.add(second);
        panel.add(sortBy);
        panel.add(export);
        panel.add(detail);
        this.add(panel);

        GridLayout g = new GridLayout(3,3);
        //FlowLayout f2 = new FlowLayout();

        this.setLayout(g);


        this.add(tablePanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    private void display() {
        setSize(400,400);
        setVisible(true);
        pack();
    }

    private void creat() {
        first = new JLabel("Filter");
        second = new JLabel("Sort by");
        export = new JButton("Export");
        export.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    System.out.println(forms.get(i));
                }
            }
        });
        detail = new JButton("Detail");
        detail.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getSelectedRow() != -1) {
                    int i = table.getSelectedRow();
                    Vehicle[] vehicles = {new Vehicle("1", "2015", "Honda", null, true,
                                "35000", "black", "black", BodyType.SUV, "0"),
                                new Vehicle("2", "2020", "Toyota", null, false,
                                        "25000", "black", "black", BodyType.CAR, "40000")};
                    Address address = new Address("401 Terry Ave N #103", "","Seattle", "WA", "98109");
                    Customer customer = new Customer("Xingfu", "Du", address, "(206) 467-5480", "Xingfu@gmail.com");
                    String userNotes = "user notes here";
                    new DetailsWindow(customer, vehicles, userNotes).buildGUI();
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

        String[] s = {"--Select sort--", "car", "model", "name", "phone", "email", "comments", "operation"};
        sortBy = new JComboBox(s);
        //sortBy.setSelectedIndex(0);
        sortBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) sortBy.getSelectedItem();
                if (s == "car") {
                    forms.sort(Comparator.comparing(a -> a.getCar()));
                    table.updateUI();
                }
                if ( s == "model") {
                    forms.sort(Comparator.comparing(a -> a.getModel()));
                    table.updateUI();
                }
                if (s == "name") {
                    forms.sort(Comparator.comparing(a -> a.getName()));
                    table.updateUI();
                }
                if ( s == "phone") {
                    forms.sort(Comparator.comparing(a -> a.getPhone()));
                    table.updateUI();
                }
                if (s == "email") {
                    forms.sort(Comparator.comparing(a -> a.getEmail()));
                    table.updateUI();
                }
                if ( s == "comments") {
                    forms.sort(Comparator.comparing(a -> a.getComments()));
                    table.updateUI();
                }
            }
        });



        creatTableComponents();



    }
    private List<LeadForm> createSomeForms() {
        ArrayList<LeadForm> forms = new ArrayList<LeadForm>();
        forms.add(new LeadForm("Honda", "Y1", "Loe", "5111111111",
                "111111@gmail.com", "I am interested in this car", "  "));
        forms.add(new LeadForm("BMW", "X2", "Moe", "2222211111",
                "122111@gmail.com", "I am interested in this car", "  "));
        forms.add(new LeadForm("Audi", "A3", "Joe", "6331111111",
                "133111@gmail.com", "I am interested in this car", "  "));
        forms.add(new LeadForm("Ferrari", "F4", "Yoe", "4411111111",
                "144111@gmail.com", "I am interested in this car", "  "));

        return forms;
    }

    private void creatTableComponents() {
        forms = createSomeForms();
        tableModel = new LeadFormsTableModel(forms);
        table = new JTable(tableModel);
        tableFont = new Font("Baskerville", Font.PLAIN,18);
        tableHeadFont = new Font("Baskerville", Font.PLAIN,19);


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
class Frame1 extends JFrame{
    public Frame1(){
        setSize(300,300);
        setVisible(true);
    }
}

