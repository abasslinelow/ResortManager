package sample;

import java.awt.Dimension;
import java.util.Collection;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

  @FXML private GridPane catalogGridPane;

  @FXML private GridPane managerGridPane;

  @FXML private Pane managerReportFormPane;

  @FXML
  public void initialize() {
    Room[] rooms = createRoomArray();
    setupGridPaneWithRooms(rooms);
    setupManagerGridPane(rooms);
    setupManagerReportForm();
  }

  private void setupManagerReportForm() {

    // Add elements to the managerReportFormPane

    // Add Label
    Label titleLabel = new Label("Generate Report");
    titleLabel.setPrefWidth(750);
    titleLabel.setLayoutY(15);
    titleLabel.setAlignment(Pos.CENTER);
    managerReportFormPane.getChildren().add(titleLabel);

    // Add Label
    Label startLabel = new Label("Start Date");
    startLabel.setPrefWidth(250);
    startLabel.setLayoutX(50);
    startLabel.setLayoutY(50);
    managerReportFormPane.getChildren().add(startLabel);

    // Add Date Picker
    DatePicker startDatePicker = new DatePicker();
    startDatePicker.setPrefWidth(250);
    startDatePicker.setLayoutX(50);
    startDatePicker.setLayoutY(70);
    managerReportFormPane.getChildren().add(startDatePicker);

    // Add Label
    Label endLabel = new Label("End Date");
    endLabel.setPrefWidth(250);
    endLabel.setLayoutX(450);
    endLabel.setLayoutY(50);
    managerReportFormPane.getChildren().add(endLabel);

    // Add Date Picker
    DatePicker endDatePicker = new DatePicker();
    endDatePicker.setPrefWidth(250);
    endDatePicker.setLayoutX(450);
    endDatePicker.setLayoutY(70);
    managerReportFormPane.getChildren().add(endDatePicker);

    // Add Button
    Button btn = new Button("Generate Report");
    btn.setPrefSize(150, 40);
    btn.setLayoutX(300);
    btn.setLayoutY(140);
    btn.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent e) {
            System.out.println("present report");
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);

            Pane pane = new Pane();
            pane.getChildren().add(new TableView<>());

            dialogVbox.getChildren().add(pane);
            Scene dialogScene = new Scene(dialogVbox, 250, 400);
            dialog.setScene(dialogScene);
            dialog.show();
          }
        });
    managerReportFormPane.getChildren().add(btn);

  }


  private void setupManagerGridPane(Room[] rooms) {

    managerGridPane.setGridLinesVisible(false);

    // For each room in the list of rooms
    for (int i = 0; i < rooms.length; i++) {

      // Get row and column of current grid in gridpane
      int row = (i < 5) ? 0 : 1;
      int column = (i < 5) ? i : i - 5;

      // Add items to the grid
      Room room = rooms[i];

      // Add elements to this pane
      Pane pane = new Pane();

      // Add Label
      Label bedSizeLbl = new Label(room.getBedSize());
      bedSizeLbl.setPrefWidth(150);
      bedSizeLbl.setLayoutX(0);
      bedSizeLbl.setLayoutY(5);
      bedSizeLbl.setAlignment(Pos.CENTER);
      pane.getChildren().add(bedSizeLbl);

      // Add Label
      Label numBedsLbl = new Label("Number of Beds: " + room.getNumBeds());
      numBedsLbl.setPrefWidth(150);
      numBedsLbl.setLayoutX(0);
      numBedsLbl.setLayoutY(20);
      numBedsLbl.setAlignment(Pos.CENTER);
      pane.getChildren().add(numBedsLbl);

      Image roomImage = new Image(Controller.class.getResourceAsStream("hotelRoom.jpg"));
      ImageView imgView = new ImageView(roomImage);
      imgView.setFitHeight(90);
      imgView.setFitWidth(90);
      imgView.setLayoutX(30);
      imgView.setLayoutY(50);
      pane.getChildren().add(imgView);

      // Add Label
      Label vacantLbl = new Label();
      String vacancyText = room.getVacant() ? "Vacant" : "Occupied";
      vacantLbl.setText(vacancyText);
      vacantLbl.setPrefWidth(150);
      vacantLbl.setLayoutX(0);
      vacantLbl.setLayoutY(150);
      vacantLbl.setAlignment(Pos.CENTER);
      pane.getChildren().add(vacantLbl);

      // Add Button
      Button btn = new Button("View");
      btn.setPrefSize(60, 30);
      btn.setLayoutX(45);
      btn.setLayoutY(175);
      btn.setOnAction(
          new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
              ManagerRoomView roomView = new ManagerRoomView();
              roomView.setRoom(room);
              roomView.presentRoomView();
              System.out.println("Manager Room View presented");
            }
          });
      pane.getChildren().add(btn);

      managerGridPane.add(pane,column,row);
    } // end loop
  }

  private void setupGridPaneWithRooms(Room[] rooms) {

    catalogGridPane.setGridLinesVisible(false);
    // For each room in the list of rooms
    for (int i = 0; i < rooms.length; i++) {

      // Get row and column of current grid in gridpane
      int row = (i < 5) ? 0 : 1;
      int column = (i < 5) ? i : i - 5;

      // Add items to the grid
      Room room = rooms[i];

      // Add elements to this pane
      Pane pane = new Pane();

      // Add Label
      Label bedSizeLbl = new Label(room.getBedSize());
      bedSizeLbl.setPrefWidth(150);
      bedSizeLbl.setLayoutX(0);
      bedSizeLbl.setLayoutY(5);
      bedSizeLbl.setAlignment(Pos.CENTER);
      pane.getChildren().add(bedSizeLbl);

      // Add Label
      Label numBedsLbl = new Label("Number of Beds: " + room.getNumBeds());
      numBedsLbl.setPrefWidth(150);
      numBedsLbl.setLayoutX(0);
      numBedsLbl.setLayoutY(20);
      numBedsLbl.setAlignment(Pos.CENTER);
      pane.getChildren().add(numBedsLbl);

      Image roomImage = new Image(Controller.class.getResourceAsStream("hotelRoom.jpg"));
      ImageView imgView = new ImageView(roomImage);
      imgView.setFitHeight(120);
      imgView.setFitWidth(120);
      imgView.setLayoutX(15);
      imgView.setLayoutY(45);
      pane.getChildren().add(imgView);

      // Add Label
      Label priceLbl = new Label("$" + room.getPrice());
      priceLbl.setPrefWidth(150);
      priceLbl.setLayoutX(0);
      priceLbl.setLayoutY(250);
      priceLbl.setAlignment(Pos.CENTER);
      pane.getChildren().add(priceLbl);


      // Add Button
      Button btn = new Button("Book Now");
      btn.setPrefSize(120, 40);
      btn.setLayoutX(15);
      btn.setLayoutY(275);
      btn.setOnAction(
          new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
              RoomView roomView = new RoomView();
              roomView.setRoom(room);
              roomView.presentRoomView();
              System.out.println("button pressed" + priceLbl.toString());
            }
          });
      pane.getChildren().add(btn);

      catalogGridPane.add(pane,column,row);
    } // end loop


  }



  private Room[] createRoomArray() {
    Room[] rooms = {
      new Room("01", "King", 1, true, 150.00),
      new Room("02", "Queen", 2, true, 150.00),
      new Room("03", "King", 1, true, 850.00),
      new Room("04", "Queen", 2, true, 150.00),
      new Room("05", "King", 1, true, 140.00),
      new Room("06", "Queen", 2, true, 150.00),
      new Room("07", "King", 1, true, 30.00),
      new Room("08", "Queen", 2, true, 650.00),
      new Room("09", "King", 1, true, 290.00),
      new Room("10", "Queen", 2, true, 160.00)
    };
    return rooms;
  }
}
