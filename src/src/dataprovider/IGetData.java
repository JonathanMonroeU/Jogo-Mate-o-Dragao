package src.dataprovider;

import src.exceptions.AdicaoInvalida;
import src.exceptions.RemocaoInvalida;

public interface IGetData {
	public void inserePersonagem(int comando, int x, int y) throws AdicaoInvalida;
	public void removePersonagem(int x, int y) throws RemocaoInvalida;
	public void setX(int x);
	public void setY(int y);
	public void setTipo(int tipo);
	public int[] getData();
}
