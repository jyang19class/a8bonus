
public interface Controller {

	void clicked(Spot spot);
	void entered(Spot spot);
	void exited(Spot spot);
	void randomize();
	void sizeChange(int size);
	void clearBoard();
	void nextTurn();
	void changeBirthThresh(int num);
	void changeMinSurvive(int num);
	void changeMaxSurvive(int num);
	void toggleTorus();
	void autoRun();
}
