# javafx-screen-manager
Screen manager to JavaFX application. Referenced in [https://github.com/acaicedo/JFX-MultiScreen]

The screens are extended from StackPane and are managed by main application through composition strategy.
The source can be exported to .jar file and used as lib in JavaFX project.

How-To:
<p>
<pre><code>
/* imports ommited */
public class Main extends Application {
  @Override
	public void start(Stage stage) {
		try {
			ScreenManager manager = new ScreenManager();
			// One calling loadScreen for each FXML of the application
			manager.loadScreen("Screen1 ID", Main.class, "FXML1 filename");
			manager.loadScreen("Screen2 ID", Main.class, "FXML2 filename");
			manager.loadScreen("Screen3 ID", Main.class, "FXML3 filename");
			Scene scene = new Scene(manager,400,300);
			stage.setTitle("Welcome");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
</pre></code>
</p>
