/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui2;

import com.sun.javaws.Main;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import model.InterfaceTypes;
import model.TSNTypes;
import model.Task;
import model.guiControler;
import model.*;
/**
 *
 * @author jonas
 */
public class GUI2 extends Application {
           
    
   private BorderPane root;
   private String State="";
   private Button okButton,BackButton;   
   private MenuItem P_2_P_MenuItem;
   private guiControler Contolloer;
   private Menu P_2_P_Menu,ModeMenu;   
   private CheckMenuItem Plan,Live;
   private HBox TopLine,CenterHBox;
   private VBox ToplineVBox,CentetVBox;
   private MenuBar menulist;
   private GridPane Net;
   private Tab Overview,Interface,Nodes,tabPlanScren,tabP_2_P,tabLiveMode;
   private TabPane tabPane;
   private ArrayList<TreeItem> ListOfInterfaceArea,ListOfNodesArea;
   private DatePicker DatePicer;
   private final String pattern = "yyyy-mm-dd";
   private LocalDate DateToPresent;  
   private DateTimeFormatter dateFormatter;
   
    
    @Override   
    
  public void start(Stage primaryStage) {
       
        Contolloer = new guiControler(this);        
        root = new BorderPane();
        tabPane = new TabPane();        
        TopLine = new HBox(20);       
        ToplineVBox = new VBox();
        Contolloer.setScreen();        
        Live.setSelected(false);       
        menulist = new MenuBar();
        BackButton = new Button("Back");
        BackButton.setStyle("-fx-background-color: #ccccb3");
        DateToPresent = LocalDate.now();
        ///////////////////////////////////////////////////////////////////////
        BackButton.addEventFilter(ActionEvent.ACTION, new BackButton());
        Contolloer.SetDate(LocalDate.now());
        menulist.getMenus().add(ModeMenu);    
       
       
        root.setCenter(tabPane);
        Contolloer.modeState();
        SetColor();
        
        Scene scene = new Scene(root, 700, 320);
        
      
        primaryStage.setTitle("GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
        
      
    
    }      
    /**
     *  Adding the first 3 tabs to the screen: Overview ,Communication type and Nodes
     */
  public void SetTabsForLiveMode(){
        tabPane.getTabs().clear();
        Overview = new Tab("Overwiew");
        Interface = new Tab("Comunication type");
        Nodes = new Tab("Nodes");
        Overview.setClosable(false);
        Interface.setClosable(false);
        Nodes.setClosable(false);        
        tabPane.getTabs().addAll(Overview,Nodes,Interface);      
           
        
        
   } 
  /**
   * Creates the menu list to select live mode or plan mode
   */
  public void ModeMenu(){
    ModeMenu = new Menu("_Mode");
    Plan = new CheckMenuItem("Plan");
    Plan.addEventHandler(ActionEvent.ACTION, new ModeMenuChoice());
    Live = new CheckMenuItem("Live");
    Live.addEventHandler(ActionEvent.ACTION, new ModeMenuChoice());
   // Simulate = new CheckMenuItem("Simnulate");
    //Simulate.addEventHandler(ActionEvent.ACTION, new ModeMenuChoice());
    ModeMenu.getItems().addAll(Plan,Live);
    

}   
/**
 * Creates the menu to select P2P connection
 */
  public void P_2_PMenu(){
    P_2_P_Menu = new Menu("_P_2_P");
    P_2_P_Menu.addEventHandler(ActionEvent.ACTION, new MenuP_2_PChoice());
    P_2_P_MenuItem = new MenuItem("P-2-P");
    P_2_P_MenuItem.addEventHandler(ActionEvent.ACTION, new MenuP_2_PChoice());
    P_2_P_Menu.getItems().add(P_2_P_MenuItem);
}     
/**
 * Sets colour to the menu bar and the screen
 */
  public void SetColor(){
    TopLine.setStyle("-fx-background-color: #ccccb3");   
    menulist.setStyle("-fx-background-color: #ccccb3");
    ToplineVBox.setStyle("-fx-background-color: #ccccb3");

}   
/**
 * Event handler for selecting date in plan mode, to filer the missions by the date. 
 * Updating the date window to the selected data and send the new date to the model, 
 * what will update the list of missions.
 */
  private class ChoiseOfDate implements EventHandler<ActionEvent>{

               @Override
        public void handle(ActionEvent event) {
           
            DateToPresent = DatePicer.getValue();
            DatePicer.setValue(DateToPresent);
            Contolloer.SetDate(DateToPresent);
            
        }
    } 
   /**
    * Event handler when the user hits the back button. 
    * It will change to the plan mode and call the functions in the controller to update the screen to plan mode.
    */
  private class BackButton implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == BackButton) {
                switch(State){
                    case "Plan":
                        Contolloer.modeState(true, false, false);
                        Contolloer.setScreenForPlanMode();
                    default: break;
                }
            }
        }
    }
