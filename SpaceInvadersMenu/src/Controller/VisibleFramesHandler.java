package Controller;

public class VisibleFramesHandler<K extends javax.swing.JFrame, T extends javax.swing.JFrame> {

    public VisibleFramesHandler() {
    }

    public void invisibleAndVisibleFrame(T frame, K newFrame) {
        frame.setVisible(false);
        frame.dispose();
        newFrame.setVisible(true);
        newFrame.setLocationRelativeTo(null);
    }
    
    public void visibleFrame(T frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    public void invisibleFrame(T frame) {
        frame.setVisible(false);
        frame.dispose();
    }
}
