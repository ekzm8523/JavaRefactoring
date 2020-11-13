
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PlayPanel extends JPanel {
	private int mapArray[][]; // 화면에 보여질 맵 정보
	private Map map;
//	private JLabel Map[][] = new JLabel[12][12]; // 화면에
//	private Icon icon;
	private Undo undo;
	private boolean isMovable = true, isGameOver; // 움직였는지, 게임 오버됐는지 반환
//	private ImageIcon dogFrontImage, dogBackImage, dogRightImage, dogLeftImage, wallImage, groundImage, boneImage,
//			treeImage, bowlImage, fullBowlImage; // 아이콘 이름들
	private ImageIcon stageIcon,scoreIcon,moveIcon;
	private TimeThread lblTime;
	
// *******
	Player player;
	ArrayList<Bone> boneList = new ArrayList<>();
	ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

// *******
	public PlayPanel(int mapArray[][]) {
		
		Game game = GameManager.getInstance().getGame();
		Model model = GameManager.getInstance().getModel();
		
		//mapArray = a; // 맵 배열 전달 받기
		map = new Map(mapArray);
		undo = new Undo();
		
		// 패널 기본 설정
		setBounds(0, 100, 600, 600);
		setBackground(Color.red);
		setLayout(null);
		addKeyListener(new KeyListener() {

	         @Override
	         public void keyTyped(KeyEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void keyPressed(KeyEvent e) {
	            // TODO Auto-generated method stub

	         }

	         @Override
	         public void keyReleased(KeyEvent e) {
	            // TODO Auto-generated method stub
	            int keyEvent = e.getKeyCode();

	            System.out.println(keyEvent);
	            System.out.println("Good");
	            move(keyEvent);
	            PlayMusic.getInstance().moveMusic();
	            view(keyEvent);
	            if(isGameClear())
	               game.nextStage();

	         }
	         
	      });


		JLabel lblStage, lblScore, lblMove;
	       stageIcon= new Icon("stage" + model.getLevel() + ".png").getIcon(100,100);
	       lblStage = new Label(stageIcon).getPlayLabel(0, 0, 100, 100);
	         
	       scoreIcon= new Icon("ScoreBoard.png").getIcon(200,100);
	       lblScore = new Label(scoreIcon, SwingConstants.CENTER).getPlayLabel(Color.blue,Color.black,100, 0, 200, 100);
	         
	       moveIcon= new Icon("MoveBoard.png").getIcon(150,100);
	       lblMove = new Label(moveIcon, SwingConstants.CENTER).getPlayLabel(Color.red,Color.black,300, 0, 150, 100);

		lblTime = new TimeThread();
		

		add(lblStage);
		add(lblScore);
		add(lblMove);
		add(lblTime);

		// -----------맵배열의 정보에 따라 캐릭터, 뼈다귀, 밥그릇을 먼저 라벨로 그려서 화면의 맨 위로 보이게 하기
		// ----------한 칸당 50x50의 크기

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (map.mapArray[i][j] == GameObject.PLAYER) { // 캐릭터 좌표 저장
					map.mapArray[i][j] = 0;
					player = new Player(j, i, MyImage.dogFrontImage);
					player.addImageIcon();
					add(player.label);

				} else if (map.mapArray[i][j] == GameObject.BONE) { // 뼈다귀의 갯수만큼 좌표 저장, 그리기
					Bone tmpBone = new Bone(j, i, MyImage.boneImage);
					tmpBone.addImageIcon();
					add(tmpBone.label);
					boneList.add(tmpBone);

				} else if (map.mapArray[i][j] == GameObject.RICEBOWL) { // 밥그릇의 갯수만큼 좌표저장, 그리기
					RiceBowl riceBowl = new RiceBowl(j, i, MyImage.bowlImage);
					riceBowl.addImageIcon();
					add(riceBowl.label);
					riceBowlList.add(riceBowl);

				}
			}
		}

		map.DrawMap(this);

		// ---------------------------------맵 그리기------------------------------------
		
	}



	public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드

		switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
		case 38: // UP-------------------------------------------------------------------------------------------
			player.moveUp();
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
					// player.getY()++; // 원위치
					player.moveDown();
					isMovable = false; // 못움직임
					undo.nUndo = 0; // 언두도 못함
				}

			} else if (map.mapArray[player.getY()][player.getX()] == GameObject.BRICK) {
				player.moveDown();
				isMovable = false; // 못움직임
				undo.nUndo = 0; // 언두도 못함
			}

			break; // 아래, 왼쪽, 오른쪽도 같은 방법으로 바꿔준다.
		case 40: // DOWN-------------------------------------------------------------------------------
			player.moveDown();
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
				// player.getY()--;
				player.moveUp();
				isMovable = false;
				undo.nUndo = 0;
			}
			break;
		case 37: // LEFT----------------------------------------------------------------------------------
			// player.moveLeft();
			player.moveLeft();
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
				// player.getX()++;
				player.moveRight();
				isMovable = false;
				undo.nUndo = 0;
			}
			break;
		case 39: // RIGHT--------------------------------------------------------------------------------------
			// player.getX()++; // 캐릭터 오른쪽으로 이동(x좌표 + 1)
			player.moveRight();
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
				player.moveLeft();
				isMovable = false;
				undo.nUndo = 0;
			}
			break;
		}
	} // move

	public void undo() {

		switch (undo.nUndo) { // undo.nUndo값에 따라 직전 상태로 바뀜
		case 1:
			move(40); // 캐릭터만 아래로 움직여줌
			break;
		case 11: // 캐릭터와 뼈다귀 모두 아래로 움직여줌
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY + 1][undo.undoX] = 2; // 뼈다귀 먼저 맵에서 움직여주고
			for (int i = 0; i < riceBowlList.size(); i++) { // 뼈다귀 좌표 바꿔주고
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setY(undo.undoY + 1);
				}
			}
			move(40); // 캐릭터 움직이기
			break;
		case 2:
			move(38); // 캐릭터만 위로 움직이기
			break;
		case 21: // 캐릭터와 뼈다귀 모두 위로
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY - 1][undo.undoX] = 2; // 뼈다귀 먼저 움직이고
			for (int i = 0; i < riceBowlList.size(); i++) { // 뼈다귀 좌표 바꿔주고
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setY(undo.undoY - 1);
				}
			}
			move(38); // 캐릭터 움직이기
			break;
		case 3: // 캐릭터만 오른쪽으로 움직이기
			move(39);
			break;
		case 31: // 캐릭터와 뼈다귀 모두 오른쪽으로
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY][undo.undoX + 1] = 2; // 뼈다귀 먼저 움직여주고
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setX(undo.undoX + 1);
				}
			}
			move(39); // 캐릭터 움직이기
			break;
		case 4:
			move(37); // 캐릭터만 왼쪽으로 움직이기
			break;
		case 41: // 캐릭터와 뼈다귀 움직이기
			map.mapArray[undo.undoY][undo.undoX] = 0;
			map.mapArray[undo.undoY][undo.undoX - 1] = 2; // 뼈다귀 먼저 움직여주고
			for (int i = 0; i < riceBowlList.size(); i++) {
				if (boneList.get(i).getX() == undo.undoX && boneList.get(i).getY() == undo.undoY) {
					boneList.get(i).setX(undo.undoX - 1);
				}
			}
			move(37); // 캐릭터 움직이기
			break;
		}
		undo.nUndo = 0; // 다시 못 바꾸게 하기
	} // undo

	public void view(int key) { // 화면에 보이게 하기
		int flag = 0;
		switch (key) { // 누른 방향키에 따라 캐릭터 방향바꾸기
		case 38: // 위
			player.label.setIcon(MyImage.dogBackImage);
			break;
		case 40: // 아래
			player.label.setIcon(MyImage.dogFrontImage);
			break;
		case 37: // 오른쪽
			player.label.setIcon(MyImage.dogLeftImage);
			break;
		case 39: // 왼쪽
			player.label.setIcon(MyImage.dogRightImage);
			break;
		}

		player.label.setBounds(player.getX() * 50, player.getY() * 50 + 100, 50, 50); // 옮겨진 캐릭터 그리기

		for (int i = 0; i < riceBowlList.size(); i++) { // 밥그릇 그리기
			if (map.mapArray[riceBowlList.get(i).getY()][riceBowlList.get(i).getX()] == 2) { // 뼈다귀가 있다면(좌표가 겹친다면) 꽉찬 밥그릇
				riceBowlList.get(i).label.setIcon(MyImage.fullBowlImage);
			} else {
				riceBowlList.get(i).label.setIcon(MyImage.bowlImage); // 뼈다귀가 있다면 빈 밥그릇
			}
		}

		for (int i = 0; i < riceBowlList.size(); i++) { // 뼈다귀 그리기
			flag = 0;
			for (int j = 0; j < riceBowlList.size(); j++) {
				if (boneList.get(i).getX() == riceBowlList.get(j).getX()
						&& boneList.get(i).getY() == riceBowlList.get(j).getY()) { // 밥그릇에 있다면 뼈다귀 없애기
					flag = 1;
					boneList.get(i).label.setVisible(false);
				}
			}
			boneList.get(i).label.setBounds(boneList.get(i).getX() * 50, boneList.get(i).getY() * 50 + 100, 50, 50); // 움직인
																														// 뼈다귀
																														// 그리기
			if (flag == 0)
				boneList.get(i).label.setVisible(true); // 아니면 그냥 보이게 하기
		}

	} // view()

	public boolean isGameClear() { // 라운드 클리어 했는지 반환
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

	public boolean isGameOver() { // 움직일 수 없는 상황에 도달했는지(박스가 ㄱ자 벽에 붙으면 게임 오버)

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
			} // if( 상자 왼쪽! )

		} // for(i)

		return this.isGameOver;
	}

	public boolean getIsMovable() {
		return isMovable;
	} // 움직였는지 반환

	public void setIsMovable(boolean init) {
		isMovable = init;
	} // 움직임 여부 초기화
}