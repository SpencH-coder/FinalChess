import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class StatusPanel extends JPanel {

    private String turn = "TURN: WHITE";
    private String message = "";
    private List<Piece> capturedByWhite = new ArrayList<>();
    private List<Piece> capturedByBlack = new ArrayList<>();

    public StatusPanel() {
        setPreferredSize(new Dimension(160, 0));
        setBackground(new Color(0x0C0C14));
    }

    public void setTurn(Piece.Color t) {
        turn = "TURN: " + t.name();
        repaint();
    }

    public void setMessage(String msg) {
        message = msg == null ? "" : msg;
        repaint();
    }

    public void addCaptured(Piece piece) {
        if (piece.getColor() == Piece.Color.BLACK) {
            capturedByWhite.add(piece);
        } else {
            capturedByBlack.add(piece);
        }
        repaint();
    }

    private String pieceSymbol(Piece.Type type) {
        switch (type) {
            case PAWN:   return "P";
            case ROOK:   return "R";
            case KNIGHT: return "N";
            case BISHOP: return "B";
            case QUEEN:  return "Q";
            case KING:   return "K";
            default:     return "?";
        }
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        g.setFont(new Font("Monospaced", Font.BOLD, 13));

        // Turn indicator
        g.setColor(new Color(0xE8C08C));
        g.drawString(turn, 10, 24);

        // Divider
        g.setColor(new Color(0x333344));
        g.fillRect(10, 32, getWidth() - 20, 1);

        // White's captures
        g.setColor(new Color(0xAAAAAA));
        g.drawString("White took:", 10, 52);
        g.setColor(new Color(0xE53935));
        StringBuilder wb = new StringBuilder();
        for (Piece p : capturedByWhite) wb.append(pieceSymbol(p.getType())).append(" ");
        g.drawString(wb.toString().trim(), 10, 70);

        // Divider
        g.setColor(new Color(0x333344));
        g.fillRect(10, 80, getWidth() - 20, 1);

        // Black's captures
        g.setColor(new Color(0xAAAAAA));
        g.drawString("Black took:", 10, 100);
        g.setColor(new Color(0xF5F5DC));
        StringBuilder bb = new StringBuilder();
        for (Piece p : capturedByBlack) bb.append(pieceSymbol(p.getType())).append(" ");
        g.drawString(bb.toString().trim(), 10, 118);

        // Divider
        g.setColor(new Color(0x333344));
        g.fillRect(10, 128, getWidth() - 20, 1);

        // Illegal move message
        g.setColor(new Color(0xFF3860));
        g.drawString(message, 10, 148);

        g.dispose();
    }
}
