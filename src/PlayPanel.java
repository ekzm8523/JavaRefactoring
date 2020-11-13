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
	Player player = new Player();
	ArrayList<Bone> boneList = new ArrayList<>();
	ArrayList<RiceBowl> riceBowlList = new ArrayList<>();

// *******
	public PlayPanel(int mapArray[][]) {

		//mapArray = a; // 맵 배열 전달 받기
		map = new Map(mapArray);
		undo = new Undo();
		
		// 패널 기본 설정
		setBounds(0, 100, 600, 600);
		setBackground(Color.red);
		setLayout(null);
		addKeyListener(new KeyboardListener());

		Font font = new Font("Verdana", Font.BOLD, 20);

		JLabel lblStage, lblScore, lblMove;
		String strLevel = String.valueOf(1);
	    stageIcon= new Icon("stage" + strLevel + "png").getIcon(100,100);
	    lblStage = new JLabel(stageIcon);
	      
	    scoreIcon= new Icon("ScoreBoard.png").getIcon(200,100);
	    lblScore = new JLabel(scoreIcon, SwingConstants.CENTER);
	      
	    moveIcon= new Icon("MoveBoard.png").getIcon(150,100);
	    lblMove = new JLabel(moveIcon, SwingConstants.CENTER);

		lblStage.setBounds(0, 0, 100, 100); // lblStage 초기화
		lblStage.setOpaque(true);
		lblStage.setBackground(Color.white);

		lblScore.setBounds(100, 0, 200, 100); // lblScore 초기화
		lblScore.setFont(font);
		lblScore.setForeground(Color.black);
		lblScore.setOpaque(true);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setBackground(Color.blue);

		lblMove.setBounds(300, 0, 150, 100); // lblMove 초기화
		lblMove.setFont(font);
		lblMove.setForeground(Color.black);
		lblMove.setOpaque(true);
		lblMove.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMove.setBackground(Color.red);

		lblTime = new TimeThread();
		lblTime.setBounds(450, 0, 150, 100);
		lblTime.setOpaque(true);
		lblTime.setBackground(Color.white);
		lblTime.start(); // 시간 재기

		add(lblStage);
		add(lblScore);
		add(lblMove);
		add(lblTime);

		map.DrawObject(this,player,boneList,riceBowlList);
		map.DrawMap(this);

		
	}
	

	public class KeyboardListener implements KeyListener {

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
			view(keyEvent);

		}

	}

	public void move(int key) { // 캐릭터와 뼈다귀, 밥그릇 좌표 옮기는 메소드

		switch (key) { // 방향키 값을 받아와서 그 값에 따라 움직임
		case 38: // UP-------------------------------------------------------------------------------------------
			GameManager.getInstance().getGame().getController().moveUp(player, undo, map, boneList, riceBowlList);


			break; // 아래, 왼쪽, 오른쪽도 같은 방법으로 바꿔준다.
		case 40: // DOWN-------------------------------------------------------------------------------
			GameManager.getInstance().getGame().getController().moveDown(player, undo, map, boneList, riceBowlList);
			break;
		case 37: // LEFT----------------------------------------------------------------------------------
			// player.moveLeft();
			GameManager.getInstance().getGame().getController().moveLeft(player, undo, map, boneList, riceBowlList);

			break;
		case 39: // RIGHT--------------------------------------------------------------------------------------
			// player.getX()++; // 캐릭터 오른쪽으로 이동(x좌표 + 1)
			GameManager.getInstance().getGame().getController().moveRight(player, undo, map, boneList, riceBowlList);

			break;
		}
	} // move

	public void undo() {
		GameManager.getInstance().getGame().getController().undo(player, undo, map, boneList, riceBowlList);

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