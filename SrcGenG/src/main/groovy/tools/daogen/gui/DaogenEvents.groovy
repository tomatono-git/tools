/**
 *
 */
package tools.daogen.gui

import groovy.util.logging.Log4j

import java.awt.event.MouseAdapter

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JTextArea
import javax.swing.JTextField

import tools.daogen.generator.GeneratorFactory
import tools.daogen.generator.GeneratorFactory.GeneratorType
import tools.daogen.generator.GeneratorFactory.LoaderType


/**
 *
 *
 */
@Log4j
class DaogenEvents {

	public DaogenEvents(JFrame frame) {
		this.frame = frame
	}

	private JFrame frame

	def addGenerateButtonClickEvent(JButton generateButton, JTextField queryNameTextField, JTextArea sqlTextArea) {

		generateButton.addMouseListener([mouseClicked : {
				try {
					if (!queryNameTextField.text) {
						JOptionPane.showMessageDialog(frame, "�N�G��������͂��Ă��������B");
						return
					}
					if (!sqlTextArea.text) {
						JOptionPane.showMessageDialog(frame, "SQL����͂��Ă��������B");
						return
					}

					// TODO ����
					def facotry = new GeneratorFactory();
					def generator = facotry.create(GeneratorType.QueryDao, LoaderType.OracleQuery)
					def name = queryNameTextField.text
					def query = sqlTextArea.text
					generator.generate(name : name, query : query);

					JOptionPane.showMessageDialog(frame, "��������");

				} catch (Exception e) {
					log.error(e.message, e)
					JOptionPane.showMessageDialog(frame, "�������s");
				}
			}] as MouseAdapter)
	}
}
