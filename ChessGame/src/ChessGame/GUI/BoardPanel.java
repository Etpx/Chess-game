package ChessGame.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import ChessGame.Logic.Game;
import ChessGame.Logic.Piece;

public class BoardPanel extends JPanel {

	// Declaring an object of 'Game' class in order to use its functions.
	private final Game Game;

    // Label for showing the game state.
	private JLabel lblGameState;

	// Declaring the center point of the pieces.
	private static final int BOARD_START_X = 45;
	private static final int BOARD_START_Y = 120;

	// Declaring tiles size.
    private static final int SQUARE_WIDTH = 90;
    private static final int SQUARE_HEIGHT = 90;

    // Declaring pieces size.
    private static final int PIECE_WIDTH = 60;
    private static final int PIECE_HEIGHT = 60;

	// Declaring variables for Converting methods.
    private static final int PIECES_START_X = BOARD_START_X + (int)(SQUARE_WIDTH/2.0 - PIECE_WIDTH/2.0);
    private static final int PIECES_START_Y = BOARD_START_Y + (int)(SQUARE_HEIGHT/2.0 - PIECE_HEIGHT/2.0);
    private static final int DRAG_TARGET_SQUARE_START_X = BOARD_START_X - (int)(PIECE_WIDTH/2.0);
    private static final int DRAG_TARGET_SQUARE_START_Y = BOARD_START_Y - (int)(PIECE_HEIGHT/2.0);

	// ArrayList to store all the pieces.
	private List<GuiPiece> guiPieces = new ArrayList<>();


	// Constructor
	public BoardPanel() {

	    // Create the chess game
        this.Game = new Game();

		// Assigning the listeners to the objects.
		PiecesListener handler = new PiecesListener(this.guiPieces, this);
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);

		// We used "this" because we want to assign the listeners to the same object that has been created.

        // put the game pieces into their graphical place.
        for (Piece piece : this.Game.getPieces()) {
            createGuiPiece(piece);
        }

