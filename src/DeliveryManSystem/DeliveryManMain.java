package DeliveryManSystem;

import DeliveryManSystem.GraphicalInterfaceClientSystem.Gui;


import java.io.IOException;

public class DeliveryManMain {
    public static void main(String[] args) throws IOException {
        DeliveryManClient andrea = new DeliveryManClient("Andrea");
        andrea.updateList();
        andrea.sendList();
        Gui gui = new Gui();
        gui.ClientGUI();
    }
}
