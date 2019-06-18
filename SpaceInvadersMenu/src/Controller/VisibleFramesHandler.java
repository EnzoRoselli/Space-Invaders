package Controller;

/**
 *
 * @author InvadersTeam
 * @param <K> Extends javax.swing.JFrame.
 * @param <T> Extends javax.swing.JFrame.
 */
public class VisibleFramesHandler<K extends javax.swing.JFrame, T extends javax.swing.JFrame> {
   
    /**
     *
     */
    public VisibleFramesHandler() {
        
    }

    /**
     * Exchange current frame with new frame.
     * @param frame Current frame. Must extend from javax.swing.JFrame.
     * @param newFrame New frame. Must extend from javax.swing.JFrame.
     */
    public void invisibleAndVisibleFrame(T frame, K newFrame) {
        frame.setVisible(false);
        frame.dispose();
        newFrame.setVisible(true);
        newFrame.setLocationRelativeTo(null);
    }
    
    /**
     * Sets a frame visible.
     * @param frame Must extend from javax.swing.JFrame.
     */
    public void visibleFrame(T frame) {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    
    /**
     * Sets a frame invisible.
     * @param frame Must extend from javax.swing.JFrame.
     */
    public void invisibleFrame(T frame) {
        frame.setVisible(false);
        frame.dispose();
    }
}
