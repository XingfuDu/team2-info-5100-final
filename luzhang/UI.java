import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UI extends JFrame {
    private JPanel panel, tablePanel;
    private JTable table;
    private JLabel first, second;
    private JButton button;
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
        this.setLayout(f);
        panel = new JPanel();
        panel.add(first);
        panel.add(filter);
        panel.add(second);
        panel.add(sortBy);
        panel.add(button);
        this.add(panel);

        GridLayout g = new GridLayout(3,3);
        //FlowLayout f2 = new FlowLayout();

        this.setLayout(g);


        this.add(tablePanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void display() {
        setSize(400,400);
        setVisible(true);
        pack();
    }

    private void creat() {
        first = new JLabel("Filter");
        second = new JLabel("Sort by");
        button = new JButton("Export");
        String[] f = {"--Select filter--", "1", "2"};
        filter = new JComboBox(f);
        filter.setSelectedIndex(0);
        String[] s = {"--Select sort--", "1", "2"};
        sortBy = new JComboBox(s);
        sortBy.setSelectedIndex(0);
        creatTableComponents();


    }
    private List<LeadForm> creatSomeForms() {
        ArrayList<LeadForm> forms = new ArrayList<LeadForm>();
        forms.add(new LeadForm("BMW", "X1", "Joe", "1111111111",
                "111111@gmail.com", "I am interested in this car", "  "));
        forms.add(new LeadForm("BMW", "X2", "Moe", "2222211111",
                "122111@gmail.com", "I am interested in this car", "  "));
        forms.add(new LeadForm("BMW", "X3", "Loe", "3331111111",
                "133111@gmail.com", "I am interested in this car", "  "));
        forms.add(new LeadForm("BMW", "X4", "Yoe", "4411111111",
                "144111@gmail.com", "I am interested in this car", "  "));
        return forms;
    }

    private void creatTableComponents() {
        forms = creatSomeForms();
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
        jScrollPane.setPreferredSize(new Dimension(1000, 420));
        jScrollPane.setBackground(getContentPane().getBackground());
    }

    public static void main(String[] args) {
        new UI();
    }


}
