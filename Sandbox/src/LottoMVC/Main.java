package LottoMVC;


import javafx.stage.Stage;
import javafx.application.Application;

public class Main extends Application {
	private LottoModel model;
	private LottoView view;
	private LottoController controller;
	
	
	public void start(Stage primaryStage) {
		try {
			model = new LottoModel();
			view = new LottoView(primaryStage, model);
			controller = new LottoController(model, view);
			
			view.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() {
		if (view != null) view.stop();
	}
	
	
}
