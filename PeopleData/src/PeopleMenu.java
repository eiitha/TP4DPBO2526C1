import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> GenreComboBox;
    private JComboBox<String> AwardComboBox;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable peopleTable;

    private ButtonGroup genderGroup;
    private ArrayList<Person> listPerson;
    private int selectedIndex = -1;

    public static void main(String[] args) {
        PeopleMenu menu = new PeopleMenu();
        menu.setSize(800, 650);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public PeopleMenu() {
        listPerson = new ArrayList<>();

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        maleRadioButton.setSelected(true);

        populateList();
        peopleTable.setModel(setTable());

        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        String[] genreData = { "--- Select Genre ---", "Action / Sci-Fi", "Drama / Biopic", "Comedy", "Horror / Thriller" };
        GenreComboBox.setModel(new DefaultComboBoxModel<>(genreData));

        String[] awardsData = { "--- Select Award Status ---", "Oscar Winner", "Oscar Nominee", "Golden Globe Winner", "Rising Star" };
        AwardComboBox.setModel(new DefaultComboBoxModel<>(awardsData));

        deleteButton.setVisible(false);

        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this actor?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        peopleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = peopleTable.getSelectedRow();

                String curId = peopleTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curName = peopleTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curYear = peopleTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curCategory = peopleTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curAward = peopleTable.getModel().getValueAt(selectedIndex, 5).toString();
                String curGender = peopleTable.getModel().getValueAt(selectedIndex, 6).toString();

                idField.setText(curId);
                namaField.setText(curName);
                tahunLahirField.setText(curYear);
                GenreComboBox.setSelectedItem(curCategory);
                AwardComboBox.setSelectedItem(curAward);

                if (curGender.equalsIgnoreCase("Male")) {
                    maleRadioButton.setSelected(true);
                } else {
                    femaleRadioButton.setSelected(true);
                }

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    public void populateList() {
        listPerson.add(new Person("ACT001", "Cillian Murphy", 1976, "Drama / Biopic", "Oscar Winner", "Male"));
        listPerson.add(new Person("ACT002", "Tom Holland", 1996, "Action / Sci-Fi", "Rising Star", "Male"));
        listPerson.add(new Person("ACT003", "Zendaya", 1996, "Drama / Biopic", "Golden Globe Winner", "Female"));
        listPerson.add(new Person("ACT004", "Keanu Reeves", 1964, "Action / Sci-Fi", "Rising Star", "Male"));
    }

    public DefaultTableModel setTable() {
        Object[] cols = { "No", "Actor ID", "Actor Name", "Birth Year", "Genre Specialty", "Award Status", "Gender" };
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {
                    (i + 1),
                    listPerson.get(i).getId(),
                    listPerson.get(i).getName(),
                    listPerson.get(i).getBirthYear(),
                    listPerson.get(i).getCategory(),
                    listPerson.get(i).getAwardStatus(),
                    listPerson.get(i).getGender()
            };
            tmp.addRow(row);
        }
        return tmp;
    }

    // Method Validasi Format ID & Duplikasi
    public boolean isIdValid(String id) {
        // Cek apakah ID diawali 'ACT' dan diikuti angka
        if (!id.matches("^ACT\\d+$")) {
            JOptionPane.showMessageDialog(null,
                    "Actor ID must start with 'ACT' followed by numbers! (e.g., ACT005)",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Cek duplikasi ID pada data yang sudah ada
        for (int i = 0; i < listPerson.size(); i++) {
            if (i != selectedIndex && listPerson.get(i).getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(null,
                        "Actor ID '" + id + "' already exists! Please use a unique ID.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }

    public void insertData() {
        try {
            String id = idField.getText().trim();
            String name = namaField.getText().trim();
            String yearStr = tahunLahirField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all text fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!isIdValid(id)) {
                return;
            }

            int birthYear = Integer.parseInt(yearStr);
            String category = GenreComboBox.getSelectedItem().toString();
            String awardStatus = AwardComboBox.getSelectedItem().toString();
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";

            listPerson.add(new Person(id, name, birthYear, category, awardStatus, gender));
            peopleTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Actor data successfully added!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Birth year must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateData() {
        try {
            String id = idField.getText().trim();
            String name = namaField.getText().trim();
            String yearStr = tahunLahirField.getText().trim();

            if (id.isEmpty() || name.isEmpty() || yearStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all text fields!", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!isIdValid(id)) {
                return;
            }

            int birthYear = Integer.parseInt(yearStr);
            String category = GenreComboBox.getSelectedItem().toString();
            String awardStatus = AwardComboBox.getSelectedItem().toString();
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";

            listPerson.get(selectedIndex).setId(id);
            listPerson.get(selectedIndex).setName(name);
            listPerson.get(selectedIndex).setBirthYear(birthYear);
            listPerson.get(selectedIndex).setCategory(category);
            listPerson.get(selectedIndex).setAwardStatus(awardStatus);
            listPerson.get(selectedIndex).setGender(gender);

            peopleTable.setModel(setTable());
            clearForm();
            JOptionPane.showMessageDialog(null, "Actor data successfully updated!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Birth year must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteData() {
        listPerson.remove(selectedIndex);
        peopleTable.setModel(setTable());
        clearForm();
        JOptionPane.showMessageDialog(null, "Actor data successfully deleted!");
    }

    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        GenreComboBox.setSelectedIndex(0);
        AwardComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(true);
        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}