/*
 * Copyright (C) 2016 Ulises Beltrán Gómez   ---   beltrangomezulises@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package Utilerias;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ulises beltran gomez
 */
public class ImageResizer {

    //Ancho máximo
    private final int maxWidth;

    private final int maxHeight;

    public ImageResizer(int maxPixels) {
        maxWidth = maxPixels;
        maxHeight = maxPixels;
    }

    public void resizeImage(String filePath, String copyPath) {
        BufferedImage nuevaImagen = cargarImagen(filePath);
        if (nuevaImagen.getHeight() > nuevaImagen.getWidth()) {
            int heigt = (nuevaImagen.getHeight() * maxWidth) / nuevaImagen.getWidth();
            nuevaImagen = resize(nuevaImagen, maxWidth, heigt);
            int width = (nuevaImagen.getWidth() * maxHeight) / nuevaImagen.getHeight();
            nuevaImagen = resize(nuevaImagen, width, maxHeight);
        } else {
            int width = (nuevaImagen.getWidth() * maxHeight) / nuevaImagen.getHeight();
            nuevaImagen = resize(nuevaImagen, width, maxHeight);
            int heigt = (nuevaImagen.getHeight() * maxWidth) / nuevaImagen.getWidth();
            nuevaImagen = resize(nuevaImagen, maxWidth, heigt);
        }
        guardarImagen(nuevaImagen, copyPath);
    }

    /*
    Con este método, cargamos la imagen inicial, indicándole el path
     */
    private BufferedImage cargarImagen(String pathName) {
        BufferedImage nuevaImagen = null;
        try {
            nuevaImagen = ImageIO.read(new File(pathName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevaImagen;
    }

    /*
    Este método se utiliza para redimensionar la imagen
     */
    private BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage imagenRedimensionada = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return imagenRedimensionada;
    }

    /*
    Con este método almacenamos la imagen en el disco
     */
    private void guardarImagen(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file = new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
