import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.net.URL;
 



public class DetailsWindow {
    private String carInfo = "car info here";
    private String dealerInfo = "dealer info here";
    private String customerInfo = "Name: Alice\n\n" + "Email: alice@gmail.com"
	+ "\n\nPhone: 111-111-1111\n";
    private String userNotesInfo = "user notes here";
    private String[] tabNames = { "Car Info", "Dealer Info", "Customer Info", "User Notes"};
    JTextArea carInfoTextArea;
    JTextArea dealerInfoTextArea;
    JTextArea customerInfoTextArea;
    JTextArea userNotesTextArea;
    JTextArea userNotesReplyTextArea;
    JButton replyButton;
    private JTabbedPane mainPanel;
    private JFrame theFrame;

    public DetailsWindow () {
    }
    public DetailsWindow (Car car, Customer customer, Dealer dealer, UserNotes userNotes) {
	carInfo = car.toString();
	customerInfo = customer.toString();
	dealerInfo = dealer.toString();
	userNotesInfo = userNotes.toString();
    }
    public static void main (String[] args) {
	new DetailsWindow().buildGUI();
    }

    public void buildGUI () {
	theFrame = new JFrame("DetailsWindow");
	theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainPanel = new JTabbedPane();
	layoutComponents();
	theFrame.getContentPane().add(mainPanel);
	theFrame.setPreferredSize(new Dimension(800, 600));
        theFrame.pack();
	theFrame.setLocationRelativeTo(null); 
        theFrame.setVisible(true);
    }

    private void layoutComponents() {
	int i = 0;
	/** 
	    car info panel
	 */
	JPanel carInfoPanel = new JPanel();	
        carInfoPanel.setLayout(new BorderLayout());
	
        carInfoTextArea = new JTextArea();
	carInfoTextArea.setEditable(false);
        carInfoTextArea.setLineWrap(true);
        carInfoTextArea.setText(carInfo);

        JScrollPane CarInfoTextScroller = new JScrollPane(customerInfoTextArea);
        CarInfoTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        CarInfoTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        carInfoPanel.add(carInfoTextArea);

	mainPanel.addTab(tabNames[i++], null, carInfoPanel, "first");

 


	/**
	   dealer info panel
	*/
	JPanel dealerInfoPanel = new JPanel();
        dealerInfoPanel.setLayout(new BorderLayout());

        dealerInfoTextArea = new JTextArea();
	dealerInfoTextArea.setEditable(false);
        dealerInfoTextArea.setLineWrap(true);
        dealerInfoTextArea.setText(dealerInfo);

        JScrollPane dealerInfoTextScroller = new JScrollPane(dealerInfoTextArea);
        dealerInfoTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        dealerInfoTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        dealerInfoPanel.add(dealerInfoTextArea);

	mainPanel.addTab(tabNames[i++], null, dealerInfoPanel, "second");

	/** 
	    customer info panel
	*/
        JPanel customerInfoPanel = new JPanel();
	customerInfoPanel.setLayout(new BorderLayout());
     
	customerInfoTextArea = new JTextArea();
	customerInfoTextArea.setEditable(false);
        customerInfoTextArea.setLineWrap(true);
	customerInfoTextArea.setText(customerInfo);

        JScrollPane customerInfoTextScroller = new JScrollPane(customerInfoTextArea);
        customerInfoTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        customerInfoTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	
	customerInfoPanel.add(customerInfoTextArea);

        mainPanel.addTab(tabNames[i++], null, customerInfoPanel, "third");




	/** 
	    user notes panel
	*/
        JPanel userNotesPanel = new JPanel();
        userNotesPanel.setLayout(new BorderLayout());
        
	GridLayout grid = new GridLayout(2,1);
        grid.setVgap(10);
        grid.setHgap(1);	
	JPanel userNotesSubPanel = new JPanel(grid);

        userNotesTextArea = new JTextArea(12,40);
	userNotesTextArea.setEditable(false);
        userNotesTextArea.setLineWrap(true);
        userNotesTextArea.setText(userNotesInfo);

        JScrollPane userNotesTextScroller = new JScrollPane(userNotesTextArea);
        userNotesTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        userNotesTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        userNotesReplyTextArea = new JTextArea();
        userNotesReplyTextArea.setLineWrap(true);

        JScrollPane userNotesReplyTextScroller = new JScrollPane(userNotesReplyTextArea);
        userNotesReplyTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	userNotesReplyTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

	userNotesSubPanel.add(userNotesTextScroller);
	userNotesSubPanel.add(userNotesReplyTextScroller);

        userNotesPanel.add(BorderLayout.NORTH, userNotesSubPanel);
	userNotesPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        replyButton = new JButton("Reply");
	replyButton.addActionListener(new ActionListener() {
		@Override
		    public void actionPerformed(ActionEvent e) {
		    int result = JOptionPane.showConfirmDialog(theFrame,"Do you want to continue sending the message", 
							       "Swing Tester",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
		    if(result == JOptionPane.YES_OPTION){
			replyButton.setText("message sent");
			userNotesReplyTextArea.setText(null);
		    }
		}
	    });



	JPanel buttonPane = new JPanel();
	buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
	buttonPane.add(Box.createHorizontalGlue());
	buttonPane.add(replyButton);



	userNotesPanel.add(BorderLayout.PAGE_END, buttonPane);
        

        mainPanel.addTab(tabNames[i++], null, userNotesPanel, "fourth");


    }


}

class Car {
    public String toString () {
	return "";
    }
}

class Customer {
    public String toString () {
	return "";
    }
}

class Dealer {
    public String toString () {
	return "";
    }
}

class UserNotes {
    public String toString () {
	return "";
    }
}