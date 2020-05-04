import javax.swing.*;
import java.awt.*;

public class StartNewGameWindow extends JFrame {
    private static final int WIN_HEIGHT =450;
    private static final int WIN_WIDTH = 400;
    private static final int WIN_POS_X = 650;
    private static final int WIN_POS_Y = 450;
    private static final int MIN_FIELD_SIZE =3;
    private static final int MAX_FIELD_SIZE =10;
    private static final int MIN_WIN_LENGTH =3;


    private JRadioButton jrbHumVsHum;
    private JRadioButton jrbHumVsAi;
    private ButtonGroup gameMode;
    private JSlider jsFiledSize;
    private JSlider jsWinLength;

    private GameWindow gameWindow;

    public StartNewGameWindow(GameWindow gameWindow)  {
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);

        setTitle("TicTacToe");
        setResizable(false);
        setVisible(false);
        setLayout(new GridLayout(8,1 ));

        this.gameWindow = gameWindow;
        add(new JLabel("Choose game mode"));

        jrbHumVsAi = new JRadioButton("Hum vs Ai", true);
        jrbHumVsHum = new JRadioButton("Hum vs Hum");
        gameMode = new ButtonGroup();
        gameMode.add(jrbHumVsAi);
        gameMode.add(jrbHumVsHum);
        add(jrbHumVsHum );
        add(jrbHumVsAi);

        add(new JLabel("Choose filed size"));
        jsFiledSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        add(jsFiledSize);
        jsFiledSize.setMajorTickSpacing(1);
        jsFiledSize.setPaintLabels(true);
        jsFiledSize.setPaintTicks(true);
        jsFiledSize.addChangeListener(e->{
            int filedSize = jsFiledSize.getValue();
            jsWinLength.setMaximum(filedSize);

        });


        add(new JLabel("Choose winning length"));
        jsWinLength = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_WIN_LENGTH);
        add(jsWinLength);
        jsWinLength.setMajorTickSpacing(1);
        jsWinLength.setPaintLabels(true);
        jsWinLength.setPaintTicks(true);

        JButton btnStartGame = new JButton("Start new game");
        add(btnStartGame);
        btnStartGame.addActionListener(e->{
                btnStartGameClick();
        });



    }
    private void btnStartGameClick(){
        setVisible(false);
        int gameMode;
        if (jrbHumVsAi.isSelected()){
         gameMode = BattleMap.MODE_H_V_A;

        } else{
            gameMode = BattleMap.MODE_H_V_H;

        }
        int filedSize = jsFiledSize.getValue();
        int winLength = jsWinLength.getValue();

        Logic.SIZE = filedSize;
        Logic.DOTS_TO_WIN = winLength;
        Logic.initMap();
        Logic.printMap();
        Logic.gameFinished = false;

        gameWindow.startNewGame(gameMode, filedSize, filedSize, winLength);
    };
}
