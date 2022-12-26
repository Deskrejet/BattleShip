package bot;

import data.GameData;
import game_structure.Cell;
import game_structure.Player;
import game_structure.Ship;
import service.Auxiliary;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {
    public static Point getRndMove(JTable table, int row, int col){
        Point p = new Point(new Random().nextInt(row),new Random().nextInt(row));
        while (Integer.parseInt(table.getValueAt(p.x, p.y).toString()) == 2 || Integer.parseInt(table.getValueAt(p.x, p.y).toString()) == 3){
            p = new Point(new Random().nextInt(row),new Random().nextInt(row));
        }
        return p;
    }

}
