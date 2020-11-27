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
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	
	
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
			ArrayList<RiceBowl> riceBowlList,int moveX,int moveY,int direction) {
//		int moveX = 0;
//		int moveY = -1;
//		int direction = UP;
		int opDirection = (direction+2)%4;
		
		player.move(direction);
		//player.moveUp();
		GameManager.getInstance().getBarObject().moveCountUp();
		// 캐릭터만 움직임
		undo.setnUndo(direction +1);
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
				undo.setnUndo((direction+1) *10);
				isMovable = true;
				// 벽인 경우
			} else {
				GameManager.getInstance().getBarObject().moveCountDown();
				player.move(opDirection);
				isMovable = false;
				undo.setnUndo(0);
			}
		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
			player.move(opDirection);
			isMovable = false;
			GameManager.getInstance().getBarObject().moveCountDown();
			undo.setnUndo(0);
		}
		SoundManager.getInstance().getBarkSound().startMusic();
	
	}
//
//	public void moveUp(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//		int moveX = 0;
//		int moveY = -1;
//		int direction = UP;
//		int opDirection = (direction+2)%4;
//		
//		player.move(direction);
//		//player.moveUp();
//		GameManager.getInstance().getBarObject().moveCountUp();
//		// 캐릭터만 움직임
//		undo.setnUndo(direction +1);
//		// 플레이어 이동할 좌표가 BONE이라면
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			// 뼈다귀가 이동해야할 부분이 길이나 골인 경우
//			if (map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.GRASS
//					|| map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.RICEBOWL) {
//				// 캐릭터 자리 0으로 만들고 위를 뼈다귀로 바꾸고 뼈위치 기억
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				map.mapArray[player.getY() + moveY][player.getX()+moveX] = GameObject.BONE;
//				undo.setUndoY(player.getY() +moveY);
//				undo.setUndoX(player.getX() +moveX);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						if(direction % 2 == 0) 
//							 boneList.get(i).setY(undo.getUndoY());
//						else boneList.get(i).setX(undo.getUndoX());
//					}
//				}
//				undo.setnUndo((direction+1) *10);
//				isMovable = true;
//				// 벽인 경우
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			player.move(opDirection);
//			isMovable = false;
//			GameManager.getInstance().getBarObject().moveCountDown();
//			undo.setnUndo(0);
//		}
//		SoundManager.getInstance().getBarkSound().startMusic();
//	
//	}
//
//	public void moveRight(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//		int moveX = 1;
//		int moveY = 0;
//		int direction = RIGHT;
//		int opDirection = (direction+2)%4;
//		player.move(direction);
//		//player.moveUp();
//		GameManager.getInstance().getBarObject().moveCountUp();
//		// 캐릭터만 움직임
//		undo.setnUndo(direction +1);
//		// 플레이어 이동할 좌표가 BONE이라면
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			// 뼈다귀가 이동해야할 부분이 길이나 골인 경우
//			if (map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.GRASS
//					|| map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.RICEBOWL) {
//				// 캐릭터 자리 0으로 만들고 위를 뼈다귀로 바꾸고 뼈위치 기억
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				map.mapArray[player.getY() + moveY][player.getX()+moveX] = GameObject.BONE;
//				undo.setUndoY(player.getY() +moveY);
//				undo.setUndoX(player.getX() +moveX);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						if(direction % 2 == 0) 
//							 boneList.get(i).setY(undo.getUndoY());
//						else boneList.get(i).setX(undo.getUndoX());
//					}
//				}
//				undo.setnUndo((direction+1) *10);
//				isMovable = true;
//				// 벽인 경우
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			player.move(opDirection);
//			isMovable = false;
//			GameManager.getInstance().getBarObject().moveCountDown();
//			undo.setnUndo(0);
//		}
//		SoundManager.getInstance().getBarkSound().startMusic();
//	}
//	public void moveDown(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//
//		int moveX = 0;
//		int moveY = 1;
//		int direction = DOWN;
//		int opDirection = (direction+2)%4;
//		player.move(direction);
//		//player.moveUp();
//		GameManager.getInstance().getBarObject().moveCountUp();
//		// 캐릭터만 움직임
//		undo.setnUndo(direction +1);
//		// 플레이어 이동할 좌표가 BONE이라면
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			// 뼈다귀가 이동해야할 부분이 길이나 골인 경우
//			if (map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.GRASS
//					|| map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.RICEBOWL) {
//				// 캐릭터 자리 0으로 만들고 위를 뼈다귀로 바꾸고 뼈위치 기억
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				map.mapArray[player.getY() + moveY][player.getX()+moveX] = GameObject.BONE;
//				undo.setUndoY(player.getY() +moveY);
//				undo.setUndoX(player.getX() +moveX);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						if(direction % 2 == 0) 
//							 boneList.get(i).setY(undo.getUndoY());
//						else boneList.get(i).setX(undo.getUndoX());
//					}
//				}
//				undo.setnUndo((direction+1) *10);
//				isMovable = true;
//				// 벽인 경우
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			player.move(opDirection);
//			isMovable = false;
//			GameManager.getInstance().getBarObject().moveCountDown();
//			undo.setnUndo(0);
//		}
//		SoundManager.getInstance().getBarkSound().startMusic();
//
//	}
//
//	public void moveLeft(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//
//		int moveX = -1;
//		int moveY = 0;
//		int direction = LEFT;
//		int opDirection = (direction+2)%4;
//		player.move(direction);
//		//player.moveUp();
//		GameManager.getInstance().getBarObject().moveCountUp();
//		// 캐릭터만 움직임
//		undo.setnUndo(direction +1);
//		// 플레이어 이동할 좌표가 BONE이라면
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			// 뼈다귀가 이동해야할 부분이 길이나 골인 경우
//			if (map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.GRASS
//					|| map.mapArray[player.getY() + moveY][player.getX() + moveX] == GameObject.RICEBOWL) {
//				// 캐릭터 자리 0으로 만들고 위를 뼈다귀로 바꾸고 뼈위치 기억
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				map.mapArray[player.getY() + moveY][player.getX()+moveX] = GameObject.BONE;
//				undo.setUndoY(player.getY() +moveY);
//				undo.setUndoX(player.getX() +moveX);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						if(direction % 2 == 0) 
//							 boneList.get(i).setY(undo.getUndoY());
//						else boneList.get(i).setX(undo.getUndoX());
//					}
//				}
//				undo.setnUndo((direction+1) *10);
//				isMovable = true;
//				// 벽인 경우
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			player.move(opDirection);
//			isMovable = false;
//			GameManager.getInstance().getBarObject().moveCountDown();
//			undo.setnUndo(0);
//		}
//		SoundManager.getInstance().getBarkSound().startMusic();
//	}


	public void undo(Player player, Undo undo, ArrayList<Bone> boneList,
			ArrayList<RiceBowl> riceBowlList) {
		
		// undo.nUndo값에 따라 직전 상태로 바뀜
		switch (undo.getnUndo()) {
		// 캐릭터만 아래로 움직여줌
		case 1:
			this.movePlayer(player, undo, boneList, riceBowlList,0,1,2);
			break;

		// 뼈다귀를 먼저 아래로 움직이고 캐릭터도 아래로 움직여줌
		case 10:
			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
			map.mapArray[undo.getUndoY() + 1][undo.getUndoX()] = GameObject.BONE;
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
					boneList.get(i).setY(undo.getUndoY() + 1);
				}
			}
			this.movePlayer(player, undo, boneList, riceBowlList,0,1,2);
			break;
		case 2:
			this.movePlayer(player, undo, boneList, riceBowlList,-1,0,3);
			break;

		// 캐릭터와 뼈다귀 왼쪽으로 움직이기
		case 20:
			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
			map.mapArray[undo.getUndoY()][undo.getUndoX() - 1] = GameObject.BONE;
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
					boneList.get(i).setX(undo.getUndoX() - 1);
				}
			}
			this.movePlayer(player, undo, boneList, riceBowlList,-1,0,3);
			break;
		
		// 캐릭터만 위로 움직여줌
		case 3:
			this.movePlayer(player, undo, boneList, riceBowlList,0,-1,0);
			break;

		// 캐릭터와 뼈다귀 위로 움직여줌
		case 30:
			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
			map.mapArray[undo.getUndoY() - 1][undo.getUndoX()] = GameObject.BONE;
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
					boneList.get(i).setY(undo.getUndoY() - 1);
				}
			}
			this.movePlayer(player, undo, boneList, riceBowlList,0,-1,0);
			break;

		// 캐릭터만 오른쪽으로 움직여줌
		case 4:
			this.movePlayer(player, undo, boneList, riceBowlList,1,0,1);
			break;

		// 캐릭터와 뼈다귀 모두 오른쪽으로 움직여줌
		case 40:
			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
			map.mapArray[undo.getUndoY()][undo.getUndoX() + 1] = GameObject.BONE;
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
					boneList.get(i).setX(undo.getUndoX() + 1);
				}
			}
			this.movePlayer(player, undo, boneList, riceBowlList,1,0,1);
			break;
		}
		// 캐릭터만 왼쪽으로 움직여줌
		
		SoundManager.getInstance().getBarkSound().startMusic();
		undo.setnUndo(0); // 다시 못 바꾸게 하기
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
			if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == GameObject.BRICK) { 
				// 게임오버 조건에 충족되면
				if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == GameObject.BRICK) {
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
				} else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == GameObject.BRICK) { 
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
			// 뼈다귀 오른쪽 확인
			else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == GameObject.BRICK) {
				// 위쪽도 확인
				if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == GameObject.BRICK) {
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
				// 아랫쪽도 확인
				else if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == GameObject.BRICK) { 
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
			// 뼈다귀 아랫쪽 확인
			else if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == GameObject.BRICK) {
				// 오른쪽 확인
				if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == GameObject.BRICK) { 
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
				// 왼쪽도 확인
				} else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == GameObject.BRICK) { 
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
			// 뼈다귀 왼쪽 확인
			else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == GameObject.BRICK) { 
				// 아랫쪽 확인
				if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == GameObject.BRICK) { 
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
				// 위쪽도 확인	
				} else if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == GameObject.BRICK) { 
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

		return this.isGameOver;
	}

}