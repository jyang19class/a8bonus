import java.awt.Color;

public class Model{

	private JSpotBoard _board;
	private int minThresh;
	private int maxThresh;
	private int birthThresh;
	private boolean torus;
	
	public Model(int width, int height) {
		_board = new JSpotBoard(width, height);
		minThresh = 2;
		maxThresh = 3;
		birthThresh = 3;
		torus = false;
	}
	
	
	public JSpotBoard getBoard() {
		return _board;
	}
	
	public void clearBoard() {
		for (Spot s : _board) {
			s.setBackground(Color.WHITE);
			s.setIsAlive(false);
		}
	}
	
	public void newSize(int size) {
		_board = new JSpotBoard(size,size);
	}
	
	public int getMinThreshold(){
		return minThresh;
	}
	
	public int getMaxThreshold() {
		return maxThresh;
	}
	
	public int getBirthThreshold() {
		return birthThresh;
	}
	
	public void setMinThreshold(int i) {
		minThresh = i;
	}
	
	public void setMaxThreshold(int i) {
		maxThresh = i;
	}
	
	public void setBirthThreshold(int i) {
		birthThresh = i;
	}
	
	public boolean isTorusOn() {
		return torus;
	}
	
	public void setTorus(boolean bool) {
		torus = bool;
	}
}