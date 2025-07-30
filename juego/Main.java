package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private final int size = 5;
    private final int tileSize = 100;
    private Tablero tablero;
    private GridPane gridPane;
    private Text resultadoText;

    @Override
    public void start(Stage primaryStage) {
        tablero = new Tablero(size);
        gridPane = new GridPane();
        resultadoText = new Text();

        dibujarTablero();

        Scene scene = new Scene(gridPane, size * tileSize, size * tileSize + 30);
        primaryStage.setTitle("Pirata y Tesoro");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
            boolean continuar = tablero.moverPirataUnPaso();
            dibujarTablero();

            String estado = tablero.verificarEstadoFinal();
            if (!estado.isEmpty()) {
                resultadoText.setText(estado);
                if (!gridPane.getChildren().contains(resultadoText)) {
                    gridPane.add(resultadoText, 0, size, size, 1);
                }
            }

            if (!continuar || !estado.isEmpty()) {
                ((Timeline) e.getSource()).stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void dibujarTablero() {
        gridPane.getChildren().clear();
        Casilla[][] grid = tablero.getGrid();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle rect = new Rectangle(tileSize, tileSize);

                switch (grid[i][j]) {
                    case AGUA -> rect.setFill(Color.DEEPSKYBLUE);
                    case TIERRA -> rect.setFill(Color.BEIGE);
                    case PUENTE -> rect.setFill(Color.SADDLEBROWN);
                    case TESORO -> rect.setFill(Color.GOLD);
                }

                // Dibujar pirata encima
                if (tablero.getPirataX() == i && tablero.getPirataY() == j) {
                    rect.setFill(Color.RED);
                }

                rect.setStroke(Color.BLACK);
                gridPane.add(rect, j, i);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

