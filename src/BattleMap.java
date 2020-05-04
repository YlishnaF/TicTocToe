import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleMap extends JPanel {
    private GameWindow gameWindow;
    public static final int MODE_H_V_A=0;
    public static final int MODE_H_V_H=1;

    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;

    private int cellHeight;
    private int cellWidth;

    private boolean isInit = false;




    public BattleMap(GameWindow gameWindow) {
       setBackground(new Color(212, 236, 249));
       this.gameWindow=gameWindow;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
    }
    private void update(MouseEvent e){
        int cellX = e.getX()/cellWidth;
        int cellY = e.getY() / cellHeight;

        if(!Logic.gameFinished){
            Logic.setHumanXY(cellX,cellY);
        }


        System.out.println(cellX+ ""+cellY);
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    private void render(Graphics g){
        if(!isInit){
            return;
        }
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        cellHeight = panelHeight/fieldSizeY;
        cellWidth = panelWidth/fieldSizeX;
        for (int i = 0; i < fieldSizeX; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth,y);

        }
        for (int i = 0; i < fieldSizeY; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x,panelHeight);

        }
        for (int i = 0; i < Logic.SIZE; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if(Logic.map[i][j] ==  Logic.DOT_O){
                    drawO(g,j,i);
                }
                if(Logic.map[i][j] == Logic.DOT_X){
                    drawX(g,j,i);
                }

            }

        }
        

    }

    private void drawO(Graphics g, int cellX, int cellY) {
        g.setColor(new Color(0,0,255));
        g.drawOval(cellX*cellWidth, cellY*cellHeight, cellWidth,cellHeight);

    }
    private void drawX(Graphics g, int cellX, int cellY) {
        g.setColor(new Color(255, 20, 8));
        g.drawLine(cellX*cellWidth, cellY*cellHeight, (cellX+1)*cellWidth,(cellY+1)*cellHeight);
        g.drawLine((cellX+1)*cellWidth, cellY*cellHeight, cellX*cellWidth,(cellY+1)*cellHeight);

    }

    void startNewGame(int gameMode, int filedSizeX, int filedSizeY, int winLength){
        System.out.println(gameMode+ ""+ filedSizeX+""+ filedSizeY+""+winLength+"");
        this.fieldSizeX = filedSizeX;
        this.fieldSizeY=filedSizeY;
        this.winLength=winLength;
        isInit=true;
        repaint();
    }
}
