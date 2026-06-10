import gui.SearchFrame;

import javax.swing.SwingUtilities;

public class GuiMain {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        new SearchFrame();
                    }
                });
    }
}