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
			manager.loadScreen(MyScreenEnum.SCREEN_LOGIN, Main.class, MyScreenEnum.SCREEN_LOGIN.getFXMLName());
			manager.loadScreen(MyScreenEnum.SCREEN_FORM, Main.class, MyScreenEnum.SCREEN_FORM.getFXMLName());
			manager.loadScreen(MyScreenEnum.SCREEN_DETAIL, Main.class, MyScreenEnum.SCREEN_DETAIL.getFXMLName());
			manager.setScreen(MyScreenEnum.SCREEN_LOGIN);
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