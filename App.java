import javax.swing.UIManager;
import java.awt.Font;
import model.DataService;
import view.MainListView;
import controller.RumorController;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.put("Label.font", new Font("Tahoma", Font.PLAIN, 14));
            UIManager.put("Button.font", new Font("Tahoma", Font.PLAIN, 14));
            
            DataService model = new DataService();
            model.loadDataFromCSV();
            MainListView view = new MainListView();
            new RumorController(model, view);
            view.setVisible(true);
        } catch (Exception e) { e.printStackTrace(); }
    }
}