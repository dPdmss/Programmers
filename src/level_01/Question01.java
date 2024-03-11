package level_01;

import com.sun.tools.jconsole.JConsoleContext;

public class Question01 {

    /*
    붕대 감기는 t초 동안 붕대를 감으면서 1초마다 x만큼의 체력을 회복합니다.
    t초 연속으로 붕대를 감는 데 성공한다면 y만큼의 체력을 추가로 회복합니다.
    기술을 쓰는 도중 몬스터에게 공격을 당하면 기술이 취소되고
    공격을 당하는 순간에는 체력을 회복할 수 없습니다
    몬스터에게 공격당해 기술이 취소당하거나 기술이 끝나면 그 즉시 붕대 감기를 다시 사용하며
    연속 성공 시간이 0으로 초기화됩니다.
    몬스터의 공격을 받으면 정해진 피해량만큼 현재 체력이 줄어듭니다.
    이때, 현재 체력이 0 이하가 되면 캐릭터가 죽으며 더 이상 체력을 회복할 수 없습니다.

    붕대감기 기술의 정보, 캐릭터가 가진 최대 체력과 몬스터의 공격 패턴이 주어질 때 캐릭터가 끝까지 생존할 수 있는지 궁금합니다.

    매개변수
    붕대 감기 기술의 시전 시간, 1초당 회복량, 추가 회복량을 담은 1차원 정수 배열 bandage
    최대 체력을 의미하는 정수 health
    몬스터의 공격 시간과 피해량을 담은 2차원 정수 배열 attacks

    모든 공격이 끝난 직후 남은 체력을 return 하도록 solution 함수를 완성해 주세요.
    만약 몬스터의 공격을 받고 캐릭터의 체력이 0 이하가 되어 죽는다면 -1을 return 해주세요.
    */

    /*

    bandage	    health	attacks	                                result
    [5, 1, 5]	30	    [[2, 10], [9, 15], [10, 5], [11, 5]]	5
    [3, 2, 7]	20  	[[1, 15], [5, 16], [8, 6]]	            -1
    [4, 2, 7]	20	    [[1, 15], [5, 16], [8, 6]]	            -1
    [1, 1, 1]	5	    [[1, 2], [3, 2]]	                    3
     */
    public static void main(String[] args) {
        int answer = 0;
        int[] bandage = {5, 1, 5}; // 5초동안 1씩 회복 5초 연속 회복시 +5 추가회복
        int health = 30; // 최대 HP
        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};

        int lastTime = attacks[attacks.length-1][0]; // 마지막 공격시간
        int successive = 0; // 붕대 감기 연속 성공
        int realHp = health; // 현재체력
        boolean monsterHit;
        int attackIdx = 0;

        for(int i=1; i<=lastTime; i++) {

            if(attacks[attackIdx][0] == i) {
                monsterHit = true;
            }else{
                monsterHit = false;
            }


            if(monsterHit) {

                successive = 0; // 붕대 감기 연속 성공 시간 초기화
                realHp = realHp - attacks[attackIdx][1];

                if(realHp <= 0) {
                    answer = -1;
                    return;
                }
                attackIdx++;

            }else {

                if((realHp + bandage[1]) > health) {
                    realHp = health;
                }else {
                    realHp = realHp + bandage[1];
                }

                successive++;

                if(successive == bandage[0]) {
                    successive = 0;
                    if((realHp + bandage[2]) > health) {
                        realHp = health;
                    }else {
                        realHp = realHp + bandage[2];
                    }
                }
            }

            System.out.println("시간=" + i + ", 현재 체력=" + realHp + ", 연속 공격 성공=" + successive + ", 몬스터 공격 여부=" + monsterHit);
        }

        answer = realHp;
        System.out.println("남은 hp >> " + answer);
    }


}
