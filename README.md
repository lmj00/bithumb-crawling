주제
- 암호화폐 변동률


개발 동기
- 실제로는 변동률이 플러스여도 하락 추세를, 
마이너스이지만 상승 추세를 타는 경우도 있다. <br>
이처럼 수백 개의 코인 추세를 사용자가 한눈에 파악하기
어려운 점을 해결하고자 만들었다.


보완할 점
- Ajax로 구현한 게 아닌, html의 meta refresh를 사용하여 구현함.  추후에는 비동기 방식으로 코인 목록을 가져오기
- 급등, 급락한 코인들을 매수, 매도하기
- 입출금 내역을 통해 현재까지의 수익 금액, 수익률 표시
- 5분, 10분, 1시간 등 여러 기준을 추가하기


기간
- 2022.05 ~ 2022.06


인원
- 1명


사용 기술
- Spring boot, Jsoup


기능
- 상승률 - 1분 전 기준으로 1% 급상승한 코인들의 목록을 가져온다.<br>
5.1원에서 5.38원으로 오른 것을 확인할 수 있다.
![image](https://user-images.githubusercontent.com/54443194/221151625-27dfd3d9-1d02-41c3-8b47-e7ad67397053.png)
![image](https://user-images.githubusercontent.com/54443194/221157493-fb67e9fd-00f8-490c-8dc3-8a8a287e13bc.png)

<br>

- 하락률 - 1분 전 기준으로 1% 급하락한 코인들의 목록을 가져온다.
![image](https://user-images.githubusercontent.com/54443194/221151763-eebbfa6c-ec07-4af1-9875-f32e9dcf9e97.png)
![image](https://user-images.githubusercontent.com/54443194/221157631-a14f6444-d4d5-437e-a41c-f1c9316c54d4.png)

<br>

- 상위 변동률 - 당일 기준으로 변동률이 가장 높은 목록을 가져온다
 ![image](https://user-images.githubusercontent.com/54443194/221157785-63efa5a4-8383-4373-93cc-03ec59ae52fb.png)

<br>

활용
- 다량의 코인이 같이 상승하기 때문에 순간적으로 상승 추세를 탄 것으로 예측할 수 있다. <br>
하지만, 상승 추세 이후 하락 추세로 전환될 수도 있기 때문에 참고 지표로 사용해야 한다.
![image](https://user-images.githubusercontent.com/54443194/221151896-21efb79c-d1bc-4985-9888-fefc572a914c.png)