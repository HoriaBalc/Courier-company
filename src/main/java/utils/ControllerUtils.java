package utils;

import javax.swing.*;

public class ControllerUtils {

    public static void createSwingErrorMessage(String message){
        JOptionPane.showMessageDialog(null,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}
