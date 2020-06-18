package mrp_2;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

public class Button extends JFrame {

	private JPanel contentPane;
	constructBOM cb = new constructBOM();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		constructBOM.make_structure();
		constructBOM.setPartsName();
		constructBOM.make_worklist();
		constructBOM.make_orderlist();
		constructBOM.setwork_order_ID();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Button frame = new Button();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Button() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Work Data");
		btnNewButton.setBounds(126, 37, 109, 48);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       constructBOM.printWorkdata();
		    }
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Parts in BOM");
		btnNewButton_1.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		btnNewButton_1.setBounds(345, 1, 163, 33);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      constructBOM.printBOMStructure();
		    }
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setBounds(5, 40, 112, 42);
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	System.out.println("Exit");
		    }
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Order");
		btnNewButton_3.setBounds(239, 37, 118, 48);
		btnNewButton_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	constructBOM.printOrderData();
		    }
		});
		contentPane.add(btnNewButton_3);
		
		textField = new JTextField();
		textField.setBounds(5, 5, 323, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("MRP");
		btnNewButton_4.setBounds(5, 105, 175, 48);
		btnNewButton_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ReadWriteData.main(null);
		    	}
		});
		contentPane.add(btnNewButton_4);
	}
}	