		// Label to display the game state.
		String labelText = this.getGameStateAsText();
		this.lblGameState = new JLabel(labelText);
		lblGameState.setBounds(0, 30, 80, 30); // sets the size.
		lblGameState.setForeground(Color.WHITE); // sets the color of the foreground.
		this.add(lblGameState); // add the label to the panel.


	}

	// this method is for creating the Pieces
	private void createGuiPiece(Piece piece) {

		Image img = this.ImageForPiece(piece.getColor(), piece.getType()); // Assigning the Piece with the proper image.
		GuiPiece  guiPiece = new GuiPiece(img, piece); // new GuiPiece Object
		this.guiPieces.add(guiPiece); // Add the Object to the ArrayList
	}

	// override paintComponents used to draw graphics on the screen.
	public void paintComponent(Graphics g) {


		super.paintComponent(g);
//		this.setBackground(new Color(38,252,192)); // custom light green color
		this.setBackground(Color.gray);

		// Border of the board
		g.setColor(new Color(150, 101, 41)); // outerBorder with custom light brown color
		g.fillRoundRect(70, 70, 820, 820, 40, 40);

		g.setColor(new Color(127, 80, 44)); // innerrBorder with custom dark brown color
		g.fillRect(110, 110, 740, 740);


		// file and rank labels
		g.setColor(new Color(234, 207, 174));
		g.setFont(new Font("Serif", Font.BOLD, 12));

		g.drawString("a",162, 100); //up files labels
		g.drawString("b",252, 100);
		g.drawString("c",342, 100);
		g.drawString("d",432, 100);
		g.drawString("e",522, 100);
		g.drawString("f",612, 100);
		g.drawString("g",702, 100);
		g.drawString("h",792, 100);
		
		
		g.drawString("8", 90, 165); // left rank labels
		g.drawString("7", 90, 260);
		g.drawString("6", 90, 350);
		g.drawString("5", 90, 435);
		g.drawString("4", 90, 525);
		g.drawString("3", 90, 615);
		g.drawString("2", 90, 705);
		g.drawString("1", 90, 800);


		g.drawString("8", 860, 165); // right rank labels
		g.drawString("7", 860, 260);
		g.drawString("6", 860, 350);
		g.drawString("5", 860, 435);
		g.drawString("4", 860, 525);
		g.drawString("3", 860, 615);
		g.drawString("2", 860, 705);
		g.drawString("1", 860, 800);


		
		g.drawString("a",162, 867); //down files labels
		g.drawString("b",252, 867);
		g.drawString("c",342, 867);
		g.drawString("d",432, 867);
		g.drawString("e",522, 867);
		g.drawString("f",612, 867);
		g.drawString("g",702, 867);
		g.drawString("h",792, 867);


		// the board
		int row;
		int col;
		int x;
		int y;

		for (row = 0; row < 8; row++) // 8 rows
		{
			for (col = 0; col < 8; col++) // 8 columns
			{
				// the size of the square
				x = col * 90;
				y = row * 90;

				if ((row % 2) == (col % 2)) {
					g.setColor(Color.WHITE);  // for light squares
				}
				else {
					g.setColor(Color.black); // for dark squares
				}

				g.fillRect(x + 120, y + 120, 90, 90); // +120 for setting the board in the center

			}
		}

		// for each loop to paint the pieces into their right place.

		for (GuiPiece guiPiece : this.guiPieces) {
			if( !guiPiece.isCaptured()){
				g.drawImage(guiPiece.getImage(), guiPiece.getX(), guiPiece.getY(), null);
			}
		}

		this.lblGameState.setText(this.getGameStateAsText());
	}


	// this method sets the Pieces images with its proper piece.
	private Image ImageForPiece(String color, String type) {

		String filename = "";

		filename += (color.equals(Piece.COLOR_WHITE) ? "w" : "b"); // if color is white add "w" to the filename else add "b".

		// The idea of this switch is to define each type then add the proper letter of it to get the name of the image.
		switch (type) {

			case Piece.TYPE_BISHOP:
				filename += "b"; // if the type is BISHOP add "b". for ex, if the color is white the name will be "wb".
				break;
			case Piece.TYPE_KING:
				filename += "k";
				break;
			case Piece.TYPE_KNIGHT:
				filename += "n";
				break;
			case Piece.TYPE_PAWN:
				filename += "p";
				break;
			case Piece.TYPE_QUEEN:
				filename += "q";
				break;
			case Piece.TYPE_ROOK:
				filename += "r";
				break;
		}

		filename += ".gif"; // add the file format.
		URL PieceImg = getClass().getResource("/ChessGame/GUI/Icons/" + filename); // gets the image location.
		return new ImageIcon(PieceImg).getImage(); // create a new object and get the image of it.

	}


    // Converts the state as text.
	private String getGameStateAsText() {
		return (this.Game.getGameState() == Game.GAME_STATE_BLACK ? "Black Turn" : "White Turn");
	}

	// returns the current game state from the 'Game' class.
	public int getGameState(){
		return this.Game.getGameState();
	}

	// convert logical column to X coordinate.
    public static int convertColumnToX(int column){
        return PIECES_START_X + SQUARE_WIDTH * column;
    }


    // convert logical row into y coordinate.
    public static int convertRowToY(int row){
        return PIECES_START_Y + SQUARE_HEIGHT * (Piece.ROW_8 - row);
    }


    // convert x coordinate into logical column.
    public static int convertXToColumn(int x){
        return (x - DRAG_TARGET_SQUARE_START_X)/SQUARE_WIDTH;
    }


    // convert y coordinate into logical row.
    public static int convertYToRow(int y){
        return Piece.ROW_8 - (y - DRAG_TARGET_SQUARE_START_Y)/SQUARE_HEIGHT;
    }

    /*
     * change location of given piece, if the location is valid.
     * If the location is not valid, move the piece back to its original position.
     */
    public void setNewPieceLocation(GuiPiece dragPiece, int x, int y) {

    	int targetRow = convertYToRow(y);
        int targetColumn = convertXToColumn(x);

        if( targetRow < Piece.ROW_1 || targetRow > Piece.ROW_8 || targetColumn < Piece.COLUMN_A
				|| targetColumn > Piece.COLUMN_H){

        	// reset piece position if move is not valid
            dragPiece.resetPiecePosition();

        } else {
	        	// Print the change to the console.
				System.out.println("Moving " + dragPiece.getPiece().getColor() + " " + dragPiece.getPiece().getType() +
						" From " + dragPiece.getPiece().getRow() +
						dragPiece.getPiece().getColumnString(dragPiece.getPiece().getColumn()) + " To " + targetRow +
						dragPiece.getPiece().getColumnString(targetColumn));

				// Print the change to .
				SidePanel.movementsRecordArea.append("     Moving " + dragPiece.getPiece().getColor() + " " + dragPiece.getPiece().getType() +
						" From " + dragPiece.getPiece().getRow() +
						Piece.getColumnString(dragPiece.getPiece().getColumn()) + " To " + targetRow +
						Piece.getColumnString(targetColumn)+"\n");

				// Move the piece.
				this.Game.movePiece(
						dragPiece.getPiece().getRow(), dragPiece.getPiece().getColumn() // Source row and column.
						, targetRow, targetColumn); // Destination row and column.

			dragPiece.resetPiecePosition();
        }
    }
}