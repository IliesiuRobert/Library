package View;

import View.Model.BookDTO;
import View.Model.SaleDTO;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;


public class BookView {
    private TableView bookTableView;
    private TableView saleTableView;
    private ObservableList<BookDTO> booksObservableList;
    private ObservableList<SaleDTO> saleObservableList;
    private TextField authorTextField;
    private TextField titleTextField;
    private TextField amounTextField;
    private TextField priceTextField;
    private TextField saleTitleBookTextField;
    private TextField saleQuantityTextField;
    private Label authorLabel;
    private Label titleLabel;
    private Label amountLabel;
    private Label priceLabel;
    private Label saleTitleBookLabel;
    private Label saleQuantityLabel;
    private Button saveButton;
    private Button deleteButton;
    private Button saleButton;

    public BookView(Stage primaryStage, List<BookDTO> bookDTOS, List<SaleDTO> saleDTOS) {
        primaryStage.setTitle("Library");

        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 1100, 500);
        primaryStage.setScene(scene);

        booksObservableList = FXCollections.observableArrayList(bookDTOS);
        saleObservableList = FXCollections.observableArrayList(saleDTOS);

        initTableViewBook(gridPane);
        initTableSale(gridPane);

        initSaveOptions(gridPane);

        primaryStage.show();
    }

    private void initTableViewBook(GridPane gridPane) {
        bookTableView = new TableView<BookDTO>();
        bookTableView.setPlaceholder(new Label("No rows to display"));

        TableColumn<BookDTO, String> titleColumn = new TableColumn<BookDTO, String>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<BookDTO, String> authorColumn = new TableColumn<BookDTO, String>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<BookDTO, Number> amountColumn = new TableColumn<BookDTO, Number>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<BookDTO, Number> priceColumn = new TableColumn<BookDTO, Number>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookTableView.getColumns().addAll(titleColumn, authorColumn, amountColumn, priceColumn);

        bookTableView.setItems(booksObservableList);

        gridPane.add(bookTableView,0,0, 5,1);
    }

    private void initTableSale(GridPane gridPane) {
        saleTableView = new TableView<SaleDTO>();
        saleTableView.setPlaceholder(new Label("No rows to display"));

        TableColumn<SaleDTO, String> bookTitleColumn = new TableColumn<SaleDTO, String>("Title Book");
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));

        TableColumn<SaleDTO, Number> quantityColumn = new TableColumn<SaleDTO, Number>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<SaleDTO, Number> totalPriceColumn = new TableColumn<SaleDTO, Number>("Total price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<SaleDTO, String> timesTampColumn = new TableColumn<SaleDTO, String>("TimesTamp");
        timesTampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        saleTableView.getColumns().addAll(bookTitleColumn, quantityColumn, totalPriceColumn, timesTampColumn);

        saleTableView.setItems(saleObservableList);

        gridPane.add(saleTableView,5,0, 5,1);
    }

    private void initSaveOptions(GridPane gridPane){
        titleLabel = new Label("Title");
        gridPane.add(titleLabel, 1, 1);

        titleTextField = new TextField();
        gridPane.add(titleTextField, 2, 1);

        authorLabel = new Label("Author");
        gridPane.add(authorLabel, 3, 1);

        authorTextField = new TextField();
        gridPane.add(authorTextField, 4, 1);

        amountLabel = new Label("Amount");
        gridPane.add(amountLabel, 5, 1);

        amounTextField = new TextField();
        gridPane.add(amounTextField, 6, 1);

        priceLabel = new Label("Price");
        gridPane.add(priceLabel, 7, 1);

        priceTextField = new TextField();
        gridPane.add(priceTextField, 8, 1);

        saleTitleBookLabel = new Label("Sale book title");
        gridPane.add(saleTitleBookLabel, 1, 3);

        saleTitleBookTextField = new TextField();
        gridPane.add(saleTitleBookTextField, 2, 3);

        saleQuantityLabel = new Label("Quantity");
        gridPane.add(saleQuantityLabel, 3, 3);

        saleQuantityTextField = new TextField();
        gridPane.add(saleQuantityTextField, 4, 3);

        saveButton = new Button("Save");
        gridPane.add(saveButton, 1, 2);

        deleteButton = new Button("Delete");
        gridPane.add(deleteButton, 2, 2);

        saleButton = new Button("Sale");
        gridPane.add(saleButton, 3, 2);
    }

    private void initializeGridPane(GridPane gridPane){
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener){
        saveButton.setOnAction(saveButtonListener);
    }

    public void addSelectionTableListener(ChangeListener selectionTableListener){
        bookTableView.getSelectionModel().selectedItemProperty().addListener(selectionTableListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener){
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addSaleButtonListener(EventHandler<ActionEvent> saleButtonListener){
        saleButton.setOnAction(saleButtonListener);
    }

    public void displayAlertMessage(String titleInformation, String headerInformation, String contextInformation){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleInformation);
        alert.setHeaderText(headerInformation);
        alert.setContentText(contextInformation);

        alert.showAndWait();
    }

    public String getTitle(){
        return titleTextField.getText();
    }

    public String getAuthor(){
        return authorTextField.getText();
    }

    public int getAmount(){
        return Integer.parseInt(amounTextField.getText());
    }

    public double getPrice(){
        return Double.parseDouble(priceTextField.getText());
    }

    public String getSaleTitleBook() {
        return saleTitleBookTextField.getText();
    }

    public int getSaleQuantity() {
        //return Integer.parseInt(saleQuantityTextField.getText());
        String quantityText = saleQuantityTextField.getText();
        if (quantityText == null || quantityText.trim().isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(quantityText);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public ObservableList<BookDTO> getBooksObservableList(){
        return booksObservableList;
    }

    public ObservableList<SaleDTO> getSaleObservableList(){
        return saleObservableList;
    }

    public void addBookToObservableList(BookDTO bookDTO){
        this.booksObservableList.add(bookDTO);
    }

    public void updateBookToObservabileList(BookDTO bookDTO) {
        for (int i = 0; i < booksObservableList.size(); i++) {
            if (booksObservableList.get(i).getTitle().equals(bookDTO.getTitle())) {
                booksObservableList.set(i, bookDTO);
                break;
            }
        }
    }

    public void removeBookFromObservableList(BookDTO bookDTO){
        this.booksObservableList.remove(bookDTO);
    }

    public void addSaleToObservableList(SaleDTO saleDTO){
        this.saleObservableList.add(saleDTO);
    }

    public void removeSaleFromObservableList(SaleDTO saleDTO){
        this.saleObservableList.remove(saleDTO);
    }

    public TableView getBookTableView(){
        return bookTableView;
    }

    public TableView getSaleTableView(){
        return saleTableView;
    }
}
