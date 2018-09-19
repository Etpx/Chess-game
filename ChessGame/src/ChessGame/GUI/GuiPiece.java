package ChessGame.GUI;

import java.awt.Image;
import ChessGame.Logic.Piece;
// This class is used to represents the images for the pieces.

public class GuiPiece {

    private Image img; // image of the piece.
    private int x; // X coordinate of the piece
    private int y; // Y coordinate of the piece
    private Piece piece; // The logic piece.

    public GuiPiece(Image img, Piece piece) {
        this.img = img;
        this.piece = piece;

        this.resetPiecePosition();
    }

    // gets the image of the piece.
    public Image getImage() {
        return this.img;
    }

    // gets the X coordinate.
    public int getX() {
        return this.x;
    }

    // gets the Y coordinate.
    public int getY() {
        return this.y;
    }

    // sets the X coordinate.
    public void setX(int x) {
        this.x = x;
    }

    // sets the Y coordinate.
    public void setY(int y) {
        this.y = y;
    }

    // gets the width of the piece.
    public int getWidth() {
        return this.img.getHeight(null);
    }

    // gets the width of the piece.
    public int getHeight() {
        return this.img.getHeight(null);
    }

    // gets the color of the piece.
    public String getColor() {
        return this.piece.getColor();
    }

    @Override
    public String toString() {
        return this.piece + " " + x + "/" + y;
    }


     // move the gui piece back to the coordinates that correspond with the underlying piece's row and column.
    public void resetPiecePosition() {

        this.x = BoardPanel.convertColumnToX(piece.getColumn());
        this.y = BoardPanel.convertRowToY(piece.getRow());
    }

    // gets the Piece, all of the properties represented in the Piece class will be applied here (Object).
    public Piece getPiece() {
        return piece;
    }

    // if the piece is captured or not.
    public boolean isCaptured() {
        return this.piece.isCaptured();
    }

}