package a8;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class GameController implements Controller{
	
	GameView _view;
	Model _model;
	ArrayList<Spot> toKill;
	ArrayList<Spot> toBirth;
	autoRunner t;
	private boolean hasRunner;
	
	
	public GameController(GameView view, Model model) {
		_view = view;
		_view.addObserver(this);
		_model = model;
		boolean hasRunner = false;
		toKill = new ArrayList<Spot>();
		toBirth = new ArrayList<Spot>();
		t = new autoRunner(1000, this);
	}

		
	

	@Override
	public void clicked(Spot spot) {
		if (!spot.isAlive()) {
			spot.setBackground(Color.BLACK);
			spot.setIsAlive(true);
		}
		else {
			spot.setBackground(Color.WHITE);
			spot.setIsAlive(false);
		}
		
	}

	@Override
	public void entered(Spot spot) {
		spot.highlightSpot();
		
	}

	@Override
	public void exited(Spot spot) {
		spot.unhighlightSpot();
		
	}
	
	public void randomize() {
		for (Spot s: _model.getBoard()) {
			if (Math.round(Math.random() + .2) == 0) {
				s.setBackground(Color.BLACK);
				s.setIsAlive(true);
			} else {
				s.setBackground(Color.WHITE);
				s.setIsAlive(false);
			}
		}
	}




	@Override
	public void sizeChange(int size) {
		_model.newSize(size);
		
	}

	public void checkCells() {
		toKill.clear();
		toBirth.clear();
		for (Spot s: _model.getBoard()) {
			int liveCells = 0;
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()-1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()+1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()+1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()-1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {}
			
			if (s.isAlive() && !(liveCells<=_model.getMaxThreshold() && liveCells>=_model.getMinThreshold())) {
				toKill.add(s);
			}
			if (!s.isAlive() && liveCells == _model.getBirthThreshold()) {
				toBirth.add(s);
			}
		}
	}
	
	public void torusCheckCells() {
		toKill.clear();
		toBirth.clear();
		for (Spot s: _model.getBoard()) {
			int liveCells = 0;
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()+1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(s.getSpotX(), 0).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX(), s.getSpotY()-1).isAlive()) { 
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(s.getSpotX(), _model.getBoard().getSpotHeight()-1).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(0, s.getSpotY()).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(_model.getBoard().getSpotWidth()-1, s.getSpotY()).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()-1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(_model.getBoard().getSpotWidth()-1, _model.getBoard().getSpotHeight()-1).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()+1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(0, 0).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()-1, s.getSpotY()+1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(_model.getBoard().getSpotWidth()-1, 0).isAlive()) {
					liveCells++;
				}
			}
			
			try {
				if (_model.getBoard().getSpotAt(s.getSpotX()+1, s.getSpotY()-1).isAlive()) {
					liveCells++;
				}
			} catch (IllegalArgumentException e) {
				if (_model.getBoard().getSpotAt(0, _model.getBoard().getSpotHeight()-1).isAlive()) {
					liveCells++;
				}
			}
			
			if (s.isAlive() && !(liveCells<=_model.getMaxThreshold() && liveCells>=_model.getMinThreshold())) {
				toKill.add(s);
			}
			if (!s.isAlive() && liveCells == _model.getBirthThreshold()) {
				toBirth.add(s);
			}
		}
	}
	
	public void changeCells() {
		for (Spot s: toKill) {
			s.setBackground(Color.WHITE);
			s.setIsAlive(false);
		}
		for (Spot s: toBirth) {
			s.setBackground(Color.BLACK);
			s.setIsAlive(true);
		}
	}

	public void nextTurn() {
		if (_model.isTorusOn()) {
			torusCheckCells();
			changeCells();
		} else {
			checkCells();
			changeCells();
		}
	}

	@Override
	public void clearBoard() {
		_model.clearBoard();
		
	}




	@Override
	public void changeBirthThresh(int num) {
		_model.setBirthThreshold(num);
		
	}




	@Override
	public void changeMinSurvive(int num) {
		_model.setMinThreshold(num);
		
	}




	@Override
	public void changeMaxSurvive(int num) {
		_model.setMaxThreshold(num);
		
	}




	@Override
	public void toggleTorus() {
		if (_model.isTorusOn()) {
			_model.setTorus(false);
		} else {
			_model.setTorus(true);
		}
		
	}
	
	public void autoRun() {
		
		if (!t.getRunning()) {
			t.run();
		} else {
			t.halt();
		}
		
	}
	
	public void changeDelay(int delay) {
		t.halt();
		t = new autoRunner(delay, this);
	}
	
	class autoRunner extends Thread{
		private boolean running;
		private int _delay;
		GameController control;
		private Timer timer;
		
		public autoRunner(int delay, GameController control) {
			running = false;
			_delay = delay;
			this.control = control;
			
			timer = new Timer(_delay, new ActionListener() {
				public void actionPerformed(ActionEvent evt) {

					control.nextTurn();
					_view.repaint();
				} 
			});
		}
		public void halt() {
			timer.stop();
			running = false;
		}
		public void run() {
			timer.start();
			running = true;
		}
		
		public boolean getRunning() {
			return running;
		}
		
	
	}
	
}