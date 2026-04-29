/**
 * Empty placeholder for chess move validation.
 * Assume a real rules engine lives here; for the demo every move is legal
 * (except moving onto a piece you already own).
 */
public class ChessRules {

    /** Return true if the side-to-move may move from (fromR,fromC) to (toR,toC). */
    public boolean isLegalMove(Piece[][] board, int fromR, int fromC, int toR, int toC) {
        if (fromR == toR && fromC == toC) return false;
        Piece mover = board[fromR][fromC];
        if (mover == null) return false;
        Piece target = board[toR][toC];
        if (target != null && target.getColor() == mover.getColor()) return false;
        // delegated to "already built component" — return true for the demo
        
        int deltaRow = toR - fromR;
        int deltaCol = toC - fromC;
        
        switch (mover.getType()){
			case PAWN:   return validatePawn(board, fromR, fromC, toR, toC, mover.getColor());
			case ROOK:   return validateRook(board, fromR, fromC, toR, toC, mover.getColor());
            case KNIGHT: return validateKnight(fromR, fromC, toR, toC);
            case BISHOP: return validateBishop(board, fromR, fromC, toR, toC);
            case QUEEN:  return validateQueen(board, fromR, fromC, toR, toC);
            case KING:   return validateKing(fromR, fromC, toR, toC);
            default:     return false;
		}
	}
	
	private boolean validatePawn(Piece[][] board, int fromR, int fromC, int toR, int toC, Piece.Color color) {
        if (color == Piece.Color.WHITE) {
            if(toR == fromR - 1 && toC == fromC && board[toR][toC] == null){
                return true;
			}
            if(toR == fromR - 2 && toC == fromC && fromR == 6 && board[fromR - 1][fromC] == null && board[fromR - 2][fromC] == null){
                return true;
			}
            if(toR == fromR - 1 && Math.abs(toC - fromC) == 1 && board[toR][toC] != null){
                return true;
			}
        }else{

            if(toR == fromR + 1 && toC == fromC && board[toR][toC] == null){
                return true;
			}

            if(toR == fromR + 2 && toC == fromC && fromR == 1 && board[fromR + 1][fromC] == null && board[fromR + 2][fromC] == null){
                return true;
			}

            if(toR == fromR + 1 && Math.abs(toC - fromC) == 1 && board[toR][toC] != null){
                return true;
			}
		}
        return false;
	}

	private boolean validateRook(Piece[][] board, int fromR, int fromC, int toR, int toC, Piece.Color color) {
        boolean vertical   = (toR != fromR && toC == fromC);
        boolean horizontal = (toR == fromR && toC != fromC);
        if(!vertical && !horizontal){
			return false;
		}

        if(vertical) {
            int step;
			if(toR > fromR){
				step = 1;   //  right
			}else{
				step = -1;  //  left
			}
            for(int r = fromR + step; r != toR; r += step)
                if(board[r][fromC] != null){ 
					return false;
			}
        }else{
            int step;
			if(toC > fromC){
				step = 1;   //  right
			}else{
				step = -1;  //  left
			}
            for(int c = fromC + step; c != toC; c += step)
                if(board[fromR][c] != null){
					return false;
				}
        }
        return true;
    }
    

    private boolean validateKnight(int fromR, int fromC, int toR, int toC) {
        int deltaRow = Math.abs(toR - fromR);
        int deltaCol = Math.abs(toC - fromC);
        return (deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2);
    }

    private boolean validateBishop(Piece[][] board, int fromR, int fromC, int toR, int toC) {
        int deltaRow = Math.abs(toR - fromR);
        int deltaCol = Math.abs(toC - fromC);
        if (deltaRow != deltaCol || deltaRow == 0){
			return false;
		}

        int rowStep;
        if(toR > fromR){
			rowStep = 1;   //  right
		}else{
			rowStep = -1;  //  left
		}
		
		int colStep;
        if(toC > fromC){
			colStep = 1;   //  right
		}else{
			colStep = -1;  //  left
		}

        int r = fromR + rowStep, c = fromC + colStep;
        while (r != toR && c != toC) {
            if (board[r][c] != null){
				return false;
			}
            r += rowStep;
            c += colStep;
        }
        return true;
    }

    private boolean validateQueen(Piece[][] board, int fromR, int fromC, int toR, int toC) {
        int deltaRow = Math.abs(toR - fromR);
        int deltaCol = Math.abs(toC - fromC);
        boolean straight = (toR == fromR || toC == fromC);
        boolean diagonal = (deltaRow == deltaCol && deltaRow != 0);
        if(!straight && !diagonal){
			 return false;
		 }

        int rowStep = Integer.signum(toR - fromR);
        int colStep = Integer.signum(toC - fromC);
        int r = fromR + rowStep;
        int c = fromC + colStep;
        while (r != toR || c != toC) {
            if(board[r][c] != null){
				return false;
			}
            r += rowStep;
            c += colStep;
        }
        return true;
    }

    private boolean validateKing(int fromR, int fromC, int toR, int toC){
        int deltaRow = Math.abs(toR - fromR);
        int deltaCol = Math.abs(toC - fromC);
        return deltaRow <= 1 && deltaCol <= 1;
    }
}
	
	
	
	
	
	
	

