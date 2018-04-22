import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

import java.awt.Color;

public class FxWithSwing extends JFrame {

	private JPanel contentPane;

	private JPanel panel;
	private final JFXPanel jfxPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FxWithSwing frame = new FxWithSwing();
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
	public FxWithSwing() {
		setTitle("Demo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 422, 251);

		jfxPanel = new JFXPanel();
		panel.add(jfxPanel, BorderLayout.CENTER);
		


		// Create the RootNode
		TreeItem<Person> rootNode = TreeTableUtil.getModel();
		rootNode.setExpanded(true);

		// Create a TreeTableView with a model
		TreeTableView<Person> treeTable = new TreeTableView<Person>(rootNode);
		treeTable.setPrefWidth(400);

		// Must make the TreeTableView editable
		treeTable.setEditable(true);

		// Create Columns with Cell Factories
		TreeTableColumn<Person, String> firstNameColumn = TreeTableUtil.getFirstNameColumn();
		firstNameColumn.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, String> lastNameColumn = TreeTableUtil.getLastNameColumn();
		lastNameColumn.setCellFactory(TextFieldTreeTableCell.<Person>forTreeTableColumn());

		TreeTableColumn<Person, LocalDate> birthDateColumn = TreeTableUtil.getBirthDateColumn();
		LocalDateStringConverter converter = new LocalDateStringConverter();

		birthDateColumn.setCellFactory(TextFieldTreeTableCell.<Person, LocalDate>forTreeTableColumn(converter));

		// Add Columns to the Tree
		treeTable.getColumns().add(firstNameColumn);
		treeTable.getColumns().add(lastNameColumn);
		treeTable.getColumns().add(birthDateColumn);
		
		VBox root = new VBox(treeTable);
		
		Scene scene = new Scene(root);
		jfxPanel.setScene(scene);
		
		contentPane.add(panel);

	}
	
	
}
