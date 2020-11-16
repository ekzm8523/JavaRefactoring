package Controller;
import java.util.ArrayList;

import Model.BarkSound;
import Model.Bone;
import Model.GameObject;
import Model.Player;
import Model.RiceBowl;
import Model.Undo;
import View.Map;

public class GameController {
	public boolean isMovable = true, isGameOver; // 움직였는지, 게임 오버됐는지 반환
	
	public GameController() {
		
	}
	public boolean getIsGameOver() {
		return isGameOver;
	}
	public void setIsGameOverTrue() {
		isGameOver = false;
	}
	void moveUp(Player player,Undo undo,Map map,
				ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList){
		
		player.moveUp();
		GameManager.getInstance().getModel().moveUp();
        
        undo.nUndo = 1; // 캐릭터만 움직임
        if (map.mapArray[player.getY()][player.getX()] == GameObject.BONE) // 플레이어 이동할 좌표가 Box라면
        {
           if (map.mapArray[player.getY() - 1][player.getX()] == GameObject.GRASS
                 || map.mapArray[player.getY() - 1][player.getX()] == GameObject.RICEBOWL) // 박스가 이동해야할 부분이
                                                                       // 길이나 골이라면
           {
              map.mapArray[player.getY()][player.getX()] = GameObject.GRASS; // 캐릭터 자리 0으로 만들고
              map.mapArray[player.getY() - 1][player.getX()] = GameObject.BONE; // 위를 박스로 바꾼다
              undo.undoY = player.getY() - 1; // 박스 위치 기억해주고
              undo.undoX = player.getX();
              for (int i = 0; i < riceBowlList.size(); i++) {
                 if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
                    boneList.get(i).setY(undo.undoY);
                 }
              }
              undo.nUndo = 11; // 캐릭터와 박스 모두 움직임
              isMovable = true;
           } else { // 벽이면
        	  System.out.println("벽입니다 !! ");
              // player.getY()++; // 원위치
              player.moveDown();
              isMovable = false; // 못움직임
              undo.nUndo = 0; // 언두도 못함
           }
        }else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
            player.moveDown();
            isMovable = false; // 못움직임
            GameManager.getInstance().getModel().moveDown();
            undo.nUndo = 0; // 언두도 못함
         }
         BarkSound.getInstance().startMusic();
         

	}
	void moveDown(Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList) {
		player.moveDown();
		GameManager.getInstance().getModel().moveUp();
        undo.nUndo = 2;
        if (map.mapArray[player.getY()][player.getX()] == 2) {
           if (map.mapArray[player.getY() + 1][player.getX()] == 0
                 || map.mapArray[player.getY() + 1][player.getX()] == 3) {
              map.mapArray[player.getY()][player.getX()] = 0;
              map.mapArray[player.getY() + 1][player.getX()] = 2;
              undo.undoX = player.getX();
              undo.undoY = player.getY() + 1;
              for (int i = 0; i < riceBowlList.size(); i++) {
                 if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
                    boneList.get(i).setY(undo.undoY);
                 }
              }
              undo.nUndo = 21;
              isMovable = true;
           } else {
              // player.getY()--;
              player.moveUp();
              isMovable = false;
              undo.nUndo = 0;
           }
        } else if (map.mapArray[player.getY()][player.getX()] == 1) {
        	GameManager.getInstance().getModel().moveDown();
        	player.moveUp();
           isMovable = false;
           undo.nUndo = 0;
        }
        BarkSound.getInstance().startMusic();
		
	}
	void moveLeft(Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList) {
		// player.moveLeft();
        player.moveLeft();
        GameManager.getInstance().getModel().moveUp();
        undo.nUndo = 3;
        if (map.mapArray[player.getY()][player.getX()] == 2) {
           if (map.mapArray[player.getY()][player.getX() - 1] == 0
                 || map.mapArray[player.getY()][player.getX() - 1] == 3) {
              map.mapArray[player.getY()][player.getX()] = 0;
              map.mapArray[player.getY()][player.getX() - 1] = 2;
              undo.undoX = player.getX() - 1;
              undo.undoY = player.getY();
              for (int i = 0; i < riceBowlList.size(); i++) {
                 if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
                    boneList.get(i).setX(undo.undoX);
                 }
              }
              undo.nUndo = 31;
              isMovable = true;
           } else {
              // player.getX()++;
              player.moveRight();
              isMovable = false;
              undo.nUndo = 0;
           }
        } else if (map.mapArray[player.getY()][player.getX()] == 1) {
        	GameManager.getInstance().getModel().moveDown();
           player.moveRight();
           isMovable = false;
           undo.nUndo = 0;
        }
        BarkSound.getInstance().startMusic();
		
	}
	void moveRight(Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList) {
		// player.getX()++; // 캐릭터 오른쪽으로 이동(x좌표 + 1)
        player.moveRight();
        GameManager.getInstance().getModel().moveUp();
        
        undo.nUndo = 4;
        if (map.mapArray[player.getY()][player.getX()] == 2) {
           if (map.mapArray[player.getY()][player.getX() + 1] == 0
                 || map.mapArray[player.getY()][player.getX() + 1] == 3) {
              map.mapArray[player.getY()][player.getX()] = 0;
              map.mapArray[player.getY()][player.getX() + 1] = 2;
              undo.undoX = player.getX() + 1;
              undo.undoY = player.getY();
              for (int i = 0; i < riceBowlList.size(); i++) {
                 if (boneList.get(i).getX() == player.getX() && boneList.get(i).getY() == player.getY()) {
                    boneList.get(i).setX(undo.undoX);
                 }
              }
              undo.nUndo = 41;
              isMovable = true;
           } else {
              player.moveLeft();

              isMovable = false;
              undo.nUndo = 0;
           }

        } else if (map.mapArray[player.getY()][player.getX()] == 1) {
        	GameManager.getInstance().getModel().moveDown();
           player.moveLeft();
           isMovable = false;
           undo.nUndo = 0;
        }
        BarkSound.getInstance().startMusic();
     }
     
	void undo(Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList) {

		switch (undo.nUndo) { // undo.nUndo값에 따라 직전 상태로 바뀜
		case 1:
			GameManager.getInstance().getGame().getController().moveDown(player, undo, map, boneList, riceBowlList); // 캐릭터만 아래로 움직여줌
			break;
		case 11: // 캐릭터와 뼈다귀 모두 아래로 움직여줌
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY + 1][undo.undoX] = 2; // 뼈다귀 먼저 맵에서 움직여주고
			for (int i = 0; i < riceBowlList.size(); i++) { // 뼈다귀 좌표 바꿔주고
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setY(undo.undoY + 1);
				}
			}
			GameManager.getInstance().getGame().getController().moveDown(player, undo, map, boneList, riceBowlList); // 캐릭터 움직이기
			break;
		case 2:
			GameManager.getInstance().getGame().getController().moveUp(player, undo, map, boneList, riceBowlList);  // 캐릭터만 위로 움직이기
			break;
		case 21: // 캐릭터와 뼈다귀 모두 위로
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY - 1][undo.undoX] = 2; // 뼈다귀 먼저 움직이고
			for (int i = 0; i < riceBowlList.size(); i++) { // 뼈다귀 좌표 바꿔주고
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setY(undo.undoY - 1);
				}
			}
			GameManager.getInstance().getGame().getController().moveUp(player, undo, map, boneList, riceBowlList);
 // 캐릭터 움직이기
			break;
		case 3: // 캐릭터만 오른쪽으로 움직이기
			GameManager.getInstance().getGame().getController().moveRight(player, undo, map, boneList, riceBowlList);
			break;
		case 31: // 캐릭터와 뼈다귀 모두 오른쪽으로
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY][undo.undoX + 1] = 2; // 뼈다귀 먼저 움직여주고
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setX(undo.undoX + 1);
				}
			}
			GameManager.getInstance().getGame().getController().moveRight(player, undo, map, boneList, riceBowlList); // 캐릭터 움직이기
			break;
		case 4:
			GameManager.getInstance().getGame().getController().moveLeft(player, undo, map, boneList, riceBowlList); // 캐릭터만 왼쪽으로 움직이기
			break;
		case 41: // 캐릭터와 뼈다귀 움직이기
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY][undo.undoX - 1] = 2; // 뼈다귀 먼저 움직여주고
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setX(undo.undoX - 1);
				}
			}
			GameManager.getInstance().getGame().getController().moveLeft(player, undo, map, boneList, riceBowlList); // 캐릭터 움직이기
			break;
		}
		undo.nUndo = 0; // 다시 못 바꾸게 하기
	}
	public boolean isGameClear(Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList)  { // 라운드 클리어 했는지 반환		int Goal_Count = 0; // 꽉찬 밥그릇 갯수
		int Goal_Count = 0; // 꽉찬 밥그릇 갯수

	      for (int i = 0; i < riceBowlList.size(); i++) {
	         if (map.mapArray[riceBowlList.get(i).getY()][riceBowlList.get(i).getX()] == 2) {
	            Goal_Count++; // 목표지점에 상자가 들어가면 들어감표시.
	         }
	      }

	      if (Goal_Count == riceBowlList.size()) { // 밥그릇이 모두 꽉차있는지 여부 반환
	         return true;
	      } else
	         return false;
	}

	public boolean isGameOver(Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList)  { // 움직일 수 없는 상황에 도달했는지(박스가 ㄱ자 벽에 붙으면 게임 오버)

		boolean OverFlag = false;

	      for (int i = 0; i < riceBowlList.size(); i++) { // 각 단계의 상자의 개수만큼 확인!!
	         if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == 1) { // 상자 위가 벽
	            if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == 1) { // 게임오버 조건에 충족되면.
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            } else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == 1) { // 왼쪽도 확인
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!

	               }
	            }
	         } // if(상자 위쪽 확인)

	         else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == 1) { // 오른쪽 확인
	            if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == 1) { // 위쪽도 확인
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            }

	            else if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == 1) { // 아랫쪽도 확인
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            }
	         } // if( 상자 오른쪽 확인 )

	         else if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == 1) { // 아랫쪽 확인
	            if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() + 1] == 1) { // 오른쪽 확인
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            } else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == 1) { // 왼쪽도 확인
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            }
	         } // if( 상자 아랫쪽! )

	         else if (map.mapArray[boneList.get(i).getY()][boneList.get(i).getX() - 1] == 1) { // 왼쪽 확인
	            if (map.mapArray[boneList.get(i).getY() + 1][boneList.get(i).getX()] == 1) { // 아랫쪽 확인
	               OverFlag = true; // 오버 미수입니다...
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            } else if (map.mapArray[boneList.get(i).getY() - 1][boneList.get(i).getX()] == 1) { // 위쪽도 확인
	               OverFlag = true; // 오버 미수입니다…
	               for (int j = 0; j < riceBowlList.size(); j++) { // 그게 골인지점에 들어와있는지 확인합니다!
	                  if (boneList.get(i).getX() == riceBowlList.get(j).getX()
	                        && boneList.get(i).getY() == riceBowlList.get(j).getY()) // 만약 들어와있으면
	                     OverFlag = false; // 용의선상에서 제외!!
	               }

	               if (OverFlag) { // 게임오버가 확정되면!
	                  this.isGameOver = true; // 맞으면 트루!
	                  break; // 인연을 끊어버리기~!
	               }
	            }
	         } // if( 상자 왼쪽! )

	      } // for(i)

	      return this.isGameOver;
	}
	
}