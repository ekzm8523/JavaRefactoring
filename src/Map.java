import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map {

	public int mapArray[][];
	private JLabel mapLabel[][];
	
	
	
	public Map(int mapArray[][]) {
		this.mapArray = mapArray;
		mapLabel = new JLabel[12][12];
	}
	
	public void DrawMap(JPanel panel) {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) { // 땅 그리기 - 캐릭터나 뼈다귀, 밥그릇은 땅 위에 올려지기 때문에 이 좌표들도 땅으로 그려준다.
				if (mapArray[i][j] == GameObject.PLAYER || mapArray[i][j] == GameObject.BONE
						|| mapArray[i][j] == GameObject.GRASS || mapArray[i][j] == GameObject.RICEBOWL) {

					mapLabel[i][j] = new JLabel(MyImage.groundImage);
					mapLabel[i][j].setOpaque(true);
					panel.add(mapLabel[i][j]);
				} else if (mapArray[i][j] == 1) { // 벽 그리기
					mapLabel[i][j] = new JLabel(MyImage.wallImage);
					mapLabel[i][j].setOpaque(true);
					panel.add(mapLabel[i][j]);
				} else if (mapArray[i][j] == 5) { // 밖 그리기
					mapLabel[i][j] = new JLabel(MyImage.treeImage);
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
//					player = new Player(j, i, myImage.dogFrontImage);
					player.setPoint(j,i);
					player.setImage(MyImage.dogFrontImage);
					player.addImageIcon();
					panel.add(player.label);

				} else if (mapArray[i][j] == GameObject.BONE) { // 뼈다귀의 갯수만큼 좌표 저장, 그리기
					Bone tmpBone = new Bone(j, i, MyImage.boneImage);
					tmpBone.addImageIcon();
					panel.add(tmpBone.label);
					boneList.add(tmpBone);

				} else if (mapArray[i][j] == GameObject.RICEBOWL) { // 밥그릇의 갯수만큼 좌표저장, 그리기
					RiceBowl riceBowl = new RiceBowl(j, i, MyImage.bowlImage);
					riceBowl.addImageIcon();
					panel.add(riceBowl.label);
					riceBowlList.add(riceBowl);

				}
			}
		}
	}
	
	
}