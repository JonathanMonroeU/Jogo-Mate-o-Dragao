package mateodragao.components.projetil;

public class BolaDeFogo extends Projetil{
	private static final long serialVersionUID = -3677744954496112045L;
	public static String DIRETORIO =
		      BolaDeFogo.class.getResource(".").getPath();
	public BolaDeFogo(int x, int y, int z,String direcaoA) {
		super(DIRETORIO+"yoshi.png", x, y, z);
		direcao=direcaoA;
		dano=1;
		velocidade= 2;
		emConflito=0;
		
	}
	
}
