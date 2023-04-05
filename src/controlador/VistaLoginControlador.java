package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import modelo.SentenciasSQL;
import modelo.UDPSocket;

public class VistaLoginControlador implements Initializable {

    //private SentenciasSQL model = new SentenciasSQL();
    private UDPSocket udpSocket;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private Label txtError;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            udpSocket = new UDPSocket("localhost", 9000);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    @FXML
    private void clickLogin(ActionEvent event) {

        Object evento = event.getSource();

        if (evento.equals(btnLogin)) {
            //Si no esta vacio los campos
            if (!txtCorreo.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
                String correo = txtCorreo.getText();
                String contrasena = txtPassword.getText();
                try {
                    // Enviar los datos de autenticación al servidor
                    udpSocket.send(correo + ":" + contrasena);
                    // Esperar la respuesta del servidor
                    String response = udpSocket.receive();

                    if (response.equals("correcto")) {
                        // Datos correctos, ingresar a la otra ventana
                        loadStage("/vista/VistaMesa.fxml", event);
                    } else {
                        // Datos incorrectos, mostrar mensaje de error
                        txtError.setText("Correo o contraseña incorrectos.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        /*
        if (evento.equals(btnLogin)) {
            //Si no esta vacio los campos
            if (!txtCorreo.getText().isEmpty() && !txtPassword.getText().isEmpty()) {
                String correo = txtCorreo.getText();
                String contrasena = txtPassword.getText();

                boolean bandera = model.consultaDB(correo, contrasena);
                if (bandera == true) {
                    //Datos correctos, ingresa a la otra ventana
                    loadStage("/vista/VistaMesa.fxml",event);
                } else {
                    txtError.setText("Datos incorrectos. Intente nuevamente");
                }
                //Hacemos la validacion en la BD
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo iniciar sesión. No debe haber campos vacios", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }*/
    }

    private void loadStage(String url, Event event) {
        try {

            ((Node) (event.getSource())).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(url));
            Scene scene = new Scene(root);

            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.exit();
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(VistaLoginControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void clickRegister(ActionEvent event) {
    }

    //Con este metodo verifica el evento de presionar la tecla espaciadora y no deja que se ingrese en los TextField
    //para evitar espacios tanto en contraseña como en correo
    @FXML
    private void eventKey(KeyEvent event) {
        // Filtro para impedir espacios en blanco
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("\\s+")) {
                // Si el texto que se intenta ingresar contiene espacios en blanco, no se permite
                return null;
            } else {
                // De lo contrario, se permite el cambio
                return change;
            }
        };

        // TextFormatter para el campo txtCorreo
        TextFormatter<String> correoFormatter = new TextFormatter<>(filter);
        txtCorreo.setTextFormatter(correoFormatter);

        // TextFormatter para el campo txtPassword
        TextFormatter<String> passwordFormatter = new TextFormatter<>(filter);
        txtPassword.setTextFormatter(passwordFormatter);
    }

}
