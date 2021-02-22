package application;

import backend.Coordinate;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FXBoard 
{
    VBox board;
        
    public FXBoard()
    {
        board = new VBox();
        for(int i = 0; i < 10; ++i)
        {
            HBox row = new HBox();
            for(int j = 0; j < 10; ++j)
            {
                Rectangle r = new FXCell();
                row.getChildren().add(r);
            }
            board.getChildren().add(row);
        }
    }
    
    public VBox getBoard()
    {
        return board;
    }
    
    public void update(Coordinate c, int outcome)
    {
        int x = c.getX(), y = c.getY();
        Node temp = board.getChildren().get(y);
        temp = ((HBox)temp).getChildren().get(x);
        
        if(outcome == 0) ((FXCell)temp).changeColor(false);
        else ((FXCell)temp).changeColor(true);
        // Casting is necessary because getChildren doesn't know about 
        // the type of its children, and returns the base class (Node)
    }
    
    private class FXCell extends Rectangle
    {

        public FXCell()
        {
            super(25, 25);
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
        }

        public FXCell(double arg0, double arg1)
        {
            super(arg0, arg1);
            setFill(Color.LIGHTGRAY);
            setStroke(Color.BLACK);
        }
        
        public void changeColor(boolean b)
        {
            // b == true means the cell contains a ship
            if(b) setFill(Color.RED);
            else setFill(Color.AQUA);
        }

    }

}