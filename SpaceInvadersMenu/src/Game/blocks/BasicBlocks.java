package Game.blocks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
/**
 * This class creates, updates and draw the game bunkers
 * @author InvadersTeam
 */
public class BasicBlocks {

    private ArrayList<Rectangle> wall = new ArrayList<Rectangle>();

    /**
     *  Creates four bunkers in a predetermined position.
     */    
    
    public BasicBlocks() {
        //Crea los 4 bunkers del juego
        Bunker(75, 450);
        Bunker(275, 450);
        Bunker(475, 450);
        Bunker(675, 450);
    }
    
     /**
     *
     * @return Arraylist where the bunkers are loaded
     */
    
    public ArrayList<Rectangle> getWall() {
        return wall;
    }
    
    /**
     * Draws Paints a bunker green.
     * @param g Renders colour.
     */
    
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN);
        for (int i = 0; i < wall.size(); i++) {
            g.fill(wall.get(i));
        }
    }
    
    /**
     * Creates a block in an X,Y specified coordinates
     * 
     * @param xPos X Position where the block is going to be created.
     * @param yPos Y Position where the block is going to be created.
     */
    
    public void Bunker(int xPos, int yPos) {

        int wallWidth = 3;  //abertura de los bunkers
        int x = 0;
        int y = 0;

        for (int i = 0; i < 13; i++) { // construccion hacia abajo
            if ((14 + (i * 2) + wallWidth < 22 + wallWidth)) { //14 + (i*2) = forma la punta superior, <22 + wallWidth cantidad de barras del bloque superior
                row(14 + (i * 2) + wallWidth, xPos - (i * 3), yPos + (i * 3)); //ancho bloque/punta superior,posicion punta superior "x" y "y"  // en cada vuelta tiene q aumentar tamaño del bloque,reducir su x y aumentar y
                x = (i * 3) + 3;  // para que el "bloque del bunker" este a la misma "x" que la punta
            } else {
                row(22 + wallWidth, xPos - x, yPos + (i * 3)); //Luego de hacer la punta superior, hace el "bloque del bunker"
                y = (i * 3); //para acomodar la "y" de la "parte de abajo de los bunkers" correctamente ya q estan en un for distinto
            }
        }
        //Parte de abajo de los bunkers//

        //Lado izquierdo de cada bloque
        for (int i = 0; i < 5; i++) {
            row(8 + wallWidth - i, xPos - x, (yPos + y) + (i * 3));
        }

        //Lado derecho de cada bloque
        for (int i = 0; i < 5; i++) {
            row(8 + wallWidth - i, (xPos - x + (14 * 3)) + (i * 3), (yPos + y) + (i * 3));
        }
    }

        /**
     * Sets each brick width
     * @param rows Number of blocks for each bunker
     * @param xPos Specified X coordinate where the blocks are going to be created
     * @param yPos Specified Y coordinate where the blocks are going to be created
     */
    
    public void row(int rows, int xPos, int yPos) { //ancho bloque, posicion "x" y posicion "y" 
        for (int i = 0; i < rows; i++) {
            Rectangle brick = new Rectangle(xPos + (i * 3), yPos, 3, 3);
            wall.add(brick); //mete todo lo construido en Blocks adentro de un arreglo
        }
    }

     /**
     *  Creates the four bunkers again.
     */
    
    public void reset() {
        wall.clear();

        Bunker(75, 450);
        Bunker(275, 450);
        Bunker(475, 450);
        Bunker(675, 450);

    }

}//fin de la clase
