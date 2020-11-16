package Model;
public class Undo {
	public int undoX,undoY,nUndo;
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
	
}