package service;

import data.GameData;
import game_structure.Cell;
import game_structure.Direction;
import game_structure.Player;
import game_structure.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fill {

    public static int[][] getPlayerTable(GameData data, Player player, int row, int col){
        int[][] arr = new int[row][col];

        for (Ship s:data.getPlayerShips().getOrDefault(player, new ArrayList<>())) {
            for (Cell c:s.getDamaged()) {
                Point p = Auxiliary.getPointByCell(data, player, c, row, col);
                if(p == null)
                    continue;
                arr[p.x][p.y] = 2;
            }
            for (Cell c:s.getCells()) {
                Point p = Auxiliary.getPointByCell(data, player, c, row, col);
                if(p == null)
                    continue;
                arr[p.x][p.y] = 1;
            }

        }

        for (Cell c:data.getCantBeat().getOrDefault(player, new ArrayList<>())) {
            Point p = Auxiliary.getPointByCell(data, player, c, row, col);
            if(p == null)
                continue;
            arr[p.x][p.y] = 3;
        }

        for (Cell c:data.getMines().getOrDefault(player, new ArrayList<>())){
            Point p = Auxiliary.getPointByCell(data, player, c, row, col);
            if(p == null)
                continue;
            arr[p.x][p.y] = 4;
        }

        return arr;
    }


    public static int[][] getEnemyTable(GameData data, Player player, int row, int col){
        int[][] arr = new int[row][col];

        for (Ship s:data.getPlayerShips().getOrDefault(player, new ArrayList<>())) {
            for (Cell c:s.getDamaged()) {
                Point p = Auxiliary.getPointByCell(data, player, c, row, col);
                if(p == null)
                    continue;
                arr[p.x][p.y] = 2;
            }
        }

        for (Cell c:data.getCantBeat().getOrDefault(player, new ArrayList<>())) {
            Point p = Auxiliary.getPointByCell(data, player, c, row, col);
            if(p == null)
                continue;
            arr[p.x][p.y] = 3;
        }

        return arr;
    }

    public static Cell createBoard(int count){
        List<List<Cell>> cells = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            cells.add(createHorisontalLine(count));
        }
        bindCells(cells);

        return cells.get(0).get(0);
    }
    public static List<Cell> createHorisontalLine(int count){
        List<Cell> cells = new ArrayList<>();

        cells.add(new Cell());

        for (int i = 1; i < count-1; i++) {
            Cell c = new Cell();
            c.add(Direction.LEFT,cells.get(i-1));
            cells.get(i-1).add(Direction.RIGHT,c);
            cells.add(c);
        }
        Cell c = new Cell();
        c.add( Direction.LEFT, cells.get(cells.size()-1));
        cells.get(cells.size()-1).add( Direction.RIGHT,c);
        cells.add(c);

        return cells;
    }

    public static void bindCells(List<List<Cell>> cells){
        for (int i = 1; i < cells.size(); i++) {
            for (int j = 0; j < cells.get(i).size(); j++) {
                Cell c1 = cells.get(i-1).get(j);
                Cell c2 = cells.get(i).get(j);
                c1.add(Direction.DOWN, c2);
                c2.add( Direction.UP,c1);
            }
        }
    }





}
