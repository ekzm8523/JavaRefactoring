package Controller;

import java.util.ArrayList;
import java.util.Map;

import Model.Bone;
import Model.GameObject;
import Model.Player;
import Model.RiceBowl;
import Model.Undo;
import View.MyView;

public class GameController {
	
	
	private boolean isMovable = true, isGameOver;
	MyView map = MyView.getInstance();
	private static GameController s_Instance;
	
	public static GameController getInstance() {
		if (s_Instance == null)
			s_Instance = new GameController();
		return s_Instance;
	}

	public boolean getIsGameOver() {
		return isGameOver;
	}

	public void setIsGameOverTrue() {
		isGameOver = false;
	}
	public void movePlayer(Player player, Undo undo, ArrayList<Bone> boneList,
			ArrayList<RiceBowl> riceBowlList,int direction) {
		int moveX = 0;
		int moveY = -1; // default == UP
		
		if(direction == GameDirection.RIGHT) { moveX = 1; moveY = 0;}
		else if(direction == GameDirection.DOWN) {moveX = 0; moveY = 1;}
		else if(direction == GameDirection.LEFT) {moveX = -1; moveY = 0;}
		
		int opDirection = (direction+2)%4;
		
		player.move(direction);
		//player.moveUp();
		GameManager.getInstance().getBarObject().moveCountUp();
		// 캐릭터만 움직임
		undo.setnUndo(direction);
		// 플레이어 이동할 좌표가 BONE이라면
		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
			// 뼈다귀가 이동해야할 부분이 길이나 골인 경우
			if (map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.GRASS
					|| map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.RICEBOWL) {
				// 캐릭터 자리 0으로 만들고 위를 뼈다귀로 바꾸고 뼈위치 기억
				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
				map.mapArray[player.getY() + moveY][player.getX()+moveX] = GameObject.BONE;
				undo.setUndoY(player.getY() +moveY);
				undo.setUndoX(player.getX() +moveX);
				for (int i = 0; i < riceBowlList.size(); i++) {
					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
						if(direction % 2 == 0) 
							 boneList.get(i).setY(undo.getUndoY());
						else boneList.get(i).setX(undo.getUndoX());
					}
				}
				undo.setnUndo(direction+10);
				isMovable = true;
				// 벽인 경우
			} else {
				GameManager.getInstance().getBarObject().moveCountDown();
				player.move(opDirection);
				isMovable = false;
				undo.setnUndo(GameDirection.BREAK);
			}
		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
			player.move(opDirection);
			isMovable = false;
			GameManager.getInstance().getBarObject().moveCountDown();
			undo.setnUndo(GameDirection.BREAK);
		}
		SoundManager.getInstance().getBarkSound().startMusic();
	
	}

	public void undo(Player player, Undo undo, ArrayList<Bone> boneList,
			ArrayList<RiceBowl> riceBowlList) {
	
		// undo.nUndo값에 따라 직전 상태로 바뀜
		if(GameDirection.UP<=undo.getnUndo() && undo.getnUndo() <= GameDirection.LEFT) {	
			this.movePlayer(player, undo, boneList, riceBowlList,(undo.getnUndo()+2)%4);
		}
		else if(10<=undo.getnUndo() && undo.getnUndo() <= 13) {
			int moveX = 0;
			int moveY = 1;
			if(undo.getnUndo() == 11) {moveX = -1; moveY = 0;}
			else if(undo.getnUndo() == 12) {moveX = 0; moveY = -1;}
			else if(undo.getnUndo() == 13) {moveX = 1; moveY = 0;}
		
			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
			map.mapArray[undo.getUndoY() + moveY][undo.getUndoX()+moveX] = GameObject.BONE;
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
					if(undo.getnUndo() % 2 == 0)boneList.get(i).setY(undo.getUndoY() + moveY);
					else boneList.get(i).setX(undo.getUndoX() + moveX);
					
				}
			}
			this.movePlayer(player, undo, boneList, riceBowlList,(undo.getnUndo()-10+2)%4);
		}

		SoundManager.getInstance().getBarkSound().startMusic();
		undo.setnUndo(GameDirection.BREAK); // 다시 못 바꾸게 하기
	}

	public boolean isGameClear(Player player, Undo undo, ArrayList<Bone> boneList,
			ArrayList<RiceBowl> riceBowlList) {
		int Goal_Count = 0;

		for (int i = 0; i < riceBowlList.size(); i++) {
			if (map.mapArray[riceBowlList.get(i).getY()][riceBowlList.get(i).getX()] == GameObject.BONE) {
				Goal_Count++;
			}
		}

		if (Goal_Count == riceBowlList.size()) {
			return true;
		} else
			return false;
	}

	public boolean isGameOver(Player player, Undo undo, ArrayList<Bone> boneList,
			ArrayList<RiceBowl> riceBowlList) {
		
		boolean OverFlag = false;
		// 각 단계의 뼈다귀의 개수만큼 확인!!
		

		for (int i = 0; i < riceBowlList.size(); i++) {
			// 뼈다귀 위가 벽인지 확인 
			for(int direction = GameDirection.UP;direction<=GameDirection.LEFT;direction++) {
				int moveX = 0;
				int moveY = -1; // default == UP
				if(direction == GameDirection.RIGHT) { moveX = 1; moveY = 0;}
				else if(direction == GameDirection.DOWN) {moveX = 0; moveY = 1;}
				else if(direction == GameDirection.LEFT) {moveX = -1; moveY = 0;}
				
				if (map.getMapValue(boneList.get(i).getY(),boneList.get(i).getX(),direction) == GameObject.BRICK) { 
					// 게임오버 조건에 충족되면
					
					if (map.getMapValue(boneList.get(i).getY(),boneList.get(i).getX(),(direction-1+4) % 4) == GameObject.BRICK) {
						OverFlag = true; 
						 // 골인지점 확인
						for (int j = 0; j < riceBowlList.size(); j++) {
							if (boneList.get(i).getX() == riceBowlList.get(j).getX()
									&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
								OverFlag = false; 
						}
						// 게임오버이면
						if (OverFlag) { 
							this.isGameOver = true; 
							break; 
						}
					// 왼쪽도 확인	
					} else if (map.getMapValue(boneList.get(i).getY(),boneList.get(i).getX(),(direction+1) % 4) == GameObject.BRICK) { 
						OverFlag = true; 
						for (int j = 0; j < riceBowlList.size(); j++) { 
							if (boneList.get(i).getX() == riceBowlList.get(j).getX()
									&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
								OverFlag = false; 
						}
	
						if (OverFlag) { 
							this.isGameOver = true; 
							break; 
	
						}
					}
				}
			}
		}		return this.isGameOver;
	}

}