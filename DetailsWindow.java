import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
 



public class DetailsWindow {
    private Customer customer;
    private String customerInfo = "Name: Alice\n\n" + "Email: alice@gmail.com"
        + "\n\nPhone: 111-111-1111\n";
    private String carInfo[] = {"car1 info here", "car2 info here", "car3 info here"};
    private int carAndDealerIndex = 0;
    private String dealerInfo[] = {"dealer1 info here", "dealer2 info here", "dealer3 info here"};
    private String userNotesInfo = "user notes here";
    private String[] tabNames = {"Customer Info", "Car Info", "Dealer Info", "User Notes"};
    JTextArea carInfoTextArea;
    JTextArea dealerInfoTextArea;
    JTextArea customerInfoTextArea;
    JTextArea userNotesTextArea;
    JTextArea userNotesReplyTextArea;
    JButton replyButton;
    JButton carInfoPreviousButton, carInfoNextButton;
    JButton dealerInfoPreviousButton, dealerInfoNextButton;
    private JTabbedPane mainPanel;
    private JFrame theFrame;

    public DetailsWindow () {
    }
    public DetailsWindow (Car[] car, Customer customer, Dealer[] dealer, UserNotes userNotes) {
	for (int i = 0; i < car.length; i++) {
	    carInfo[i] = car[i].toString();
	    dealerInfo[i] = dealer[i].toString();
	}
	this.customer = customer;
	customerInfo = customer.toString();
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
	
        mainPanel.addTab(tabNames[i++], null, customerInfoPanel, "first");




	/** 
	    car info panel
	 */
	JPanel carInfoPanel = new JPanel();	
        carInfoPanel.setLayout(new BorderLayout());
	
        carInfoTextArea = new JTextArea();
	carInfoTextArea.setEditable(false);
        carInfoTextArea.setLineWrap(true);
        carInfoTextArea.setText(carInfo[carAndDealerIndex]);

        JScrollPane carInfoTextScroller = new JScrollPane(carInfoTextArea);
        carInfoTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        carInfoTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JToolBar carToolBar = new JToolBar();
	carInfoAddButtons(carToolBar);
	JPanel carInfoSubPanel= new JPanel();
	carInfoSubPanel.setLayout(new BorderLayout());
	carInfoSubPanel.add(carToolBar,BorderLayout.PAGE_START);
	carInfoSubPanel.add(carInfoTextScroller,BorderLayout.CENTER);

        carInfoPanel.add(carInfoSubPanel);

	mainPanel.addTab(tabNames[i++], null, carInfoPanel, "second");

 


	/**
	   dealer info panel
	*/
	JPanel dealerInfoPanel = new JPanel();
        dealerInfoPanel.setLayout(new BorderLayout());

        dealerInfoTextArea = new JTextArea();
	dealerInfoTextArea.setEditable(false);
        dealerInfoTextArea.setLineWrap(true);
        dealerInfoTextArea.setText(dealerInfo[carAndDealerIndex]);

        JScrollPane dealerInfoTextScroller = new JScrollPane(dealerInfoTextArea);
        dealerInfoTextScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        dealerInfoTextScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JToolBar dealerToolBar = new JToolBar();
        dealerInfoAddButtons(dealerToolBar);
        JPanel dealerInfoSubPanel= new JPanel();
        dealerInfoSubPanel.setLayout(new BorderLayout());
	dealerInfoSubPanel.add(dealerToolBar,BorderLayout.PAGE_START);
        dealerInfoSubPanel.add(dealerInfoTextScroller,BorderLayout.CENTER);

        dealerInfoPanel.add(dealerInfoSubPanel);

	mainPanel.addTab(tabNames[i++], null, dealerInfoPanel, "third");



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
			sendMessage(customer, userNotesReplyTextArea.getText());
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

    private void carInfoAddButtons (JToolBar toolBar) {
	/** 
	    previous button
	*/
	carInfoPreviousButton = makeNavigationButton("previous", "go to previous car");
	carInfoPreviousButton.addActionListener(new ActionListener() {
                @Override
		public void actionPerformed(ActionEvent e) {
                    if (carAndDealerIndex > 0) {
			dealerInfoNextButton.setEnabled(true);
			carInfoNextButton.setEnabled(true);
                        carAndDealerIndex--;
                        carInfoTextArea.setText(carInfo[carAndDealerIndex]);
                        dealerInfoTextArea.setText(dealerInfo[carAndDealerIndex]);
                        if (carAndDealerIndex == 0) {
                            carInfoPreviousButton.setEnabled(false);
                            dealerInfoPreviousButton.setEnabled(false);
                        }
		    }
                }
            });

	/** 
	    next button
	*/
	carInfoNextButton = makeNavigationButton("next", "go to next car");
        carInfoNextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (carAndDealerIndex < carInfo.length - 1) {
			dealerInfoPreviousButton.setEnabled(true);
                        carInfoPreviousButton.setEnabled(true);
                        carAndDealerIndex++;
                        carInfoTextArea.setText(carInfo[carAndDealerIndex]);
                        dealerInfoTextArea.setText(dealerInfo[carAndDealerIndex]);
                        if (carAndDealerIndex == carInfo.length - 1) {
                            carInfoNextButton.setEnabled(false);
                            dealerInfoNextButton.setEnabled(false);
                        }
                    }
		}
	});

        if (carAndDealerIndex == 0) {
            carInfoPreviousButton.setEnabled(false);
        }
        if (carAndDealerIndex == carInfo.length - 1) {
            carInfoNextButton.setEnabled(false);
        }

        toolBar.add(carInfoPreviousButton);
        toolBar.add(carInfoNextButton);
    }


    private void dealerInfoAddButtons (JToolBar toolBar) {
        /**                                                                                                                                                                        
            previous button                                                                                                                                                        
	*/
        dealerInfoPreviousButton = makeNavigationButton("previous", "go to previous dealer");
        dealerInfoPreviousButton.addActionListener(new ActionListener() {
                @Override
		    public void actionPerformed(ActionEvent e) {
                    if (carAndDealerIndex > 0) {
			dealerInfoNextButton.setEnabled(true);
			carInfoNextButton.setEnabled(true);
                        carAndDealerIndex--;
                        carInfoTextArea.setText(carInfo[carAndDealerIndex]);
                        dealerInfoTextArea.setText(dealerInfo[carAndDealerIndex]);
			if (carAndDealerIndex == 0) {
			    carInfoPreviousButton.setEnabled(false);
			    dealerInfoPreviousButton.setEnabled(false); 
			}
                    }
                }
            });

        /**                                                                                                                                                                        
            next button                                                                                                                                                            
	*/
        dealerInfoNextButton = makeNavigationButton("next", "go to next dealer");
        dealerInfoNextButton.addActionListener(new ActionListener() {
                @Override
		    public void actionPerformed(ActionEvent e) {
                    if (carAndDealerIndex < carInfo.length - 1) {
                        dealerInfoPreviousButton.setEnabled(true);
                        carInfoPreviousButton.setEnabled(true);
                        carAndDealerIndex++;
			carInfoTextArea.setText(carInfo[carAndDealerIndex]);
			dealerInfoTextArea.setText(dealerInfo[carAndDealerIndex]);
			if (carAndDealerIndex == carInfo.length - 1) {
                            carInfoNextButton.setEnabled(false);
                            dealerInfoNextButton.setEnabled(false);
                        }
                    }
                }
	    });
        if (carAndDealerIndex == 0) {
            dealerInfoPreviousButton.setEnabled(false);
        }
        if (carAndDealerIndex == carInfo.length - 1) {
            dealerInfoNextButton.setEnabled(false);
        }

        toolBar.add(dealerInfoPreviousButton);
        toolBar.add(dealerInfoNextButton);
    }

    private JButton makeNavigationButton (String actionCommand, String toolTipText) {
	JButton button = new JButton();
	button.setActionCommand(actionCommand);
	button.setToolTipText(toolTipText);
	button.setText(actionCommand);
	return button;
    }

    private void sendMessage (Customer cust, String message) {
	
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