/**
 * Event handler when the user is selection with mode it will work on, plan or live mode.
 * It will call for function in controller to update the screen for the desired mode.
 */
  private class ModeMenuChoice implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == Plan) {
               
                Contolloer.modeState(true, false,false);
            }
            else if (event.getSource() == Live) {
               
                Contolloer.modeState(false, true,false);
            }
           
        }
    }
    
  public void upDateModeState(boolean Plan,boolean Live,boolean Simulate){
        this.Plan.setSelected(Plan);
        this.Live.setSelected(Live);        
    }
    
  private class MenuP_2_PChoice implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
           
            if (event.getSource() == P_2_P_MenuItem) {                
                Contolloer.updateP_2_P();
                
            }
        }
    }  
  /**
   *     Creates the overview tab scene for missions, and the in parameter is a copy of the mission.
   * @param mission 
   */
  public void  OverViewSceen(Task mission){
        Net = new GridPane();
        CenterHBox = new HBox();
        CentetVBox = new VBox();
        Label labelText = new Label("Information:");       
        TextArea text = new TextArea(mission.getInfo());
        text.setMaxWidth(150);
        text.setPrefColumnCount(10);
        text.setWrapText(true);
        text.setEditable(false);
        text.setMouseTransparent(true);
        text.setFocusTraversable(false);
        
       
        
        ////////////////////////////////////////////////////////////////
        
        Label globalPriotet = new Label("This mision has priority: "+mission.getRank());
        Label startDayLabel = new Label("The mission begins at: "+mission.getStartTime().getTime());
        Label endDateLabel = new Label("The mission ends at: "+mission.getEndTime().getTime());
       
       
        Net.setVgap(20);
        Net.setHgap(20);
        Net.setAlignment(Pos.TOP_CENTER);
        CentetVBox.setAlignment(Pos.TOP_CENTER);
        Net.add(globalPriotet,0,2);
             
       
        Net.add(startDayLabel, 0, 3);
        Net.add(endDateLabel, 0, 4);
        CentetVBox.getChildren().addAll(labelText,text);
        CenterHBox.setSpacing(20);
        CenterHBox.setAlignment(Pos.CENTER_LEFT);
        CenterHBox.getChildren().addAll(CentetVBox,Net);        
        Overview.setContent(CenterHBox);
        
    }
  /**
   * Creates the tab to search or select communications types that is used in the mission. It also creates the tree of communications types
   * @param noder 
   */
  public void InterfaceScreen(ArrayList<Interface> noder){      
       
        TreeItem<String> root;        
        root = new TreeItem<>();
        root.setExpanded(true);
        BorderPane pane = new BorderPane();
      
       ArrayList<String> temp = new ArrayList<>();
       
       for (int i = 0; i < noder.size(); i++) {
          temp.add(noder.get(i).getName());
      }
       
        
      final  TextField textComType = new TextField();
        textComType.setPromptText("Search for comunication type");
        textComType.setMaxWidth(220);
        new Auto(temp, textComType);
        
        Button okBut = new Button("Ok");
        okBut.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Contolloer.newTabInterface((Object) textComType.getText());
            }
            
        });
        
        ListOfInterfaceArea = new ArrayList<>();        
        makeTreeAreaInterface(root);
        new getMuseCordinates(this.root);
      
        for (int i = 0; i < noder.size(); i++) {
            makeTreeInterfaceSubArea(noder.get(i));
        }
        HBox box = new HBox();
        box.getChildren().addAll(textComType,okBut);
        box.setSpacing(10);
        TreeView tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabInterface(newvalue);});         
        pane.setTop(box);
        pane.setCenter(tree);
    Interface.setContent(pane);
}
/**
 * Creates the tab to search or select nodes that are in the mission. It creates a tree view and a search function.  
 * @param noder 
 */  
  public void NodeTabScreen(ArrayList<TSN> noder){
     
        TreeItem<String> root;
        
        root = new TreeItem<>();
        root.setExpanded(true);
        BorderPane pane = new BorderPane();
        ArrayList<String> temp = new ArrayList<>();
        
        for (int i = 0; i < noder.size(); i++) {
            temp.add(noder.get(i).getName());
      }
        
       final TextField searchNode = new TextField();
        searchNode.setPromptText("Search for node");
        searchNode.setMaxWidth(150);
        new Auto(temp, searchNode);
        
        Button okBut = new Button("Ok");
        okBut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Contolloer.newTabNode((Object) searchNode.getText());
            }
        });
        
        ListOfNodesArea = new ArrayList<>();
        makeTreeAreaNode(root);
       new getMuseCordinates(this.root);
       for (int i = 0; i < noder.size(); i++) {
           makeTreeNodeSubArea(noder.get(i));
         }
        
        TreeView tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabNode(newvalue);}); 
        new getMuseCordinates(tree);
        
        HBox box = new HBox();
        box.setSpacing(10);
        box.getChildren().addAll(searchNode,okBut);
        
       pane.setTop(box);
       pane.setCenter(tree);
    Nodes.setContent(pane);
}
/**
 * Creates the different area in the tree view for the communication types tab
 * @param parentItem 
 */
  public void makeTreeAreaInterface(TreeItem<String> parentItem){
      for (int i = 1; i < InterfaceTypes.values().length+1; i++) {
          TreeItem<String> item = new TreeItem(InterfaceTypes.getTypes(i).toString());
          item.setExpanded(true);
          parentItem.getChildren().add(item);
          ListOfInterfaceArea.add(item);
      }
  }
  /**
   * Creates the different area in the tree view for the node tab
   * @param parentItem 
   */
  public void makeTreeAreaNode(TreeItem<String> parentItem){
      for (int i = 1; i < TSNTypes.values().length+1; i++) {
          TreeItem<String> item = new TreeItem(TSNTypes.getTypes(i).toString());
          item.setExpanded(true);          
          parentItem.getChildren().add(item);
          ListOfNodesArea.add(item);
      }
  }  
  /**
   * Creates the nodes that belongs to a desired area in the tree view in the tab for nodes 
   * @param node 
   */
  public void makeTreeNodeSubArea(TSN node){
      TreeItem<String> item = new TreeItem<>(node.getName());
      for (int i = 0; i <ListOfNodesArea.size(); i++) {          
          if (ListOfNodesArea.get(i).toString().toLowerCase().contains(node.getType().toString().toLowerCase())) {
              ListOfNodesArea.get(i).getChildren().add(item);
          }
      }
  }
  /**
   * Creates the communications types that belongs to desired area in the tree view in the tab for communication types. 
   * @param node 
   */
  public void makeTreeInterfaceSubArea(Interface node){
      TreeItem<String> item = new TreeItem<>(node.getName());
      for (int i = 0; i <ListOfInterfaceArea.size(); i++) {          
          if (ListOfInterfaceArea.get(i).toString().toLowerCase().contains(node.getType().toString().toLowerCase())) {
              ListOfInterfaceArea.get(i).getChildren().add(item);
          }
      }
  }
  /**
   * Creates the top line for the planning mode. 
   */
  public void topLineForPlanmode(){
     
     
      menulist.getMenus().remove(P_2_P_Menu);
      //////Time Choise/////////////////////
        dateFormatter = DateTimeFormatter.ofPattern(pattern);
        DatePicer = new DatePicker(DateToPresent);
        DatePicer.setShowWeekNumbers(true);
        new getMuseCordinates(DatePicer);
        
        Button SimulateButton = new Button("Simulate");
        //"-fx-base: #ccccb3"
        SimulateButton.setStyle("-fx-background-color: #ccccb3");
        SimulateButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              Contolloer.modeState(false, false, true);
          }
      });
        
        
        Tooltip tooltip = new Tooltip("Choose day, to see the missions that belongings to that day");
        tooltip.setWrapText(true);
        DatePicer.setTooltip(tooltip);
        
       
        
        DatePicer.setPromptText(pattern.toLowerCase());
       
        
        
        DatePicer.addEventHandler(ActionEvent.ACTION, new ChoiseOfDate());
        //tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabInterface(newvalue);});
        Label label = new Label("Date of mission");
        TopLine.getChildren().addAll(label,DatePicer,menulist,SimulateButton);
       
        
        ToplineVBox.getChildren().add(TopLine);
        root.setTop(ToplineVBox);
        DatePicer.requestFocus();
        
  }
  /**
   * Creates the top line for the for edit the missions
   */
  public void topLineforEdeting(){
       
        menulist.getMenus().add(P_2_P_Menu);
        /////////////////////////////////////////////////////////////
        TopLine.setAlignment(Pos.CENTER_LEFT);
        TopLine.getChildren().clear();
        TopLine.setSpacing(20);        
        TopLine.getChildren().addAll(BackButton,menulist);
        ToplineVBox.getChildren().clear();       
        ToplineVBox.setSpacing(5);
       
               
               
       ToplineVBox.getChildren().addAll(TopLine);
       
       root.setTop(ToplineVBox);
  }
  /**
   * Creates the scene to present the missions for the live and simulation mode  
   * @param Tasks
   * @param Simultate 
   */
  public void SetScreenForLiveAndSimulateMode(ObservableList<Task> Tasks,boolean Simultate){
      menulist.getMenus().remove(P_2_P_Menu);
      if (!Simultate) menulist.getMenus().add(P_2_P_Menu);
      ToplineVBox.getChildren().clear();    
      tabPane.getTabs().clear();
      TopLine.getChildren().clear();;
      TopLine.getChildren().addAll(BackButton,menulist);
      ToplineVBox.getChildren().add(TopLine);    
      
      tabLiveMode = new Tab("Live");
      tabPane.getTabs().clear();
     
      
      TableView<Task> table = new TableView();
      table.setEditable(true);
      new getMuseCordinates(table);
      //
      TableColumn checkBox = new TableColumn("Select");
      checkBox.setMaxWidth(50);
      checkBox.setCellFactory(new Callback<TableColumn<Task,String>,TableCell<Task,String>>(){
          @Override
          public TableCell<Task, String> call(TableColumn<Task, String> param) {
             return new TableCell<Task,String>(){
                  @Override public void updateItem(String string,boolean  isEmpty){
                    super.updateItem(string, isEmpty);
                     if (!isEmpty) {
                        final CheckBox check = new CheckBox();
                         setGraphic(check);
                         check.setOnAction(new EventHandler<ActionEvent>() {
                             @Override
                             public void handle(ActionEvent event) {
                                 //getTableView().getItems().get(getTableRow().getIndex())
                                 Contolloer.setDesierdTask(getTableView().getItems().get(getTableRow().getIndex()).getName());
                                
                                 
                             }
                         });
                     }
                     }
             };
          }
         
      });
         
      
      
      // Colum 1 Mission
      TableColumn misionColumn = new TableColumn("Mission");
        misionColumn.setMaxWidth(200);
        misionColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("Name")); 
           
        
      // Colum 2 info
      TableColumn<Task,String> InfoColumn = new TableColumn("Info");
        InfoColumn.setMinWidth(150);
        InfoColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("info"));
        InfoColumn.setCellFactory(new Callback<TableColumn<Task,String>,TableCell<Task,String>>(){
           @Override
           public TableCell<Task, String> call(TableColumn<Task, String> param) {
               return new TextFieldTableCell<Task,String>(){
                 @Override public void updateItem(String string,boolean  isEmpty){
                    super.updateItem(string, isEmpty);
                     if (!isEmpty) {
                         Task temp = getTableView().getItems().get(getTableRow().getIndex());
                         Tooltip tip = new Tooltip(temp.getInfo());
                         setTooltip(tip);
                         setEditable(false);
                     }
                    
                 }
               };
           };
            
        });
        
        TableColumn NrOfWorkingNode = new TableColumn("Operating nodes");
        NrOfWorkingNode.setMaxWidth(150);
        NrOfWorkingNode.setCellValueFactory(new PropertyValueFactory<Task, Integer>("percentOfWorkingsNodes"));
        NrOfWorkingNode.setCellFactory(new Callback<TableColumn<Task,Integer>,TableCell<Task,Integer>>(){
          @Override
          public TableCell<Task, Integer> call(TableColumn<Task, Integer> param) {
             return  new TextFieldTableCell<Task,Integer>(){
               @Override public void updateItem(Integer value,boolean  isEmpty){                  
                           // int temp = getTableView().getItems().get(getTableRow().getIndex()).getPercentOfWorkingsNodes();
                          if (!isEmpty) {
                            String text = value+"%";
                            setText(text);
                   }
             };
          };
          }
         
          
            
        });
       
        //Colum 4 Error for 
        TableColumn NodeErros = new TableColumn("Nodes with Error");
        NodeErros.setMinWidth(150);
        NodeErros.setCellValueFactory(new PropertyValueFactory<Task,String>("ErrorNodeName"));
        NodeErros.setCellFactory(new Callback<TableColumn<Task,String>,TableCell<Task,String>>() {
           @Override
           public TableCell<Task, String> call(TableColumn<Task, String> param) {
             TableCell<Task, String> cell = new TableCell<Task, String>(){
                    @Override
                    public void updateItem(String item, boolean empty) {
                        if(item!=null){
                           ObservableList<String> temp = FXCollections.observableArrayList();
                          ObservableList<TSN> temp2 = FXCollections.observableArrayList();
                          temp2.addAll(getTableView().getItems().get(getTableRow().getIndex()).getListOfNodesErros());
                           
                            Menu m0 = new Menu("Nodes");
                            m0.setStyle(getTableRow().getStyle());
                            
                             if (temp2.size() != 0) {
                                
                            for (int i = 0; i < temp2.size(); i++) {
                               
                                temp.add(temp2.get(i).getName());
                                Menu mTemp = new Menu(temp2.get(i).getName());
                                m0.getItems().add(mTemp);  
                                
                               
                                
                                for (int j = 0; j < temp2.get(i).getListOfInterfasErros().size(); j++) {
                                   MenuItem tempc = new MenuItem(temp2.get(i).getListOfInterfasErros().get(j).getName());
                                    mTemp.getItems().add(tempc);
                                    final String missionTemp = getTableView().getItems().get(getTableRow().getIndex()).getName();
                                    final String nameTemp = temp2.get(i).getListOfInterfasErros().get(j).getName();                                    
                                    
                                    
                                    tempc.setOnAction(new EventHandler<ActionEvent>() {
                                       @Override
                                       public void handle(ActionEvent event) {
                                          Contolloer.choiseOfInterfaceLiveMode(nameTemp, missionTemp);
                                       }
                                   });
                                   
                                    
                                
                                }
                            }
                           }
                            
                           
                           MenuBar mb = new MenuBar(m0);
                           mb.setStyle(getTableRow().getStyle());
                           mb.setMaxWidth(90);
                           new getMuseCordinates(mb);
                           
                           
                           setGraphic(mb);
                           Tooltip tip = new Tooltip();
                           tip.setText(temp.toString());
                           setTooltip(tip);
                          // tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabNode(newvalue);}); 
                            
                        } 
                    }
                };
             
             
               return cell;
           }
       });
        
       
       
        table.setItems(Tasks);
        if (Simultate) {
          table.getColumns().addAll(misionColumn,InfoColumn,NrOfWorkingNode,NodeErros);
      }
        else{
            table.getColumns().addAll(checkBox,misionColumn,InfoColumn,NrOfWorkingNode,NodeErros);
        }
                
        tabLiveMode.setContent(table);
        tabLiveMode.setClosable(false);
        
      tabPane.getTabs().add(tabLiveMode);
      root.setCenter(tabPane);
  }  
  /**
   * Creates the scene to present the missions for the planning mode
   * @param Tasks 
   */
  public void screenForPlanMode(ObservableList<Task> Tasks){
      tabPlanScren = new Tab("Missions");
      tabPane.getTabs().clear();
      TopLine.getChildren().clear();
      root.setLeft(null);
      ToplineVBox.getChildren().clear();      
      topLineForPlanmode();
      SetColor();
      State = "Plan";
      new getMuseCordinates(tabPane);
      //
       

      
      
      ///Update level of ranks
      ObservableList<Integer> ratingSample = FXCollections.observableArrayList();
        for (int i = 1; i < Tasks.size()+1; i++) {
          ratingSample.add(i);           
        }
      
        // Tabels
      TableView<Task> table = new TableView<>();     
      new getMuseCordinates(table);
      
      table.setEditable(true);
 
        TableColumn misionColumn = new TableColumn("Mission");
        misionColumn.setMinWidth(200);
        misionColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("Name"));
      
 
 
        TableColumn InfoColumn = new TableColumn("Mission info");
        InfoColumn.setMinWidth(200);
        InfoColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("info"));     
        
 
        TableColumn OrgColumn = new TableColumn("Orginasation");
        OrgColumn.setMinWidth(200);
        OrgColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("orginsastion"));
      
        
       
        //////////////////////////////////////////////////////////////////////
       
        TableColumn rankColumn = new TableColumn("Rank");
        rankColumn.setMinWidth(100);
        rankColumn.setCellValueFactory(new PropertyValueFactory<Task,Integer>("rank"));
        rankColumn.setCellFactory(ComboBoxTableCell.forTableColumn(ratingSample));
        rankColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Task,Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Task,Integer> t) {               
                ((Task) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setRank(t.getNewValue());
                
                
               
            }
        });
        //rankColumn.
       
        Tooltip tooltip = new Tooltip();
        tooltip.setText("Double click at the rank number to change the value");
        //tooltip.wrapTextProperty(true);
        // Tooltip.install(rankColumn, tooltip);
