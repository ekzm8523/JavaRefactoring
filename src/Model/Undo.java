package Model;
public class Undo {
	private int undoX,undoY;
	private int nUndo;
	public Undo() {
		undoX = 0;
		undoY = 0;
		nUndo = 0;
	}
	public Undo(int undoX,int undoY,int nUndo) {
		this.undoX = undoX;
		this.undoY = undoY;
		this.nUndo = nUndo;
	}
	
	public int getUndoX() {
		return undoX;
	}
	public void setUndoX(int undoX) {
		this.undoX = undoX;
	}
	public int getUndoY() {
		return undoY;
	}
	public void setUndoY(int undoY) {
		this.undoY = undoY;
	}
	public int getnUndo() {
		return nUndo;
	}
	public void setnUndo(int nUndo) {
		this.nUndo = nUndo;
	}
	
}