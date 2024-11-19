import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassTicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9]; // Array untuk tombol permainan
    private char currentPlayer = 'X'; // Pemain saat ini
    private boolean gameOver = false; // Status permainan

    // Constructor untuk mengatur GUI
    public ClassTicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // Tata letak papan permainan

        // Membuat tombol-tombol dan menambahkannya ke papan
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    // Event handler untuk aksi tombol
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (!gameOver && buttonClicked.getText().equals("")) {
            buttonClicked.setText(String.valueOf(currentPlayer));
            buttonClicked.setForeground(currentPlayer == 'X' ? Color.RED : Color.BLUE);
            checkForWin();
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // Method untuk mengecek kondisi kemenangan atau seri
    private void checkForWin() {
        int[][] winConditions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] condition : winConditions) {
            if (buttons[condition[0]].getText().equals(buttons[condition[1]].getText()) &&
                    buttons[condition[1]].getText().equals(buttons[condition[2]].getText()) &&
                    !buttons[condition[0]].getText().equals("")) {
                gameOver = true;
                String winner = buttons[condition[0]].getText();
                showEndGameOptions("Player " + winner + " wins!");
                return;
            }
        }

        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            showEndGameOptions("It's a draw!");
        }
    }

    // Method untuk menampilkan dialog akhir permainan
    private void showEndGameOptions(String message) {
        int option = JOptionPane.showOptionDialog(
                this,
                message + "\nDo you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Play Again", "Exit"},
                "Play Again"
        );

        if (option == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0); // Mengakhiri program
        }
    }

    // Method untuk mengatur ulang permainan
    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
        }
        currentPlayer = 'X';
        gameOver = false;
    }
}
