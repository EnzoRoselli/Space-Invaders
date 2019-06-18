package Controller;

/**
 *
 * @author InvadersTeam
 * @param <K>
 * @param <T>
 */
public class VisibleFramesHandler<K extends javax.swing.JFrame, T extends javax.swing.JFrame> {
   
    /**
     *
     */
    public VisibleFramesHandler() {
        
    }

    /**
     *
     * @param frame
     * @param newFrame
     */
    public void invisibleAndVisibleFrame(T frame, K newFrame) {
        frame.setVisible(false);
        frame.dispose();
        newFrame.setVisible(true);
        newFrame.setLocationRelativeTo(null);
    }
    
    /**
     *
     * @param frame
     */
    public void visibleFrame(T frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    /**
     *
     * @param frame
     */
    public void invisibleFrame(T frame) {
        frame.setVisible(false);
        frame.dispose();
    }
}
