package mateodragao.components.personagem;

import java.util.Random;

import mateodragao.interfaces.IMovimento;
import mateodragao.interfaces.IPersonagem;
import mateodragao.interfaces.ITabuleiro;

public class Personagem implements IPersonagem {
	public static int custo;
	protected int x, y, vida, frequencia, movimento, status;
	protected Random alea=new Random();
	protected int upperbound=2;
	
	public Personagem(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int[] move(ITabuleiro tab) {
		int newPosition[] = new int[2];
		//parte do codigo q vai dar a nova posição
		//sobre essa parte falta analisar algumas condicoes especificas de movimento
		if (status == 0) {
			int newX = x;
			int newY = y;
			while (newX == x && newY ==y) {
				int addX = alea.nextInt(upperbound)-1;
				int addY = alea.nextInt(upperbound)-1;
				newX += addX;
				newY += addY;
			}
			x = newX;
			y = newY;
			newPosition[0] = x;
			newPosition[1] = y;
		}
		else
			newPosition = null;
		status = (status + 1)%movimento;
		
		return newPosition;
	}

	@Override
	public void perdeVida(IMovimento ataque) {
		//pegar dano do ataque e subtrair de vida
		
	}

	@Override
	public void disparaAtaque(ITabuleiro tab) {
		//esse daqui talvez seja mais complicado de fazer
		
	}

	
}
