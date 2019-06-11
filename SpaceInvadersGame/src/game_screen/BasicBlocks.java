
package game_screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;


public class BasicBlocks {
    
    private ArrayList<Rectangle> wall = new ArrayList<Rectangle>();
    
    public BasicBlocks(){
        //Crea los 4 bunkers del juego
		Blocks(75, 450);
		Blocks(275, 450);
		Blocks(475, 450);
		Blocks(675, 450);
	}
	
    public ArrayList<Rectangle> getWall(){
        return wall;
    }
    
	public void draw(Graphics2D g){
		g.setColor(Color.GREEN);
		for(int i = 0; i < wall.size(); i++){
			g.fill(wall.get(i));
		}
	}
	
	public void Blocks(int xPos, int yPos){
            
		int wallWidth = 3;  //abertura de los bunkers
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < 13; i++){ // construccion hacia abajo
			if((14 + (i * 2) + wallWidth < 22 + wallWidth)){ //14 + (i*2) = forma la punta superior, <22 + wallWidth cantidad de barras del bloque superior
				row(14 + (i * 2) + wallWidth, xPos - (i * 3), yPos + (i * 3)); //ancho bloque/punta superior,posicion punta superior "x" y "y"  // en cada vuelta tiene q aumentar tamaño del bloque,reducir su x y aumentar y
				x = (i * 3) + 3;  // para que el "bloque del bunker" este a la misma "x" que la punta
			}else{
				row(22 + wallWidth, xPos - x, yPos + (i * 3)); //Luego de hacer la punta superior, hace el "bloque del bunker"
				y = (i * 3); //para acomodar la "y" de la "parte de abajo de los bunkers" correctamente ya q estan en un for distinto
			}
		}
		//Parte de abajo de los bunkers//
                
		//Lado izquierdo de cada bloque
		for(int i = 0; i < 5; i++){
			row(8 + wallWidth - i, xPos - x, (yPos + y) + (i * 3));
		}
		
		//Lado derecho de cada bloque
		for(int i = 0; i < 5; i++){
			row(8 + wallWidth - i, (xPos - x + (14 * 3)) + (i * 3), (yPos + y) + (i * 3));
		}
	}
	
        
	public void row(int rows, int xPos, int yPos){ //ancho bloque, posicion "x" y posicion "y" 
		for(int i = 0; i < rows; i++){
			Rectangle brick = new Rectangle(xPos + (i * 3), yPos, 3, 3);
			wall.add(brick); //mete todo lo construido en Blocks adentro de un arreglo
		}
	}
	
	public void reset(){
		wall.clear();
		
		Blocks(75, 450);
		Blocks(275, 450);
		Blocks(475, 450);
		Blocks(675, 450);
    
	}
            
}



