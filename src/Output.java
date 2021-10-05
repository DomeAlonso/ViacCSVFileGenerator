import javax.swing.*;


public class Output {

    String title = "Viac CSV File Generator Finished";

    public Output(String msg){

        JFrame frame = new JFrame(title);
        frame.setLocationRelativeTo(null);

        if (msg.length()==0){
            JOptionPane.showMessageDialog(frame, "All files parsed without errors", title, JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.WARNING_MESSAGE);
        }

        System.exit(0);

    }
}

