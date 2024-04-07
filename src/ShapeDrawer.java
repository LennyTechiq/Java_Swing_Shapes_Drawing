import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeDrawer {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Shape Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center the frame

        // Create components
        ShapePanel shapePanel = new ShapePanel(400, 400);
        JComboBox<String> shapeComboBox = new JComboBox<>(new String[]{"Circle", "Square", "Triangle", "Rectangle", "Sphere", "Cube", "Cone", "Cylinder", "Torus"});
        JComboBox<Integer> sizeComboBox = new JComboBox<>(new Integer[]{50, 100, 150, 200});

        // Add action listeners to components
        shapeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setSelectedShape((String) shapeComboBox.getSelectedItem());
            }
        });

        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapePanel.setSize((int) sizeComboBox.getSelectedItem());
            }
        });

        // Create control panel with custom styling
        JPanel controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel shapeLabel = new JLabel("Select Shape:");
        shapeLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Custom font
        controlPanel.add(shapeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        controlPanel.add(shapeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel sizeLabel = new JLabel("Select Size:");
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Custom font
        controlPanel.add(sizeLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        controlPanel.add(sizeComboBox, gbc);

        // Add components to the frame
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(shapePanel), BorderLayout.CENTER);

        // Set frame visibility
        frame.setVisible(true);
    }
}
