package com.example.projectmanagement.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageWriter {

    public String writeImage(String imagePath, String caption) {

        String newImage = "";
        BufferedImage image;
        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            File f = new File(imagePath);
            image = ImageIO.read(f);

            Graphics graphics = image.getGraphics();
            int y = image.getHeight() / 3;
            int x = image.getWidth();
            graphics.setColor(Color.RED);
            graphics.fillRect(0, 0, 200, 50);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("Arial Black", Font.BOLD, 20));
            graphics.drawString(caption, x, y);

            ImageIO.write(image, "jpg", outputStream);

            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newImage;
    }
}
