import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Memanggil ClassTicTacToe untuk memulai permainan
            ClassTicTacToe game = new ClassTicTacToe();
            game.setVisible(true);
        });
    }
}
