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
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
   private GridPane  border;
   private Button okButton,BackButton;
   private MenuItem SetGroupOrg,SetPrioritForAllTask,SetQualityForAllTask,SetQualityForOneTask,SetPriorityForOneTask;
   private MenuItem SendToSystem,P_2_P_MenuItem,SendToSystemItem;
   private guiControler Contolloer;
   private Menu SetTaskmMenu,SetInterfacemMenu,P_2_P_Menu,SendMenu,ModeMenu;   
   private CheckMenuItem Plan,Live,Simulate;
   private ListView<String> ListOfTasks;
   private HBox TopLine,TopLineLine2,CenterHBox;
   private VBox ToplineVBox,CentetVBox;
   private MenuBar menulist;
   private GridPane Net;
   private Tab Overview,Interface,Nodes,tabPlanScren,tabP_2_P;
   private TabPane tabPane;
   private ArrayList<TreeItem> ListOfInterfaceArea,ListOfNodesArea;
   private DatePicker DatePicer;
   private final String pattern = "yyyy-mm-dd";
   private DateTimeFormatter dateFormatter;
   private LocalDate DateToPresent;
   private boolean lockUpNode2 = false;
   private TextField textnode1P2P,textnode2P2P,textComTypeP2P;
    
    @Override   
    
    public void start(Stage primaryStage) {
       
        Contolloer = new guiControler(this);        
        root = new BorderPane();
        tabPane = new TabPane();
        border = new GridPane();
        TopLine = new HBox(20);
        TopLineLine2 = new HBox();
        ToplineVBox = new VBox();
        Contolloer.setScreen();        
        Live.setSelected(false);
        Simulate.setSelected(false);
        menulist = new MenuBar();
        BackButton = new Button("Back");
        DateToPresent = LocalDate.now();
        ///////////////////////////////////////////////////////////////////////
        BackButton.addEventFilter(ActionEvent.ACTION, new BackButton());
        Contolloer.SetDate(LocalDate.now());
        menulist.getMenus().addAll(P_2_P_Menu,ModeMenu);    
       
       
        root.setCenter(tabPane);
        Contolloer.modeState();
        SetColor();
        Scene scene = new Scene(root, 700, 320);
        
        primaryStage.setTitle("GUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
    
  public void SetTabsForLiveMode(){
        tabPane.getTabs().clear();
        Overview = new Tab("Overwiew");
        Interface = new Tab("Comunication type");
        Nodes = new Tab("Nodes");
        Overview.setClosable(false);
        Interface.setClosable(false);
        Nodes.setClosable(false);        
        tabPane.getTabs().addAll(Overview,Interface,Nodes);      
           
        
        
   }
  
  public void SetScreenForLiveMode(){
       Label Task = new Label("Task");
       TopLineLine2.getChildren().clear();
       ToplineVBox.getChildren().clear();
      TopLineLine2.getChildren().addAll(Task);
      tabPane.getTabs().clear();
      ToplineVBox.getChildren().addAll(menulist,TopLineLine2);
      root.setLeft(ListOfTasks);
  }  
  
  public void ModeMenu(){
    ModeMenu = new Menu("_Mode");
    Plan = new CheckMenuItem("Plan");
    Plan.addEventHandler(ActionEvent.ACTION, new ModeMenuChoice());
    Live = new CheckMenuItem("Live");
    Live.addEventHandler(ActionEvent.ACTION, new ModeMenuChoice());
    Simulate = new CheckMenuItem("Simnulate");
    Simulate.addEventHandler(ActionEvent.ACTION, new ModeMenuChoice());
    ModeMenu.getItems().addAll(Plan,Live,Simulate);

}   

  public void TaskMenu(){
    SetTaskmMenu = new Menu("_Task");
    SetPrioritForAllTask = new MenuItem("Set Priority for all task");
    SetPrioritForAllTask.addEventHandler(ActionEvent.ACTION, new MenuTaskChoice());
   SetQualityForAllTask = new MenuItem("Set Quality for all task");
   SetQualityForAllTask.addEventHandler(ActionEvent.ACTION, new MenuTaskChoice());
    SetTaskmMenu.getItems().addAll(SetPrioritForAllTask,SetQualityForAllTask);
        
}

  public void InterfaceMenu(){
    SetInterfacemMenu = new Menu("_Comunication type");
    SetPriorityForOneTask = new MenuItem("Set Priority");
    SetPriorityForOneTask.addEventHandler(ActionEvent.ACTION, new menuInterfaceChoice());
    SetQualityForOneTask = new MenuItem("Set Quality");
    SetQualityForOneTask.addEventHandler(ActionEvent.ACTION, new menuInterfaceChoice());
    SetInterfacemMenu.getItems().addAll(SetPriorityForOneTask,SetQualityForOneTask);
}

  public void SendMenu(){
    SendMenu = new Menu("_Send");
    SendToSystem = new Menu("Send to system");
    SendToSystem.addEventHandler(ActionEvent.ACTION, new MenuSendChoice());
    SendMenu.getItems().add(SendToSystem);
}

  public void P_2_PMenu(){
    P_2_P_Menu = new Menu("_P_2_P");
    P_2_P_Menu.addEventHandler(ActionEvent.ACTION, new MenuP_2_PChoice());
    P_2_P_MenuItem = new MenuItem("P-2-P");
    P_2_P_MenuItem.addEventHandler(ActionEvent.ACTION, new MenuP_2_PChoice());
    P_2_P_Menu.getItems().add(P_2_P_MenuItem);
} 
  
  public void setListOfTask(){
    ListOfTasks = new ListView<String>();
    ListOfTasks.setPrefSize(100, 280);
    ListOfTasks.getSelectionModel().selectedIndexProperty().addListener((v,oldvalue,newvalue)-> Contolloer.setDesierdTask((int) newvalue));
    
}
  
  public void UppdateListOfTask(ArrayList<String> task){
      tabPane.getTabs().clear();
     ListOfTasks.getItems().clear();
     ListOfTasks.getItems().addAll(task);
   
}

  public void SetColor(){
    TopLine.setStyle("-fx-background-color: #ccccb3");
    TopLineLine2.setStyle("-fx-background-color: #ccccb3");
    menulist.setStyle("-fx-background-color: #ccccb3");
    ToplineVBox.setStyle("-fx-background-color: #ccccb3");

} 

  private class ChoiseOfDate implements EventHandler<ActionEvent>{

               @Override
        public void handle(ActionEvent event) {
           
            DateToPresent = DatePicer.getValue();
            DatePicer.setValue(DateToPresent);
            Contolloer.SetDate(DateToPresent);
            
        }
    } 
   
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

  private class P_2_PButtonChice implements EventHandler<ActionEvent>{       

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == okButton) {
                tabPane.getTabs().remove(tabP_2_P);
            }
        }
    } 

  private class ModeMenuChoice implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == Plan) {
               
                Contolloer.modeState(true, false,false);
            }
            else if (event.getSource() == Live) {
               
                Contolloer.modeState(false, true,false);
            }
            else if (event.getSource() == Simulate) {
             
                Contolloer.modeState(false, false,true);
            }
        }
    }
    
  public void upDateModeState(boolean Plan,boolean Live,boolean Simulate){
        this.Plan.setSelected(Plan);
        this.Live.setSelected(Live);
        this.Simulate.setSelected(Simulate);
    }
    
  private class MenuP_2_PChoice implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
           
            if (event.getSource() == P_2_P_MenuItem) {
                System.out.println("P_2_P\n");
                Contolloer.updateP_2_P();
                
            }
        }
    }

  private class MenuSendChoice implements EventHandler<ActionEvent>{

       

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == SendToSystem) {
                System.out.println("Send To System\n");
                
            }
        }
    }  

  private class MenuTaskChoice implements EventHandler<ActionEvent>{

        
        @Override
        public void handle(ActionEvent event) {
            
            if (event.getSource() == SetQualityForAllTask) {
                System.out.println("Set Quality\n");
                
            }else if (event.getSource() == SetPrioritForAllTask) {
                System.out.println("Set Priority\n");
            }
                
        }
    }

  private class menuInterfaceChoice implements EventHandler<ActionEvent>{

       
        
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == SetPriorityForOneTask) {
                System.out.println("Set priority\n");
            }
            else if(event.getSource() == SetQualityForOneTask){
                System.out.println("Set Quality\n");
            }
        }
    }   
    
  public void  OverViewSceen(int Rank,String info,Calendar startDate,Calendar endDate, Task temp){
        Net = new GridPane();
        CenterHBox = new HBox();
        CentetVBox = new VBox();
        Label labelText = new Label("Information:");
        //Text texttest = new Text(info);
        TextArea text = new TextArea(temp.getInfo());
        text.setMaxWidth(150);
        text.setPrefColumnCount(10);
        text.setWrapText(true);
        text.setEditable(false);
        text.setMouseTransparent(true);
        text.setFocusTraversable(false);
        
        ////////////////////////////////////////////////////////////////
        System.out.println("This mision has priority: "+temp.getRank());
        //System.out.println("of: "+TotalRank);
        
        ////////////////////////////////////////////////////////////////
        
        Label globalPriotet = new Label("This mision has priority: "+temp.getRank());
        Label startDayLabel = new Label("The mission begins at: "+temp.getStartTime().getTime());
        Label endDateLabel = new Label("The mission ends at: "+temp.getEndTime().getTime());
       // Label globalQuality = new Label("of "+TotalRank);
        Net.setVgap(20);
        Net.setHgap(20);
        Net.setAlignment(Pos.TOP_CENTER);
        CentetVBox.setAlignment(Pos.TOP_CENTER);
        Net.add(globalPriotet,0,2);
        //Net.add(globalQuality, 0, 3);
        
        //Label temp1 = new Label(GlobalPriorityIput.toString());
       // Label temp2 = new Label(GlobalQualityInput.toString());
        Net.add(startDayLabel, 0, 3);
        Net.add(endDateLabel, 0, 4);
        CentetVBox.getChildren().addAll(labelText,text);
        CenterHBox.setSpacing(20);
        CenterHBox.setAlignment(Pos.CENTER_LEFT);
        CenterHBox.getChildren().addAll(CentetVBox,Net);        
        Overview.setContent(CenterHBox);
        
    }
  
  public void InterfaceScreen(ArrayList<Interface> noder){      
       
        TreeItem<String> root;        
        root = new TreeItem<>();
        root.setExpanded(true);
        BorderPane pane = new BorderPane();
        TextField textComType = new TextField();
        textComType.setPromptText("Search for comunication type");
        textComType.setMaxWidth(220);
        ListOfInterfaceArea = new ArrayList<>();        
        makeTreeAreaInterface(root);
      
        for (int i = 0; i < noder.size(); i++) {
            makeTreeInterfaceSubArea(noder.get(i));
        }
        
        TreeView tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabInterface(newvalue);});         
        pane.setTop(textComType);
        pane.setCenter(tree);
    Interface.setContent(pane);
}
  
  public void NodeTabScreen(ArrayList<TSN> noder){
     
        TreeItem<String> root;
        
        root = new TreeItem<>();
        root.setExpanded(true);
        BorderPane pane = new BorderPane();
        TextField searchNode = new TextField();
        searchNode.setPromptText("Search for node");
        searchNode.setMaxWidth(150);
        ListOfNodesArea = new ArrayList<>();
        makeTreeAreaNode(root);
       
       for (int i = 0; i < noder.size(); i++) {
           makeTreeNodeSubArea(noder.get(i));
         }
        
        TreeView tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabNode(newvalue);}); 
        
       pane.setTop(searchNode);
       pane.setCenter(tree);
    Nodes.setContent(pane);
}

  public TreeItem<String> makeBrach(String titel,TreeItem<String> parent){
        TreeItem<String> item = new TreeItem<>(titel);
        item.setExpanded(true);        
        parent.getChildren().add(item);
        return item;
    }
  
  public void makeTreeAreaInterface(TreeItem<String> parentItem){
      for (int i = 1; i < InterfaceTypes.values().length+1; i++) {
          TreeItem<String> item = new TreeItem(InterfaceTypes.getTypes(i).toString());
          item.setExpanded(true);
          parentItem.getChildren().add(item);
          ListOfInterfaceArea.add(item);
      }
  }
  
  public void makeTreeAreaNode(TreeItem<String> parentItem){
      for (int i = 1; i < TSNTypes.values().length+1; i++) {
          TreeItem<String> item = new TreeItem(TSNTypes.getTypes(i).toString());
          item.setExpanded(true);
          parentItem.getChildren().add(item);
          ListOfNodesArea.add(item);
      }
  }  
  
  public void makeTreeNodeSubArea(TSN node){
      TreeItem<String> item = new TreeItem<>(node.getName());
      for (int i = 0; i <ListOfNodesArea.size(); i++) {          
          if (ListOfNodesArea.get(i).toString().toLowerCase().contains(node.getType().toString().toLowerCase())) {
              ListOfNodesArea.get(i).getChildren().add(item);
          }
      }
  }
  
  public void makeTreeInterfaceSubArea(Interface node){
      TreeItem<String> item = new TreeItem<>(node.getName());
      for (int i = 0; i <ListOfInterfaceArea.size(); i++) {          
          if (ListOfInterfaceArea.get(i).toString().toLowerCase().contains(node.getType().toString().toLowerCase())) {
              ListOfInterfaceArea.get(i).getChildren().add(item);
          }
      }
  }
  
  public void topLineForPlanmode(){
     
      TopLineLine2 = new HBox();      
      TopLineLine2.setSpacing(20);
      //////Time Choise/////////////////////
        dateFormatter = DateTimeFormatter.ofPattern(pattern);
        DatePicer = new DatePicker(DateToPresent);
        DatePicer.setShowWeekNumbers(true);
        
        Tooltip tooltip = new Tooltip("Choose day, to see the missions that belongings to that day");
        tooltip.setWrapText(true);
        DatePicer.setTooltip(tooltip);
        
        /*StringConverter converter = new StringConverter<LocalDate>(){
            @Override
            public String toString(LocalDate object) {
                if (object != null) {
                    return dateFormatter.format(object);
                }
                else{
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                }
                else{
                    return null;
                }
            }
            
        };*/
         
    
       
        
        DatePicer.setPromptText(pattern.toLowerCase());
        System.out.println("Time: "+DatePicer.getValue());
        
        
        DatePicer.addEventHandler(ActionEvent.ACTION, new ChoiseOfDate());
        //tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabInterface(newvalue);});
        Label label = new Label("Date of mision");
        TopLineLine2.getChildren().addAll(label,DatePicer,menulist);
        
        ToplineVBox.getChildren().add(TopLineLine2);
        root.setTop(ToplineVBox);
        DatePicer.requestFocus();
        
  }
  
  public void topLineforEdeting(){
       
        
        /////////////////////////////////////////////////////////////
        TopLine.setAlignment(Pos.CENTER_LEFT);
        TopLine.getChildren().clear();
        TopLine.setSpacing(20);        
        TopLine.getChildren().addAll(BackButton,menulist);
        ToplineVBox.getChildren().clear();
        TopLineLine2.getChildren().clear();       
        TopLineLine2.setSpacing(20);
        ToplineVBox.setSpacing(5);
       
               
               
       ToplineVBox.getChildren().addAll(TopLine,TopLineLine2);
       
       root.setTop(ToplineVBox);
  }
  
  public void screenForPlanMode(ObservableList<Task> Tasks){
      tabPlanScren = new Tab("Missions");
      tabPane.getTabs().clear();
      TopLine.getChildren().clear();
      root.setLeft(null);
      ToplineVBox.getChildren().clear();      
      topLineForPlanmode();
      SetColor();
      State = "Plan";
      ///Update level of ranks
      ObservableList<Integer> ratingSample = FXCollections.observableArrayList();
        for (int i = 1; i < Tasks.size()+1; i++) {
          ratingSample.add(i);           
        }
      
        // Tabels
      TableView<Task> table = new TableView<>();     
      
      
      table.setEditable(true);
 
        TableColumn misionColumn = new TableColumn("Mission");
        misionColumn.setMinWidth(200);
        misionColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("Name"));
      
 
 
        TableColumn InfoColumn = new TableColumn("Mission info");
        InfoColumn.setMinWidth(200);
        InfoColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("info"));
      
        
        /*TableColumn StartTimeColumn = new TableColumn("Start Time");
        StartTimeColumn.setMinWidth(150);
        StartTimeColumn.setCellValueFactory(new PropertyValueFactory<Task,Date>("StartTime"));
        
        TableColumn EndTimeColumn = new TableColumn("End time");
        StartTimeColumn.setMaxWidth(150);
        EndTimeColumn.setCellValueFactory(new PropertyValueFactory<Task,Date>("EndTime"));*/
       
 
        TableColumn OrgColumn = new TableColumn("Orginasation");
        OrgColumn.setMinWidth(200);
        OrgColumn.setCellValueFactory(
            new PropertyValueFactory<Task, String>("orginsastion"));
       // OrgColumn.setCellFactory(TextFieldTableCell.forTableColumn());
      
        
       
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
                System.out.println(event.getPickResult().getIntersectedNode());
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
      
      // text.setMouseTransparent(true);
      // text.setFocusTraversable(false);
      ///Search window for node 1
      textnode1P2P = new TextField();
      textnode1P2P.setPromptText("Node 1");
      new Auto(Nodes, textnode1P2P);  
      textnode1P2P.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              lockUpNode2 = true;
              textnode2P2P.setMouseTransparent(false);
              textnode2P2P.setFocusTraversable(true);
              System.out.println("Hello");
          }
      });      
      
      // Search window for node 2
      textnode2P2P = new TextField();      
      textnode2P2P.setPromptText("Node 2");
      new Auto(Nodes, textnode2P2P);
      textnode2P2P.setFocusTraversable(false);
      textnode2P2P.setMouseTransparent(true);
      textnode2P2P.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              textComTypeP2P.setMouseTransparent(false);
              textComTypeP2P.setFocusTraversable(true);
          }
      });
      
      // Search window for com type
      textComTypeP2P = new TextField();
      textComTypeP2P.setPromptText("Comunication type");
      new Auto(inters, textComTypeP2P);
      textComTypeP2P.setFocusTraversable(false);
      textComTypeP2P.setMouseTransparent(true);
      
      
      
      //Combobox for Priority
     final ComboBox<String> priBox = new ComboBox<>(levels);
     priBox.setPromptText("Priority");
     priBox.setMouseTransparent(true);
    
     // Combobox for quality
    final ComboBox<String> QualbBox = new ComboBox<>(levels);
      QualbBox.setPromptText("Quality");
      QualbBox.setMouseTransparent(true);
      
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
      //okButton.addEventHandler(ActionEvent.ACTION, new P_2_PButtonChice(){});
       //tree.getSelectionModel().selectedItemProperty().addListener((v,oldvalue,newvalue) -> {Contolloer.newTabNode(newvalue);}); 
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
  
  public void nodeAndComtypeTab(TSN temp,Interface itemp){
      
      String name = null,info = null,priority = null,quality = null;
      if (temp != null) {
          name = temp.getName();
          info = temp.getInfo();
          priority = temp.getPriority().toString();
          quality = temp.getQuality().toString();
      }
      else if(itemp != null){
          name = itemp.getName();
          info = itemp.getInfo();         
          priority = itemp.getPriority().toString();
          quality = itemp.getQuality().toString();
      }
      
      System.out.println("Priority: "+priority);
      System.out.println("Quality: "+quality);
     final Tab tab = new Tab(name);
      ObservableList<String> levels = FXCollections.observableArrayList();
      
      
      for (int i = 1; i < priorityAndQulaityLevels.values().length+1; i++) {
          levels.add(priorityAndQulaityLevels.getTypes(i).toString());
      }
      GridPane netPane = new GridPane();
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
       text.setMinHeight(130);
       text.setPrefColumnCount(10);
       text.setWrapText(true);
       text.setMouseTransparent(true);
       text.setFocusTraversable(false);
        
        
       Button okButton = new Button("Ok");
       okButton.setOnAction(new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
              if (PriBox.getValue() != null && QulBox.getValue() != null ) {
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
       
       
      //ImageView Image = new ImageView();
      //Image image1 = new Image(Main.class.getResourceAsStream(""));
      
      netPane.setVgap(20);
      netPane.setHgap(20);
      
      //Image
      //netPane.add(Image, 1, 0);
      
      //TextArea
      netPane.add(infoLabel, 1, 2);
      netPane.add(text, 1, 3);
      
      //ChoiceBox
      netPane.add(PriLabel, 2, 1);
      netPane.add(PriBox, 2, 2);
      netPane.add(QuaLabel, 3, 1);
      netPane.add(QulBox, 3, 2);
      
      //ok button
       netPane.add(okButton, 7, 2);
      tab.setContent(netPane);
              
      tabPane.getTabs().add(tab);
  }

    private static class EventHandlerImpl implements EventHandler<KeyEvent> {

        public EventHandlerImpl() {
        }

        @Override
        public void handle(KeyEvent event) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
}

  
  
