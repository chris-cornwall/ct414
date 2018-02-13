package client;

import java.awt.*;       // Using layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import client.ClientControl.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.NoMatchingAssessment;
import server.UnauthorizedAccess;

// A Swing GUI application inherits the top-level container javax.swing.JFrame
public class LoginScreen extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField user, pass;
    private JButton submit;
    private boolean isVerified = false;
    private int userID, token;
    private ArrayList<String> assessList= new ArrayList();

    // Constructor to setup the GUI components and event handlers
    public LoginScreen() {
        // Retrieve the content-pane of the top-level container JFrame
        // All operations done on the content-pane
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(3, 2, 5, 5));  // The content-pane sets its layout

        cp.add(new JLabel("Username: "));
        user = new JTextField(10);
        cp.add(user);
        cp.add(new JLabel("Password: "));
        pass = new JTextField(10);
        cp.add(pass);

        cp.add(new JLabel(" "));

        submit = new JButton("Submit");
        cp.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String userInfoString = user.getText();
                String passInfo = pass.getText();
                int userInfo = Integer.parseInt(userInfoString);

                verifyUser(userInfo, passInfo);
                if (isVerified == true) {
//assessList = (ArrayList<String>) ClientControl.server.getAvailableSummary(token, userID);
                    //System.out.println("AssessList contains" + assessList.get(0) + assessList.get(1) + assessList.get(2));
                                        /*
        	  		try {
						ClientControl.setToken(ClientControl.server.login(userInfo,passInfo));
					} catch (RemoteException | UnauthorizedAccess e) {
						e.printStackTrace();
					}
                     */
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new assesmentSummary(userInfoString, assessList);
                        }
                    });
                    cp.setVisible(false);
                    dispose();
                    isVerified = false;
                }

            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Login"); // "super" Frame sets title
        setSize(900, 300);  // "super" Frame sets initial size
        setLocationRelativeTo(null);
        setVisible(true);   // "super" Frame shows
    }

    public void verifyUser(int userID, String pass) {
       
        

        try {
            int verified = ClientControl.server.login(userID, pass);
             this.userID = userID;
             this.token = verified;
             assessList = (ArrayList<String>) ClientControl.server.getAvailableSummary(verified, userID);
            
            // Check if int is returned...
            if (verified == (int)verified) {
                isVerified = true;
            }
        } catch (Exception e) {
            System.err.println("GetAssessment exception:");
            e.printStackTrace();
            //connect to server and verify use
        }

    }

    // The entry main() method
}
