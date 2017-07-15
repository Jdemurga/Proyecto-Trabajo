package intento1;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoEscritorio extends JPanel {
	private Image img;
	private int alto = 0;

	public FondoEscritorio() {
	}

	public void alto(int altura) {
		this.alto = altura;
	}

	public FondoEscritorio(String nombreImagen) {
		if (nombreImagen != null) {
			img = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
		}
	}

	public FondoEscritorio(Image imagenInicial) {
		if (imagenInicial != null) {
			img = imagenInicial;
		}
	}

	public void setImagen(String nombreImagen) {
		if (nombreImagen != null) {
			img = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
		} else {
			img = null;
		}

		repaint();
	}

	public void setImagen(Image nuevaImagen) {
		img = nuevaImagen;

		repaint();
	}

	@Override
	public void paint(Graphics g) {
		if (alto == 0) {
			if (img != null) {
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

				setOpaque(false);
			} else {
				setOpaque(true);
			}

			super.paint(g);

		} else {
			if (img != null) {
				g.drawImage(img, 0, 0, getWidth(), alto, this);

				setOpaque(false);
			} else {
				setOpaque(true);
			}

			super.paint(g);
		}
	}
}
