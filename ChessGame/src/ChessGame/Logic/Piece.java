package ChessGame.Logic;

// This class is used to create the logic design of the game.
public class Piece {

    // Declaring the color and type.
    private String color;
    private String type;

    // Declaring the colors of the Players to know which piece will take the current move.
    public static final String COLOR_WHITE = "WHITE";
    public static final String COLOR_BLACK = "BLACK";

    // Declaring the pieces types.
    public static final String TYPE_ROOK = "ROOK";
    public static final String TYPE_KNIGHT = "KNIGHT";
    public static final String TYPE_BISHOP = "BISHOP";
    public static final String TYPE_QUEEN = "QUEEN";
    public static final String TYPE_KING = "KING";
    public static final String TYPE_PAWN = "PAWN";

    // Chess is played on tiles 8 rows & 8 columns. So we declare them.
    private int row;
    private int column;

    // Declare rows.
    public static final int ROW_1 = 1;
    public static final int ROW_2 = 2;
    public static final int ROW_3 = 3;
    public static final int ROW_4 = 4;
    public static final int ROW_5 = 5;
    public static final int ROW_6 = 6;
    public static final int ROW_7 = 7;
    public static final int ROW_8 = 8;

    // Declare Columns.
    public static final int COLUMN_A = 1;
    public static final int COLUMN_B = 2;
    public static final int COLUMN_C = 3;
    public static final int COLUMN_D = 4;
    public static final int COLUMN_E = 5;
    public static final int COLUMN_F = 6;
    public static final int COLUMN_G = 7;
    public static final int COLUMN_H = 8;

    private boolean isCaptured = false;

    // Constructor
    public Piece(String color, String type, int row, int column) {

        this.row = row;
        this.column = column;
        this.color = color;
        this.type = type;
    }

    // gets the row of the piece.
    public int getRow() {
        return row;
    }

    // sets the row of the piece.
    public void setRow(int row) {
        this.row = row;
    }

    // gets the column of the piece.
    public int getColumn() {
        return column;
    }

    // sets the column of the piece.
    public void setColumn(int column) {
        this.column = column;
    }

    // gets the color of the piece.
    public String getColor() {
        return color;
    }

    // gets the type of the piece.
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {

        String strColor = ( this.color.equals(COLOR_WHITE) ? "White" : "Black"); // if the color is white add white.. etc.
        String strType = "Unknown";

        // based on the type add the proper letter to print it later.
        switch(this.type) {

            case TYPE_BISHOP:
                strType = "B"; break;
            case TYPE_KING:
                strType = "K"; break;
            case TYPE_KNIGHT:
                strType = "N"; break;
            case TYPE_PAWN:
                strType = "P"; break;
            case TYPE_QUEEN:
                strType = "Q"; break;
            case TYPE_ROOK:
                strType = "R"; break;
        }

        String strRow = getRowString(this.row);
        String strCol = getColumnString(this.column);

        return strColor + " " + strType + " " + strRow + "/" + strCol;

    }

    // Method to converts the rows to Strings.
    public static String getRowString(int row){
        String strRow = "Unknown";
        switch (row) {

            case ROW_1:
                strRow = "1"; break;
            case ROW_2:
                strRow = "2"; break;
            case ROW_3:
                strRow = "3"; break;
            case ROW_4:
                strRow = "4"; break;
            case ROW_5:
                strRow = "5"; break;
            case ROW_6:
                strRow = "6"; break;
            case ROW_7:
                strRow = "7"; break;
            case ROW_8:
                strRow = "8"; break;

        }
        return strRow;
    }

    // Method to converts the rows to Strings.
    public static String getColumnString(int column){

        String strColumn = "Unknown";
        switch (column) {
            case COLUMN_A:
                strColumn = "A"; break;
            case COLUMN_B:
                strColumn = "B"; break;
            case COLUMN_C:
                strColumn = "C"; break;
            case COLUMN_D:
                strColumn = "D"; break;
            case COLUMN_E:
                strColumn = "E"; break;
            case COLUMN_F:
                strColumn = "F"; break;
            case COLUMN_G:
                strColumn = "G"; break;
            case COLUMN_H:
                strColumn = "H"; break;
        }
        return strColumn;
    }

    // Method to save the state of capture to the variable.
    public void isCaptured(boolean isCaptured) {
        this.isCaptured = isCaptured;
    }

    // Method to declare weather a specific piece is captured or not.
    public boolean isCaptured() {
        return this.isCaptured;
    }

}