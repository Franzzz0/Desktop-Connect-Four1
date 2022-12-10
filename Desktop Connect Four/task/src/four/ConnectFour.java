package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    private final Grid grid;

    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(525, 450);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new BorderLayout());
        grid = new Grid();

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.addActionListener(e -> this.grid.reset());
        bottomPanel.add(resetButton);

        this.add(grid, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Connect Four");
    }
}