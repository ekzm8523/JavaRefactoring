import java.util.ArrayList;

public class View {
	
	public View() {
		
	}
	public void inputKeyValueView(int key, Player player,Undo undo,Map map,
			ArrayList<Bone> boneList, ArrayList<RiceBowl> riceBowlList) {
		boolean flag = false;
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
			flag = false;
			for (int j = 0; j < riceBowlList.size(); j++) {
				if (boneList.get(i).getX() == riceBowlList.get(j).getX()
						&& boneList.get(i).getY() == riceBowlList.get(j).getY()) { // 밥그릇에 있다면 뼈다귀 없애기
					flag = true;
					boneList.get(i).label.setVisible(false);
				}
			}
			boneList.get(i).label.setBounds(boneList.get(i).getX() * 50, boneList.get(i).getY() * 50 + 100, 50, 50); // 움직인
																														// 뼈다귀
																														// 그리기
			if (flag == false)
				boneList.get(i).label.setVisible(true); // 아니면 그냥 보이게 하기
		}

	}
	
}