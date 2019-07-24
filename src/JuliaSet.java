import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JuliaSet extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 360;
	public static final int HEIGHT = 640;

	public JuliaSet() {
		super("Julia Set Simulator");
		
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().add(new JuliaSetPanel(WIDTH, HEIGHT));
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new JuliaSet();
	}
	
	private class JuliaSetPanel extends JPanel implements ActionListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public static final int ITERATIONS = 100;
		public static final int SCALE = 180;
		
		private int width;
		private int height;
		
		private float cx = 0.4f;
		private float cy = 0.4f;
		
		private Timer timer;	
		
		private BufferedImage image;

		public JuliaSetPanel(int width, int height) {
			this.width = width;
			this.height = height;
			
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			timer = new Timer(5, this);
			timer.start();
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					int color = calculatePoint((x - width/2f)/SCALE, (y - height/2f)/SCALE);
					image.setRGB(x, y, color);
				}
			}
			
			g2.drawImage(image, 0, 0, null);
			
		}
		
		private int calculatePoint(float x, float y) {
			
			int i = 0;
			
			for(i = 0; i < ITERATIONS; i++) {
				float nx = x*x - y*y + cx;
				float ny = 2*x*y + cy;
				
				x = nx;
				y = ny;
				
				if(x*x + y*y > 4) break;
				
			}
			
			if(i == ITERATIONS) return 0x05668D;
			return Color.HSBtoRGB((float)i/ITERATIONS + (float)(100)*360/1000, .28f, .76f);
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cx -= 0.0015f;
			cy += 0.001f;
			if(cx >= 1.0f) {
				cx = 0.0f;
				cy = 0;
			}
			repaint();
		}
	}
}
