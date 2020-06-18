package mrp_2;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 535, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Work Data");
		btnNewButton.setFont(new Font("メイリオ", Font.BOLD, 18));
		btnNewButton.setBounds(5, 60, 510, 50);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		       constructBOM.printWorkdata();
		    }
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Parts in BOM");
		btnNewButton_1.setFont(new Font("メイリオ", Font.BOLD, 18));
		btnNewButton_1.setBounds(345, 5, 170, 50);
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		      constructBOM.printBOMStructure();
		    }
		});
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.setFont(new Font("メイリオ", Font.BOLD, 18));
		btnNewButton_2.setBounds(5, 225, 510, 50);
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	System.exit(0);
		    }
		});
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Order");
		btnNewButton_3.setFont(new Font("メイリオ", Font.BOLD, 18));
		btnNewButton_3.setBounds(5, 115, 510, 50);
		btnNewButton_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	constructBOM.printOrderData();
		    }
		});
		contentPane.add(btnNewButton_3);

		textField = new JTextField();
		textField.setBounds(5, 15, 323, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_4 = new JButton("MRP");
		btnNewButton_4.setFont(new Font("メイリオ", Font.BOLD, 18));
		btnNewButton_4.setBounds(5, 170, 510, 50);
		btnNewButton_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	ReadWriteData.main(null);
		    	}
		});
		contentPane.add(btnNewButton_4);
	}
}
