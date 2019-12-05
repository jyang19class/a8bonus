package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class GameView extends JPanel implements ActionListener, SpotListener{

	Model _model;
	ArrayList<Controller> observers;
	private JButton random;
	private JButton reset;
	private JButton nextTurn;
	private JButton torusButton;
	private JButton autoRun;
	private JTextField sizeInput;
	private JTextField birthThresh;
	private JTextField minSurvive;
	private JTextField maxSurvive;
	private JTextField delay;
	private JLabel torusState;
	private JLabel autoState;
	
	public GameView(Model model) {
		observers = new ArrayList<Controller>();
		
		_model = model;
		setLayout(new BorderLayout());
		add(_model.getBoard(), BorderLayout.CENTER);
		
		
		model.getBoard().addSpotListener(this);
		
		JPanel toolsPanel = new JPanel();
		
		toolsPanel.setLayout(new BorderLayout());
		add(toolsPanel, BorderLayout.SOUTH);
		
		reset = new JButton("Clear");
		toolsPanel.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);
		
		random = new JButton("Random Cells");
		toolsPanel.add(random, BorderLayout.EAST);
		random.addActionListener(this);
		
		/*JSlider slider = new JSlider(10, 500, 10);
		toolsPanel.add(slider, BorderLayout.SOUTH);
		slider.setMajorTickSpacing(35);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);*/
		
		sizeInput = new JTextField(3);
		toolsPanel.add(sizeInput, BorderLayout.CENTER);
		sizeInput.addActionListener(this);
		
		JLabel label = new JLabel("Enter dimensions (10 to 500):");
		toolsPanel.add(label, BorderLayout.WEST);
		
		nextTurn = new JButton("advance turn");
		nextTurn.addActionListener(this);
		toolsPanel.add(nextTurn, BorderLayout.SOUTH);
		
		JPanel moreTools = new JPanel();
		moreTools.setLayout(new GridLayout(6,2));
		add(moreTools, BorderLayout.EAST);
		
		JLabel birth = new JLabel("Set Birth Threshold");
		moreTools.add(birth);
		
		birthThresh = new JTextField(3);
		moreTools.add(birthThresh);
		birthThresh.addActionListener(this);
		
		JLabel minDeath = new JLabel("Set Low Survival Threshold");
		moreTools.add(minDeath);
		
		minSurvive = new JTextField(3);
		moreTools.add(minSurvive);
		minSurvive.addActionListener(this);
		
		JLabel maxDeath = new JLabel("Set High Survival Threshold");
		moreTools.add(maxDeath);
		
		maxSurvive = new JTextField(3);
		moreTools.add(maxSurvive);
		maxSurvive.addActionListener(this);
		
		
		torusButton = new JButton("Toggle Torus");
		torusButton.addActionListener(this);
		moreTools.add(torusButton);
		
		torusState = new JLabel("Off");
		moreTools.add(torusState);
		
		autoRun = new JButton("Autorun");
		autoRun.addActionListener(this);
		moreTools.add(autoRun);
		
		autoState = new JLabel("Off");
		moreTools.add(autoState);
		
		JLabel setTick = new JLabel("<html>Set delay(10 to 1000 ms)<br/>Press enter to confirm</html>");
		moreTools.add(setTick);
		
		delay = new JTextField();
		delay.addActionListener(this);
		moreTools.add(delay);
	}
	
	public void repaintBoard() {
		repaint();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == random) {
			for (Controller c : observers) {
				c.randomize();;
			}
		} else if (e.getSource() == sizeInput) {
			_model.getBoard().setVisible(false);
			remove(_model.getBoard());
			for (Controller c : observers) {
				c.sizeChange(Integer.parseInt(sizeInput.getText()));
			}
			add(_model.getBoard());
			_model.getBoard().addSpotListener(this);
			
		} else if (e.getSource() == reset) {
			for (Controller c : observers) {
				c.clearBoard();
			}
		} else if (e.getSource() == nextTurn) {
			for (Controller c : observers) {
				c.nextTurn();
			}
		} else if (e.getSource() == birthThresh) {
			for (Controller c : observers) {
				c.changeBirthThresh(Integer.parseInt(birthThresh.getText()));
			} 
		} else if (e.getSource() == minSurvive) {
			for (Controller c : observers) {
				c.changeMinSurvive(Integer.parseInt(minSurvive.getText()));
			}
		} else if (e.getSource() == maxSurvive) {
			for (Controller c : observers) {
				c.changeMaxSurvive(Integer.parseInt(maxSurvive.getText()));
			}
		} else if (e.getSource() == torusButton) {
			for (Controller c : observers) {
				c.toggleTorus();
			}
			if (_model.isTorusOn()) {
				torusState.setText("On");
			} else {
				torusState.setText("Off");
			}
		} else if (e.getSource() == autoRun) {
			for (Controller c : observers) {
				c.autoRun();
			}
			if (autoState.getText().equals("On")) {
				autoState.setText("Off");
			} else {
				autoState.setText("On");
			}
		} else if (e.getSource() == delay) {
			for (Controller c : observers) {
				c.changeDelay(Integer.parseInt(delay.getText()));
			}
			autoState.setText("Off");
		}
	}




	@Override
	public void spotClicked(Spot spot) {
		for (Controller c : observers) {
			c.clicked(spot);
		}
	}

	@Override
	public void spotEntered(Spot spot) {
		for (Controller c : observers) {
			c.entered(spot);
		}
		
	}

	@Override
	public void spotExited(Spot spot) {
		for (Controller c : observers) {
			c.exited(spot);
		}
		
	}

	
	public void addObserver(Controller control) {
		observers.add(control);
	}
	
	
}