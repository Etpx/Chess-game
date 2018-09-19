package ChessGame.GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import ChessGame.Logic.Piece;
import ChessGame.Logic.Game;

public class PiecesListener extends MouseAdapter {

    private List<GuiPiece> guiPieces;
    private BoardPanel boardPanel;

    private GuiPiece draggedPiece;
    private int dragOffsetX;
    private int dragOffsetY;

    // Constructor
    public PiecesListener(List<GuiPiece> pieces, BoardPanel boardPanel) {

        this.guiPieces = pieces;
        this.boardPanel = boardPanel;
    }


    @Override
    // if the mouse is pressed
    public void mousePressed(MouseEvent evt) {

        // Assigning the coordinates of the action point ( eg. mouse click ) to variables.
        int x = evt.getPoint().x;
        int y = evt.getPoint().y;

        // find out which piece to move. we check the list from top to bottom in reverse order.

        for (int i = this.guiPieces.size()-1; i >= 0; i--) {

            GuiPiece guiPiece = this.guiPieces.get(i);
            if(guiPiece.isCaptured()) continue;

            if (mouseOverPiece(guiPiece,x,y)) {

                // if the game state is white and the color of the moved piece is white move it, else move the black.
                if ( (this.boardPanel.getGameState() == Game.GAME_STATE_WHITE
                        && guiPiece.getColor().equals(Piece.COLOR_WHITE))
                        || (this.boardPanel.getGameState() == Game.GAME_STATE_BLACK
                        && guiPiece.getColor().equals(Piece.COLOR_BLACK)) ) {

                    // calculate offset, because we do not want the drag piece
                    // to jump with it's upper left corner to the current mouse position.

                    this.dragOffsetX = x - guiPiece.getX();
                    this.dragOffsetY = y - guiPiece.getY();
                    this.draggedPiece = guiPiece;
                    break;
                }
            }
        }

        // move dragged piece to the top of the ArrayList.

        if(this.draggedPiece != null){
            this.guiPieces.remove( this.draggedPiece);
            this.guiPieces.add(this.draggedPiece);
        }
    }


    // check whether the mouse is currently over this piece

    private boolean mouseOverPiece(GuiPiece piece, int x, int y) {

        return piece.getX() <= x && piece.getX() + piece.getWidth() >= x
                && piece.getY() <= y && piece.getY() + piece.getHeight() >= y;
    }

    @Override
    // if the mouse is released make the draggedPiece null.
    public void mouseReleased(MouseEvent evt) {
       if (this.draggedPiece != null){

           int x = evt.getPoint().x - this.dragOffsetX; // sets the X coordinates
           int y = evt.getPoint().y - this.dragOffsetY; // sets the Y coordinates

           // set game piece to the new location if possible.
           boardPanel.setNewPieceLocation(this.draggedPiece, x, y); // sets the new location of the piece.
           this.boardPanel.repaint(); // repaint the board panel.
           this.draggedPiece = null; // sets the dragged Piece as null.
       }
    }


    // if the mouse is dragged set the new coordinates and repaint the board. ( make the piece move visually).
    public void mouseDragged(MouseEvent evt) {

        if(this.draggedPiece != null) {
            this.draggedPiece.setX(evt.getPoint().x - this.dragOffsetX);
            this.draggedPiece.setY(evt.getPoint().y - this.dragOffsetY);
            this.boardPanel.repaint();
        }

    }

}
