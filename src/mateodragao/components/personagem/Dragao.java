package mateodragao.components.personagem;

import mateodragao.components.projetil.BolaDeFogo;
import mateodragao.interfaces.ITabuleiro;

import java.lang.Math;

public class Dragao extends Personagem{
	private static final long serialVersionUID = 1529623392218092957L;
	public static String DIRETORIO =
		      Dragao.class.getResource(".").getPath();
	/*{frequencia = 1;
	movimento = 1;
	passo = 1;}*/
	
	public Dragao(int x, int y) {
		super(DIRETORIO+"yoshi.png",x,y);
		vida = 1000;
		frequencia = 2;	//estávamos pensando em 1
		movimento = 1;
		passo = 1;
	}

	//Instancia um projetil na posição do personagem e imediatamente ativa o método que move o projétil, depois atualizando a frequência de ataque, que diz que o personagem pode disparar quando for 0
	/*Como funciona o disparo direcionado: se a frequência de ataque for 0, é a vez do personagem atacar. 
	 * É calculada a distância horizontal(hor) e vertical(ver) subtraindo a posição do alvo da posição do personagem que vai disparar, pode ser positiva ou negativa, no caso do dragão, ele procura alvos em uma distância de 4 casas para cada lado.
	 * Se o módulo da distância horizontal for menor (ou igual, para não ficarem casos sem ser abrangidos)que o da vertical, ele tem que atirar verticalmente, senão, horizontalmente.
	 * Se for verticalmente: Se ver for negativo, atira para cima, se for positivo, atira para baixo, depois o método testa se tem que atirar diretamente para cima/baixo ou em alguma das diagonais, 
	 * comparando ver-hor, que é a distância entre o ataque e o alvo quando está na linha do alvo, com hor que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo.
	 * Se for horizontalmente: Se hor for negativo, atira para esquerda, se for positivo, atira para direita, depois o método testa se tem que atirar diretamente para esquerda/direita ou em alguma das diagonais, 
	 * comparando hor-ver, que é a distância entre o ataque e o alvo quando está na coluna do alvo, com ver que é a distância que o ataque ficará do alvo atirando diretamente para cima/baixo. 
	 * 
	 *  Caso o dragão não encontre alvos no seu raio específico de tiro direcionado, ele atira aleatoriamente em uma das 12 posições+direções possíveis */
	@Override
	public void disparaProjetil(ITabuleiro tab) {
		if (freqA==0) {
			System.out.println("dispara");
			int hor,ver, //distância horizontal e vertical do dragão ao personagem
				pX=100,pY=100; //posição
			
			for (int i=-5;i<=4;i++) {
				if (i!=0 && i!=1) {
					if (x+i>=0 && x+i<=15 && y-4>=0){
						if (tab.getPeca(x+i,y-4)!=null){
							pX=x+i;
							pY=-y-4;
							break;
						}
					}
					if (x+i>=0 && x+i<=15 && y+4<=15){
						if (tab.getPeca(x+i,y+4)!=null) {
							pX=x+i;
							pY=y+4;
							break;
						}
					}	
					if (x-4>=0 && y+i>=0 && y+i<=15){
						if (tab.getPeca(x-4,y+i)!=null) {
							pX=x-4;
							pY=y+i;
							break;
						}
					}if (x+4<=15 && y+i>=0 && y+i<=15){
						if (tab.getPeca(x+4,y+i)!=null) {
							pX=x+4;
							pY=y+i;
							break;
						}
					}
				}
			}
			if (pX!=100) {
				hor=pX-x;
				ver=pY-y;
				
				if (Math.abs(hor)<=Math.abs(ver)){		//atira na vertical
					if (ver<0) {	//atira para cima
						if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) {
							tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"ci", "boladefogo-ci.png"));
							tab.getProjetil(x,y-1,0).move(tab);
						}else {
							if (hor<0) {
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0, "cies","boladefogo-cies.png"));
								tab.getProjetil(x-1,y-1,0).move(tab);
							}if (hor>0) {
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"cidi", "boladefogo-cidi.png"));
								tab.getProjetil(x,y,0).move(tab);
							}
						}
					}if (ver>0) {	//atira para baixo
						if (Math.abs(hor)<=(Math.abs(ver)-Math.abs(hor))) { 
							tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bx", "boladefogo-bx.png"));
							tab.getProjetil(x,y,0).move(tab);
						}else {
							if (hor<0) {
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0, "bxes","boladefogo-bxes.png"));
								tab.getProjetil(x,y,0).move(tab);
							}if (hor>0) {
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0, "bxdi","boladefogo-bxdi.png"));
								tab.getProjetil(x,y,0).move(tab);
							}
						}
					}
				}
				
				else {		//atira na horizontal
					if (hor<0) {	//atira para esquerda
						if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) {
							tab.putProjetil(x-1,y, 0, new BolaDeFogo(x-1, y, 0,"es", "boladefogo-es.png"));
							tab.getProjetil(x-1,y,0).move(tab);
						}else {
							if (ver<0) {
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0, "cies", "boladefogo-cies.png"));
								tab.getProjetil(x-1,y-1,0).move(tab);
							}if (ver>0) {
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0, "bxes", "boladefogo-bxes.png"));
								tab.getProjetil(x,y,0).move(tab);
							}
						}
					}if (hor>0) {	//atira para direita
						if (Math.abs(ver)<=(Math.abs(hor)-Math.abs(ver))) { 
							tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"di", "boladefogo-di.png"));
							tab.getProjetil(x,y,0).move(tab);
						}else {
							if (ver<0) {
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"cidi", "boladefogo-cidi.png"));
								tab.getProjetil(x,y,0).move(tab);
							}if (ver>0) {
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bxdi", "boladefogo-bxdi.png"));
								tab.getProjetil(x,y,0).move(tab);
							}
						}
					}
				}
			}
			else {
				int disparado=0;
				while (disparado==0) {
					int direcaoAlea = alea.nextInt(12);
					switch (direcaoAlea) {
						case 0:
							if (x-2>0 && y-2>0){
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"cies", "boladefogo-cies.png"));
								tab.getProjetil(x-1,y-1,0).move(tab);
								disparado=1;
							}break;
						case 1:
							if (x-1>0 && y-2>0){
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"ci", "boladefogo-ci.png"));
								tab.getProjetil(x-1,y-1,0).move(tab);
								disparado=1;
							}break;
						case 2:
							if (y-2>0){
								tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"ci", "boladefogo-ci.png"));
								tab.getProjetil(x,y-1,0).move(tab);
								disparado=1;
							}break;
						case 3:
							if (x+1<15 && y-2>0){
								tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"cidi", "boladefogo-cidi.png"));
								tab.getProjetil(x,y-1,0).move(tab);
								disparado=1;
							}break;
						case 4:
							if (x+1<15 && y-1>0){
								tab.putProjetil(x, y-1, 0, new BolaDeFogo(x, y-1, 0,"di", "boladefogo-di.png"));
								tab.getProjetil(x,y-1,0).move(tab);
								break;
							}
						case 5:
							if (x+1<15){
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"di", "boladefogo-di.png"));
								tab.getProjetil(x,y,0).move(tab);
								disparado=1;
							}break;
						case 6:
							if (x+1<15 && y+1<15){
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bxdi", "boladefogo-bxdi.png"));
								tab.getProjetil(x,y,0).move(tab);
								disparado=1;
							}break;
						case 7:
							if (y+1<15){
								tab.putProjetil(x, y, 0, new BolaDeFogo(x, y, 0,"bx", "boladefogo-bx.png"));
								tab.getProjetil(x,y,0).move(tab);
								disparado=1;
							}break;
						case 8:
							if (x-1>0 && y+1<15){
								tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"bx", "boladefogo-bx.png"));
								tab.getProjetil(x-1,y,0).move(tab);
								disparado=1;
							}break;
						case 9:
							if (x-2>0 && y+1<15){
								tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"bxes","boladefogo-bxes.png"));
								tab.getProjetil(x-1,y,0).move(tab);
								disparado=1;
							}break;
						case 10:
							if (x-2>0){
								tab.putProjetil(x-1, y, 0, new BolaDeFogo(x-1, y, 0,"es", "boladefogo-es.png"));
								tab.getProjetil(x-1,y,0).move(tab);
								disparado=1;
							}break;
						case 11:
							if (x-2>0 && y-1>0){
								tab.putProjetil(x-1, y-1, 0, new BolaDeFogo(x-1, y-1, 0,"es", "boladefogo-es.png"));
								tab.getProjetil(x-1,y-1,0).move(tab);
								disparado=1;
							}break;
					}
				}	
			}	
		}freqA = (freqA + 1)%frequencia;	
	}
}