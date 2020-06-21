package mateodragao.components.personagem;

import mateodragao.components.projetil.Flecha;
import mateodragao.interfaces.ITabuleiro;

import java.lang.Math;

public class Arqueiro extends Personagem{
	{custo=5;
	frequencia = 1;
	movimento = 1;
	passo = 2;}
	public Arqueiro(int x, int y) {
		super(x,y);
		vida = 1;
	}

	@Override
	public void disparaProjetil(ITabuleiro tab) {
		int hor,ver; //distância horizontal e vertical ao dragao
		//ver sobre enum
		//provavelmente vai ser um switch case abaixo
		//tem q ver tbm a questao do Dragon Position
		hor=(tab.getDragonPosition()[0])-x;
		ver=(tab.getDragonPosition()[1])-y;
		
		if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
			if (ver<0) {	//atira para cima
				if (Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y-1, 0, new Flecha(x, y-1, 0,"ci"));
				else {
					if (hor<0)
						tab.putProjetil(x-1, y-1, 0, new Flecha(x-1, y-1, 0,"cies"));
					if (hor>0)
						tab.putProjetil(x+1, y-1, 0, new Flecha(x+1, y-1, 0,"cidi"));
				}
			}if (ver>0) {	//atira para baixo
				if (Math.abs(hor/2)<=(Math.abs(ver)-Math.abs(hor))) 
					tab.putProjetil(x, y+1, 0, new Flecha(x, y+1, 0,"bx"));
				else {
					if (hor<0)
						tab.putProjetil(x-1, y+1, 0, new Flecha(x-1, y+1, 0,"bxes"));
					if (hor>0)
						tab.putProjetil(x+1, y+1, 0, new Flecha(x+1, y+1, 0,"bxdi"));
				}
			}
		}
		
		else {		//atira na horizontal
			if (hor<0) {	//atira para esquerda
				if (Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x-1, y, 0, new Flecha(x-1, y, 0,"es"));
				else {
					if (ver<0)
						tab.putProjetil(x-1, y-1, 0, new Flecha(x-1, y-1, 0,"cies"));
					if (ver>0)
						tab.putProjetil(x-1, y+1, 0, new Flecha(x-1, y+1, 0,"bxes"));
				}
			}if (hor>0) {	//atira para direita
				if (Math.abs(ver/2)<=(Math.abs(hor)-Math.abs(ver))) 
					tab.putProjetil(x+1, y, 0, new Flecha(x+1, y, 0,"di"));
				else {
					if (ver<0)
						tab.putProjetil(x+1, y-1, 0, new Flecha(x+1, y-1, 0,"cidi"));
					if (ver>0)
						tab.putProjetil(x+1, y+1, 0, new Flecha(x+1, y+1, 0,"bxdi"));
				}
			}
		}
	}
}
