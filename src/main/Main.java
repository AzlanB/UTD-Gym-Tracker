package main;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        try {
            // Specify the path to your HTML file
            String htmlFilePath = "src\\gui\\LogInPage.html";

            // Open the HTML file in the default web browser
            openInBrowser(htmlFilePath);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void openInBrowser(String htmlFilePath) throws IOException, URISyntaxException {
        File htmlFile = new File(htmlFilePath);

        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } else {
            // Handle the case where the Desktop API is not supported
            System.out.println("Desktop API is not supported on this platform.");
        }
    }
}
