import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GUI extends JFrame{
    private JButton importCSVFileButton;
    private JButton typeSQLManipulationButton;
    private JPanel jPanel;


    public GUI(){
        add(jPanel);
        setTitle("Hvad vil du foretage dig?");
        setSize(500,200);
        setVisible(true);
        importCSVFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //csv
                //TODO
                IndlaesDatafilEksempel datafil = new IndlaesDatafilEksempel();
                datafil.CSVimport();
                dispose();
            }
        });
        typeSQLManipulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //sql command
                ManipulateUniversity datafil = new ManipulateUniversity();
                ManipulateUniversity.ActionDatabase();
                dispose();
            }
        });
    }
}
