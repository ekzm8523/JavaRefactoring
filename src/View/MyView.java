package View;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Bone;
import Model.GameObject;
import Model.GameImage;
import Model.Player;
import Model.RiceBowl;
import Model.Undo;

public class MyView {
	
	private static MyView s_Instance;
	public static MyView getInstance() {
		if(s_Instance == null)
			s_Instance = new MyView();
		return s_Instance;
	}
	public void inputKeyValueView(int key, Player player, Undo undo, ArrayList<Bone> boneList,
			ArrayList<RiceBowl> riceBowlList) {
		boolean flag = false;
		switch (key) { // 누른 방향키에 따라 캐릭터 방향바꾸기
		case 38: // 위
			player.label.setIcon(GameImage.dogBackImage);
			break;
		case 40: // 아래
			player.label.setIcon(GameImage.dogFrontImage);
			break;
		case 37: // 오른쪽
			player.label.setIcon(GameImage.dogLeftImage);
			break;
		case 39: // 왼쪽
			player.label.setIcon(GameImage.dogRightImage);
			break;
		}

		// 옮겨진 캐릭터 그리기
		player.label.setBounds(player.getX() * 50, player.getY() * 50 + 100, 50, 50);

		// 밥그릇 그리기
		for (int i = 0; i < riceBowlList.size(); i++) {
			// 뼈다귀가 있다면(좌표가 겹친다면) 꽉찬 밥그릇
			if (mapArray[riceBowlList.get(i).getY()][riceBowlList.get(i).getX()] == 2) {
				riceBowlList.get(i).label.setIcon(GameImage.fullBowlImage);
			} else {
				// 뼈다귀가 있다면 빈 밥그릇
				riceBowlList.get(i).label.setIcon(GameImage.bowlImage);
			}
		}

		// 뼈다귀 그리기
		for (int i = 0; i < riceBowlList.size(); i++) {
			flag = false;
			for (int j = 0; j < riceBowlList.size(); j++) {
				// 밥그릇에 있다면 뼈다귀 없애기
				if (boneList.get(i).getX() == riceBowlList.get(j).getX()
						&& boneList.get(i).getY() == riceBowlList.get(j).getY()) {
					flag = true;
					boneList.get(i).label.setVisible(false);
				}
			}
			// 움직인 뼈다귀 그리기
			boneList.get(i).label.setBounds(boneList.get(i).getX() * 50, boneList.get(i).getY() * 50 + 100, 50, 50);

			if (flag == false)
				boneList.get(i).label.setVisible(true);
		}
	}

	public int mapArray[][];
	private JLabel mapLabel[][];
	
	
	public void SettingMap(int mapArray[][]) {
		this.mapArray = mapArray;
		mapLabel = new JLabel[12][12];
	}
	
	public void DrawMap(JPanel panel) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) { // 땅 그리기 - 캐릭터나 뼈다귀, 밥그릇은 땅 위에 올려지기 때문에 이 좌표들도 땅으로 그려준다.
				if (mapArray[i][j] == GameObject.PLAYER || mapArray[i][j] == GameObject.BONE
						|| mapArray[i][j] == GameObject.GRASS || mapArray[i][j] == GameObject.RICEBOWL) {

					mapLabel[i][j] = new JLabel(GameImage.groundImage);
					mapLabel[i][j].setOpaque(true);
					panel.add(mapLabel[i][j]);
				} else if (mapArray[i][j] == 1) { // 벽 그리기
					mapLabel[i][j] = new JLabel(GameImage.wallImage);
					mapLabel[i][j].setOpaque(true);
					panel.add(mapLabel[i][j]);
				} else if (mapArray[i][j] == 5) { // 밖 그리기
					mapLabel[i][j] = new JLabel(GameImage.treeImage);
					mapLabel[i][j].setOpaque(true);
					panel.add(mapLabel[i][j]);
				}
				mapLabel[i][j].setBounds(j * 50, i * 50 + 100, 50, 50);
				mapLabel[i][j].setVisible(true);

			}
		} // 캐릭터 좌표, 박스 좌표 저장
	}
	public void DrawObject(JPanel panel,Player player,
						   ArrayList<Bone> boneList ,
						   ArrayList<RiceBowl> riceBowlList) {

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				if (mapArray[i][j] == GameObject.PLAYER) { // 캐릭터 좌표 저장
					mapArray[i][j] = 0;
					player.setPoint(j,i);
					player.setImage(GameImage.dogFrontImage);
					player.addImageIcon();
					panel.add(player.label);

				} else if (mapArray[i][j] == GameObject.BONE) { // 뼈다귀의 갯수만큼 좌표 저장, 그리기
					Bone tmpBone = new Bone(j, i, GameImage.boneImage);
					tmpBone.addImageIcon();
					panel.add(tmpBone.label);
					boneList.add(tmpBone);

				} else if (mapArray[i][j] == GameObject.RICEBOWL) { // 밥그릇의 갯수만큼 좌표저장, 그리기
					RiceBowl riceBowl = new RiceBowl(j, i, GameImage.bowlImage);
					riceBowl.addImageIcon();
					panel.add(riceBowl.label);
					riceBowlList.add(riceBowl);

				}
			}
		}
	}
}