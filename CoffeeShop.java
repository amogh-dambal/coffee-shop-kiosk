import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CoffeeShop extends Application {
	Stage window;
	Scene scene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Jumping Jive Coffee Kiosk 1");

		BorderPane border_pane = new BorderPane();

		//Welcome label 
		Label welcome = new Label("Welcome to the Jumping Jive!");
		welcome.setFont(Font.font("Verdana", 20));
		welcome.setPadding(new Insets(15, 0, 15, 0));
		border_pane.setTop(welcome);
		BorderPane.setAlignment(welcome, Pos.TOP_CENTER);

		//HBox - names 
		HBox h_top = new HBox(20);

		//Label - name
		Label name = new Label("Enter your name: ");

		//TextField for name
		TextField name_field = new TextField();

		h_top.getChildren().addAll(name, name_field);

		//next HBox on the left side 
		HBox h_left = new HBox(40);

		//ToggleGroup of coffee type
		ToggleGroup coffee_type = new ToggleGroup();

		//Label - select kind of coffee
		Label coffee = new Label("Select type of coffee:");

		//RadioButtons - types
		RadioButton regular = new RadioButton("Regular");
		regular.setToggleGroup(coffee_type);

		RadioButton decaf = new RadioButton("Decaf");
		decaf.setToggleGroup(coffee_type);

		h_left.getChildren().addAll(regular, decaf);

		//Label - addins
		Label addin = new Label("Select additional options: ");

		//Addins as checkBoxes
		CheckBox whip_cream = new CheckBox("Whipped Cream ($0.89)");
		CheckBox cinnamon = new CheckBox("Cinnamon ($0.25)");
		CheckBox chocolate_sauce = new CheckBox("Chocolate Sauce ($0.59)");

		CheckBox[] boxes = {whip_cream, cinnamon, chocolate_sauce};

		//TextArea for output
		TextArea output = new TextArea();
		output.setPrefHeight(300);
		output.setPrefWidth(200);

		//Button - calculate
		Button calculate = new Button("Order now!");
		calculate.setOnAction(e -> {
			calculatePrice(name_field, coffee_type, boxes, output);
		});

		border_pane.setRight(calculate);
		BorderPane.setAlignment(calculate, Pos.CENTER_RIGHT);

		//The final VBox
		VBox v_final = new VBox(30);
		v_final.setPadding(new Insets(20, 20, 20, 20));
		v_final.getChildren().addAll(h_top, coffee, h_left, addin, whip_cream, 
				cinnamon, chocolate_sauce, output, calculate);

		border_pane.setLeft(v_final);

		scene = new Scene(border_pane, 500, 825);


		window.setScene(scene);
		window.show();

	}

	private void calculatePrice(TextField name_field, ToggleGroup coffee_type, CheckBox[] boxes, TextArea output) {
		double bill = 2.00;
		String str = "Order Completed!" + "\n" + "Welcome, ";

		try {
			if (!name_field.getText().equals("")) {
				str += name_field.getText() + "\n";
			}
			else {
				throw new IllegalArgumentException("ERROR: please input your name!");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Please enter a name in the field");
		}

		
		str += "----------------------------------" + "\n";
		
		RadioButton r = (RadioButton) coffee_type.getSelectedToggle();
		str += r.getText() + "\n";

		String text;
		for (CheckBox box : boxes) {
			text = "";
			if (box.isSelected()) {
				text = box.getText();
				
				switch(text) {
				case "Whipped Cream ($0.89)" :
					bill += 0.89;
				case "Cinnamon ($0.25)" : 
					bill += 0.25;
				case "Chocolate Sauce ($0.59)" : 
					bill += 0.59;
				}
				str += text + "\n";
			}
		}
		
		str += "Bill: $" + bill;
		output.setText(str);

	}

}
