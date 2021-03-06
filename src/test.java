//package Controller;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//import Model.BarkSound;
//import Model.Bone;
//import Model.GameObject;
//import Model.Player;
//import Model.RiceBowl;
//import Model.Undo;
//import View.MyView;
//
//public class GameController {
//	public static final int UP = 0;
//	public static final int RIGHT = 1;
//	public static final int DOWN = 2;
//	public static final int LEFT = 3;
//	
//	private boolean isMovable = true, isGameOver;
//	MyView map = MyView.getInstance();
//	private static GameController s_Instance;
//	
//	public static GameController getInstance() {
//		if (s_Instance == null)
//			s_Instance = new GameController();
//		return s_Instance;
//	}
//
//	public boolean getIsGameOver() {
//		return isGameOver;
//		
//	}
//
//	public void setIsGameOverTrue() {
//		isGameOver = false;
//	}
//
//	public void moveUp(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList,int DX,int DY,int D) {
//		int directionY = -1;
//		int directionX = 0;
//		int direction = UP;
//		int opDirection = (direction + 2) % 4;
////		int directionY = DY;
////		int directionX = DX;
////		int direction = D;
//		player.move(direction);//player.moveUp();
//		GameManager.getInstance().getBarObject().moveCountUp(); // ************
//		// 캐릭터만 움직임
//		undo.setnUndo((direction+1) );//undo.setnUndo(1);
//		// 플레이어 이동할 좌표가 BONE이라면
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {//********************
//			// 뼈다귀가 이동해야할 부분이 길이나 골인 경우
//			if (map.mapArray[player.getY() + directionY][player.getX() + directionX] == GameObject.GRASS // -1 -> directionY
//					|| map.mapArray[player.getY() + directionY][player.getX() + directionX] == GameObject.RICEBOWL) { // -1 -> directionY
//				// 캐릭터 자리 0으로 만들고 위를 뼈다귀로 바꾸고 뼈위치 기억
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;//********************
//				map.mapArray[player.getY() + directionY][player.getX() + directionX] = GameObject.BONE; // -1 -> directionY
//				undo.setUndoX(player.getX() + directionX);
//				undo.setUndoY(player.getY() + directionY); // -1 -> directionY
//				for (int i = 0; i < riceBowlList.size(); i++) { //********************
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {//********************
//						boneList.get(i).setY(undo.getUndoY());//********************
//					}
//				}
//				undo.setnUndo((direction+1)  * 10);//undo.setnUndo(10);
//				isMovable = true;//********************
//				// 벽인 경우
//			} else {//********************
//				player.move(opDirection);//player.moveDown();
//				isMovable = false;//********************
//				undo.setnUndo(0);//********************
//			}
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {//********************
//			player.move(opDirection);//player.moveDown();
//			isMovable = false;//********************
//			GameManager.getInstance().getBarObject().moveCountDown();//********************
//			undo.setnUndo(0);//********************
//		}
//		SoundManager.getInstance().getBarkSound().startMusic();//********************
//	
//	}
//
//	public void moveRight(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//		int directionY = 0;
//		int directionX = 1;
//		int direction = RIGHT;
//		int opDirection = (direction + 2) % 4;
//		
//		player.move(direction);
//		GameManager.getInstance().getBarObject().moveCountUp();
//
//		undo.setnUndo((direction+1) );
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			if (map.mapArray[player.getY()+directionY][player.getX() + directionX] == GameObject.GRASS
//					|| map.mapArray[player.getY()+ directionY][player.getX() + directionX] == GameObject.RICEBOWL) {
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				map.mapArray[player.getY()+directionY][player.getX() + directionX] = GameObject.BONE;
//				undo.setUndoX(player.getX() + directionX);
//				undo.setUndoY(player.getY() + directionY);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						boneList.get(i).setX(undo.getUndoX());
//					}
//				}
//				undo.setnUndo((direction+1) *10);
//				isMovable = true;
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			GameManager.getInstance().getBarObject().moveCountDown();
//			player.move(opDirection);
//			isMovable = false;
//			undo.setnUndo(0);
//		}
//		
//		SoundManager.getInstance().getBarkSound().startMusic();
//	}
//	public void moveDown(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//
//		int directionY = 1;
//		int directionX = 0;
//		int direction = DOWN;
//
//		int opDirection = (direction + 2) % 4;
//		player.move(direction);
//		GameManager.getInstance().getBarObject().moveCountUp();
//
//		undo.setnUndo(direction+1);
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			if (map.mapArray[player.getY()+directionY][player.getX() + directionX] == GameObject.GRASS
//					|| map.mapArray[player.getY()+ directionY][player.getX() + directionX] == GameObject.RICEBOWL) {
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				System.out.println(opDirection);
//				map.mapArray[player.getY()+directionY][player.getX() + directionX] = GameObject.BONE;
//				undo.setUndoX(player.getX() + directionX);
//				undo.setUndoY(player.getY() + directionY);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						boneList.get(i).setX(undo.getUndoY());
//					}
//				}
//				undo.setnUndo((direction+1) *10);
//				isMovable = true;
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			GameManager.getInstance().getBarObject().moveCountDown();
//			player.move(opDirection);
//			isMovable = false;
//			undo.setnUndo(0);
//		}
//		
//		SoundManager.getInstance().getBarkSound().startMusic();
////		player.moveDown();
////		GameManager.getInstance().getBarObject().moveCountUp();
////		undo.setnUndo(3);
////		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
////			if (map.mapArray[player.getY() + 1][player.getX()] == GameObject.GRASS
////					|| map.mapArray[player.getY() + 1][player.getX()] == GameObject.RICEBOWL) {
////				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
////				map.mapArray[player.getY() + 1][player.getX()] = GameObject.BONE;
////				undo.setUndoX(player.getX());
////				undo.setUndoY(player.getY() + 1);
////				for (int i = 0; i < riceBowlList.size(); i++) { 
////					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
////						boneList.get(i).setY(undo.getUndoY());
////					}
////				}
////				undo.setnUndo(30);
////				isMovable = true;
////			} else {
////				player.moveUp();
////				isMovable = false;
////				undo.setnUndo(0);
////			}
////		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
////			player.moveUp();
////			isMovable = false;
////			GameManager.getInstance().getBarObject().moveCountDown();
////			undo.setnUndo(0);
////		}
////		SoundManager.getInstance().getBarkSound().startMusic();
//		
//
//	}
//
//	public void moveLeft(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//		int directionY = 0;
//		int directionX = -1;
//		int direction = LEFT;
//		int opDirection = (direction + 2) % 4;
//		int plusDirection = (direction+1) % 4;
//		System.out.println(opDirection + "출력!!");
//		System.out.println(opDirection + "출력!!");
//		
//		player.move(direction);
//		GameManager.getInstance().getBarObject().moveCountUp();
//
//		undo.setnUndo(direction+1);
//		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
//			if (map.mapArray[player.getY()+directionY][player.getX() + directionX] == GameObject.GRASS
//					|| map.mapArray[player.getY()+ directionY][player.getX() + directionX] == GameObject.RICEBOWL) {
//				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
//				map.mapArray[player.getY()+directionY][player.getX() + directionX] = GameObject.BONE;
//				undo.setUndoX(player.getX() + directionX);
//				undo.setUndoY(player.getY() + directionY);
//				for (int i = 0; i < riceBowlList.size(); i++) {
//					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
//						boneList.get(i).setX(undo.getUndoX());
//					}
//				}
//				undo.setnUndo((direction+1)*10);
//				isMovable = true;
//			} else {
//				GameManager.getInstance().getBarObject().moveCountDown();
//				player.move(opDirection);
//				isMovable = false;
//				undo.setnUndo(0);
//			}
//
//		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
//			GameManager.getInstance().getBarObject().moveCountDown();
//			player.move(opDirection);
//			isMovable = false;
//			undo.setnUndo(0);
//		}
//		
//		SoundManager.getInstance().getBarkSound().startMusic();
////		player.moveLeft();
////		GameManager.getInstance().getBarObject().moveCountUp();
////		undo.setnUndo(4);
////		if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) {
////			if (map.mapArray[player.getY()][player.getX() - 1] == GameObject.GRASS
////					|| map.mapArray[player.getY()][player.getX() - 1] == GameObject.RICEBOWL) {
////				map.mapArray[player.getY()][player.getX()] = GameObject.GRASS;
////				map.mapArray[player.getY()][player.getX() - 1] = GameObject.BONE;
////				undo.setUndoX(player.getX() - 1);
////				undo.setUndoY(player.getY());
////				for (int i = 0; i < riceBowlList.size(); i++) {
////					if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
////						boneList.get(i).setX(undo.getUndoX());
////					}
////				}
////				undo.setnUndo(40);
////				isMovable = true;
////			} else {
////				player.moveRight();
////				isMovable = false;
////				undo.setnUndo(0);
////			}
////		} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
////			GameManager.getInstance().getBarObject().moveCountDown();
////			player.moveRight();
////			isMovable = false;
////			undo.setnUndo(0);
////		}
////		SoundManager.getInstance().getBarkSound().startMusic();
//
//	}
//
//	
//	public void undo(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//
//		// undo.nUndo값에 따라 직전 상태로 바뀜
//		switch (undo.getnUndo()) {
//		// 캐릭터만 아래로 움직여줌
//		case 1: // UP
//			this.moveDown(player, undo, boneList, riceBowlList);
//			break;
//
//		// 뼈다귀를 먼저 아래로 움직이고 캐릭터도 아래로 움직여줌
//		case 10:
//			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
//			map.mapArray[undo.getUndoY() + 1][undo.getUndoX()] = GameObject.BONE;
//			for (int i = 0; i < riceBowlList.size(); i++) {
//				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
//					boneList.get(i).setY(undo.getUndoY() + 1);
//				}
//			}
//			this.moveDown(player, undo, boneList, riceBowlList);
//			break;
//
//			// 캐릭터만 왼쪽으로 움직여줌
//		case 2: // Right
//			this.moveLeft(player, undo, boneList, riceBowlList);
//			break;
//
//			// 캐릭터와 뼈다귀 왼쪽으로 움직이기
//		case 20:
//			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
//			map.mapArray[undo.getUndoY()][undo.getUndoX() - 1] = GameObject.BONE;
//			for (int i = 0; i < riceBowlList.size(); i++) {
//				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
//					boneList.get(i).setX(undo.getUndoX() - 1);
//						}
//				}
//			this.moveLeft(player, undo, boneList, riceBowlList);
//			break;
//						
//		// 캐릭터만 위로 움직여줌
//		case 3: // Down
//			//this.moveUp(player, undo, boneList, riceBowlList);
//			this.moveUp(player, undo, boneList, riceBowlList,0,-1,0);
//			break;
//
//		// 캐릭터와 뼈다귀 위로 움직여줌
//		case 30:
//			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
//			map.mapArray[undo.getUndoY() - 1][undo.getUndoX()] = GameObject.BONE;
//			for (int i = 0; i < riceBowlList.size(); i++) {
//				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
//					boneList.get(i).setY(undo.getUndoY() - 1);
//				}
//			}
//			//this.moveUp(player, undo, boneList, riceBowlList);
//			this.moveUp(player, undo, boneList, riceBowlList,0,-1,0);
//			break;
//
//		// 캐릭터만 오른쪽으로 움직여줌
//		case 4: // Left
//			this.moveRight(player, undo, boneList, riceBowlList);
//			break;
//
//		// 캐릭터와 뼈다귀 모두 오른쪽으로 움직여줌
//		case 40:
//			map.mapArray[undo.getUndoY()][undo.getUndoX()] = GameObject.GRASS;
//			map.mapArray[undo.getUndoY()][undo.getUndoX() + 1] = GameObject.BONE;
//			for (int i = 0; i < riceBowlList.size(); i++) {
//				if (boneList.get(i).getX() == undo.getUndoX() && boneList.get(i).getY() == undo.getUndoY()) {
//					boneList.get(i).setX(undo.getUndoX() + 1);
//				}
//			}
//			this.moveRight(player, undo, boneList, riceBowlList);
//			break;
//		}
//		
//		SoundManager.getInstance().getBarkSound().startMusic();
//		undo.setnUndo(0); // 다시 못 바꾸게 하기
//	}
//
//	public boolean isGameClear(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//		int Goal_Count = 0;
//
//		for (int i = 0; i < riceBowlList.size(); i++) {
//			if (map.mapArray[riceBowlList.get(i).getY()][riceBowlList.get(i).getX()] == GameObject.BONE) {
//				Goal_Count++;
//			}
//		}
//
//		if (Goal_Count == riceBowlList.size()) {
//			return true;
//		} else
//			return false;
//	}
//
//	public boolean isGameOver(Player player, Undo undo, ArrayList<Bone> boneList,
//			ArrayList<RiceBowl> riceBowlList) {
//
//		boolean OverFlag = false;
//		// 각 단계의 뼈다귀의 개수만큼 확인!!
//		for (int i = 0; i < riceBowlList.size(); i++) {
//			// 뼈다귀 위가 벽인지 확인 
//			if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == GameObject.BRICK) { 
//				// 게임오버 조건에 충족되면
//				if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == GameObject.BRICK) {
//					OverFlag = true; 
//					 // 골인지점 확인
//					for (int j = 0; j < riceBowlList.size(); j++) {
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//					// 게임오버이면
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//					}
//				// 왼쪽도 확인	
//				} else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == GameObject.BRICK) { 
//					OverFlag = true; 
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//
//					}
//				}
//			} 
//			// 뼈다귀 오른쪽 확인
//			else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == GameObject.BRICK) {
//				// 위쪽도 확인
//				if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == GameObject.BRICK) {
//					OverFlag = true; 
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//					}
//				}
//				// 아랫쪽도 확인
//				else if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == GameObject.BRICK) { 
//					OverFlag = true; 
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//					}
//				}
//			} 
//			// 뼈다귀 아랫쪽 확인
//			else if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == GameObject.BRICK) {
//				// 오른쪽 확인
//				if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == GameObject.BRICK) { 
//					OverFlag = true; 
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//					}
//				// 왼쪽도 확인
//				} else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == GameObject.BRICK) { 
//					OverFlag = true; 
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//					}
//				}
//			} 
//			// 뼈다귀 왼쪽 확인
//			else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == GameObject.BRICK) { 
//				// 아랫쪽 확인
//				if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == GameObject.BRICK) { 
//					OverFlag = true; 
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY()) 
//							OverFlag = false; 
//					}
//
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break; 
//					}
//				// 위쪽도 확인	
//				} else if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == GameObject.BRICK) { 
//					OverFlag = true;
//					for (int j = 0; j < riceBowlList.size(); j++) { 
//						if (boneList.get(i).getX() == riceBowlList.get(j).getX()
//								&& boneList.get(i).getY() == riceBowlList.get(j).getY())
//							OverFlag = false; 
//					}
//	
//					if (OverFlag) { 
//						this.isGameOver = true; 
//						break;
//					}
//				}
//			}
//
//		}
//
//		return this.isGameOver;
//	}
//
//}