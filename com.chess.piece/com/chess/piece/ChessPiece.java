package com.chess.piece;

import javax.swing.text.Position;

public class ChessPiece {
    public abstract class Piece{
        protected PieceColor color;
        protected Position pos;

        public Piece(PieceColor color, Position position)
        {
            this.color = color;
            this.pos = position;
        }
        public PieceColor getColor()
        {
            return color;
        }
        public Position getPosition()
        {
            return pos;
        }
        public void setPosition(Position position)
        {
            this.pos = position;
        }
    }
    public enum PieceColor{
        black, white;
    }
    public class Position
    {
        private int row = 0;
        private int column = 0;

        public Position(int row, int column)
        {
            this.row = row;
            this.column = column;
        }
        public int getRow()
        {
            return row;
        }
        public int getColumn()
        {
            return column;
        }
    }
}

//TODO: add pieces

