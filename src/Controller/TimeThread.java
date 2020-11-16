package Controller;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import Model.Icon;
import Model.MyFont;

public class TimeThread extends JLabel implements Runnable {

   private Thread myThread;
   private int nSleep, m, s;            // 기본 정보(텀 시간, 분, 초)
   private ImageIcon lblTimeIcon, lblIcon;
   private Image originImg, changedImage;
   private static TimeThread lblTime;
   private boolean stop = false;
   
   public static TimeThread getInstance() {
      if (lblTime == null) 
         lblTime = new TimeThread();
      
      return lblTime;
   }

   public TimeThread() {
      nSleep = 1000;   // 시간이 바뀌는 텀 1초
      // 남은 시간 초기화
      m = 10;   
      s = 0;
      
      // 배경 이미지 아이콘 부착
      lblTimeIcon = new Icon("TimeBoard.png").getIcon(150, 100);
      setIcon(lblTimeIcon);
      
      setText("" + m + " : 0" + s);   // 남은 시간 10 : 00 보이게 하기
      setFont(MyFont.getPlayFont());
      setForeground(Color.black);
      // label과 글씨 겹치게 하기
      setHorizontalAlignment(SwingConstants.CENTER);
      setHorizontalTextPosition(SwingConstants.CENTER);
      
      myThread = new Thread(this); // 쓰레드 생성
      
      setBounds(450, 0, 150, 100);
      setOpaque(true);
      setBackground(Color.white);
      
      myThread.start();
   }

   public void start() {
      myThread.start();
   }
   
   public void stop(boolean stop) {
      this.stop = stop;
      System.out.println(String.valueOf(this.stop));
   }

   public void run() {
      
      while(!stop) {
         
         try {
            myThread.sleep(nSleep);
         } catch (Exception e) {
         } // 1초 나게 하기
         
         for (m = 9; m >= 0; m--) {
            for (s = 59; s >= 0; s--) {
               if (s < 10) {
                  setText("0" + m + " : 0" + s);
               } else {
                  setText("0" + m + " : " + s);
               } // 초가 1자리 수면 0s로 보이게 함.
               
               try { // 텀 1초
                  myThread.sleep(nSleep);
               } catch (Exception e) {
               }
            }

         } // 남은 시간 경과 표시
         
      }

      // 남은 시간을 빨간색으로 강조
      setFont(MyFont.getPlayFont());
      setText("00 : 00");
      setForeground(Color.red);
   }

   public void setNull() {
      this.lblTime = null;
   }
   public int getMinute() {
      return m;
   } // 분 정보

   public int getSecond() {
      return s;
   } // 초 정보 

   public void initTime() {
      m = 10;
      s = 0;
   } // 시간 초기화

} // 디지털 시계 끝