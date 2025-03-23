package view;

import javax.swing.JPanel;

import controller.Controller;

public abstract class PagePanel extends JPanel {

    protected Controller controller;

    public PagePanel(Controller controller) {
        this.controller = controller;
    }

    protected abstract void drawPage();
    public abstract void onOpen();
    public abstract void onClose();
}
