import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class login extends JFrame
{
	// Variables declaration
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JTextField jTextField1;
	private JPasswordField jPasswordField1;
	private JButton jButton1;
	private JPanel contentPane;
	// End of variables declaration


	public login()
	{
		super();
		create();
		this.setVisible(true);
	}

	
	private void create()
	{
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jTextField1 = new JTextField();
		jPasswordField1 = new JPasswordField();
		jButton1 = new JButton();
		contentPane = (JPanel)this.getContentPane();

		//
		// jLabel1
		//
		jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel1.setForeground(new Color(0, 0, 255));
		jLabel1.setText("username:");
		//
		// jLabel2
		//
		jLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel2.setForeground(new Color(0, 0, 255));
		jLabel2.setText("password:");
		//
		// jTextField1
		//
		jTextField1.setForeground(new Color(0, 0, 255));
		jTextField1.setSelectedTextColor(new Color(0, 0, 255));
		jTextField1.setToolTipText("Enter your username");
		jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jTextField1_actionPerformed(e);
			}

		});
		//
		// jPasswordField1
		//
		jPasswordField1.setForeground(new Color(0, 0, 255));
		jPasswordField1.setToolTipText("Enter your password");
		jPasswordField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jPasswordField1_actionPerformed(e);
			}

		});
		//
		// jButton1
		//
		jButton1.setBackground(new Color(204, 204, 204));
		jButton1.setForeground(new Color(0, 0, 255));
		jButton1.setText("Login");
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jButton1_actionPerformed(e);
			}

		});
		//
		// contentPane
		//
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createEtchedBorder());
		contentPane.setBackground(new Color(204, 204, 204));
		addComponent(contentPane, jLabel1, 5,10,106,18);
		addComponent(contentPane, jLabel2, 5,47,97,18);
		addComponent(contentPane, jTextField1, 110,10,183,22);
		addComponent(contentPane, jPasswordField1, 110,45,183,22);
		addComponent(contentPane, jButton1, 150,75,83,28);
		//
		// login
		//
		this.setTitle("Login To Members Area");
		this.setLocation(new Point(76, 182));
		this.setSize(new Dimension(335, 141));
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

	
	private void jTextField1_actionPerformed(ActionEvent e)
	{
		

	}

	private void jPasswordField1_actionPerformed(ActionEvent e)
	{
		
	}

	private void jButton1_actionPerformed(ActionEvent e)
	{
		System.out.println("\njButton1_actionPerformed(ActionEvent e) called.");
		String username = new String(jTextField1.getText());
		String password = new String(jPasswordField1.getText());
		
		if(username.equals("") || password.equals("")) // If password and username is empty > Do this >>>
				{
					jButton1.setEnabled(false);
				JLabel errorFields = new JLabel("<HTML><FONT COLOR = Blue>You must enter a username and password to login.</FONT></HTML>");	
				JOptionPane.showMessageDialog(null,errorFields); 
				jTextField1.setText("");
	   			jPasswordField1.setText("");
	   			jButton1.setEnabled(true);
				this.setVisible(true);
				}
				else
				{
					String usern = "user";
					String passw = "password";
		JLabel optionLabel = new JLabel("<HTML><FONT COLOR = Blue>You entered</FONT><FONT COLOR = RED> <B>"+username+"</B></FONT> <FONT COLOR = Blue>as your username.<BR> Is this correct?</FONT></HTML>");
int confirm =JOptionPane.showConfirmDialog(null,optionLabel);
switch(confirm){	   // Switch > Case
	   	case JOptionPane.YES_OPTION:  // Attempt to Login user
	   		
	   		JOptionPane.showMessageDialog(this, "login successful");
	   		if(username.equals(usern) && (password == passw)) {
	   			JOptionPane.showMessageDialog(this, "login successful");
	   	        jButton1.setEnabled(false);   // Set button enable to false to prevent 2 login attempts
	   		}
	   	break;

			case JOptionPane.NO_OPTION:   // No Case.(Go back. Set text to 0)
	   		jButton1.setEnabled(false);
	   		jTextField1.setText("");
	   		jPasswordField1.setText("");
	   		jButton1.setEnabled(true);
			break;
	   		
	   		case JOptionPane.CANCEL_OPTION:  // Cancel Case.(Go back. Set text to 0)
	   		jButton1.setEnabled(false);
	   		jTextField1.setText("");
	   		jPasswordField1.setText("");
	   		jButton1.setEnabled(true);
	   		break;
	   	
	   	}   // End Switch > Case
	   	   
	  
	   }
	   }
		


	



	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		new login();
	};



}
