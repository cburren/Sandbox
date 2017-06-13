package BackupMVC;


import javafx.stage.Stage;
import OtherClasses.ServiceLocator;
import OtherClasses.Translator;
import OtherClasses.Configuration;
import javafx.application.Application;

public class Main extends Application {
	private LottoModel model;
	private LottoView view;
	private LottoController controller;
	
	private ServiceLocator serviceLocator; // resources, after initialization
	
	
	public void start(Stage primaryStage) {
		try {
			
			serviceLocator = ServiceLocator.getServiceLocator();
			serviceLocator.setConfiguration(new Configuration());
			String language = serviceLocator.getConfiguration().getOption("Language");
	        serviceLocator.setTranslator(new Translator(language));
			
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
