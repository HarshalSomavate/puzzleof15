import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;

public class PuzzleOf15 extends JFrame {
    private JButton[] tiles;
    private JButton newGameButton;
    private JPanel gamePanel;
    private int emptyTileIndex;
    private int[] board;
    
    public PuzzleOf15() {
        super("Puzzle of 15");
        
        // Set up the game board
        gamePanel = new JPanel(new GridLayout(4, 4));
        tiles = new JButton[16];
        board = new int[16];
        for (int i = 0; i < 16; i++) {
            tiles[i] = new JButton(String.valueOf(i+1));
            tiles[i].addActionListener(new MoveListener(i));
            gamePanel.add(tiles[i]);
            board[i] = i+1; 
        }
        emptyTileIndex = 15;
        tiles[emptyTileIndex].setVisible(false);
        
        // Set up the new game button
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameListener());
        
        // Add components to the content pane
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(gamePanel, BorderLayout.CENTER);
        c.add(newGameButton, BorderLayout.SOUTH);
        
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void shuffleBoard() {
        // Shuffle the board by making 100 random moves
        for (int i = 5; i < 100; i++) {
            int randomIndex = (int)(Math.random() * 15);
            swapTiles(randomIndex);
        }
    }
    
    private void swapTiles(int index) {
        int row = index / 4;
        int col = index % 4;
        int emptyRow = emptyTileIndex / 4;
        int emptyCol = emptyTileIndex % 4;
        if ((row == emptyRow && Math.abs(col - emptyCol) == 1) || (col == emptyCol && Math.abs(row - emptyRow) == 1)) {
            // Swap the tiles
            int temp = board[index];
            board[index] = board[emptyTileIndex];
            board[emptyTileIndex] = temp;
            // Update the GUI
            tiles[index].setText(String.valueOf(board[index]));
            tiles[emptyTileIndex].setText(String.valueOf(temp));
            tiles[emptyTileIndex].setVisible(true);
            tiles[index].setVisible(false);
            // Update the empty tile index
            emptyTileIndex = index;
        }

        //check if the player has won

        if(isWin()){

            JOptionPane.showMessageDialog(this, "Success is delivered to the deserving.\n Congratulations ! You Win");
        }
    }

    private boolean isWin(){

        for(int i=0; i<15; i++){
         
         if (board[i] != i+1){
          
          return false;

         }

        }

        return true;
    }
    
    private class MoveListener implements ActionListener {
        private int index;
        public MoveListener(int index) {
            this.index = index;
        }
        public void actionPerformed(ActionEvent e) {
            swapTiles(index);
        }
    }
    
    private class NewGameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            shuffleBoard();
            // Update the GUI
            for (int i = 0; i < 16; i++) {
                tiles[i].setText(String.valueOf(board[i]));
                if (i == emptyTileIndex) {
                    tiles[i].setVisible(false);
                } else {
                    tiles[i].setVisible(true);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        PuzzleOf15 game = new PuzzleOf15();
        game.shuffleBoard();
        // Update the GUI
        for (int i = 0; i < 16; i++) {
            game.tiles[i].setText(String.valueOf(game.board[i]));
            if (i == game.emptyTileIndex) {
        game.tiles[i].setVisible(false);
        } else {
            game.tiles[i].setVisible(true);
        }
    }
}
}

