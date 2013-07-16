/**
 *
 */
package tools.daogen.gui.frame;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import tools.daogen.gui.DaogenEvents;

/**
 *
 *
 */
public class DaogenFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 3866567465314540717L;
	private JPanel contentPane;
	private JTextField queryNameTextField;
	private JTextArea sqlTextArea = new JTextArea();
	private JTextArea sourceTextArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DaogenFrame frame = new DaogenFrame();
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
	public DaogenFrame() {
		setTitle("Dao\u751F\u6210");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 642);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		sqlTextArea.setText("select * from pis_bookmark_tbl");

		sourceTextArea.setText("");

		DaogenEvents envets = new DaogenEvents(this);

		queryNameTextField = new JTextField();
		queryNameTextField.setText("QueryName");
		queryNameTextField.setColumns(10);

		JButton generateButton = new JButton("\u751F\u6210");
		envets.addGenerateButtonClickEvent(generateButton, queryNameTextField, sqlTextArea);

		JLabel queryNameLabel = new JLabel("\u30AF\u30A8\u30EA\u540D\uFF1A");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(sqlTextArea, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(queryNameLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(queryNameTextField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(413, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(generateButton)
							.addGap(302))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(sourceTextArea, GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(queryNameLabel)
						.addComponent(queryNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(sqlTextArea, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(generateButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sourceTextArea, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(229))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