//////////////////////////////////////////////////////////////////////
 
        table.setTooltip(tooltip);
        table.setItems(Tasks);
        table.getColumns().addAll(misionColumn, rankColumn,OrgColumn ,InfoColumn);           
        table.setRowFactory(tv -> {
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && !event.getPickResult().getIntersectedNode().toString().toLowerCase().contains("ComboBoxTableCell".toLowerCase())){
                   Task clikrow = row.getItem();
                    Contolloer.ChoiseOfTaskPlanMode(clikrow);
                }
            });
            
            
          return row;
        });
        
        VBox box = new VBox();
        box.getChildren().add(table);
        tabPlanScren.setContent(box);
        tabPlanScren.setClosable(false); 
        tabPane.getTabs().add(tabPlanScren);
      
      
      
  }
/**
 * Creates the scene to make a point to point connection. The user is searching for nodes and communication type and selects the priority and quality in qombobox 
 * @param Nodes
 * @param inters 
 */
  public void P_2_PScreen(ArrayList<String> Nodes,ArrayList<String> inters){    
     
      ObservableList<String> levels = FXCollections.observableArrayList();
      for (int i = 1; i < priorityAndQulaityLevels.values().length+1; i++) {
          levels.add(priorityAndQulaityLevels.getTypes(i).toString());
      }
      
      tabP_2_P = new Tab("P_2_P");
      tabPane.getTabs().remove(tabP_2_P);
      GridPane pnet = new GridPane();
      Label nod1 = new Label("Node 1");
      Label nod2 = new Label("Node 2");
      Label comType = new Label("Com type");
      new getMuseCordinates(pnet);
      // text.setMouseTransparent(true);
      // text.setFocusTraversable(false);
      ///Search window for node 1
     final TextField textnode1P2P = new TextField();
      textnode1P2P.setPromptText("Node 1");
      new getMuseCordinates(textnode1P2P);
      new Auto(Nodes, textnode1P2P);  
      textnode1P2P.setFocusTraversable(true);
     
         
      
      // Search window for node 2
    final TextField textnode2P2P = new TextField();      
      textnode2P2P.setPromptText("Node 2");
      new getMuseCordinates(textnode2P2P);
      new Auto(Nodes, textnode2P2P);
      textnode2P2P.setFocusTraversable(false);
      textnode2P2P.setMouseTransparent(true);
      
       textnode1P2P.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
             
              textnode2P2P.setMouseTransparent(false);
              textnode2P2P.setFocusTraversable(true);             
          }
      });  
      // Search window for com type
      TextField textComTypeP2P = new TextField();
      textComTypeP2P.setPromptText("Comunication type");
      new getMuseCordinates(textComTypeP2P);
      new Auto(inters, textComTypeP2P);
      textComTypeP2P.setFocusTraversable(false);
      textComTypeP2P.setMouseTransparent(true);
      
      textnode2P2P.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              textComTypeP2P.setMouseTransparent(false);
              textComTypeP2P.setFocusTraversable(true);
          }
      });
      
      //Combobox for Priority
     final ComboBox<String> priBox = new ComboBox<>(levels);
     priBox.setPromptText("Priority");
     priBox.setMouseTransparent(true);
     new getMuseCordinates(priBox);
    
     // Combobox for quality
    final ComboBox<String> QualbBox = new ComboBox<>(levels);
      QualbBox.setPromptText("Quality");
      QualbBox.setMouseTransparent(true);
      new getMuseCordinates(QualbBox);
      
      textComTypeP2P.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              priBox.setMouseTransparent(false);
          }
      });
      
       priBox.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              QualbBox.setMouseTransparent(false);
          }
      });
       
       
      // Ok button 
      okButton = new Button("Ok");      
      okButton.localToScene(okButton.getBoundsInLocal());
      new getMuseCordinates(okButton);
    
      
      
      
       okButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              if (priBox.getValue() != null && QualbBox.getValue() != null && !textnode1P2P.getText().trim().isEmpty() && !textnode2P2P.getText().trim().isEmpty() && !textComTypeP2P.getText().trim().isEmpty()) {
                  Contolloer.setNodesAndComTypeForP_2_P(textnode1P2P.getText(),textnode2P2P.getText(),textComTypeP2P.getText(),priBox.getValue(),QualbBox.getValue());
                  tabPane.getTabs().remove(tabP_2_P);
              }
          }
      });
        
      
      pnet.setHgap(20);
      pnet.setVgap(20);
      /////Tooltips
      Tooltip tip1 = new Tooltip("Type the first two letters in the text field and choose the desired first node");
      tip1.setWrapText(true);
      textnode1P2P.setTooltip(tip1);
      nod1.setTooltip(tip1);
      
      Tooltip tip2 = new Tooltip("Type the first two letters in the text field and choose the desired secend node");
      tip2.setWrapText(true);
      textnode2P2P.setTooltip(tip2);
      nod2.setTooltip(tip2);
      
      Tooltip tip3 = new Tooltip("Type the first two letters in the text field and choose the desired communication type");
      tip3.setWrapText(true);
      textComTypeP2P.setTooltip(tip3);
      comType.setTooltip(tip3);
      
      //////Node 1
      pnet.add(nod1, 1, 1);
      pnet.add(textnode1P2P, 2, 1);
      
      ///Node 2
      pnet.add(nod2, 1, 2);
      pnet.add(textnode2P2P, 2, 2);
      
      ///Comunication type
      pnet.add(textComTypeP2P, 2, 3);
      pnet.add(comType, 1, 3);
      
      ///ChoiceBox
      pnet.add(priBox, 1, 4);
      pnet.add(QualbBox, 2, 4);
      
      //Button
      pnet.add(okButton, 3, 4);
      tabP_2_P.setContent(pnet);
      
      tabPane.getTabs().add(tabP_2_P);
      
  }    
  /**
   * Creates the scene to edit the  of properties of the node or the communication type 
   * @param temp
   * @param itemp 
   */
  public void nodeAndComtypeTab(TSN temp,Interface itemp){
      
      String name = null,info = null,priority = null,quality = null;
      Image image = null;// = new Image(getClass().getResourceAsStream("noPic.JPG"));
      
      if (temp != null) {
          name = temp.getName();
          info = temp.getInfo();
          priority = temp.getPriority().toString();
          quality = temp.getQuality().toString();
          image = temp.getImage();
      }
      else if(itemp != null){
          name = itemp.getName();
          info = itemp.getInfo();         
          priority = itemp.getPriority().toString();
          quality = itemp.getQuality().toString();                      
          image = itemp.getImage();
      }
      
     
     final Tab tab = new Tab(name);
      ObservableList<String> levels = FXCollections.observableArrayList();
      
      
      for (int i = 1; i < priorityAndQulaityLevels.values().length+1; i++) {
          levels.add(priorityAndQulaityLevels.getTypes(i).toString());
      }
      
      BorderPane pane = new BorderPane();
      GridPane CenterPane = new GridPane();
      GridPane LeftPane = new GridPane();
      Label PriLabel = new Label("Priority: ");
      Label QuaLabel = new Label("Quality: ");
      Label infoLabel = new Label("Information");
     final  ComboBox<String> PriBox = new ComboBox<>(levels);
      
      
    //  PriBox.setPromptText("Priority");
     PriBox.setValue(priority);
    final  ComboBox<String> QulBox = new ComboBox<>(levels);      
    //  QulBox.setPromptText("Quality");
     QulBox.setValue(quality);
       TextArea text = new TextArea(info);
       text.setMaxWidth(150);
       text.setMinHeight(10);
      // text.setPrefColumnCount(10);
       text.setWrapText(true);
       text.setMouseTransparent(true);
       text.setFocusTraversable(false);
        
        
       Button okButton = new Button("Ok");
       okButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              if (PriBox.getValue() != null ) {
                  if (temp != null) {
                      temp.setPriority(priorityAndQulaityLevels.getTypes(PriBox.getSelectionModel().getSelectedIndex()+1));
                      temp.setQuality(priorityAndQulaityLevels.getTypes(QulBox.getSelectionModel().getSelectedIndex()+1));
                      Contolloer.newNode(temp);
                      
                  }
                  else if (itemp != null) {                     
                      itemp.SetPriority(priorityAndQulaityLevels.getTypes(PriBox.getSelectionModel().getSelectedIndex()+1));
                      itemp.setQuality(priorityAndQulaityLevels.getTypes(QulBox.getSelectionModel().getSelectedIndex()+1));
                      Contolloer.newInterface(itemp);
                  }
                  tabPane.getTabs().remove(tab);
              }
          }
      });
       
      
       //Center pane
       CenterPane.setVgap(20);
       CenterPane.setHgap(5);
       CenterPane.setAlignment(Pos.CENTER_LEFT);
       
       
       //LeftPane
       LeftPane.setVgap(10);
       LeftPane.setHgap(10);
       
       
      //ImageView Image = new ImageView();
      ImageView imageView = new ImageView();
      imageView.setImage(image);
      imageView.setFitHeight(75);
      imageView.setFitWidth(125);      
      imageView.setSmooth(true);
      imageView.setCache(true);
      
      
     
      
     
      
      //TextArea
      LeftPane.add(infoLabel, 1, 2);
      LeftPane.add(text, 1, 3);
      
      //ChoiceBox
      CenterPane.add(PriLabel, 2, 2);
      CenterPane.add(PriBox, 3, 2);
   //   if (itemp != null) {
      CenterPane.add(QuaLabel, 2, 3);
      CenterPane.add(QulBox, 3, 3);
       //Image
      LeftPane.add(imageView, 1, 1);
    //  }
     
      
      //ok button
       CenterPane.add(okButton, 7, 3);
       pane.setLeft(LeftPane);
       pane.setCenter(CenterPane);
      tab.setContent(pane);
              
      tabPane.getTabs().add(tab);
  }    
  /**
   * Alert function to the user
   * @param info
   * @param head
   * @param titel 
   */  
  public void AlertToUser(String info, String head, String titel){
      Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(head);
        alert.setTitle(titel);
        alert.setContentText(info);
        alert.show();
  }
}

  
  
